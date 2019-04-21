package pub.functions;
 
public class HtmlFuncs {

	public static String textToHtml(Object oText) {
		if (oText == null) return "";
		String text = oText.toString();
		text = text.replace("\r", "").replace("\t", "    ").replace(" ", "&nbsp;");
		return "<p>" + text.replace("\n", "</p>\n<p>") + "</p>";
	}

	public static String textToHtml(String text) {
		if (text == null) return "";
		text = text.replace("\r\n", "<p />");
		return text;
	}

}
