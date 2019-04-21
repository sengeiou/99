<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ include file="/common/taglib.jsp"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title> 
<link type="text/css" rel="stylesheet" href="${home }/css/scan.css"> 
<script src="${home }/js/jquery-1.7.1.min.js" type="text/javascript"></script>
<script src="${home }/js/slides-1.1.1-min.js" type="text/javascript"></script>
<script type="text/javascript">
$(function() {
	$('.succesny').olvSlides({
		thumb: true,
		thumbPage: true,
		thumbDirection: "Y",
		effect: 'fade'

	});
})
</script> 
</head> 
<body>
    <div class="successlunbo" style="margin-top:60px">
        <div class="succesny">
            <div class="control">
                <ul class="change">
                </ul>
            </div>
            <div class="thumbWrap">
                <div class="thumbCont">
                    <ul>
                        <!-- img属性, url=url, text=描述, bigimg=大图, alt=标题  -->
                        <li>
                            <div><img src="${home }/images/s1.jpg" url="url" bigImg="${home }/images/1.jpg" alt="标题1"></div>
                        </li>
                        <li>
                            <div><img src="${home }/images/s2.jpg" url="url"  bigImg="${home }/images/2.jpg" alt="标题2"></div>
                        </li>
                        <li>
                            <div><img src="${home }/images/s3.jpg" url="url"  bigImg="${home }/images/3.jpg" alt="标题3"></div>
                        </li>
                        <li>
                            <div><img src="${home }/images/s4.jpg" url="url"  bigImg="${home }/images/4.jpg" alt="标题4"></div>
                        </li>
                        <li>
                            <div><img src="${home }/images/s5.jpg" url="url"  bigImg="${home }/images/5.jpg" alt="标题5"></div>
                        </li>
                        <li>
                            <div><img src="${home }/images/s1.jpg" url="url" bigImg="${home }/images/1.jpg" alt="标题6"></div>
                        </li>
                        <li>
                            <div><img src="${home }/images/s2.jpg" url="url"  bigImg="${home }/images/2.jpg" alt="标题7"></div>
                        </li>
                        <li>
                            <div><img src="${home }/images/s3.jpg" url="url"  bigImg="${home }/images/3.jpg" alt="标题8"></div>
                        </li>
                        <li>
                            <div><img src="${home }/images/s4.jpg" url="url"  bigImg="${home }/images/4.jpg" alt="标题9"></div>
                        </li>
                        <li>
                            <div><img src="${home }/images/s5.jpg" url="url"  bigImg="${home }/images/5.jpg" alt="标题10"></div>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</body> 
</html>
