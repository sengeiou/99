package com.oty.constant;

import com.googlecode.protobuf.format.JsonFormat;

import io.netty.util.AttributeKey;

public class Constants {
	
	public static String HOME;	
	
	public static String HOME_ADMIN;
	
	public static String REALPATH;	
	/** app列表每页元素个数 */
	public static final int APP_PAGE_SIZE = 10;
	/** 成功 */
	public static int CODE_SUCCESS = 200;	
	/** 失败 */
	public static int CODE_FAIL = 500;
	/** 登录失效 */
	public static int CODE_FAILURE = 403;
	/** 文件上传最大字节数 */
	public static long MAX_UPLOAD_SIZE = 1024 * 1024 * 100;
	
	/** 公共审核状态表名*/
	public static final String COMMON_AUDIT_STATUS_TABLE_NAME = "common_audit_status"; 
	public static final String FIELD_TYPE = "audit_status"; 
	
	public static final String SESSION_ADMIN_DATA = "ADMIN_SESSION_DATA"; 
	public static final String SESSION_MANAGER_DATA = "SESSION_MANAGER_DATA"; 
	public static final String SESSION_WEIXIN_DATA = "SESSION_WEIXIN_DATA"; 
	
	 
    public static interface ImserverConfig{
    	//连接空闲时间
      	public static final int READ_IDLE_TIME = 60;//秒
      	//发送心跳包循环时间
      	public static final int WRITE_IDLE_TIME = 40;//秒
        //心跳响应 超时时间
      	public static final int PING_TIME_OUT = 70; //秒   需大于空闲时间
      	
        // 最大协议包长度
        public static final int MAX_FRAME_LENGTH = 1024 * 10; // 10k
        //
        public static final int MAX_AGGREGATED_CONTENT_LENGTH = 65536;
        
        public static final String REBOT_SESSIONID="0";//机器人SessionID
        
        public static final int WEBSOCKET = 1;//websocket标识
        
        public static final int SOCKET =0;//socket标识
        
        public static final int DWR = 2;//dwr标识 
    }
    
    public static interface DWRConfig{
    	   public static final String DWR_SCRIPT_FUNCTIONNAME="showMessage";//dwr显示消息的script方法名
    	   public static final String SS_KEY = "scriptSession_Id";  
    	   public static final String BROWSER = "browser";  
    	   public static final String BROWSER_VERSION = "browserVersion";  
    	   public static final JsonFormat JSONFORMAT =new JsonFormat();
    }

	@SuppressWarnings("rawtypes")
    public static interface SessionConfig{
    	 public static final String SESSION_KEY= "account" ;
    	 public static final AttributeKey<String> SERVER_SESSION_ID = AttributeKey.valueOf(SESSION_KEY); 
		public static final AttributeKey SERVER_SESSION_HEARBEAT = AttributeKey.valueOf("heartbeat");
    }
    
    public static interface ProtobufType{
    	 byte SEND = 1; //请求
    	 byte RECEIVE = 2; //接收
    	 byte NOTIFY = 3; //通知
    	 byte REPLY = 4; //回复
	}
   
    public static interface CmdType{
	   	 byte BIND = 1; //绑定  
	   	 byte HEARTBEAT = 2; //心跳 
	   	 byte ONLINE = 3; //上线
	   	 byte OFFLINE = 4; //下线 
	   	 byte MESSAGE = 5; //消息
	   	 byte RECON = 6; //重连
	}
  
}
