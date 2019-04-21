package pub.spring.tags;

import java.io.Serializable;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.el.ELException;

import org.springframework.util.Assert;
import org.springframework.web.servlet.tags.form.TagWriter; 

import pub.functions.VarFuncs;

@SuppressWarnings({ "unchecked", "deprecation", "rawtypes" })
public class FormTag extends org.springframework.web.servlet.tags.form.FormTag {

	private static final long serialVersionUID = 1L;
	public static final String EXPRESSION_SUPPORT_CONTEXT_PARAM = "springJspExpressionSupport";
	public static final String EXPRESSION_PREFIX = "${";
	public static final String EXPRESSION_SUFFIX = "}";

	private Boolean outputXDatas = true;
	private Boolean showErrors = false;

	protected boolean isOutputXDatas() {
		if (this.outputXDatas != null) {
			return this.outputXDatas;
		} else {
			return true;
		}
	}

	public void setOutputXDatas(String outputXDatas) throws JspException {
		this.outputXDatas = evaluateBoolean("outputXDatas", outputXDatas, pageContext);
	}

	protected boolean isShowErrors() {
		return this.showErrors != null && this.showErrors == true;
	}

	public void setShowErrors(String showErrors) throws JspException {
		this.showErrors = evaluateBoolean("showErrors", showErrors, pageContext);
	}

	@Override
	protected int writeTagContent(TagWriter tagWriter) throws JspException {
		int result = super.writeTagContent(tagWriter);
		if (isOutputXDatas()) {
			_outputXDatas(tagWriter);
		}
		pageContext.setAttribute("_FORM_TAG_SHOW_ERRORS", isShowErrors());
		return result;
	}

	@Override
	protected String resolveAction() throws JspException {
		String action = super.resolveAction();
		ServletRequest request = this.pageContext.getRequest();
		Object pageKey = request.getAttribute("pageKey");
		if (pageKey == null || action.indexOf("pageKey=") != -1) {
			return action;
		}
		if (action.indexOf('?') == -1) {
			action += '?';
		} else {
			action += '&';
		}
		action += "pageKey=" + pageKey;
		return action;
	}

	private void _outputXDatas(TagWriter tw) throws JspException {
		Map<String, Object> xDataMap = (Map<String, Object>) this.pageContext.getRequest().getAttribute("xDataMap");
		if (xDataMap == null) {
			return;
		}
		for (Map.Entry<String, Object> entry : xDataMap.entrySet()) {
			tw.startTag("input");
			tw.writeAttribute("type", "hidden");
			tw.writeAttribute("name", entry.getKey());
			String xData = VarFuncs.serializeToXData((Serializable) entry.getValue());
			tw.writeAttribute("value", xData);
			tw.endTag();
		}
	}

	public static boolean isSpringJspExpressionSupportActive(PageContext pageContext) {
		ServletContext sc = pageContext.getServletContext();
		String springJspExpressionSupport = sc.getInitParameter(EXPRESSION_SUPPORT_CONTEXT_PARAM);
		if (springJspExpressionSupport != null) {
			return Boolean.valueOf(springJspExpressionSupport);
		}
		if (sc.getMajorVersion() >= 3) {
			if (sc.getMajorVersion() == 2 && sc.getMinorVersion() < 4) {
				return true;
			}
		}
		return false;
	}

	public static boolean isExpressionLanguage(String value) {
		return (value != null && value.contains(EXPRESSION_PREFIX));
	}
 
	private static Object doEvaluate(String attrName, String attrValue, Class resultClass, PageContext pageContext)
			throws JspException {
		Assert.notNull(attrValue, "Attribute value must not be null");
		Assert.notNull(resultClass, "Result class must not be null");
		Assert.notNull(pageContext, "PageContext must not be null");
		try {
			if (resultClass.isAssignableFrom(String.class)) {
				StringBuilder resultValue = null;
				int exprPrefixIndex;
				int exprSuffixIndex = 0;
				do {
					exprPrefixIndex = attrValue.indexOf(EXPRESSION_PREFIX, exprSuffixIndex);
					if (exprPrefixIndex != -1) {
						int prevExprSuffixIndex = exprSuffixIndex;
						exprSuffixIndex = attrValue.indexOf(EXPRESSION_SUFFIX,
								exprPrefixIndex + EXPRESSION_PREFIX.length());
						String expr;
						if (exprSuffixIndex != -1) {
							exprSuffixIndex += EXPRESSION_SUFFIX.length();
							expr = attrValue.substring(exprPrefixIndex, exprSuffixIndex);
						} else {
							expr = attrValue.substring(exprPrefixIndex);
						}
						if (expr.length() == attrValue.length()) {
							return evaluateExpression(attrValue, resultClass, pageContext);
						} else {
							if (resultValue == null) {
								resultValue = new StringBuilder();
							}
							resultValue.append(attrValue.substring(prevExprSuffixIndex, exprPrefixIndex));
							resultValue.append(evaluateExpression(expr, String.class, pageContext));
						}
					} else {
						if (resultValue == null) {
							resultValue = new StringBuilder();
						}
						resultValue.append(attrValue.substring(exprSuffixIndex));
					}
				} while (exprPrefixIndex != -1 && exprSuffixIndex != -1);
				return resultValue.toString();
			} else {
				return evaluateExpression(attrValue, resultClass, pageContext);
			}
		} catch (ELException ex) {
			throw new JspException("Parsing of JSP EL expression failed for attribute '" + attrName + "'", ex);
		}
	}

	private static Object evaluateExpression(String exprValue, Class resultClass, PageContext pageContext)
			throws ELException {
		return pageContext.getExpressionEvaluator().evaluate(exprValue, resultClass, pageContext.getVariableResolver(),
				null);
	}

	public static boolean evaluateBoolean(String attrName, String attrValue, PageContext pageContext)
			throws JspException {

		if (isSpringJspExpressionSupportActive(pageContext) && isExpressionLanguage(attrValue)) {
			return (Boolean) doEvaluate(attrName, attrValue, Boolean.class, pageContext);
		} else {
			return Boolean.valueOf(attrValue);
		}
	}
}
