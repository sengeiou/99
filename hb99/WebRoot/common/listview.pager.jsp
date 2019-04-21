<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ page import="pub.web.ServletUtils" %> 
<script type="text/javascript">
<%
  String listUrl = (String) request.getAttribute("LIST_URL");
  if(listUrl == null) {
	 listUrl = request.getParameter("LIST_URL");
  }
  if(listUrl == null) {
	listUrl = ServletUtils.getShortRequestUrl();
  }
%>
var listUrl = '<%=listUrl%>';
function page(pageNo) {
  pageNo = parseInt(pageNo);
  if (isNaN(pageNo)) {
	 alert('请输入有效的页码');
	 return;
  }
  var re = new RegExp('([?&]page=)(-?\\d+)');
  var url = listUrl.replace(re, '$1' + pageNo);
  if (url.indexOf('page=' + pageNo) == -1) {
	 url += (url.indexOf('?') > 0 ? '&' : '?') + 'page=' + pageNo;
  }
  gotoUrl(url);
}
</script> 
<c:choose>
  <c:when test="${pagehelper.total==0}">
    <div class="layui-container">   
	   <div class="layui-row" style="text-align: center;color: red;"><img src="${home }/images/zanwushuju.png"/>&nbsp;&nbsp;暂无数据</div> 
    </div>
  </c:when>
  <c:otherwise>
	<div class="page_down mb30 " style="float: right;">&nbsp;  
	  <c:choose>
	    <c:when test="${!pagehelper.isFirstPage}">
	      <a href="javascript:page(1)" class="layui-btn layui-btn-mini layui-btn-primary">首页</a>
	      <a href="javascript:page(${pagehelper.pageNum-1})" class="prev layui-btn layui-btn-mini layui-btn-primary">上一页</a>
	    </c:when>
	    <c:otherwise>
	      <strong class="layui-btn layui-btn-mini layui-btn-disabled">首页</strong>
	      <strong class="layui-btn layui-btn-mini layui-btn-disabled">上一页</strong>
	    </c:otherwise>
	  </c:choose>
	  <c:choose>
	    <c:when test="${pagehelper.hasNextPage}">
	      <a href="javascript:page(${pagehelper.pageNum+1})"  class="next layui-btn layui-btn-mini layui-btn-primary">下一页</a>
	      <a href="javascript:page(${pagehelper.pages})" class="layui-btn layui-btn-mini layui-btn-primary">尾页</a>
	    </c:when>
	    <c:otherwise>
	        <strong class="layui-btn layui-btn-mini layui-btn-disabled">下一页</strong>
	        <strong class="layui-btn layui-btn-mini layui-btn-disabled">尾页</strong>
	    </c:otherwise>  
	  </c:choose>
	   <span class="page_skip">
	      <input name="" type="text" class="page_info layui-btn layui-btn-mini layui-btn-disabled" disabled value="共${pagehelper.total}条记录&nbsp;${pagehelper.pageNum}/${pagehelper.pages}页" />
	    </span>
	</div> 
  </c:otherwise>
</c:choose> 