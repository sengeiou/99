package com.oty.sys.entity;

import java.util.Date;
 
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 文件表
 */
@Table(name = TFile.TABLE_NAME)
@ToString(callSuper = false, includeFieldNames = true)
public class TFile extends BaseEntity {

	private static final long serialVersionUID = 5863606410526449415L;
	public static final String TABLE_NAME = "t_file";
 
	/** 文件名 */
	@Getter
	@Setter
	private String fileName;
	/** 大小（字节） */
	@Getter
	@Setter
	private Integer fileSize;
	/** MIME */
	@Getter
	@Setter
	private String contentType;
	/** 访问路径 */
	@Getter
	@Setter
	private String url;
	/** 上传时间 */
	@Getter
	@Setter
	private Date uploadTime;

}
