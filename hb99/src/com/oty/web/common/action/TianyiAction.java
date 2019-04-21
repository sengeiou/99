package com.oty.web.common.action;
 
import java.util.Date; 

import org.slf4j.Logger;
import org.slf4j.LoggerFactory; 
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
 
import com.iotplatform.client.NorthApiException;
import com.iotplatform.client.dto.NotifyBindDeviceDTO;
import com.iotplatform.client.dto.NotifyCommandRspDTO;
import com.iotplatform.client.dto.NotifyDeviceAddedDTO;
import com.iotplatform.client.dto.NotifyDeviceDataChangedDTO;
import com.iotplatform.client.dto.NotifyDeviceDatasChangedDTO;
import com.iotplatform.client.dto.NotifyDeviceDeletedDTO;
import com.iotplatform.client.dto.NotifyDeviceEventDTO;
//import com.iotplatform.client.dto.NotifyDeviceInfoChangedDTO;
import com.iotplatform.client.dto.NotifyDeviceModelAddedDTO;
import com.iotplatform.client.dto.NotifyDeviceModelDeletedDTO;
import com.iotplatform.client.dto.NotifyFwUpgradeResultDTO;
import com.iotplatform.client.dto.NotifyFwUpgradeStateChangedDTO;
import com.iotplatform.client.dto.NotifyMessageConfirmDTO;
import com.iotplatform.client.dto.NotifyNBCommandStatusChangedDTO;
import com.iotplatform.client.dto.NotifyRuleEventDTO;
import com.iotplatform.client.dto.NotifyServiceInfoChangedDTO;
import com.iotplatform.client.dto.NotifySwUpgradeResultDTO;
import com.iotplatform.client.dto.NotifySwUpgradeStateChangedDTO;  

import pub.functions.DateFuncs; 

@Controller
@RequestMapping("/common/tianyi")
public class TianyiAction {

	private Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 订阅注册设备通知后（订阅的通知类型为deviceAdded），在物联网平台注册设备时，平台会给第三方应用推送注册设备通知。
	 * 
	 * @param notifyType
	 *            通知类型，取值：deviceAdded。
	 * @param deviceId
	 *            设备ID，用于唯一标识一个设备。
	 * @param gatewayId
	 *            网关ID，用于唯一标识一个网关设备。
	 * @param nodeType
	 *            设备类型。 ENDPOINT GATEWAY UNKNOWN
	 * @param deviceInfo
	 *            设备信息，具体参见DeviceInfo结构体。
	 * @throws NorthApiException
	 */
	@RequestMapping(value = "/deviceAdded")
	public void deviceAdded(@RequestBody NotifyDeviceAddedDTO body) throws NorthApiException {
		System.out.println("deviceAdded ==> " + body);
		logger.error("deviceAdded DateTime" + DateFuncs.dateTimeToStr(new Date(), DateFuncs.format2) + body); 
	}

	@RequestMapping(value = "/bindDevice")
	public void bindDevice(@RequestBody NotifyBindDeviceDTO body) {
		System.out.println("bindDevice ==> " + body);
		logger.error("bindDevice DateTime" + DateFuncs.dateTimeToStr(new Date(), DateFuncs.format2) + body); 
	}

	/**
	 * 订阅设备信息变化通知后（订阅的通知类型为deviceInfoChanged），设备信息发生变化时平台会给第三方应用推送设备信息变化通知。
	 * 
	 * @param notifyType
	 *            通知类型，取值：deviceInfoChanged。
	 * @param deviceId
	 *            设备ID，用于唯一标识一个设备。
	 * @param gatewayId
	 *            网关ID，用于唯一标识一个网关设备。
	 * @param service
	 *            设备信息，具体参见DeviceInfo结构体。
	 */
/*	@RequestMapping(value = "/deviceInfoChanged")
	public void deviceInfoChanged(@RequestBody NotifyDeviceInfoChangedDTO body) {
		System.out.println("deviceInfoChanged ==> " + body.toString());
		logger.error("deviceInfoChanged DateTime" + DateFuncs.dateTimeToStr(new Date(), DateFuncs.format2)
				+ body.toString()); 
	}*/

