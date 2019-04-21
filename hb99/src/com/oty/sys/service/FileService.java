package com.oty.sys.service;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import com.oty.sys.dao.FileDao;
import com.oty.sys.entity.TFile;
import com.oty.util.FileUtil;
import com.oty.util.OperLogUtils;

@Service("fileService")
@Transactional(readOnly = true)
public class FileService {

	@Autowired
	private FileDao fileDao;
	@Autowired
	private OperLogUtils operLogUtils;

	public TFile get(Integer id) {
		return fileDao.selectByPrimaryKey(id);
	}

	@Transactional
	public Integer save(Integer sysUserId, TFile file) {
		fileDao.save(file);
		operLogUtils.insertLog(sysUserId, file);
		return file.getId();
	}

	@Transactional
	public Integer save(TFile file) {
		fileDao.save(file); 
		return file.getId();
	}

	@Transactional
	public Integer update(Integer sysUserId, TFile file) {
		operLogUtils.updateLog(sysUserId, fileDao.selectByPrimaryKey(file.getId()), file);
		fileDao.updateByPrimaryKey(file);
		return file.getId();
	}

	public String savePicture(MultipartFile file, String newname, String path) {
		String fileUrl = "";
		if (StringUtils.isEmpty(file.getOriginalFilename())) {
			return fileUrl;
		}
		String[] name = FileUtil.splitFileName(file.getOriginalFilename());
		boolean b = FileUtil.validateImgType(name[1]);
		// 文件类型验证通过
		if (b) {
			try {
				File tempFile = new File(path, newname + "." + name[1]);
				FileUtils.copyInputStreamToFile(file.getInputStream(), tempFile);
				fileUrl = newname + "." + name[1];
				// 大图裁剪

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return fileUrl;
	}

	public String saveFiles(MultipartFile file, String newname, String path) {
		String fileUrl = "";
		if (StringUtils.isEmpty(file.getOriginalFilename())) {
			return fileUrl;
		}
		String[] name = FileUtil.splitFileName(file.getOriginalFilename());
		boolean b = FileUtil.validateFileType(name[1]);
		// 文件类型验证通过
		if (b) {
			try {
				fileUrl = newname + "." + name[1];
				File tempFile = new File(path, newname + "." + name[1]);
				FileUtils.copyInputStreamToFile(file.getInputStream(), tempFile);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return fileUrl;
	}

}
