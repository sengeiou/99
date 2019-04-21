package com.oty.sys.entity;

import java.util.Date;

import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 视频监控表
 *
 */
@Table(name = TVideoMonitor.TABLE_NAME)
@ToString(callSuper = false, includeFieldNames = true)
public class TVideoMonitor extends BaseEntity {

	private static final long serialVersionUID = -5361239742432415257L;

	public static final String TABLE_NAME = "t_video_monitor";

	/**
	 * 创建时间
	 */
	@Getter
	@Setter
	private Date createTime;
	/**
	 * 修改时间
	 */
	@Getter
	@Setter
	private Date updateTime; 
	/**
	 * 摄像头名称
	 */
	@Getter
	@Setter
	private String name;
	/**
	 * 摄像头IP
	 */
	@Getter
	@Setter
	private String ip;
	/**
	 * 摄像头端口
	 */
	@Getter
	@Setter
	private String port;
	/**
	 * 摄像头帐号
	 */
	@Getter
	@Setter
	private String account;
	/**
	 * 摄像头密码
	 */
	@Getter
	@Setter
	private String password;
	/**
	 * 摄像头地址
	 */
	@Getter
	@Setter
	private String address;
	/**
	 * 摄像头联系人
	 */
	@Getter
	@Setter
	private String contacts;
	/**
	 * 摄像头联系电话
	 */
	@Getter
	@Setter
	private String tel;
	/**
	 * 摄像头经度
	 */
	@Getter
	@Setter
	private String gpsX;
	/**
	 * 摄像头纬度
	 */
	@Getter
	@Setter
	private String gpsY;
	/**
	 * 摄像头状态  1:正常 0:无效 -1:删除 未使用
	 */
	@Getter
	@Setter
	private Integer status; 
	/**
	 * 摄像头备注
	 */
	@Getter
	@Setter
	private String remark;
	/**
	 *  RTMP地址
	 */
	@Getter
	@Setter
	private String rtmpUrl;
	/**
	 * HLS地址
	 */
	@Getter
	@Setter
	private String hlsUrl;
	/**
	 *  验证码，设备机身上的六位大写字母
	 */
	@Getter
	@Setter
	private String verificationCode;
	/**
	 * 序列号
	 */
	@Getter
	@Setter
	private String deviceSerial;
	/**
	 *  是否可以控制 0否 1是
	 */
	@Getter
	@Setter
	private Integer control; 
	/**
	 * 通道号
	 */
	@Getter
	@Setter
	private Integer channelNo;
	/**
	 *  h5播放地址
	 */
	@Getter
	@Setter
	private String h5Url;
	/**
	 * 布防开始时间
	 */
	@Getter
	@Setter
	private String defenceStartTime;
	/**
	 * 布防结束时间 如果为第二天,在时间前加上n 如n00:00 间隔不能超过24个小时
	 */
	@Getter
	@Setter
	private String defenceStopTime;
	/**
	 * 1-启用，0-不启用，默认为0
	 */
	@Getter
	@Setter
	private Integer defenceEnable;
	/**
	 * 灵敏度 0到6 若到强， 默认2
	 */
	@Getter
	@Setter
	private Integer intensity = 2;
	/**
	 * 项目id
	 */
	@Getter
	@Setter
	private Integer projectId;

}