	/**
	 * 订阅设备数据变化通知后（订阅的通知类型为deviceDataChanged），设备数据发生变化时平台会给第三方应用推送设备数据变化通知。
	 * 
	 * @param notifyType
	 *            通知类型，取值：deviceDataChanged。
	 * @param requestId
	 *            消息的序列号，唯一标识该消息。
	 * @param deviceId
	 *            设备ID，用于唯一标识一个设备。
	 * @param gatewayId
	 *            网关ID，用于唯一标识一个网关设备。
	 * @param service
	 *            设备的服务数据，具体参见DeviceServiceData结构体。
	 */
	@RequestMapping(value = "/deviceDataChanged")
	public void deviceDataChanged(@RequestBody NotifyDeviceDataChangedDTO body) {
		System.out.println("deviceDataChanged ==> " + body);
		logger.error("deviceDataChanged ==> " + body); 
	}

	/**
	 * 订阅设备数据批量变化通知后（订阅的通知类型为deviceDatasChanged），发生批量设备数据变化时，平台会给第三方应用推送设备数据批量变化通知。
	 * 
	 * @param notifyType通知类型，取值：deviceDatasChanged。
	 * @param requestId
	 *            消息的序列号，唯一标识该消息。
	 * @param deviceId
	 *            设备ID，用于唯一标识一个设备。
	 * @param gatewayId
	 *            网关ID，用于唯一标识一个网关设备。
	 * @param services
	 *            服务列表信息，具体参见DeviceServiceData结构体。
	 */
	@RequestMapping(value = "/deviceDatasChanged")
	public void deviceDatasChanged(@RequestBody NotifyDeviceDatasChangedDTO body) {
		System.out.println("deviceDatasChanged ==> " + body);
		logger.error(" deviceDatasChanged DateTime" + DateFuncs.dateTimeToStr(new Date(), DateFuncs.format2)
				+ body.toString()); 
	}

	/**
	 * 订阅设备服务信息变化通知后（订阅的通知类型为serviceInfoChanged），设备服务信息发生变化时平台会给第三方应用推送设备服务信息变化通知。
	 * 
	 * @param notifyType
	 *            通知类型，取值：serviceInfoChanged。
	 * @param deviceId
	 *            设备ID，用于唯一标识一个设备。
	 * @param gatewayId
	 *            网关ID，用于唯一标识一个网关设备。
	 * @param serviceId
	 *            设备服务标识。
	 * @param serviceType
	 *            设备服务类型。
	 * @param serviceInfo
	 *            设备服务信息，增量上报，具体参见ServiceInfo结构体。
	 */
	@RequestMapping(value = "/serviceInfoChanged")
	public void serviceInfoChanged(@RequestBody NotifyServiceInfoChangedDTO body) {
		System.out.println("serviceInfoChanged ==> " + body);
		logger.error("deviceDatasChanged DateTime" + DateFuncs.dateTimeToStr(new Date(), DateFuncs.format2) + body); 
	}

	/**
	 * 订阅设备服务信息变化通知后（订阅的通知类型为serviceInfoChanged），设备服务信息发生变化时平台会给第三方应用推送设备服务信息变化通知。
	 * 
	 * @param notifyType
	 *            通知类型，取值：serviceInfoChanged。
	 * @param deviceId
	 *            设备ID，用于唯一标识一个设备。
	 * @param gatewayId
	 *            网关ID，用于唯一标识一个网关设备。
	 */
	@RequestMapping(value = "/deviceDeleted")
	public void deviceDeleted(@RequestBody NotifyDeviceDeletedDTO body) {
		System.out.println("deviceDeleted ==> " + body);
		logger.error("deviceDeleted DateTime" + DateFuncs.dateTimeToStr(new Date(), DateFuncs.format2) + body); 
	}

