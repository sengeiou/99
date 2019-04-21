<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ include file="/common/taglib.jsp"%> 
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>控制台</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
  <LINK rel="Bookmark" href="${home }/favicon.ico">
  <LINK rel="Shortcut Icon" href="${home }/favicon.ico" />
  <link rel="stylesheet" href="${home }/layui/css/layui.css" media="all">
  <link rel="stylesheet" href="${home }/css/admin.css" media="all">
</head>
<body> 
  <div class="layui-fluid">
    <div class="layui-row layui-col-space15">
      <div class="layui-col-md8">
        <div class="layui-row layui-col-space15">
          <div class="layui-col-md6">
            <div class="layui-card">
              <div class="layui-card-header">快捷方式</div>
              <div class="layui-card-body"> 
                <div class="layui-carousel layadmin-carousel">
                  <div carousel-item>
                    <ul class="layui-row layui-col-space10">
                      <li class="layui-col-xs3">
                        <a lay-href="#">
                          <i class="layui-icon layui-icon-console"></i>
                          <cite>主页一</cite>
                        </a>
                      </li>
                      <li class="layui-col-xs3">
                        <a lay-href="#">
                          <i class="layui-icon layui-icon-chart"></i>
                          <cite>主页二</cite>
                        </a>
                      </li>
                      <li class="layui-col-xs3">
                        <a lay-href="#">
                          <i class="layui-icon layui-icon-template-1"></i>
                          <cite>弹层</cite>
                        </a>
                      </li>
                      <li class="layui-col-xs3">
                        <a layadmin-event="im">
                          <i class="layui-icon layui-icon-chat"></i>
                          <cite>聊天</cite>
                        </a>
                      </li>
                      <li class="layui-col-xs3">
                        <a lay-href="#">
                          <i class="layui-icon layui-icon-find-fill"></i>
                          <cite>进度条</cite>
                        </a>
                      </li>
                      <li class="layui-col-xs3">
                        <a lay-href="#">
                          <i class="layui-icon layui-icon-survey"></i>
                          <cite>工单</cite>
                        </a>
                      </li>
                      <li class="layui-col-xs3">
                        <a lay-href="#">
                          <i class="layui-icon layui-icon-user"></i>
                          <cite>用户</cite>
                        </a>
                      </li>
                      <li class="layui-col-xs3">
                        <a lay-href="#">
                          <i class="layui-icon layui-icon-set"></i>
                          <cite>设置</cite>
                        </a>
                      </li>
                    </ul>
                    <ul class="layui-row layui-col-space10">
                      <li class="layui-col-xs3">
                        <a lay-href="#">
                          <i class="layui-icon layui-icon-set"></i>
                          <cite>我的资料</cite>
                        </a>
                      </li>
                      <li class="layui-col-xs3">
                        <a lay-href="#">
                          <i class="layui-icon layui-icon-set"></i>
                          <cite>我的资料</cite>
                        </a>
                      </li>
                      <li class="layui-col-xs3">
                        <a lay-href="set/user/info.html">
                          <i class="layui-icon layui-icon-set"></i>
                          <cite>我的资料</cite>
                        </a>
                      </li>
                      <li class="layui-col-xs3">
                        <a lay-href="set/user/info.html">
                          <i class="layui-icon layui-icon-set"></i>
                          <cite>我的资料</cite>
                        </a>
                      </li>
                      <li class="layui-col-xs3">
                        <a lay-href="set/user/info.html">
                          <i class="layui-icon layui-icon-set"></i>
                          <cite>我的资料</cite>
                        </a>
                      </li>
                      <li class="layui-col-xs3">
                        <a lay-href="set/user/info.html">
                          <i class="layui-icon layui-icon-set"></i>
                          <cite>我的资料</cite>
                        </a>
                      </li>
                      <li class="layui-col-xs3">
                        <a lay-href="set/user/info.html">
                          <i class="layui-icon layui-icon-set"></i>
                          <cite>我的资料</cite>
                        </a>
                      </li>
                      <li class="layui-col-xs3">
                        <a lay-href="set/user/info.html">
                          <i class="layui-icon layui-icon-set"></i>
                          <cite>我的资料</cite>
                        </a>
                      </li>
                    </ul> 
                  </div>
                </div> 
              </div>
            </div>
          </div>
          <div class="layui-col-md6">
            <div class="layui-card">
              <div class="layui-card-header">待办事项</div>
              <div class="layui-card-body"> 
                <div class="layui-carousel layadmin-carousel">
                  <div carousel-item>
                    <ul class="layui-row layui-col-space10">
                      <li class="layui-col-xs6">
                        <a lay-href="app/content/comment.html" class="layadmin-backlog-body">
                          <h3>待审评论</h3>
                          <p><cite>66</cite></p>
                        </a>
                      </li>
                      <li class="layui-col-xs6">
                        <a lay-href="app/forum/list.html" class="layadmin-backlog-body">
                          <h3>待审帖子</h3>
                          <p><cite>12</cite></p>
                        </a>
                      </li>
                      <li class="layui-col-xs6">
                        <a lay-href="template/goodslist.html" class="layadmin-backlog-body">
                          <h3>待审商品</h3>
                          <p><cite>99</cite></p>
                        </a>
                      </li>
                      <li class="layui-col-xs6">
                        <a href="javascript:;" onclick="layer.tips('不跳转', this, {tips: 3});" class="layadmin-backlog-body">
                          <h3>待发货</h3>
                          <p><cite>20</cite></p>
                        </a>
                      </li>
                    </ul>
                    <ul class="layui-row layui-col-space10">
                      <li class="layui-col-xs6">
                        <a href="javascript:;" class="layadmin-backlog-body">
                          <h3>待审友情链接</h3>
                          <p><cite style="color: #FF5722;">5</cite></p>
                        </a>
                      </li>
                    </ul>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class="layui-col-md12">
            <div class="layui-card">
              <div class="layui-card-header">数据概览</div>
              <div class="layui-card-body"> 
                <div class="layui-carousel layadmin-carousel">
                  <div carousel-item id="LAY-index-dataview">
                    <div><i class="layui-icon layui-icon-loading1 layadmin-loading"></i></div>
                    <div></div>
                    <div></div>
                  </div>
                </div> 
              </div>
            </div>
            <div class="layui-card">
              <div class="layui-tab layui-tab-brief layadmin-latestData">
                <ul class="layui-tab-title">
                  <li class="layui-this">今日事件</li>
                  <li>今日代办</li>
                </ul>
                <div class="layui-tab-content">
                  <div class="layui-tab-item layui-show">
                    <table id="LAY-index-topSearch"></table>
                  </div>
                  <div class="layui-tab-item">
                    <table id="LAY-index-topCard"></table>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div> 
      <div class="layui-col-md4">
        <div class="layui-card">
          <div class="layui-card-header">版本信息</div>
          <div class="layui-card-body layui-text">
            <table class="layui-table">
              <colgroup>
                <col width="120">
                <col>
              </colgroup>
              <tbody>
                <tr>
                  <td>当前版本</td>
                  <td>V1.0.1</td>
                </tr>
                <tr>
                  <td>服务器环境</td>
                  <td>CentOS 7.6</td>
                </tr>
                <tr>
                  <td>数据库版本</td>
                  <td>My SQL 5.1</td>
                </tr> 
              </tbody>
            </table>
          </div>
        </div> 
        <div class="layui-card">
          <div class="layui-card-header">效果报告</div>
          <div class="layui-card-body layadmin-takerates">
            <div class="layui-progress" lay-showPercent="yes">
              <h3>转化率（日同比 28% <span class="layui-edge layui-edge-top" lay-tips="增长" lay-offset="-15"></span>）</h3>
              <div class="layui-progress-bar" lay-percent="65%"></div>
            </div>
            <div class="layui-progress" lay-showPercent="yes">
              <h3>签到率（日同比 11% <span class="layui-edge layui-edge-bottom" lay-tips="下降" lay-offset="-15"></span>）</h3>
              <div class="layui-progress-bar" lay-percent="32%"></div>
            </div>
          </div>
        </div> 
        <div class="layui-card">
          <div class="layui-card-header">实时监控</div>
          <div class="layui-card-body layadmin-takerates">
            <div class="layui-progress" lay-showPercent="yes">
              <h3>CPU使用率</h3>
              <div class="layui-progress-bar" lay-percent="58%"></div>
            </div>
            <div class="layui-progress" lay-showPercent="yes">
              <h3>内存占用率</h3>
              <div class="layui-progress-bar layui-bg-red" lay-percent="90%"></div>
            </div>
          </div>
        </div> 
        <div class="layui-card">
          <div class="layui-card-header">产品动态</div>
          <div class="layui-card-body">
            <div class="layui-carousel layadmin-carousel" >
              <div carousel-item>
                <div><a href="http://fly.layui.com/docs/2/" target="_blank" class="layui-bg-red">快速上手文档</a></div>
                <div><a href="http://fly.layui.com/vipclub/list/layuiadmin/" target="_blank" class="layui-bg-green">会员讨论专区</a></div> 
                <div><a href="http://www.layui.com/admin/#get" target="_blank" class="layui-bg-blue"> 官方后台模板系统</a></div>
              </div>
            </div>
          </div>
        </div> 
      </div> 
    </div>
  </div>
  <script type="text/javascript" src="${home}/js/jquery.js"></script>
  <script src="${home }/layui/layui.js"></script>    
  <script>
	  layui.config({
	    base: '${home}/' //静态资源所在路径
	  }).extend({
	    index: './lib/index' //主入口模块
	  }).use(['index']);
  </script> 
</body>
</html>

