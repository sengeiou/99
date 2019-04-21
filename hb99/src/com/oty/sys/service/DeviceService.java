package com.oty.sys.service;
 
import java.util.List;

import org.commontemplate.util.coder.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; 

import com.github.abel533.entity.Example;
import com.github.pagehelper.PageHelper;
import com.oty.sys.dao.DeviceDao;
import com.oty.sys.dao.DeviceTypeDao;
import com.oty.sys.entity.TDevice;
import com.oty.sys.entity.TDeviceType;
import com.oty.util.Base64Utils;
import com.oty.util.CreateQRCodeUtils;
import com.oty.util.OperLogUtils;
import com.oty.util.OssUtils;

import net.sf.json.JSONObject;
import pub.functions.FsFuncs;
import pub.functions.StrFuncs;
import pub.types.IdText;

@Service("deviceService")
@Transactional(readOnly = true)
public class DeviceService {

	@Autowired
	private DeviceDao deviceDao;
	@Autowired
	private OperLogUtils operLogUtils;
	@Autowired
	private DeviceTypeDao deviceTypeDao;

	public TDevice get(Integer id) {
		return deviceDao.selectByPrimaryKey(id);
	}

	@Transactional
	public void save(Integer sysUserId, TDevice t) {
		if (t.getId() != null) {
			if(!StrFuncs.notEmpty(t.getUrl())){
				if(!StrFuncs.notEmpty(t.getUuid())){
					t.setUuid(UUID.randomUUID().toString());
				}
				JSONObject obj = new JSONObject();
				obj.put("uuid", t.getUuid()); 
				try {
					// 加密处理参数 并转为二维码
					byte[] createQRCode = CreateQRCodeUtils.getStringCreateQRCode(Base64Utils.base64encode(obj.toString()),
							false, null, null, t.getName());
					String key = OssUtils.getKey(t.getUuid(), FsFuncs.getFileName(t.getName() + ".jpg"));
					new OssUtils().uploadBytes(createQRCode, key);
					t.setUrl(OssUtils.getUrl(key)); 
				} catch (Exception e) { 
					e.printStackTrace();
				}  
			}
			TDeviceType deviceType = deviceTypeDao.selectByPrimaryKey(t.getTypeId());
			t.setType(deviceType.getType());
			operLogUtils.updateLog(sysUserId, deviceDao.selectByPrimaryKey(t.getId()), t);
			deviceDao.updateByPrimaryKey(t);
		} else {
			t.setUuid(UUID.randomUUID().toString());
			JSONObject obj = new JSONObject();
			obj.put("uuid", t.getUuid()); 
			try {
				// 加密处理参数 并转为二维码
				byte[] createQRCode = CreateQRCodeUtils.getStringCreateQRCode(Base64Utils.base64encode(obj.toString()),
						false, null, null, t.getName());
				String key = OssUtils.getKey(t.getUuid(), FsFuncs.getFileName(t.getName() + ".jpg"));
				new OssUtils().uploadBytes(createQRCode, key);
				t.setUrl(OssUtils.getUrl(key)); 
			} catch (Exception e) { 
				e.printStackTrace();
			}  
			TDeviceType deviceType = deviceTypeDao.selectByPrimaryKey(t.getTypeId());
			t.setType(deviceType.getType());
			deviceDao.insertUseGeneratedKeys(t);
			operLogUtils.insertLog(sysUserId, t);
		}
	}

	public List<TDevice> query(TDevice t, Integer page, Integer pageSize) {
		Example example = new Example(TDevice.class);
		Example.Criteria criteria = example.createCriteria();
		if (StrFuncs.notEmpty(t.getName())) {
			criteria.andLike("name", StrFuncs.anyLike(t.getName()));
		}
		if (t.getProperty() != null) {
			criteria.andEqualTo("property", t.getProperty());
		}
		if (t.getTypeId() != null) {
			criteria.andEqualTo("typeId", t.getTypeId());
		}
		if (t.getProjectId() != null) {
			criteria.andEqualTo("projectId", t.getProjectId());
		}
		if (t.getIsCamera() != null) {
			criteria.andEqualTo("isCamera", t.getIsCamera());
		}
		criteria.andEqualTo("delFlag", 0);
		example.setOrderByClause(" id DESC ");
		PageHelper.startPage(page, pageSize);
		return deviceDao.selectByExample(example);
	}

	public List<IdText> getAll() {
		return deviceDao.getAll();
	}

}