	/**
	 * 订阅设备消息确认通知后（订阅的通知类型为messageConfirm），设备向平台确认收到消息时，平台会给第三方应用推送设备消息确认通知。
	 * 
	 * @param notifyType
	 *            通知类型，取值：messageConfirm。
	 * @param Header
	 *            具体参见Header字段。
	 * @param body
	 *            根据业务具体定义，确认消息可以携带的状态变化等消息。
	 */
	@RequestMapping(value = "/messageConfirm")
	public void messageConfirm(@RequestBody NotifyMessageConfirmDTO body) {
		System.out.println("messageConfirm ==> " + body);
		logger.error("messageConfirm DateTime" + DateFuncs.dateTimeToStr(new Date(), DateFuncs.format2) + body); 
	}

	/**
	 * 订阅设备命令响应通知后（订阅的通知类型为commandRsp），设备发送命令响应消息给平台时，平台会给第三方应用推送设备命令响应通知。
	 * 
	 * @param notifyType
	 *            通知类型，取值：messageConfirm。
	 * @param Header
	 *            具体参见Header字段。
	 * @param body
	 *            根据业务具体定义，确认消息可以携带的状态变化等消息。
	 */
	@RequestMapping(value = "/commandRsp")
	public void commandRsp(@RequestBody NotifyCommandRspDTO body) {
		System.out.println("commandRsp ==> " + body);
		logger.error("commandRsp DateTime" + DateFuncs.dateTimeToStr(new Date(), DateFuncs.format2) + body); 
	}

	/**
	 * 订阅设备事件通知后（订阅的通知类型为deviceEvent），设备上报事件给平台时，平台会给第三方应用推送设备事件通知。
	 * 
	 * @param notifyType
	 *            通知类型，取值：messageConfirm。
	 * @param Header
	 *            具体参见Header字段。
	 * @param body
	 *            根据业务具体定义，确认消息可以携带的状态变化等消息。
	 */
	@RequestMapping(value = "/deviceEvent")
	public void deviceEvent(@RequestBody NotifyDeviceEventDTO body) {
		System.out.println("deviceEvent ==> " + body);
		logger.error("deviceEvent DateTime" + DateFuncs.dateTimeToStr(new Date(), DateFuncs.format2) + body); 
	}

	/**
	 * 订阅设备事件通知后（订阅的通知类型为deviceEvent），设备上报事件给平台时，平台会给第三方应用推送设备事件通知。
	 * 
	 * @param notifyType
	 *            通知类型，取值：deviceModelAdded。
	 * @param appId
	 *            第三方应用的身份标识，用于唯一标识一个应用。开发者可通过该标识来指定哪个应用来调用物联网平台的开放API。
	 * @param deviceType
	 *            设备的类型。
	 * @param manufacturerName
	 *            增加设备模型的操作者名称。
	 * @param manufacturerId
	 *            增加设备模型的操作者ID。
	 * @param model
	 *            设备型号。
	 * @param protocolType
	 *            设备使用的协议类型，当前支持的协议类型：CoAP，huaweiM2M，Z-Wave，ONVIF，WPS，Hue，WiFi，J808，Gateway，ZigBee，LWM2M。
	 */
	@RequestMapping(value = "/deviceModelAdded")
	public void deviceModelAdded(@RequestBody NotifyDeviceModelAddedDTO body) {
		System.out.println("deviceModelAdded ==> " + body);
		logger.error("deviceModelAdded DateTime" + DateFuncs.dateTimeToStr(new Date(), DateFuncs.format2) + body); 
	}

