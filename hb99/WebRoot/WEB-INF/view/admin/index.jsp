<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>大气污染源管控治一体化智能平台</title> 
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
  <LINK rel="Bookmark" href="${home }/favicon.ico">
  <LINK rel="Shortcut Icon" href="${home }/favicon.ico" />
  <link rel="stylesheet" href="${home }/layui/css/layui.css" media="all">
  <link rel="stylesheet" href="${home }/css/admin.css" media="all">  
  <style type="text/css"> 
	/*锁屏*/
	.admin-header-lock{width: 320px; height: 170px; padding: 20px; position: relative; text-align: center;}
	.admin-header-lock-img{width:70px; height:70px; margin: 0 auto;}
	.admin-header-lock-img img{width:70px; height:70px; border-radius: 100%; box-shadow:0 0 30px #44576b;}
	.admin-header-lock-name{color: #009688;margin: 8px 0 15px 0;}
	.input_btn{ overflow: hidden; margin-bottom: 10px; }
	.admin-header-lock-input{width: 170px; color: #fff;background-color: #009688; float: left; margin:0 10px 0 40px; border:none;}
	.admin-header-lock-input::-webkit-input-placeholder {color:#fff;}
	.admin-header-lock-input::-moz-placeholder {color:#fff;}
	.admin-header-lock-input:-ms-input-placeholder {color:#fff;}
	.admin-header-lock-input:-moz-placeholder {color:#fff;}
  </style>
</head>
<body class="layui-layout-body"> 
  <div id="LAY_app">
    <div class="layui-layout layui-layout-admin">
      <div class="layui-header">
        <!-- 头部区域 -->
        <ul class="layui-nav layui-layout-left">
          <li class="layui-nav-item layadmin-flexible" lay-unselect>
            <a href="javascript:;" layadmin-event="flexible" title="侧边伸缩">
              <i class="layui-icon layui-icon-shrink-right" id="LAY_app_flexible"></i>
            </a>
          </li> 
          <li class="layui-nav-item" lay-unselect>
            <a href="javascript:;" layadmin-event="refresh" title="刷新">
              <i class="layui-icon">&#xe669;</i>
            </a>
          </li>
        </ul>
        <ul class="layui-nav layui-layout-right" lay-filter="layadmin-layout-right"> 
          <li class="layui-nav-item" lay-unselect>
           <a href="javascript:;"> <div id="nowTime"></div></a>
          </li>
          <li class="layui-nav-item lockcms" lay-unselect>
            <a href="javascript:;" layadmin-event="lock"> 
              <i class="layui-icon">&#xe64d;</i>
            </a>
          </li>
          <li class="layui-nav-item layui-hide-xs" lay-unselect>
            <a href="javascript:;" layadmin-event="fullscreen">
              <i class="layui-icon layui-icon-screen-full"></i>
            </a>
          </li> 
          <li class="layui-nav-item layui-hide-xs clearCache" lay-unselect>
            <a href="javascript:;" layadmin-event="clearCache">
              <i class="layui-icon" data-icon="&#xe640;">&#xe640;</i>
            </a>
          </li>   
          <li class="layui-nav-item layui-hide-xs" lay-unselect>
            <a href="javascript:;" layadmin-event="theme">
              <i class="layui-icon layui-icon-theme"></i>
            </a>
          </li>
          <li class="layui-nav-item layui-hide-xs" lay-unselect>
            <a href="javascript:;" layadmin-event="note">
              <i class="layui-icon layui-icon-note"></i>
            </a>
          </li>
          <li class="layui-nav-item" lay-unselect>
            <a href="javascript:;">
              <cite>${ADMIN_SESSION_DATA.user.name}</cite>
            </a>
            <dl class="layui-nav-child">
              <dd><a lay-href="${home}/admin/system/user/user_info.html">基本资料</a></dd>
              <dd><a href="javascript:;" id="changePassword">修改密码</a></dd> 
              <hr>
              <dd layadmin-event="logout" style="text-align: center;" id="sysout"><a>退出</a></dd>
            </dl>
          </li> 
          <li class="layui-nav-item layui-hide-xs" lay-unselect>
            <a href="javascript:;" layadmin-event="about"><i class="layui-icon layui-icon-more-vertical"></i></a>
          </li>
          <li class="layui-nav-item layui-show-xs-inline-block layui-hide-sm" lay-unselect>
            <a href="javascript:;" layadmin-event="more"><i class="layui-icon layui-icon-more-vertical"></i></a>
          </li>
        </ul>
      </div> 
      <!-- 侧边菜单 -->
      <div class="layui-side layui-side-menu">
        <div class="layui-side-scroll">
          <div class="layui-logo" lay-href="${home }/admin/console.html">
            <span>大气智能平台</span>
          </div> 
          <ul class="layui-nav layui-nav-tree" lay-shrink="all" id="LAY-system-side-menu" lay-filter="layadmin-system-side-menu"> 
			<c:forEach items="${rootData.subDatas}" var="subData1" varStatus="s1">
                <li data-name="${subData2.code }" class="layui-nav-item"> 
		          <a href="javascript:;" lay-tips="${subData1.name }" lay-direction="2">
		               <i class="layui-icon">${subData1.icon }</i><cite>${subData1.name }</cite>
		          </a>
                  <dl class="layui-nav-child">
					<c:forEach items="${subData1.subDatas}" var="subData2" varStatus="s2">
	                  <dd data-name="${subData2.code }">
				          <i class="layui-icon">${subData2.icon }</i><a lay-href="${home }${subData2.link }">${subData2.name }</a>
				     </dd>
					</c:forEach>
	              </dl>
	            </li>
			</c:forEach> 
          </ul>
        </div>
      </div> 
      <!-- 页面标签 -->
      <div class="layadmin-pagetabs" id="LAY_app_tabs">
        <div class="layui-icon layadmin-tabs-control layui-icon-prev" layadmin-event="leftPage"></div>
        <div class="layui-icon layadmin-tabs-control layui-icon-next" layadmin-event="rightPage"></div> 
        <div class="layui-icon layadmin-tabs-control layui-icon-down">
          <ul class="layui-nav layadmin-tabs-select" lay-filter="layadmin-pagetabs-nav">
            <li class="layui-nav-item" lay-unselect>
              <a href="javascript:;"></a>
              <dl class="layui-nav-child layui-anim-fadein">
                <dd layadmin-event="closeThisTabs"><a href="javascript:;">关闭当前标签页</a></dd>
                <dd layadmin-event="closeOtherTabs"><a href="javascript:;">关闭其它标签页</a></dd>
                <dd layadmin-event="closeAllTabs"><a href="javascript:;">关闭全部标签页</a></dd>
              </dl>
            </li>
          </ul>
        </div>
        <div class="layui-tab" lay-unauto lay-allowClose="true" lay-filter="layadmin-layout-tabs">
          <ul class="layui-tab-title" id="LAY_app_tabsheader">
            <li lay-id="console.html" class="layui-this"><i class="layui-icon layui-icon-home"></i></li>
          </ul>
        </div>
      </div> 
      <!-- 主体内容 -->
      <div class="layui-body" id="LAY_app_body">
        <div class="layadmin-tabsbody-item layui-show">
          <iframe src="console.html" frameborder="0" class="layadmin-iframe"></iframe>
        </div>
      </div> 
      <!-- 辅助元素，一般用于移动设备下遮罩 -->
      <div class="layadmin-body-shade" layadmin-event="shade"></div>
    </div>
  </div> 
  <script type="text/javascript" src="${home}/js/jquery.js"></script>
  <script type="text/javascript" src="${home}/js/jqueryex.js"></script>
  <script src="${home}/layui/layui.js"></script>
  <script type="text/javascript" src="${home}/js/util.js"></script>
  <script type="text/javascript" src="${home}/js/message.js"></script>
  <script type="text/javascript" src="${home}/js/messagebody.js"></script>
  <script type='text/javascript' src='${home}/dwr/engine.js'></script>
  <script src="${home}/js/websocketconfig.js"></script>
  <script>
	  layui.config({
	    base: '${home}/' //静态资源所在路径
	  }).extend({
	    index: './lib/index' //主入口模块
	  }).use('index');
  </script> 
	<script type="text/javascript">
		$(function() {
	
			layui.use([ 'form' ], function() { 
				//系统退出
				$('#sysout').on('click', function() {
					layer.confirm('是否退出系统？', {
					  btn: ['退出','取消'] //按钮
					}, function(){
						location.href = '${home}/login.jsp';
					}, function(){
					  
					});
				});
				
			    //点击修改密码
			    $('#changePassword').click(function () {
			        var passIndex = layer.open({
				       type: 2,
				       title: '密码修改',
				       shadeClose: false,//点击遮罩关闭
				       anim: 3,
				       btnAlign: 'c',
				       shade: 0.3,//是否有遮罩，可以设置成false
				       maxmin: false, //开启最大化最小化按钮
				       area: ['460px', '400px'],
				       boolean:true, 
			           content: ['${home}/admin/system/user/change_password.html', 'no'], //iframe的url，no代表不显示滚动条 
			           success: function(layero, lockIndex) {
							var body = layer.getChildFrame('body', lockIndex);
							//绑定解锁按钮的点击事件
							body.find('button#close').on('click', function() {
								layer.close(lockIndex);
					        });
					  }
				    });
				});   

				//清除缓存
				$(".clearCache").click(function(){
					window.sessionStorage.clear();
			        window.localStorage.clear();
			        var index = layer.msg('清除缓存中，请稍候',{icon: 16,time:false,shade:0.8});
			        setTimeout(function(){
			            layer.close(index);
			            layer.msg("缓存清除成功！");
			        },1000);
			    }) 
				
				//锁屏
				function lockPage(){
					layer.open({
						title : false,
						type : 1,
						content : $("#lock-box"),
						closeBtn : 0,
						shade : 0.9
					})
				}
				$(".lockcms").on("click",function(){
					window.sessionStorage.setItem("lockcms",true);
					lockPage();
				})
				// 判断是否显示锁屏
				if(window.sessionStorage.getItem("lockcms") == "true"){
					lockPage();
				}
				// 解锁
				$("#unlock").on("click",function(){
					if($(this).siblings(".admin-header-lock-input").val() == ''){
						layer.msg("请输入解锁密码！");
					}else{
						var data = {op: 'validatePwd', pwd: $(this).siblings(".admin-header-lock-input").val()}; 
						var url = '${home}/admin/system/user/validatePwd'; 
						var b = false;	
						syncAjax(function() {
							$.getJSON(url, data, function(json) {										
								b = json;														
							})
						});	 
						if(b){
							window.sessionStorage.setItem("lockcms",false);
							$(this).siblings(".admin-header-lock-input").val('');
							layer.closeAll("page");
						}else{
							layer.msg("密码错误,请重新输入!");
						}
					}
				});
				$(document).on('keydown', function() {
					if (event.keyCode == 13) {
						$("#unlock").click();
					}
				});
				  
			});
		});
	</script>
	<script>
	    var currentsession= "${ADMIN_SESSION_DATA.user.id}";
	    var showmsg,lm; 
		layui.use(['layer', 'jquery'], function(){
		  var layer = layui.layer
		  ,$ = layui.jquery;  
		  
	     showmsg = function(data){
	   	 
	     } 
	  
		 layui.use('layim', function(layim){
			 
		    var initEventHandle = function () {
		          //收到消息后
		          socket.onmessage = function(event) {
		          	  if (event.data instanceof ArrayBuffer){
		          	        //如果后端发送的是二进制帧（protobuf）会收到前面定义的类型
		          	        var msg =  proto.Model.deserializeBinary(event.data);      
			          	 	var msgCon =  proto.MessageBody.deserializeBinary(msg.getContent());  
		  	    			var cache = layui.layim.cache();
		  	    			var local = layui.data('layim')[currentsession];
		  	    			var username = "",avatar="",friend=false;
		  			        layui.each(cache.friend, function(index1, item1){
					            layui.each(item1.list, function(index, item){
					              if(item.id == msg.getSender()){ 
					                username = item.username;
					                avatar = item.avatar;
					                return friend = true;
					              }
					            });
					            if(friend) return true;
				            }); 
		          	       
		          	       //心跳消息
		          	       if(msg.getCmd()==2){
		          	    	   //发送心跳回应
		          	    	   var message1 = new proto.Model();
		                       message1.setCmd(2);
		                       message1.setMsgtype(4);
		                       socket.send(message1.serializeBinary());
		          	       } 
		          	  }else {
		                    var data = event.data; 
		              } 
		          };
		          //连接后
		          socket.onopen = function(event) {
		        	   var message = new proto.Model();
		        	   var browser=BrowserUtil.info();
			   	       message.setVersion("1.0");
			   	       message.setDeviceid("")
			   	       message.setCmd(1);
			   	       message.setSender(currentsession);
			   	       message.setMsgtype(1); 
			   	       message.setFlag(1);
			   	       message.setPlatform(browser.name);
			   	       message.setPlatformversion(browser.version);
			   	       message.setToken(currentsession);
			   	       var bytes = message.serializeBinary();  
		               socket.send(bytes);  
		             
		          };
		          //连接关闭
		          socket.onclose = function(event) {
		        	  layim.setFriendStatus(currentsession, 'offline');
		        	  layer.confirm('您与服务器断开连接,请重新登录?', function(index){
		        		  window.location.href="${home}/login.jsp";
		        		  layer.close(index);
		        	  }); 
			      };
			      socket.onerror = function () {
			          reconnect(websocketurl,initEventHandle);
			      };
		    }   
		    createWebSocket(websocketurl,initEventHandle);
	   });   
	 });
		
	 //dwr推送消息方法
	 function showMessage(data) {  
		showmsg(data); 
	 }
	 
	 //获取系统时间
		var newDate = '';
		getLangDate();
		//值小于10时，在前面补0
		function dateFilter(date){
		    if(date < 10){return "0"+date;}
		    return date;
		}
		function getLangDate(){
		    var dateObj = new Date(); //表示当前系统时间的Date对象
		    var year = dateObj.getFullYear(); //当前系统时间的完整年份值
		    var month = dateObj.getMonth()+1; //当前系统时间的月份值
		    var date = dateObj.getDate(); //当前系统时间的月份中的日
		    var day = dateObj.getDay(); //当前系统时间中的星期值
		    var weeks = ["星期日","星期一","星期二","星期三","星期四","星期五","星期六"];
		    var week = weeks[day]; //根据星期值，从数组中获取对应的星期字符串
		    var hour = dateObj.getHours(); //当前系统时间的小时值
		    var minute = dateObj.getMinutes(); //当前系统时间的分钟值
		    var second = dateObj.getSeconds(); //当前系统时间的秒钟值
		    var timeValue = "" +((hour >= 12) ? (hour >= 18) ? "晚上" : "下午" : "上午" ); //当前时间属于上午、晚上还是下午
		    newDate = dateFilter(year)+"年"+dateFilter(month)+"月"+dateFilter(date)+"日 "+" "+dateFilter(hour)+":"+dateFilter(minute)+":"+dateFilter(second);
		    document.getElementById("nowTime").innerHTML = "欢迎使用九九智能平台,当前时间为： "+newDate+"　"+week;
		    setTimeout("getLangDate()",1000);
		}
        
	</script>
  <!-- 锁屏 -->
  <div class="admin-header-lock" id="lock-box" style="display: none;">
	 <div class="admin-header-lock-img">
		 <c:choose>
			 <c:when test="${not empty MANAGER_SESSION_DATA.imgUrl}">
			    <img src="${MANAGER_SESSION_DATA.imgUrl}" class="layui-circle" width="35" height="35"/>
			 </c:when>
			 <c:otherwise>
				 <img src="${home}/images/otc.png" class="layui-circle" width="35" height="35"/>
			 </c:otherwise>
		 </c:choose>
	 </div>
	 <div class="admin-header-lock-name" id="lockUserName">${MANAGER_SESSION_DATA.user.name}</div>
	 <div class="input_btn">
		 <input type="password" class="admin-header-lock-input layui-input" placeholder="请输入密码解锁.." name="lockPwd" id="lockPwd" />
		 <button class="layui-btn" id="unlock">解锁</button>
	 </div>
  </div> 
</body>
</html>



