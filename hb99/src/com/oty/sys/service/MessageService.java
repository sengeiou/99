package com.oty.sys.service;

import java.util.List; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.abel533.entity.Example;
import com.github.pagehelper.PageHelper;
import com.oty.sys.dao.MessageDao;
import com.oty.sys.entity.TMessage;
import com.oty.sys.model.admin.message.MessageData;
import com.oty.util.OperLogUtils;
import com.oty.web.base.action.BaseAction;

import pub.spring.ActionResult;
import pub.types.IdText; 

@Service("messageService")
@Transactional(readOnly = true)
public class MessageService extends BaseAction {

	@Autowired
	private MessageDao messageDao;
	@Autowired
	private OperLogUtils operLogUtils;

	public TMessage get(Integer id) {
		return messageDao.selectByPrimaryKey(id);
	}
	
	public List<IdText> selectAllUser(){	//查询所有用户
		return messageDao.exceptMe(getSysUser().getId());
	}
	
	@Transactional
	public void save(Integer sysUserId, TMessage t) {
		if (t.getId() != null) {
			operLogUtils.updateLog(sysUserId, messageDao.selectByPrimaryKey(t.getId()), t);
			messageDao.updateByPrimaryKey(t);
		} else { 
			messageDao.insertUseGeneratedKeys(t);
			operLogUtils.insertLog(sysUserId, t);
		}
	}
	
	@Transactional
	public void save(Integer sysUserId, MessageData m) {
			if (m.getMessage().getId() != null) {	//预览（修改状态）
				if(m.getMessage().getStatus() == 0 && sysUserId == m.getMessage().getReceiveUserId()){
					m.getMessage().setStatus(1);
				}
				operLogUtils.updateLog(sysUserId, messageDao.selectByPrimaryKey(m.getMessage().getId()), m.getMessage());
				messageDao.updateByPrimaryKey(m.getMessage());
			} else {
				for(int i = 0; i<m.getReceiveUsers().size(); i++){
					m.getMessage().setReceiveUserId(m.getReceiveUsers().get(i));
					m.getMessage().setStatus(0);
					messageDao.insertUseGeneratedKeys(m.getMessage());
					operLogUtils.insertLog(sysUserId, m.getMessage());
			}
		}
	}
	
	public List<TMessage> query(TMessage t, Integer page) {
		/*Example example = new Example(TMessage.class);
		Example.Criteria criteria1 = example.createCriteria();
		Example.Criteria criteria2 = example.createCriteria();
		if (t.getSendUserId() != null) {
			criteria1.andEqualTo("sendUserId", t.getSendUserId());
		}
		if (t.getReceiveUserId()!= null) {
			criteria2.andEqualTo("receiveUserId", t.getReceiveUserId());
		}
		if (t.getType() != null) {
			criteria1.andEqualTo("type", t.getType());
			criteria2.andEqualTo("type", t.getType());
		}
		if (t.getStatus() != null) {
			criteria1.andEqualTo("status", t.getStatus());
			criteria2.andEqualTo("status", t.getStatus());
		}
		example.or(criteria2);
		example.setOrderByClause(" id DESC ");*/
		PageHelper.startPage(page, 10);
		//return messageDao.selectByExample(example);
		return messageDao.getAboutMe(t,getUserId());
			
		
	} 
 
}
