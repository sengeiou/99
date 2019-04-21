package com.oty.sys.entity;

import java.util.Date;
 
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 文件信息表
 */
@Table(name = TFileInfo.TABLE_NAME)
@ToString(callSuper = false, includeFieldNames = true)
public class TFileInfo extends BaseEntity {

	private static final long serialVersionUID = -2182283608524250395L;
	public static final String TABLE_NAME = "t_file_info";
 
	/** 对应的业务实体的表名 */
	@Getter
	@Setter
	private String tableName;
	/** 对应的业务实体的id */
	@Getter
	@Setter
	private Integer referenceId;
	/** 文件类型 */
	@Getter
	@Setter
	private Integer type;
	/** 文件ID */
	@Getter
	@Setter
	private Integer fileId;
	/** 名称 */
	@Getter
	@Setter
	private String name;
	@Getter
	@Setter
	private Date createTime;
	@Getter
	@Setter
	private Date updateTime;

}