	/**
	 * 订阅设备模型删除通知后（订阅的通知类型为deviceModelDeleted），设备模型被删除时平台会给第三方应用推送设备模型删除通知。
	 * 
	 * @param notifyType
	 *            通知类型，取值：deviceModelDeleted。
	 * @param appId
	 *            第三方应用的身份标识，用于唯一标识一个应用。开发者可通过该标识来指定哪个应用来调用物联网平台的开放API。
	 * @param deviceType
	 *            设备的类型。
	 * @param manufacturerName
	 *            增加设备模型的操作者名称。
	 * @param manufacturerId
	 *            增加设备模型的操作者ID。
	 * @param model
	 *            设备型号。
	 * @param protocolType
	 *            设备使用的协议类型，当前支持的协议类型：CoAP，huaweiM2M，Z-Wave，ONVIF，WPS，Hue，WiFi，J808，Gateway，ZigBee，LWM2M。
	 */
	@RequestMapping(value = "/deviceModelDeleted")
	public void deviceModelDeleted(@RequestBody NotifyDeviceModelDeletedDTO body) {
		System.out.println("deviceModelDeleted ==> " + body);
		logger.error("deviceModelDeleted DateTime" + DateFuncs.dateTimeToStr(new Date(), DateFuncs.format2) + body); 
	}

	/**
	 * 订阅规则事件通知后（订阅的通知类型为ruleEvent），规则事件上报物联网平台时，平台会给第三方应用推送规则事件通知。
	 * 
	 * @param notifyType
	 *            通知类型，取值：deviceModelDeleted。
	 * @param author
	 *            创建此规则的用户的ID（最大长度256个字符）。
	 * @param ruleId
	 *            规则实例的ID
	 * @param ruleName
	 *            规则实例的名称。
	 * @param logic
	 *            多条件逻辑关系。
	 * @param reasons
	 *            触发原因，对应conditions，具体参考Reason结构体。
	 * @param triggerTime
	 *            规则触发的时间。
	 * @param actionsResults
	 *            规则动作执行的结果。
	 */
	@RequestMapping(value = "/ruleEvent")
	public void ruleEvent(@RequestBody NotifyRuleEventDTO body) {
		System.out.println("ruleEvent ==> " + body);
		logger.error("ruleEvent DateTime" + DateFuncs.dateTimeToStr(new Date(), DateFuncs.format2) + body); 
	}

	/**
	 * 第三方应用订阅了设备软件升级状态变化后（订阅的通知类型为swUpgradeStateChangeNotify），当设备进行软件升级时，软件升级状态发生变化，物联网平台通过此接口向第三方应用推送软件升级状态通知。
	 * 
	 * @param notifyType
	 *            通知类型，取值：swUpgradeStateChangeNotify。
	 * @param deviceId
	 *            设备ID，用于唯一标识一个设备。
	 * @param appId
	 *            设备所属应用的应用ID。
	 * @param operationId
	 *            软件升级任务ID。
	 * @param subOperationId
	 *            软件升级子任务ID。
	 * @param swUpgradeState
	 *            软件升级状态。 downloading：设备正在下载软件包 downloaded：设备下载软件包完成
	 *            updating：设备正在进行升级 idle：设备处于空闲状态
	 */
	@RequestMapping(value = "/swUpgradeStateChangeNotify")
	public void swUpgradeStateChangeNotify(@RequestBody NotifySwUpgradeStateChangedDTO body) {
		System.out.println("swUpgradeStateChanged ==> " + body);
		logger.error("swUpgradeStateChanged DateTime" + DateFuncs.dateTimeToStr(new Date(), DateFuncs.format2) + body); 
	}

	/**
	 * 第三方应用订阅了设备软件升级结果后（订阅的通知类型为swUpgradeResultNotify），当设备软件升级有了升级结果时，物联网平台通过此接口向第三方应用推送软件升级结果通知。
	 * 
	 * @param notifyType
	 *            通知类型，取值：swUpgradeResultNotify。
	 * @param deviceId
	 *            设备ID，用于唯一标识一个设备。
	 * @param appId
	 *            设备所属应用的应用ID。
	 * @param operationId
	 *            软件升级任务ID。
	 * @param subOperationId
	 *            软件升级子任务ID。
	 * @param curVersion
	 *            设备当前的软件版本。
	 * @param targetVersion
	 *            设备要升级的目标软件版本。
	 * @param sourceVersion
	 *            设备的源软件版本。
	 * @param swUpgradeResult
	 *            软件升级结果。 SUCCESS：设备升级成功 FAIL：设备升级失败
	 * @param upgradeTime
	 *            升级时长。
	 * @param resultDesc
	 *            升级结果描述。
	 * @param errorCode
	 *            设备上报的状态错误码。
	 * @param description
	 *            错误原因描述。
	 */
	@RequestMapping(value = "/swUpgradeResultNotify")
	public void swUpgradeResultNotify(@RequestBody NotifySwUpgradeResultDTO body) {
		System.out.println("swUpgradeResultNotify ==> " + body);
		logger.error("deviceDatasChanged DateTime" + DateFuncs.dateTimeToStr(new Date(), DateFuncs.format2) + body); 
	}

