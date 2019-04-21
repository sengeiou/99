<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ include file="/common/taglib.jsp"%>   
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title></title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="format-detection" content="telephone=no">
	<link rel="stylesheet" href="${home }/layui/css/layui.css" media="all" />
	<link rel="stylesheet" href="http://at.alicdn.com/t/font_eolqem241z66flxr.css" media="all" />
	<link rel="stylesheet" href="${home }/css/main.css" media="all" />
    <script type="text/javascript" src="${home}/js/jquery.js"></script>
</head>
<body class="childrenBody"> 
	<blockquote class="layui-elem-quote layui-bg-green">
		<div id="nowTime"></div>
	</blockquote>
	<div class="panel_box row">
		<div class="panel col">
			<a href="javascript:;" data-url="#">
				<div class="panel_icon" style="background-color:#FF5722;">
					<i class="iconfont icon-dongtaifensishu" data-icon="icon-dongtaifensishu"></i>
				</div>
				<div class="panel_word userNews">
					<span class="factorys"></span>
					<cite>分类1</cite>
				</div>
			</a>
		</div>
		<div class="panel col">
			<a href="javascript:;" data-url="#">
				<div class="panel_icon" style="background-color:#009688;">
					<i class="layui-icon" data-icon="&#xe613;">&#xe613;</i>
				</div>
				<div class="panel_word userAll">
					<span class="distributors"></span>
					<cite>分类2</cite>
				</div>
			</a>
		</div>
		<div class="panel col">
			<a href="javascript:;" data-url="#">
				<div class="panel_icon" style="background-color:#F7B824;">
					<i class="iconfont icon-wenben" data-icon="icon-wenben"></i>
				</div>
				<div class="panel_word appNewsAll">
					<span class="activity"></span>
					<cite>分类3</cite>
				</div>
			</a>
		</div>
		<div class="panel col">
			<a href="javascript:;" data-url="#">
				<div class="panel_icon" style="background-color:#F7B824;">
					<i class="iconfont icon-wenben" data-icon="icon-wenben"></i>
				</div>
				<div class="panel_word appNewsAll">
					<span class="activity"></span>
					<cite>分类4</cite>
				</div>
			</a>
		</div>
		<div class="panel col">
			<a href="javascript:;" data-url="#">
				<div class="panel_icon" style="background-color:#F7B824;">
					<i class="iconfont icon-wenben" data-icon="icon-wenben"></i>
				</div>
				<div class="panel_word appNewsAll">
					<span class="activity"></span>
					<cite>分类5</cite>
				</div>
			</a>
		</div>
		<div class="panel col">
			<a href="javascript:;" data-url="#">
				<div class="panel_icon" style="background-color:#F7B824;">
					<i class="iconfont icon-wenben" data-icon="icon-wenben"></i>
				</div>
				<div class="panel_word appNewsAll">
					<span class="activity"></span>
					<cite>分类6</cite>
				</div>
			</a>
		</div>
	</div>  
	<div class="row">
		<div class="sysNotice col">
			<blockquote class="layui-elem-quote title">更新日志</blockquote>
			<div class="layui-elem-quote layui-quote-nm">
			    
			    <c:forEach items="${pagehelper.list}" var="row" varStatus="s"> 
			      <h3># ${row.version} - ${row.releaseTime}</h3>
				  <p>* ${row.content}</p>
				  &nbsp;<p/>
			    </c:forEach> 
			</div>
		</div>
		<div class="sysNotice col">
			<blockquote class="layui-elem-quote title">系统参数 &nbsp; &nbsp;<i class="layui-icon" data-icon="&#xe628;" style="font-size: 25px; color: green;margin-top:5px;">&#xe628;</i></blockquote>
			<table class="layui-table">
				<colgroup>
					<col width="150">
					<col>
				</colgroup>
				<tbody>
					<tr>
						<td>当前版本</td>
						<td class="version"></td>
					</tr>
					<tr>
						<td>开发作者</td>
						<td class="author"></td>
					</tr> 
					<tr>
						<td>服务器环境</td>
						<td class="server"></td>
					</tr>
					<tr>
						<td>数据库版本</td>
						<td class="dataBase"></td>
					</tr>
					<tr>
						<td>最大上传限制</td>
						<td class="maxUpload"></td>
					</tr> 
				</tbody>
			</table>
			<blockquote class="layui-elem-quote title">最新公告<i class="iconfont icon-new1"></i></blockquote>
			<table class="layui-table" lay-skin="line">
				<colgroup>
					<col>
					<col width="110">
				</colgroup>
				<tbody class="hot_news"></tbody>
			</table> 
		</div>
	</div> 
	<script type="text/javascript" src="${home }/layui/layui.js"></script>  
	<script type="text/javascript">
		layui.config({
			base : "${home}/js/"
		}).use(['form','element','layer','jquery'],function(){
			var form = layui.form(),
				layer = parent.layer === undefined ? layui.layer : parent.layer,
				element = layui.element(),
				$ = layui.jquery;
		
			$(".panel a").on("click",function(){
				window.parent.addTab($(this));
			})  
		    
			$.ajax({
		        url: '${home}/admin/common/functions/mainParameter',
		        type: 'post',
		        dataType: 'json',
		        async: false, 
                beforeSend: function(){
                    layer.load(2); 
                },
		        success: function(data) {
		            if (data.code == 200) {
		                layer.closeAll('loading');
						$(".workOrderNews span").text(data.workOrders); 
						$(".userAll span").text(data.mgrUsers); 
						$(".userNews span").text(data.mgrUserNews); 
						$(".appAll span").text(data.apps); 
						$(".appNewsAll span").text(data.appNews);
						
						var hotNewsHtml = ''; 
						for(var i=0;i<data.pagehelper.total;i++){
							hotNewsHtml += '<tr>' 
					    	+'<td align="left"><a href="javascript:openNotice('+data.pagehelper.list[i].id+')">'+data.pagehelper.list[i].title+'</a></td>'
					    	+'<td width="200px">'+new Date(data.pagehelper.list[i].createTime).Format("yyyy-MM-dd hh:mm:ss")+'</td>'
					    	+'</tr>';
						}
						$(".hot_news").html(hotNewsHtml);
						
						fillParameter(data);
		            } else {
		               layer.closeAll('loading');
		               layer.msg(data.msg); 
		            }
		       },
		       error: function() {
		           layer.closeAll('loading');
		           layer.msg("请求异常,请稍候再试!"); 
		        }
		    });	    
		
			//数字格式化
			$(".panel span").each(function(){
				$(this).html($(this).text()>9999 ? ($(this).text()/10000).toFixed(2) + "<em>万</em>" : $(this).text());	
			}) 
		
			//填充数据方法
		 	function fillParameter(data){
		 		//判断字段数据是否存在
		 		function nullData(data){
		 			if(data == '' || data == "undefined"){
		 				return "未定义";
		 			}else{
		 				return data;
		 			}
		 		}
		 		$(".version").text(nullData(data.version));      //当前版本
				$(".author").text(nullData(data.author));        //开发作者 
				$(".server").text(nullData(data.server));        //服务器环境
				$(".dataBase").text(nullData(data.dataBase));    //数据库版本
				$(".maxUpload").text(nullData(data.maxUpload));    //最大上传限制

                $(".factorys").text(data.factorys);    //店铺
                $(".distributors").text(data.distributors);    //分销商
                $(".activity").text(data.activity);    //新活动
		 	}
		
		}) 
		
		function openNotice(id){   
			$.ajax({
		        url: "${home}/admin/common/functions/getNotice?id="+id,
		        type: 'post',
		        dataType: 'json',
		        async: false, 
                beforeSend: function(){
                    layer.load(2); 
                },
		        success: function(data) {
		            if (data.code == 200) {
		                layer.closeAll('loading'); 
						layer.open({
					        type: 1
					        ,title: false //不显示标题栏
					        ,closeBtn: true
					        ,area: '400px;'
					        ,shade: 0.8
					        ,id: 'LAY_layuipro' //设定一个id，防止重复弹出
					        ,btn: ['关闭']
					        ,btnAlign: 'c'
					        ,moveType: 1 //拖拽模式，0或者1
					        ,content: '<div style="padding: 50px; line-height: 22px; background-color: #393D49; color: #fff; font-weight: 300;">'+data.notice.contents+'</div>'
					        ,success: function(layero){
					          var btn = layero.find('.layui-layer-btn');
					          btn.find('.layui-layer-btn0').attr({
					            
					          });
					        }
					    }); 
		            } else {
		               layer.closeAll('loading');
		               layer.msg(data.msg); 
		            }
		       },
		       error: function() {
		           layer.closeAll('loading');
		           layer.msg("请求异常,请稍候再试!"); 
		        }
		    });	
		}
		
		Date.prototype.Format = function(fmt) {
            var o = {
                "M+" : this.getMonth() + 1,
                "d+" : this.getDate(),
                "h+" : this.getHours(),
                "m+" : this.getMinutes(),
                "s+" : this.getSeconds(),
                "q+" : Math.floor((this.getMonth() + 3) / 3),
                "S" : this.getMilliseconds()
            };
            if (/(y+)/.test(fmt))
                fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
            for (var k in o)
            if (new RegExp("(" + k + ")").test(fmt))
                fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
            return fmt;
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
</body>
</html>