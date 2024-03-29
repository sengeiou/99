package com.oty.server.proxy.impl;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.oty.constant.Constants;
import com.oty.rebot.proxy.RebotProxy;
import com.oty.server.model.MessageWrapper;
import com.oty.server.model.proto.MessageBodyProto;
import com.oty.server.model.proto.MessageProto;
import com.oty.server.proxy.MessageProxy;
import com.oty.sys.entity.TMessage;
import com.oty.sys.service.MessageService;

import pub.functions.DateFuncs;

public class MessageProxyImpl implements MessageProxy {
	private final static Logger log = LoggerFactory.getLogger(MessageProxyImpl.class);

	private RebotProxy rebotProxy;
	@Autowired
	private MessageService messageService;

	public MessageWrapper convertToMessageWrapper(String sessionId, MessageProto.Model message) {

		switch (message.getCmd()) {
		case Constants.CmdType.BIND:
			try {
				return new MessageWrapper(MessageWrapper.MessageProtocol.CONNECT, message.getSender(), null, message);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case Constants.CmdType.HEARTBEAT:
			try {
				return new MessageWrapper(MessageWrapper.MessageProtocol.HEART_BEAT, sessionId, null, null);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case Constants.CmdType.ONLINE:

			break;
		case Constants.CmdType.OFFLINE:

			break;
		case Constants.CmdType.MESSAGE:
			try {
				MessageProto.Model.Builder result = MessageProto.Model.newBuilder(message);
				result.setTimeStamp(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
				result.setSender(sessionId);// 存入发送人sessionId
				message = MessageProto.Model.parseFrom(result.build().toByteArray());
				// 判断消息是否有接收人
				if (StringUtils.isNotEmpty(message.getReceiver())) {
					// 判断是否发消息给机器人
					if (message.getReceiver().equals(Constants.ImserverConfig.REBOT_SESSIONID)) {
						MessageBodyProto.MessageBody msg = MessageBodyProto.MessageBody.parseFrom(message.getContent());
						return rebotProxy.botMessageReply(sessionId, msg.getContent());
					} else {
						return new MessageWrapper(MessageWrapper.MessageProtocol.REPLY, sessionId,
								message.getReceiver(), message);
					}
				} else if (StringUtils.isNotEmpty(message.getGroupId())) {
					return new MessageWrapper(MessageWrapper.MessageProtocol.GROUP, sessionId, null, message);
				} else {
					return new MessageWrapper(MessageWrapper.MessageProtocol.SEND, sessionId, null, message);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		}
		return null;
	}

	public void saveOnlineMessageToDB(MessageWrapper message) {
		try {
			TMessage userMessage = convertMessageWrapperToBean(message);
			if (userMessage != null) {
				userMessage.setStatus(1);
				messageService.save(0, userMessage);
			}
		} catch (Exception e) {
			log.error("MessageProxyImpl  user " + message.getSessionId() + " send msg to " + message.getReSessionId()
					+ " error");
			throw new RuntimeException(e.getCause());
		}
	}

	public void saveOfflineMessageToDB(MessageWrapper message) {
		try {
			TMessage userMessage = convertMessageWrapperToBean(message);
			if (userMessage != null) {
				userMessage.setStatus(0);
				messageService.save(0, userMessage);
			}
		} catch (Exception e) {
			log.error("MessageProxyImpl  user " + message.getSessionId() + " send msg to " + message.getReSessionId()
					+ " error");
			throw new RuntimeException(e.getCause());
		}
	}

	private TMessage convertMessageWrapperToBean(MessageWrapper message) {
		try {
			// 保存非机器人消息
			if (!message.getSessionId().equals(Constants.ImserverConfig.REBOT_SESSIONID)) {
				MessageProto.Model msg = (MessageProto.Model) message.getBody();
				MessageBodyProto.MessageBody msgConten = MessageBodyProto.MessageBody.parseFrom(msg.getContent());
				TMessage userMessage = new TMessage(); 
				userMessage.setContent(msgConten.getContent()); 
				userMessage.setPreDate(DateFuncs.strToDateTime(msg.getTimeStamp(), "yyyy-MM-dd HH:mm:ss"));
				userMessage.setStatus(1);
				return userMessage;
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getCause());
		}
		return null;
	}

	public void setRebotProxy(RebotProxy rebotProxy) {
		this.rebotProxy = rebotProxy;
	}

	public MessageProto.Model getOnLineStateMsg(String sessionId) {
		MessageProto.Model.Builder result = MessageProto.Model.newBuilder();
		result.setTimeStamp(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
		result.setSender(sessionId);// 存入发送人sessionId
		result.setCmd(Constants.CmdType.ONLINE);
		return result.build();
	}

	public MessageProto.Model getOffLineStateMsg(String sessionId) {
		MessageProto.Model.Builder result = MessageProto.Model.newBuilder();
		result.setTimeStamp(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
		result.setSender(sessionId);// 存入发送人sessionId
		result.setCmd(Constants.CmdType.OFFLINE);
		return result.build();
	}

	public MessageWrapper getReConnectionStateMsg(String sessionId) {
		MessageProto.Model.Builder result = MessageProto.Model.newBuilder();
		result.setTimeStamp(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
		result.setSender(sessionId);// 存入发送人sessionId
		result.setCmd(Constants.CmdType.RECON);
		return new MessageWrapper(MessageWrapper.MessageProtocol.SEND, sessionId, null, result.build());
	}

}
