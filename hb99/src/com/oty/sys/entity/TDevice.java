package com.oty.sys.entity;

import java.util.Date;
 
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 设备表
 * 
 * @author lu
 *
 */
@Table(name = TDevice.TABLE_NAME)
@ToString(callSuper = false, includeFieldNames = true)
public class TDevice extends BaseEntity {

	private static final long serialVersionUID = -4181040129781001289L;

	public static final String TABLE_NAME = "t_device";
	public static final String FIELD_PROPERTY = "property";
	public static final String FIELD_IS_CAMERA = "is_camera";
 
	/**
	 * 设备名称
	 */
	@Getter
	@Setter
	private String name;
	/**
	 * 设备唯一标识码
	 */
	@Getter
	@Setter
	private String code;
	/**
	 * 设备类型
	 */
	@Getter
	@Setter
	private Integer typeId;
	/**
	 * 属性
	 */
	@Getter
	@Setter
	private Integer property;
	/**
	 * 采集地址
	 */
	@Getter
	@Setter
	private String address;
	/**
	 * 采集周期
	 */
	@Getter
	@Setter
	private Integer cycle;
	/**
	 * 所属项目
	 */
	@Getter
	@Setter
	private Integer projectId;
	/**
	 * 所属模块
	 */
	@Getter
	@Setter
	private Integer moduleId;
	/**
	 * 是否有摄像头
	 */
	@Getter
	@Setter
	private Integer isCamera;
	/**
	 * 设备ip
	 */
	@Getter
	@Setter
	private String ip;
	/**
	 * 设备端口
	 */
	@Getter
	@Setter
	private Integer port;
	/**
	 * 触屏ip
	 */
	@Getter
	@Setter
	private String touchIp;
	/**
	 * 触屏端口
	 */
	@Getter
	@Setter
	private Integer touchPort;
	/**
	 * 生产时间
	 */
	@Getter
	@Setter
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private String produceTime;
	/**
	 * 出厂时间
	 */
	@Getter
	@Setter
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private String factoryTime;
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
	 * 删除标记 0否1是
	 */
	@Getter
	@Setter
	private Integer delFlag = 0;
	/**
	 * 设备二维码
	 */
	@Getter
	@Setter
	private String url;
	/**
	 * 设备编码
	 */
	@Getter
	@Setter
	private String uuid; 
	/**
	 * 固定式设备0 检测仪 1
	 */
	@Getter
	@Setter
	private Integer type ;
	/**
	 * 设备id
	 */
	@Getter
	@Setter
	private String deviceid;

}
