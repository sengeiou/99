<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ include file="/common/taglib.jsp"%> 
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${home }/css/device.css">
    <script type="text/javascript" src="https://api.map.baidu.com/api?v=2.0&ak=trLEKMVBCc6MKGemHlUXdyy2&s=1"></script>
</head>
<body class="unset"> 
    <section>
        <video controls>
            <!--视频引入位置-->
            <source src="http://www.runoob.com/try/demo_source/movie.mp4"  type="video/mp4">
        </video>
    </section>
    <aside>
        <div class="tabBar"><span class="active">实时视频</span><span>视频回放</span><span>视频抓拍</span></div>
        <div class="tabContent1 tabItem">
            <div class="control">
                <div class="controlWrap">
                    <div data-code="1007" class="top"><img src="${home }/images/shang.png" /></div>
                    <div data-code="1006" class="right"><img src="${home }/images/you.png" /></div>
                    <div data-code="1005" class="left"><img src="${home }/images/zuo.png" /></div>
                    <div data-code="1008" class="bottom"><img src="${home }/images/xia.png" /></div>

                </div>
                <div class="turn"></div>
            </div>
            <div class="oprationBtn">
                <div class="photograph"><img src="${home }/images/zhuapai1.png" /></div>
                <div class="refresh"><img src="${home }/images/shuaxin1.png" /></div>
            </div>
        </div>
        <div class="tabContent2 tabItem">
            <!--视频列表渲染位置-->
            <div class="videoList">
                <div class="videoItem">
                    <img src="${home }/images/mapBg.png" /><span>01名称_2019-03-29</span><img src="${home }/images/baofang.png" alt="">
                </div>
                <div class="videoItem">
                    <img src="${home }/images/mapBg.png" /><span>01名称_2019-03-29</span><img src="${home }/images/baofang.png" alt="">
                </div>
                <div class="videoItem">
                    <img src="${home }/images/mapBg.png" /><span>01名称_2019-03-29</span><img src="${home }/images/baofang.png" alt="">
                </div>
                <div class="videoItem">
                    <img src="${home }/images/mapBg.png" /><span>01名称_2019-03-29</span><img src="${home }/images/baofang.png" alt="">
                </div>
               
            </div>
            
        </div>
        <div class="tabContent3 tabItem">
            <div class="head">
                <div class="time"><time>2019-03-29</time><img src="${home }/images/xiala.png" /></div>
                
                <div class="menu">
                    <div class="obtain">获取</div>
                    <div class="delete">删除</div>
                </div>
            </div>
            <div class="imgWrap">
                <!--监控图片渲染位置-->
                <div>
                    <img src="${home }/images/video.png" />
                </div>
                <div>
                    <img src="${home }/images/video.png" />
                </div>
                <div>
                    <img src="${home }/images/video.png" />
                </div>
                <div>
                    <img src="${home }/images/video.png" />
                </div>
                <div>
                    <img src="${home }/images/video.png" />
                </div>
                <div>
                    <img src="${home }/images/video.png" />
                </div>
                <div>
                    <img src="${home }/images/video.png" />
                </div>
                <div>
                    <img src="${home }/images/video.png" />
                </div>
                <div>
                    <img src="${home }/images/video.png" />
                </div>
                <div>
                    <img src="${home }/images/video.png" />
                </div>
                <div>
                    <img src="${home }/images/video.png" />
                </div>
                <div>
                    <img src="${home }/images/video.png" />
                </div>
                <div>
                    <img src="${home }/images/video.png" />
                </div>
                <div>
                    <img src="${home }/images/video.png" />
                </div>

            </div>
        </div>
        
        <!--日历位置-->
        <div class="calendar">
                <div class="calendarHead">
                    <div class="prev"></div>
                    <time>2018/09</time>
                    <div class="next"></div>
                </div>
                <div class="calendarBody">
                    <div class="week">
                        <span>一</span>
                        <span>二</span>
                        <span>三</span>
                        <span>四</span>
                        <span>五</span>
                        <span>六</span>
                        <span>日</span>
                    </div>
                    <div class="days"></div>
                </div>
            </div>
    </aside>

    <script src="${home }/js/monitor.js"></script>
</body>
</html>