	/**
	 * 第三方应用订阅了设备固件升级状态变化后（订阅的通知类型为fwUpgradeStateChangeNotify），当设备进行固件升级时，固件升级状态发生变化，物联网平台通过此接口向第三方应用推送固件升级状态通知。
	 * 
	 * @param notifyType
	 *            通知类型，取值：swUpgradeStateChangeNotify。
	 * @param deviceId
	 *            设备ID，用于唯一标识一个设备。
	 * @param appId
	 *            设备所属应用的应用ID。
	 * @param operationId
	 *            软件升级任务ID。
	 * @param subOperationId
	 *            软件升级子任务ID。
	 * @param step
	 *            固件升级状态，可取值为0、1、2、3。
	 * @param stepDesc
	 *            固件升级状态。 1：downloading：设备正在下载软件包 2：downloaded：设备下载软件包完成
	 *            3：updating：设备正在进行升级 0：idle：设备处于空闲状态
	 */
	@RequestMapping(value = "/fwUpgradeStateChangeNotify")
	public void fwUpgradeStateChangeNotify(@RequestBody NotifyFwUpgradeStateChangedDTO body) {
		System.out.println("fwUpgradeStateChanged ==> " + body);
		logger.error(
				"fwUpgradeStateChangeNotify DateTime" + DateFuncs.dateTimeToStr(new Date(), DateFuncs.format2) + body); 
	}

	/**
	 * 第三方应用订阅了设备固件升级结果后（订阅的通知类型为fwUpgradeResultNotify），当设备固件升级有了升级结果时，物联网平台通过此接口向第三方应用推送固件升级结果通知。
	 * 
	 * @param notifyType
	 *            通知类型，取值：swUpgradeResultNotify。
	 * @param deviceId
	 *            设备ID，用于唯一标识一个设备。
	 * @param appId
	 *            设备所属应用的应用ID。
	 * @param operationId
	 *            软件升级任务ID。
	 * @param subOperationId
	 *            软件升级子任务ID。
	 * @param curVersion
	 *            设备当前的软件版本。
	 * @param targetVersion
	 *            设备要升级的目标软件版本。
	 * @param sourceVersion
	 *            设备的源软件版本。
	 * @param status
	 *            软件升级结果。 SUCCESS FAIL
	 * @param statusDesc
	 *            升级结果描述。 SUCCESS：设备升级成功 FAIL：设备升级失败
	 * @param upgradeTime
	 *            固件升级时长。
	 */
	@RequestMapping(value = "/fwUpgradeResultNotify")
	public void fwUpgradeResultNotify(@RequestBody NotifyFwUpgradeResultDTO body) {
		System.out.println("fwUpgradeResultNotify ==> " + body);
		logger.error("fwUpgradeResultNotify DateTime" + DateFuncs.dateTimeToStr(new Date(), DateFuncs.format2) + body); 
	}

	@RequestMapping(value = "/NBCommandStateChanged")
	public void NBCommandStateChanged(@RequestBody NotifyNBCommandStatusChangedDTO body) {
		System.out.println("NBCommandStateChanged ==> " + body);
		logger.error("NBCommandStateChanged DateTime" + DateFuncs.dateTimeToStr(new Date(), DateFuncs.format2) + body); 
	}
  
}
