package com.oty.util;

import java.util.HashMap;
import java.util.List; 

import org.springframework.beans.factory.annotation.Value;

import com.iotplatform.client.NorthApiClient;
import com.iotplatform.client.NorthApiException;
import com.iotplatform.client.dto.AuthOutDTO;
import com.iotplatform.client.dto.BatchTaskCreateInDTO2;
import com.iotplatform.client.dto.BatchTaskCreateOutDTO;
import com.iotplatform.client.dto.ClientInfo;
import com.iotplatform.client.dto.CoordinateReferenceSystem;
import com.iotplatform.client.dto.CreateDeviceGroupInDTO;
import com.iotplatform.client.dto.CreateDeviceGroupOutDTO;
import com.iotplatform.client.dto.CreateUpgradeTaskInDTO;
import com.iotplatform.client.dto.CreateUpgradeTaskOutDTO;
import com.iotplatform.client.dto.DeviceGroupWithDeviceListDTO;
import com.iotplatform.client.dto.GroupExpress2;
import com.iotplatform.client.dto.ModifyDeviceGroupInDTO;
import com.iotplatform.client.dto.ModifyDeviceGroupOutDTO;
import com.iotplatform.client.dto.ModifyDeviceInforInDTO;
import com.iotplatform.client.dto.ModifyDeviceShadowInDTO;
import com.iotplatform.client.dto.OperateDevices;
import com.iotplatform.client.dto.OperatePolicy;
import com.iotplatform.client.dto.QueryBatchDevicesInfoInDTO;
import com.iotplatform.client.dto.QueryBatchDevicesInfoOutDTO;
import com.iotplatform.client.dto.QueryDeviceCapabilitiesInDTO;
import com.iotplatform.client.dto.QueryDeviceCapabilitiesOutDTO;
import com.iotplatform.client.dto.QueryDeviceDataHistoryInDTO;
import com.iotplatform.client.dto.QueryDeviceDataHistoryOutDTO;
import com.iotplatform.client.dto.QueryDeviceDesiredHistoryInDTO;
import com.iotplatform.client.dto.QueryDeviceDesiredHistoryOutDTO;
import com.iotplatform.client.dto.QueryDeviceGroupMembersInDTO;
import com.iotplatform.client.dto.QueryDeviceGroupMembersOutDTO;
import com.iotplatform.client.dto.QueryDeviceGroupsInDTO;
import com.iotplatform.client.dto.QueryDeviceGroupsOutDTO;
import com.iotplatform.client.dto.QueryDeviceRealtimeLocationInDTO;
import com.iotplatform.client.dto.QueryDeviceRealtimeLocationOutDTO;
import com.iotplatform.client.dto.QueryDeviceShadowOutDTO;
import com.iotplatform.client.dto.QueryDeviceStatusOutDTO;
import com.iotplatform.client.dto.QueryOneTaskOutDTO;
import com.iotplatform.client.dto.QueryRulesInDTO2;
import com.iotplatform.client.dto.QuerySingleDeviceGroupOutDTO;
import com.iotplatform.client.dto.QuerySingleDeviceInfoOutDTO;
import com.iotplatform.client.dto.QueryTaskDetailsInDTO;
import com.iotplatform.client.dto.QueryTaskDetailsOutDTO;
import com.iotplatform.client.dto.QueryUpgradePackageListInDTO;
import com.iotplatform.client.dto.QueryUpgradePackageListOutDTO;
import com.iotplatform.client.dto.QueryUpgradePackageOutDTO;
import com.iotplatform.client.dto.QueryUpgradeSubTaskInDTO;
import com.iotplatform.client.dto.QueryUpgradeSubTaskOutDTO;
import com.iotplatform.client.dto.QueryUpgradeTaskListInDTO;
import com.iotplatform.client.dto.QueryUpgradeTaskListOutDTO;
import com.iotplatform.client.dto.QueryUpgradeTaskOutDTO;
import com.iotplatform.client.dto.RefreshDeviceKeyInDTO;
import com.iotplatform.client.dto.RefreshDeviceKeyOutDTO;
import com.iotplatform.client.dto.RegDirectDeviceInDTO2;
import com.iotplatform.client.dto.RegDirectDeviceOutDTO;
import com.iotplatform.client.dto.RuleCreateOrUpdateOutDTO;
import com.iotplatform.client.dto.RuleDTO2;
import com.iotplatform.client.dto.RulePreProcessorDTO;
import com.iotplatform.client.dto.ServiceDesiredDTO;
import com.iotplatform.client.dto.TagDTO2;
import com.iotplatform.client.dto.TimeRange;
import com.iotplatform.client.dto.UpdateBatchRuleStatusInDTO;
import com.iotplatform.client.dto.UpdateBatchRuleStatusOutDTO;
import com.iotplatform.client.dto.UpdateRuleStatusInDTO;
import com.iotplatform.client.invokeapi.Authentication;
import com.iotplatform.client.invokeapi.BatchProcess;
import com.iotplatform.client.invokeapi.DataCollection;
import com.iotplatform.client.invokeapi.DeviceGroupManagement;
import com.iotplatform.client.invokeapi.DeviceManagement;
import com.iotplatform.client.invokeapi.DeviceUpgrade;
import com.iotplatform.client.invokeapi.RuleEngine;

import pub.functions.PropsFuncs;

public class TianyiAPI {

	public static HashMap<String, String> errorMap = new HashMap<String, String>();

	static {
		errorMap.put("50204", "数据格式转换失败");
		errorMap.put("100001", "服务内部处理错误");
		errorMap.put("100002", "错误的token信息");
		errorMap.put("100003", "验证码无效");
		errorMap.put("100006", "accessToken刷新失败");
		errorMap.put("100007", "参数不合法");
		errorMap.put("100008", "空的haproxy地址");
		errorMap.put("100022", "输入参数无效");
		errorMap.put("100023", "数据库异常");
		errorMap.put("100025", "获取不到appId鉴权信息");
		errorMap.put("100028", "用户没有操作权限");
		errorMap.put("100203", "授权应用不存在");
		errorMap.put("100208", "appId或secret错误");
		errorMap.put("100216", "请求参数不合法");
		errorMap.put("100217", "应用未被授权");
		errorMap.put("100220", "获取appKey失败");
		errorMap.put("100222", "回调地址非法");
		errorMap.put("100223", "缓存的命令数已到达限制。处于PENDING状态的命令条数不超过限定值。默认限定值为20");
		errorMap.put("100234", "回调地址与应用现有推送回调地址冲突");
		errorMap.put("100403", "设备不存在");
		errorMap.put("100412", "当前应用下设备数量达到上限");
		errorMap.put("100416", "设备已经绑定");
		errorMap.put("100418", "设备信息不存在");
		errorMap.put("100419", "deviceId和gatewayId不能同时为空");
		errorMap.put("100426", "nodeId重复");
		errorMap.put("100431", "服务类型不存在");
		errorMap.put("100434", "命令不存在");
		errorMap.put("100435", "命令已撤销、过期或执行，无法撤销");
		errorMap.put("100601", "设备组内设备达到最大值");
		errorMap.put("100602", "设备组名字已经存在");
		errorMap.put("100603", "设备组不存在");
		errorMap.put("100604", "请求参数不合法");
		errorMap.put("100607", "设备组数目达到限制");
		errorMap.put("100609", "添加太多设备至设备组");
		errorMap.put("100610", "设备未激活");
		errorMap.put("101016", "获取iotws地址失败");
		errorMap.put("101017", "从oss获取新的回调地址失败");
		errorMap.put("101401", "接口参数输入不合法");
		errorMap.put("101402", "规则名称已存在");
		errorMap.put("101403", "规则不存在");
		errorMap.put("101404", "应用下的规则数量超出上限，当前上限为30");
		errorMap.put("101406", "condition参数不合法");
		errorMap.put("101409", "鉴权失败");
		errorMap.put("101417", "condition id重复");
		errorMap.put("101418", "Action id重复");
		errorMap.put("101419", "ruleId不能为空");
		errorMap.put("101420", "condition中携带的deviceId所对应的设备不存在");
		errorMap.put("101421", "acition中携带的deviceId所对应的设备不存在");
		errorMap.put("101422", "condition中携带的deviceId所对应的设备数据不存在");
		errorMap.put("101423", "action中携带的deviceId所对应的设备数据不存在");
		errorMap.put("101424", "创建规则异常");
		errorMap.put("101425", "接口参数输入不合法");
		errorMap.put("103026", "License不存在");
		errorMap.put("105001", "未完成的任务数大于等于10个时，返回任务达到数量限制");
		errorMap.put("105002", "任务名字已存在");
		errorMap.put("105005", "任务不存在");
		errorMap.put("120001", "服务器运行内部错误");
		errorMap.put("120015", "请求错误");
		errorMap.put("120203", "应用不存在");
		errorMap.put("123001", "请求错误");
		errorMap.put("123002", "设备或包不存在");
		errorMap.put("123009", "查询的任务不存在");
		errorMap.put("123016", "参数不正确，目标版本与指定设备不匹配");
		errorMap.put("123019", "厂商名称内容为空");
		errorMap.put("123020", "设备类型内容为空");
		errorMap.put("123021", "设备型号内容为空");
		errorMap.put("123022", "deviceGroups和devices不能同时为空");
		errorMap.put("123023", "deviceGroups和devices不能同时存在");
		errorMap.put("123024", "deviceGroups或devices内容数量达到上限");
		errorMap.put("123029", "参数pageNo或pageSize超出了限制范围");
		errorMap.put("123027", "部分设备组不存在");
		errorMap.put("200002", "资源冲突，通知类型已经被订阅");
		errorMap.put("200001", "找不到资源");
	}

	private static NorthApiClient northApiClient = null;

	public static NorthApiClient initApiClient() {
		if (northApiClient != null) {
			return northApiClient;
		}
		northApiClient = new NorthApiClient();
		ClientInfo clientInfo = new ClientInfo();
		clientInfo.setPlatformIp(PropsFuncs.getProperty("/iot.properties", "platformIp"));
		clientInfo.setPlatformPort(PropsFuncs.getProperty("/iot.properties", "platformPort"));
		clientInfo.setAppId(PropsFuncs.getProperty("/iot.properties", "appId"));
		clientInfo.setSecret(PropsFuncs.getProperty("/iot.properties", "appSecret"));
		try {
			northApiClient.setClientInfo(clientInfo);
			northApiClient.initSSLConfig();
		} catch (NorthApiException e) {
			System.out.println(e.toString());
		}
		return northApiClient;
	}

	/**
	 * 3.2.1 注册直连设备
	 * 第三方应用调用此接口注册设备，并获取设备的验证码，在直连设备接入物联网平台时携带验证码，获取设备ID和密码。支持注册本应用和授权应用的设备。
	 * 
	 * @param verifyCode
	 *            必选 设备验证码。 在NB-IoT方案中，verifyCode为必填参数，且必须与nodeId设置成相同值。
	 * @param nodeId
	 *            必选 设备的唯一标识，必须与设备上报的设备标识一致。通常使用IMEI作为nodeId。
	 *            使用IMEI作为nodeId时，根据不同厂家的芯片有不同填写要求。
	 *            高通芯片设备的唯一标识为urn:imei:xxxx，xxxx为IMEI号 海思芯片设备的唯一标识为IMEI号
	 *            其他厂家芯片的设备唯一标识请联系模组厂家确认。
	 * @param endUserId
	 *            可选 终端用户ID。 在NB-IoT方案中，endUserId设置为设备的IMSI号。
	 * @param psk
	 *            可选
	 *            请求中指定psk，则平台使用指定的psk；请求中不指定psk，则由平台生成psk。取值范围是a-f、A-F、0-9组成的字符串。
	 * @param timeout
	 *            可选 设备验证码有效期，单位秒，默认值180s。 值为0时，表示验证码则永不过期。
	 *            值为大于等于0的整数时，表示在指定时间内设备进行绑定，超过时间验证码无效 ，同时注册的设备会被删除。
	 * @param isSecure
	 *            指定设备是否为安全设备，默认值为false。 true：安全设备 false：非安全设备
	 * @return
	 * @throws NorthApiException
	 */
	public static RegDirectDeviceOutDTO regDirectDevice(String verifyCode, String nodeId, String endUserId, String psk,
			@Value("180") Integer timeout, boolean isSecure) throws NorthApiException {
		NorthApiClient northApiClient = initApiClient();
		DeviceManagement deviceManagement = new DeviceManagement(northApiClient);

		Authentication authentication = new Authentication(northApiClient);
		AuthOutDTO authOutDTO = authentication.getAuthToken();
		String accessToken = authOutDTO.getAccessToken();

		System.out.println("======register a new device======");
		RegDirectDeviceInDTO2 rddid = new RegDirectDeviceInDTO2(); 
		rddid.setNodeId(nodeId);
		rddid.setVerifyCode(verifyCode);
		rddid.setTimeout(timeout);
		RegDirectDeviceOutDTO rddod = deviceManagement.regDirectDevice(rddid, null, accessToken);
		System.out.println(rddod.toString());
		if (rddod != null) {
			String deviceId = rddod.getDeviceId();
			System.out.println("\n======deviceId======" + deviceId);
		}
		return rddod;
	}

	/**
	 * 3.2.2 刷新设备密钥 第三方应用需要重新绑定原设备或者新设备的时候，可以调用此接口刷新nodeId和验证码。
	 * 
	 * @param verifyCode
	 *            必选 设备验证码。 在NB-IoT方案中，verifyCode为必填参数，且必须与nodeId设置成相同值。
	 * @param nodeId
	 *            必选 设备的唯一标识，必须与设备上报的设备标识一致。通常使用IMEI作为nodeId。
	 *            使用IMEI作为nodeId时，根据不同厂家的芯片有不同填写要求。
	 *            高通芯片设备的唯一标识为urn:imei:xxxx，xxxx为IMEI号 海思芯片设备的唯一标识为IMEI号
	 *            其他厂家芯片的设备唯一标识请联系模组厂家确认。
	 * @param timeout
	 *            可选 设备验证码有效期，单位秒，默认值180s。 值为0时，表示验证码则永不过期。
	 *            值为大于等于0的整数时，表示在指定时间内设备进行绑定，超过时间验证码无效 ，同时注册的设备会被删除。
	 * @return
	 * @throws NorthApiException
	 */
	public static RefreshDeviceKeyOutDTO refreshDeviceKey(String deviceId, String verifyCode, String nodeId,
			Integer timeout, String appId) throws NorthApiException {
		NorthApiClient northApiClient = initApiClient();
		DeviceManagement deviceManagement = new DeviceManagement(northApiClient);

		Authentication authentication = new Authentication(northApiClient);
		AuthOutDTO authOutDTO = authentication.getAuthToken();
		String accessToken = authOutDTO.getAccessToken();

		RefreshDeviceKeyInDTO rdkInDTO = new RefreshDeviceKeyInDTO();
		rdkInDTO.setNodeId(nodeId);
		rdkInDTO.setVerifyCode(verifyCode);
		rdkInDTO.setTimeout(timeout);

		RefreshDeviceKeyOutDTO rdkOutDTO = deviceManagement.refreshDeviceKey(rdkInDTO, deviceId, appId, accessToken);
		System.out.println(rdkOutDTO.toString());
		return rdkOutDTO;
	}

	/**
	 * 3.2.3 修改设备信息 第三方应用调用此接口修改设备的基本信息，如设备类型、厂商信息、位置信息等。
	 * 
	 * @param deviceId
	 *            设备ID，用于唯一标识一个设备，在注册设备时由物联网平台分配获得。
	 * @param name
	 *            设备名称。
	 * @param endUser
	 *            终端用户，若为直连设备，则endUser可选；若为非直连设备，则endUser可以为null。
	 * @param mute
	 *            表示设备是否处于冻结状态，即设备上报数据时，平台是否会管理和保存。 TRUE：冻结状态 FALSE：非冻结状态
	 * @param manufacturerId
	 *            厂商ID，唯一标识一个厂商。
	 * @param manufacturerName
	 *            厂商名称。
	 * @param deviceType
	 *            设备类型，大驼峰命名方式，例如：MultiSensor、ContactSensor、Camera和WaterMeter。
	 *            在NB-IoT方案中，注册设备后必须修改设备类型，且要与profile中定义的保持一致。
	 * @param model
	 *            设备型号，由厂商定义。
	 * @param location
	 *            设备位置。
	 * @param protocolType
	 *            设备使用的协议类型，当前支持的协议类型：CoAP，huaweiM2M，Z-Wave，ONVIF，WPS，Hue，WiFi，J808，Gateway，ZigBee，LWM2M。
	 * @param deviceConfig
	 *            设备配置信息，具体参见DeviceConfigDTO 结构体。
	 * @param region
	 *            设备区域信息。
	 * @param organization
	 *            设备所属的组织信息。
	 * @param timezone
	 *            设备所在时区信息，使用时区编码，如北京时区对应的时区编码为Asia/Beijing。
	 * @param isSecure
	 *            指定设备的安全状态，默认值为false。 true：安全 false：非安全
	 * @param psk
	 *            psk参数，取值范围是a-f、A-F、0-9组成的字符串。
	 * @return
	 * @throws NorthApiException
	 */
	public static boolean modifyDeviceInfo(String deviceId, String name, String endUser, String mute,
			String manufacturerId, String manufacturerName, String deviceType, String model, String location,
			String protocolType, com.iotplatform.client.dto.DeviceConfigDTO deviceConfig, String region,
			String organization, String timezone, boolean isSecure, String psk) throws NorthApiException {
		NorthApiClient northApiClient = initApiClient();
		DeviceManagement deviceManagement = new DeviceManagement(northApiClient);

		Authentication authentication = new Authentication(northApiClient);
		AuthOutDTO authOutDTO = authentication.getAuthToken();
		String accessToken = authOutDTO.getAccessToken();

		ModifyDeviceInforInDTO mdiInDTO = new ModifyDeviceInforInDTO();
		mdiInDTO.setName(name);
		mdiInDTO.setEndUser(endUser);
		mdiInDTO.setMute(mute);
		mdiInDTO.setManufacturerId(manufacturerId);
		mdiInDTO.setManufacturerName(manufacturerName);
		mdiInDTO.setDeviceType(deviceType);
		mdiInDTO.setModel(model);
		mdiInDTO.setLocation(location);
		mdiInDTO.setProtocolType(protocolType);
		mdiInDTO.setDeviceConfig(deviceConfig);
		mdiInDTO.setRegion(region);
		mdiInDTO.setOrganization(organization);
		mdiInDTO.setTimezone(timezone);

		deviceManagement.modifyDeviceInfo(mdiInDTO, deviceId, null, accessToken);
		System.out.println("modify device info succeeded");

		return true;
	}

	/**
	 * 3.2.4 删除直连设备 支持第三方应用删除设备，支持删除本应用的设备和授予权限的其它应用的设备。
	 * 
	 * @param deviceId
	 *            设备ID，用于唯一标识一个设备，在注册设备时由物联网平台分配获得。
	 * @return
	 * @throws NorthApiException
	 */
	public static boolean deleteDirectDevice(String deviceId) throws NorthApiException {
		NorthApiClient northApiClient = initApiClient();
		DeviceManagement deviceManagement = new DeviceManagement(northApiClient);

		Authentication authentication = new Authentication(northApiClient);
		AuthOutDTO authOutDTO = authentication.getAuthToken();
		String accessToken = authOutDTO.getAccessToken();

		System.out.println("======delete device======");
		deviceManagement.deleteDirectDevice(deviceId, true, null, accessToken);
		System.out.println("delete device succeeded");
		return true;
	}

	/**
	 * 3.2.5 查询设备激活状态 支持第三方应用通过设备ID查询设备激活状态，支持查询本应用的设备和授予权限的其它应用的设备。
	 * 
	 * @param deviceId
	 *            设备ID，用于唯一标识一个设备，在注册设备时由物联网平台分配获得。
	 * @return
	 * @throws NorthApiException
	 */
	public static QueryDeviceStatusOutDTO queryDeviceStatus(String deviceId) throws NorthApiException {
		NorthApiClient northApiClient = initApiClient();
		DeviceManagement deviceManagement = new DeviceManagement(northApiClient);

		Authentication authentication = new Authentication(northApiClient);
		AuthOutDTO authOutDTO = authentication.getAuthToken();
		String accessToken = authOutDTO.getAccessToken();

		System.out.println("======query device status======");
		QueryDeviceStatusOutDTO qdsOutDTO = deviceManagement.queryDeviceStatus(deviceId, null, accessToken);
		System.out.println(qdsOutDTO.toString());
		return qdsOutDTO;
	}

	/**
	 * 3.2.6 查询设备实时位置信息
	 * 
	 * 物联网平台向GMLC服务器请求设备的位置信息，GMLC下发查询位置信息命令给设备，设备计算得到位置信息后上报给GMLC服务器，GMLC服务器返回设备的位置信息给物联网平台。
	 * 
	 * @param deviceId
	 *            设备ID，用于唯一标识一个设备，在注册设备时由物联网平台分配获得。
	 * @param horAcc
	 *            水平误差，单位：米，如果不携带，则默认1000米。
	 * @param geoInfo
	 *            地理坐标信息要求，默认WGS84，其他坐标暂不支持。具体参见CoordinateReferenceSystem结构体。
	 * @return
	 * @throws NorthApiException
	 */
	public static QueryDeviceRealtimeLocationOutDTO queryDeviceRealtimeLocation(String deviceId, Integer horAcc,
			CoordinateReferenceSystem geoInfo) throws NorthApiException {
		NorthApiClient northApiClient = initApiClient();
		DeviceManagement deviceManagement = new DeviceManagement(northApiClient);
		Authentication authentication = new Authentication(northApiClient);
		AuthOutDTO authOutDTO = authentication.getAuthToken();
		String accessToken = authOutDTO.getAccessToken();

		QueryDeviceRealtimeLocationInDTO qdrlInDTO = new QueryDeviceRealtimeLocationInDTO();
		qdrlInDTO.setDeviceId(deviceId);
		qdrlInDTO.setHorAcc(horAcc);
		qdrlInDTO.setGeoInfo(geoInfo);

		System.out.println("======query device location======");
		QueryDeviceRealtimeLocationOutDTO qdrlOutDTO = deviceManagement.queryDeviceRealtimeLocation(qdrlInDTO, null,
				accessToken);
		System.out.println(qdrlOutDTO.toString());
		return qdrlOutDTO;
	}

	/**
	 * 3.2.7 查询设备影子
	 * 
	 * 支持第三方应用查询设备影子信息，包括设备reported属性和应用/Portal给设备下发修改的desired属性信息。
	 * 
	 * @param deviceId
	 *            设备ID，用于唯一标识一个设备，在注册设备时由物联网平台分配获得
	 * @return
	 * @throws NorthApiException
	 */
	public static QueryDeviceShadowOutDTO queryDeviceShadow(String deviceId) throws NorthApiException {
		NorthApiClient northApiClient = initApiClient();
		DeviceManagement deviceManagement = new DeviceManagement(northApiClient);

		Authentication authentication = new Authentication(northApiClient);
		AuthOutDTO authOutDTO = authentication.getAuthToken();
		String accessToken = authOutDTO.getAccessToken();

		System.out.println("====== query device shadow ======");
		QueryDeviceShadowOutDTO qdsOutDTO = deviceManagement.queryDeviceShadow(deviceId, null, accessToken);
		System.out.println(qdsOutDTO.toString());
		return qdsOutDTO;
	}

	/**
	 * 3.2.8 修改设备影子
	 * 
	 * 支持第三方应用修改设备影子属性，包括设备的配置或状态数据（desired属性）信息。
	 * 
	 * @param deviceId
	 *            设备ID，用于唯一标识一个设备，在注册设备时由物联网平台分配获得。
	 * @param serviceDesireds
	 *            需要修改的设备配置或状态信息。ServiceDesiredDTO见下表。
	 * @return
	 * @throws NorthApiException
	 */
	public static boolean modifyDeviceShadow(String deviceId, List<ServiceDesiredDTO> serviceDesireds)
			throws NorthApiException {
		NorthApiClient northApiClient = initApiClient();
		DeviceManagement deviceManagement = new DeviceManagement(northApiClient);

		Authentication authentication = new Authentication(northApiClient);
		AuthOutDTO authOutDTO = authentication.getAuthToken();
		String accessToken = authOutDTO.getAccessToken();

		ModifyDeviceShadowInDTO mdsInDTO = new ModifyDeviceShadowInDTO();
		mdsInDTO.setServiceDesireds(serviceDesireds);

		System.out.println("====== modify device shadow ======");
		deviceManagement.modifyDeviceShadow(mdsInDTO, deviceId, null, accessToken);
		return true;
	}

	/**
	 * 3.3.1 创建批量任务
	 * 
	 * 支持第三方应用对设备创建批量任务。支持给本应用和授予权限的其它应用创建批量任务。
	 * 
	 * @param timeout
	 *            任务超时时长，单位秒，范围为10到2880。
	 * @param taskName
	 *            任务名称，最大长度256字符
	 * @param taskType
	 *            任务类型，DeviceReg/DeviceCmd，见下方param参数结构说明。
	 * @param param
	 *            任务详细参数，根据taskType任务类型的不同对应不同类型参数，具体参见param参数。
	 * @param tags
	 *            标签列表，请参见TagDTO2结构体说明。
	 * @return
	 * @throws NorthApiException
	 */
	public static BatchTaskCreateOutDTO createBatchTask(Integer timeout, String taskName, String taskType, Object param,
			List<TagDTO2> tags) throws NorthApiException {
		NorthApiClient northApiClient = initApiClient();
		BatchProcess batchProcess = new BatchProcess(northApiClient);

		Authentication authentication = new Authentication(northApiClient);
		AuthOutDTO authOutDTO = authentication.getAuthToken();
		String accessToken = authOutDTO.getAccessToken();

		BatchTaskCreateInDTO2 btcInDTO = new BatchTaskCreateInDTO2();
		btcInDTO.setParam(param);
		btcInDTO.setTags(tags);
		btcInDTO.setTimeout(timeout);
		btcInDTO.setTaskName(taskName);

		System.out.println("====== create device BatchTask ======");
		BatchTaskCreateOutDTO btcOutDTO = batchProcess.createBatchTask(btcInDTO, accessToken);
		return btcOutDTO;
	}

	/**
	 * 3.3.2 查询单个任务信息
	 * 
	 * 支持第三方应用查询单个批量任务的信息，支持查询本应用和授予权限的其它应用创建的批量任务信息。
	 * 
	 * @param taskId
	 *            批量任务ID，创建批量任务后获得。
	 * @param select
	 *            指定可选的返回值，可取值：tag。不指定时可填写null。
	 * @param appId
	 *            如果是本应用的任务，此参数值可以填写null，否则填写授权应用的appId
	 * @return
	 * @throws NorthApiException
	 */
	public static QueryOneTaskOutDTO queryOneTask(String taskId, String select, String appId) throws NorthApiException {
		NorthApiClient northApiClient = initApiClient();
		BatchProcess batchProcess = new BatchProcess(northApiClient);

		Authentication authentication = new Authentication(northApiClient);
		AuthOutDTO authOutDTO = authentication.getAuthToken();
		String accessToken = authOutDTO.getAccessToken();

		System.out.println("====== query device OneTask ======");
		QueryOneTaskOutDTO qotOutDTO = batchProcess.queryOneTask(taskId, select, appId, accessToken);
		return qotOutDTO;
	}

	/**
	 * 3.3.3 查询任务详情信息
	 * 
	 * 支持第三方应用查询批量任务中某个任务的详情信息，支持查询本应用和授予权限的其它应用创建的批量任务信息。
	 * 
	 * @param taskId
	 *            批量任务的ID。
	 * @param status
	 *            任务的详情状态，Pending/Success/Fail/Timeout。
	 * @param index
	 *            批量任务文件里第几行的任务，查询批量注册任务时使用。
	 * @param nodeId
	 *            设备nodeId，查询批量注册任务时使用。
	 * @param deviceId
	 *            设备Id，查询批量命令任务时使用。
	 * @param commandId
	 *            命令Id，查询批量命令任务时使用。
	 * @param pageNo
	 *            分页查询参数。 值为空时，查询内容不分页。 值为大于等于0的整数时，分页查询。 值为0时查询第一页。
	 * @param pageSize
	 *            分页查询参数，取值大于等于1的整数，缺省值：1。
	 * @param appId
	 *            如果是本应用的任务，此参数值可以填写null，否则填写授权应用的appId。
	 * @return
	 * @throws NorthApiException
	 */
	public static QueryTaskDetailsOutDTO queryTaskDetails(String taskId, String status, Integer index, String nodeId,
			String deviceId, String commandId, Integer pageNo, Integer pageSize, String appId)
			throws NorthApiException {
		NorthApiClient northApiClient = initApiClient();
		BatchProcess batchProcess = new BatchProcess(northApiClient);

		Authentication authentication = new Authentication(northApiClient);
		AuthOutDTO authOutDTO = authentication.getAuthToken();
		String accessToken = authOutDTO.getAccessToken();

		QueryTaskDetailsInDTO qtdInDTO = new QueryTaskDetailsInDTO();
		qtdInDTO.setTaskId(taskId);
		qtdInDTO.setStatus(status);
		qtdInDTO.setIndex(index);
		qtdInDTO.setDeviceId(deviceId);
		qtdInDTO.setCommandId(commandId);
		qtdInDTO.setPageNo(pageNo);
		qtdInDTO.setPageSize(pageSize);
		qtdInDTO.setAppId(appId);

		System.out.println("====== query device OneTask ======");
		QueryTaskDetailsOutDTO qtdOutDTO = batchProcess.queryTaskDetails(qtdInDTO, accessToken);
		return qtdOutDTO;
	}

	/**
	 * 3.4.1 创建规则
	 * 
	 * 支持第三方应用创建规则，使用物联网平台的规则引擎能力。
	 * 
	 * @param ruleId
	 *            规则实例的ID，仅在规则更新时有效，创建规则时不填写。
	 * @param appId
	 *            appId，第三方应用的身份标识，用于唯一标识一个应用。开发者可通过该标识来指定哪个应用来调用物联网平台的开放API。appid在物联网平台的SP
	 *            Portal上创建应用时获得
	 * @param name
	 *            规则名称。
	 * @param description
	 *            规则描述。
	 * @param author
	 *            创建此规则的用户的ID。
	 * @param conditions
	 *            条件列表，与groupExpress二选一必填，具体参见ConditionDeviceData结构体，ConditionDeviceGroupData结构体，ConditionDeviceTypeData结构体，ConditionDailyTimer结构体，ConditionCycleTimer结构体和ConditionNoDetected结构体。
	 * @param logic
	 *            多条件之间的逻辑关系，支持and和or，默认为and。
	 * @param timeRange
	 *            条件场景的时间段，具体参见TimeRange结构体。
	 * @param actions
	 *            满足规则所执行的动作，具体参见ActionDeviceCMD结构体，ActionSMS结构体，ActionEmail结构体，ActionDelay结构体，ActionRule结构体，ActionDeviceAlarm结构体和ActionEiCMD结构体。
	 * @param matchNow
	 *            表示是否立即触发，即是否立即进行规则条件判断，条件符合的话执行动作。 yes：表示立即触发 no：表示不触发
	 * @param status
	 *            规则的状态，默认为“active”状态。 active代表激活状态 inactive代表未激活
	 * @param groupExpress
	 *            复杂多条件表达式，具体参见GroupExpress结构体，与conidtions二选一必填。
	 * @param timezoneID
	 *            时区ID。 若为空，使用UTC时间 若不为空，使用本地时间
	 * @param triggerSources
	 *            触发源列表，只与GroupExpress联用构造复杂多条件规则，目前有DEVICE类型和TIMER类型，具体参见TriggerSourceDevice结构体和TriggerSourceTimer结构体。
	 * @param executor
	 *            规则执行主体，当action和condition里面的设备在同一个网关下面时，取值网关ID（代表网关执行）；否则取定值“cloud”（代表云端执行）。
	 * @param transData
	 *            平台不需要识别的数据，只做保存。
	 * @param refreshId
	 *            创建规则时是否检查ruleId不能为空标识，默认为true。
	 * @param checkNullAction
	 *            创建规则时是否检查Action不能为空标识，默认为true。
	 * @param priority
	 *            规则优先级，保留字段。
	 * @param tags
	 *            标签列表，具体参见TagDTO2结构体。
	 * @param rulePreProcessors
	 *            规则的预处理器，不符合预处理条件的规则不执行，具体参见RulePreProcessorDTO结构体。
	 * @return
	 * @throws NorthApiException
	 */
	public static RuleCreateOrUpdateOutDTO createRule(String ruleId, String appId, String name, String description,
			String author, List<Object> conditions, String logic, TimeRange timeRange, List<Object> actions,
			String matchNow, String status, GroupExpress2 groupExpress, String timezoneID, List<Object> triggerSources,
			String executor, Object transData, Boolean refreshId, Boolean checkNullAction, String priority,
			List<TagDTO2> tags, List<RulePreProcessorDTO> rulePreProcessors) throws NorthApiException {
		NorthApiClient northApiClient = initApiClient();
		RuleEngine ruleEngine = new RuleEngine(northApiClient);

		Authentication authentication = new Authentication(northApiClient);
		AuthOutDTO authOutDTO = authentication.getAuthToken();
		String accessToken = authOutDTO.getAccessToken();

		RuleDTO2 ruleDTO = new RuleDTO2();
		ruleDTO.setRuleId(ruleId);
		ruleDTO.setName(name);
		ruleDTO.setDescription(description);
		ruleDTO.setAuthor(author);
		ruleDTO.setConditions(conditions);
		ruleDTO.setLogic(logic);
		ruleDTO.setTimeRange(timeRange);
		ruleDTO.setActions(actions);
		ruleDTO.setMatchNow(matchNow);
		ruleDTO.setStatus(status);
		ruleDTO.setGroupExpress(groupExpress);
		ruleDTO.setTimezoneID(timezoneID);
		ruleDTO.setTriggerSources(triggerSources);
		ruleDTO.setTags(tags);
		ruleDTO.setRulePreProcessors(rulePreProcessors);

		System.out.println("====== create device rule ======");
		RuleCreateOrUpdateOutDTO rcouOutDTO = ruleEngine.createRule(ruleDTO, appId, accessToken);
		System.out.println(rcouOutDTO.toString());
		return rcouOutDTO;
	}

	/**
	 * 3.4.2 更新规则
	 * 
	 * 支持第三方应用创建规则，使用物联网平台的规则引擎能力。
	 * 
	 * @param ruleId
	 *            规则实例的ID，仅在规则更新时有效，创建规则时不填写。
	 * @param appId
	 *            appId，第三方应用的身份标识，用于唯一标识一个应用。开发者可通过该标识来指定哪个应用来调用物联网平台的开放API。appid在物联网平台的SP
	 *            Portal上创建应用时获得
	 * @param name
	 *            规则名称。
	 * @param description
	 *            规则描述。
	 * @param author
	 *            创建此规则的用户的ID。
	 * @param conditions
	 *            条件列表，与groupExpress二选一必填，具体参见ConditionDeviceData结构体，ConditionDeviceGroupData结构体，ConditionDeviceTypeData结构体，ConditionDailyTimer结构体，ConditionCycleTimer结构体和ConditionNoDetected结构体。
	 * @param logic
	 *            多条件之间的逻辑关系，支持and和or，默认为and。
	 * @param timeRange
	 *            条件场景的时间段，具体参见TimeRange结构体。
	 * @param actions
	 *            满足规则所执行的动作，具体参见ActionDeviceCMD结构体，ActionSMS结构体，ActionEmail结构体，ActionDelay结构体，ActionRule结构体，ActionDeviceAlarm结构体和ActionEiCMD结构体。
	 * @param matchNow
	 *            表示是否立即触发，即是否立即进行规则条件判断，条件符合的话执行动作。 yes：表示立即触发 no：表示不触发
	 * @param status
	 *            规则的状态，默认为“active”状态。 active代表激活状态 inactive代表未激活
	 * @param groupExpress
	 *            复杂多条件表达式，具体参见GroupExpress结构体，与conidtions二选一必填。
	 * @param timezoneID
	 *            时区ID。 若为空，使用UTC时间 若不为空，使用本地时间
	 * @param triggerSources
	 *            触发源列表，只与GroupExpress联用构造复杂多条件规则，目前有DEVICE类型和TIMER类型，具体参见TriggerSourceDevice结构体和TriggerSourceTimer结构体。
	 * @param executor
	 *            规则执行主体，当action和condition里面的设备在同一个网关下面时，取值网关ID（代表网关执行）；否则取定值“cloud”（代表云端执行）。
	 * @param transData
	 *            平台不需要识别的数据，只做保存。
	 * @param refreshId
	 *            创建规则时是否检查ruleId不能为空标识，默认为true。
	 * @param checkNullAction
	 *            创建规则时是否检查Action不能为空标识，默认为true。
	 * @param priority
	 *            规则优先级，保留字段。
	 * @param tags
	 *            标签列表，具体参见TagDTO2结构体。
	 * @param rulePreProcessors
	 *            规则的预处理器，不符合预处理条件的规则不执行，具体参见RulePreProcessorDTO结构体。
	 * @return
	 * @throws NorthApiException
	 */
	public static RuleCreateOrUpdateOutDTO updateRule(String ruleId, String appId, String name, String description,
			String author, List<Object> conditions, String logic, TimeRange timeRange, List<Object> actions,
			String matchNow, String status, GroupExpress2 groupExpress, String timezoneID, List<Object> triggerSources,
			String executor, Object transData, Boolean refreshId, Boolean checkNullAction, String priority,
			List<TagDTO2> tags, List<RulePreProcessorDTO> rulePreProcessors) throws NorthApiException {
		NorthApiClient northApiClient = initApiClient();
		RuleEngine ruleEngine = new RuleEngine(northApiClient);

		Authentication authentication = new Authentication(northApiClient);
		AuthOutDTO authOutDTO = authentication.getAuthToken();
		String accessToken = authOutDTO.getAccessToken();

		RuleDTO2 ruleDTO = new RuleDTO2();
		ruleDTO.setRuleId(ruleId);
		ruleDTO.setName(name);
		ruleDTO.setDescription(description);
		ruleDTO.setAuthor(author);
		ruleDTO.setConditions(conditions);
		ruleDTO.setLogic(logic);
		ruleDTO.setTimeRange(timeRange);
		ruleDTO.setActions(actions);
		ruleDTO.setMatchNow(matchNow);
		ruleDTO.setStatus(status);
		ruleDTO.setGroupExpress(groupExpress);
		ruleDTO.setTimezoneID(timezoneID);
		ruleDTO.setTriggerSources(triggerSources);
		ruleDTO.setTags(tags);
		ruleDTO.setRulePreProcessors(rulePreProcessors);

		System.out.println("====== update device rule ======");
		RuleCreateOrUpdateOutDTO rcouOutDTO = ruleEngine.updateRule(ruleDTO, appId, accessToken);
		System.out.println(rcouOutDTO.toString());
		return rcouOutDTO;
	}

	/**
	 * 3.4.3 删除规则
	 * 
	 * 支持第三方应用删除指定ruleId的规则。
	 * 
	 * @param ruleId
	 *            规则的ID。
	 * @param appId
	 *            如果是本应用的规则，此参数值可以填写null，否则填写授权应用的appId。
	 * @return
	 * @throws NorthApiException
	 */
	public static boolean deleteRule(String ruleId, String appId) throws NorthApiException {
		NorthApiClient northApiClient = initApiClient();
		RuleEngine ruleEngine = new RuleEngine(northApiClient);

		Authentication authentication = new Authentication(northApiClient);
		AuthOutDTO authOutDTO = authentication.getAuthToken();
		String accessToken = authOutDTO.getAccessToken();

		System.out.println("====== delete device rule ======");
		ruleEngine.deleteRule(ruleId, appId, accessToken);
		return true;
	}

	/**
	 * 3.4.4 查找规则
	 * 
	 * 支持第三方应用查询规则的配置信息。
	 * 
	 * @param author
	 *            创建规则的用户。
	 * @param name
	 *            规则名称。
	 * @param appId
	 *            如果是本应用的规则，此参数值可以填写null，否则填写授权应用的appId。
	 * @param select
	 *            指定可选的返回值，可取值：tag。
	 * @return
	 * @throws NorthApiException
	 */
	public static List<RuleDTO2> queryRules(String author, String name, String appId, String select)
			throws NorthApiException {
		NorthApiClient northApiClient = initApiClient();
		RuleEngine ruleEngine = new RuleEngine(northApiClient);

		Authentication authentication = new Authentication(northApiClient);
		AuthOutDTO authOutDTO = authentication.getAuthToken();
		String accessToken = authOutDTO.getAccessToken();

		QueryRulesInDTO2 qrInDTO = new QueryRulesInDTO2();
		qrInDTO.setAppId(appId);
		qrInDTO.setAuthor(author);
		qrInDTO.setSelect(select);
		qrInDTO.setName(name);

		System.out.println("====== delete device rule ======");
		List<RuleDTO2> rules = ruleEngine.queryRules(qrInDTO, accessToken);
		return rules;
	}

	/**
	 * 3.4.5 修改规则状态
	 * 
	 * 支持第三方应用修改规则状态。
	 * 
	 * @param ruleId
	 *            规则的ID。
	 * @param status
	 *            需要修改的规则状态。 active表示激活 inactive表示未激活
	 * @param appId
	 *            如果是本应用的规则，此参数值可以填写null，否则填写授权应用的appId。
	 * @return
	 * @throws NorthApiException
	 */
	public static boolean updateRuleStatus(String ruleId, String status, String appId) throws NorthApiException {
		NorthApiClient northApiClient = initApiClient();
		RuleEngine ruleEngine = new RuleEngine(northApiClient);

		Authentication authentication = new Authentication(northApiClient);
		AuthOutDTO authOutDTO = authentication.getAuthToken();
		String accessToken = authOutDTO.getAccessToken();

		UpdateRuleStatusInDTO ursInDTO = new UpdateRuleStatusInDTO();
		ursInDTO.setRuleId(ruleId);
		ursInDTO.setStatus(status);

		System.out.println("====== update device rule status ======");
		ruleEngine.updateRuleStatus(ursInDTO, appId, accessToken);
		return true;
	}

	/**
	 * 3.4.6 批量修改规则状态
	 * 
	 * 支持第三方应用批量修改规则的状态。
	 * 
	 * @param requests
	 *            请求结构体列表，具体参见UpdateRuleStatusInDTO结构体。
	 * @param appId
	 *            如果是本应用的规则，此参数值可以填写null，否则填写授权应用的appId。
	 * @return
	 * @throws NorthApiException
	 */
	public static UpdateBatchRuleStatusOutDTO updateBatchRuleStatus(List<UpdateRuleStatusInDTO> requests, String appId)
			throws NorthApiException {
		NorthApiClient northApiClient = initApiClient();
		RuleEngine ruleEngine = new RuleEngine(northApiClient);

		Authentication authentication = new Authentication(northApiClient);
		AuthOutDTO authOutDTO = authentication.getAuthToken();
		String accessToken = authOutDTO.getAccessToken();

		UpdateBatchRuleStatusInDTO ubrsInDTO = new UpdateBatchRuleStatusInDTO();
		ubrsInDTO.setRequests(requests);

		System.out.println("====== batch update device rule status ======");
		UpdateBatchRuleStatusOutDTO ubrsOutDTO = ruleEngine.updateBatchRuleStatus(ubrsInDTO, appId, accessToken);
		System.out.println(ubrsOutDTO.toString());
		return ubrsOutDTO;
	}

	/**
	 * 3.9.1 查询单个设备信息
	 * 
	 * 支持第三方应用查询指定条件的设备信息
	 * 
	 * @param deviceId
	 *            设备ID，用于唯一标识一个设备，在注册设备时由物联网平台分配获得。
	 * @param select
	 *            指定查询条件，可选值：imsi。
	 * @param appId
	 *            如果是本应用的设备，此参数值可以填写null，否则填写授权应用的appId。
	 * @return
	 * @throws NorthApiException
	 */
	public static QuerySingleDeviceInfoOutDTO querySingleDeviceInfo(String deviceId, String select, String appId)
			throws NorthApiException {
		NorthApiClient northApiClient = initApiClient();
		DataCollection dataCollection = new DataCollection(northApiClient);

		Authentication authentication = new Authentication(northApiClient);
		AuthOutDTO authOutDTO = authentication.getAuthToken();
		String accessToken = authOutDTO.getAccessToken();

		System.out.println("====== query single device info ======");
		QuerySingleDeviceInfoOutDTO qsdiOutDTO = dataCollection.querySingleDeviceInfo(deviceId, select, appId,
				accessToken);
		System.out.println(qsdiOutDTO.toString());
		return qsdiOutDTO;
	}

	/**
	 * 3.9.2 批量查询设备信息列表
	 * 
	 * 支持第三方应用按条件批量查询设备信息列表。
	 * 
	 * @param gatewayId
	 *            网关ID，用于标识一个网关设备。
	 * @param nodeType
	 *            节点类型，取值：ENDPOINT/GATEWAY/UNKNOW。
	 * @param deviceType
	 *            设备类型。
	 * @param pageNo
	 *            查询的页码。 值为空时查询内容不分页 值大于等于0的整数时分页查询 值等于0时查询第一页
	 * @param pageSize
	 *            查询每页信息的数量， 缺省值：1。
	 * @param status
	 *            查询设备的状态。 ONLINE：在线 OFFLINE：不在线 ABNORMAL：异常状态
	 * @param startTime
	 *            查询注册设备信息时间在startTime之后的记录。时间格式：yyyyMMdd'T'HHmmss'Z'，如
	 *            20151212T121212Z。
	 * @param endTime
	 *            查询注册设备信息时间在endTime之前的记录。时间格式：yyyyMMdd'T'HHmmss'Z'，如
	 *            20151212T121212Z。
	 * @param sort
	 *            指定返回记录的排序。 ASC：按注册设备的时间升序排列 DESC：按注册设备的时间降序排列 缺省值：DESC。
	 * @param select
	 *            指定返回记录，可取值：imsi。
	 * @param appId
	 *            如果是本应用的设备，此参数值可以填写null，否则填写授权应用的appId。
	 * @return
	 * @throws NorthApiException
	 */
	public static QueryBatchDevicesInfoOutDTO queryBatchDevicesInfo(String gatewayId, String nodeType,
			String deviceType, Integer pageNo, Integer pageSize, String status, String startTime, String endTime,
			String sort, String select, String appId) throws NorthApiException {
		NorthApiClient northApiClient = initApiClient();
		DataCollection dataCollection = new DataCollection(northApiClient);

		Authentication authentication = new Authentication(northApiClient);
		AuthOutDTO authOutDTO = authentication.getAuthToken();
		String accessToken = authOutDTO.getAccessToken();

		QueryBatchDevicesInfoInDTO qbdiInDTO = new QueryBatchDevicesInfoInDTO();
		qbdiInDTO.setGatewayId(gatewayId);
		qbdiInDTO.setNodeType(nodeType);
		qbdiInDTO.setDeviceType(deviceType);
		qbdiInDTO.setPageNo(pageNo);
		qbdiInDTO.setPageSize(pageSize);
		qbdiInDTO.setStatus(status);
		qbdiInDTO.setStartTime(startTime);
		qbdiInDTO.setEndTime(endTime);
		qbdiInDTO.setSort(sort);
		qbdiInDTO.setSelect(select);
		qbdiInDTO.setAppId(appId);

		System.out.println("====== query batch device info ======");
		QueryBatchDevicesInfoOutDTO qbdiOutDTO = dataCollection.queryBatchDevicesInfo(qbdiInDTO, accessToken);
		System.out.println(qbdiOutDTO.toString());
		return qbdiOutDTO;
	}

	/**
	 * 3.9.3 查询设备历史数据
	 * 
	 * 支持第三方应用查询设备历史数据。
	 * 
	 * @param appId
	 *            如果是本应用的设备，此参数值可以填写null，否则填写授权应用的appId。
	 * @param deviceId
	 *            设备ID，用于唯一标识一个设备。
	 * @param gatewayId
	 *            网关ID，用于标识一个网关设备。
	 * @param serviceId
	 *            设备的服务标识。
	 * @param property
	 *            服务属性数据。
	 * @param pageNo
	 *            查询的页码。 值为空时查询内容不分页 值大于等于0的整数时分页查询 值等于0时查询第一页
	 * @param pageSize
	 *            查询每页信息的数量， 缺省值：1。
	 * @param startTime
	 *            查询产生时间在startTime之后的历史数据。时间格式：yyyyMMdd'T'HHmmss'Z'，如
	 *            20151212T121212Z。
	 * @param endTime
	 *            查询产生时间在endTime之前的历史数据。时间格式：yyyyMMdd'T'HHmmss'Z'，如
	 *            20151212T121212Z。
	 * @return
	 * @throws NorthApiException
	 */
	public static QueryDeviceDataHistoryOutDTO queryDeviceDataHistory(String appId, String deviceId, String gatewayId,
			String serviceId, String property, Integer pageNo, Integer pageSize, String startTime, String endTime)
			throws NorthApiException {
		NorthApiClient northApiClient = initApiClient();
		DataCollection dataCollection = new DataCollection(northApiClient);

		Authentication authentication = new Authentication(northApiClient);
		AuthOutDTO authOutDTO = authentication.getAuthToken();
		String accessToken = authOutDTO.getAccessToken();

		QueryDeviceDataHistoryInDTO qddhInDTO = new QueryDeviceDataHistoryInDTO();
		qddhInDTO.setDeviceId(deviceId);
		qddhInDTO.setGatewayId(gatewayId);
		qddhInDTO.setServiceId(serviceId);
		qddhInDTO.setProperty(property);
		qddhInDTO.setPageNo(pageNo);
		qddhInDTO.setPageSize(pageSize);
		qddhInDTO.setStartTime(startTime);
		qddhInDTO.setEndTime(endTime);
		qddhInDTO.setAppId(appId);

		System.out.println("====== query device data history ======");
		QueryDeviceDataHistoryOutDTO qddhOutDTO = dataCollection.queryDeviceDataHistory(qddhInDTO, accessToken);
		System.out.println(qddhOutDTO.toString());
		return qddhOutDTO;
	}

	/**
	 * 3.9.4 查询设备影子历史数据
	 * 
	 * 支持第三方应用查询设备影子的历史数据。
	 * 
	 * @param appId
	 *            如果是本应用的设备，此参数值可以填写null，否则填写授权应用的appId
	 * @param deviceId
	 *            设备ID，用于唯一标识一个设备。
	 * @param gatewayId
	 *            网关ID，用于标识一个网关设备。
	 * @param serviceId
	 *            设备的服务标识。
	 * @param property
	 *            服务属性数据。
	 * @param pageNo
	 *            查询的页码。 值为空时查询内容不分页 值大于等于0的整数时分页查询 值等于0时查询第一页
	 * @param pageSize
	 *            查询每页信息的数量， 缺省值：1
	 * @param startTime
	 *            查询产生时间在startTime之后的历史数据。时间格式：yyyyMMdd'T'HHmmss'Z'，如
	 *            20151212T121212Z。
	 * @param endTime
	 *            查询产生时间在endTime之前的历史数据。时间格式：yyyyMMdd'T'HHmmss'Z'，如
	 *            20151212T121212Z。
	 * @return
	 * @throws NorthApiException
	 */
	public static QueryDeviceDesiredHistoryOutDTO queryDeviceDesiredHistory(String appId, String deviceId,
			String gatewayId, String serviceId, String property, Integer pageNo, Integer pageSize, String startTime,
			String endTime) throws NorthApiException {
		NorthApiClient northApiClient = initApiClient();
		DataCollection dataCollection = new DataCollection(northApiClient);

		Authentication authentication = new Authentication(northApiClient);
		AuthOutDTO authOutDTO = authentication.getAuthToken();
		String accessToken = authOutDTO.getAccessToken();

		QueryDeviceDesiredHistoryInDTO qddhInDTO = new QueryDeviceDesiredHistoryInDTO();
		qddhInDTO.setDeviceId(deviceId);
		qddhInDTO.setGatewayId(gatewayId);
		qddhInDTO.setServiceId(serviceId);
		qddhInDTO.setProperty(property);
		qddhInDTO.setPageNo(pageNo);
		qddhInDTO.setPageSize(pageSize);
		qddhInDTO.setStartTime(startTime);
		qddhInDTO.setEndTime(endTime);
		qddhInDTO.setAppId(appId);

		System.out.println("====== query device desired history ======");
		QueryDeviceDesiredHistoryOutDTO qddhOutDTO = dataCollection.queryDeviceDesiredHistory(qddhInDTO, accessToken);
		System.out.println(qddhOutDTO.toString());
		return qddhOutDTO;
	}

	/**
	 * 3.9.5 查询设备服务能力
	 * 
	 * 支持第三方应用查询设备的服务能力。
	 * 
	 * @param gatewayId
	 *            网关ID，用于标识一个网关设备。
	 * @param appId
	 *            如果是本应用的设备，此参数值可以填写null，否则填写授权应用的appId。
	 * @param deviceId
	 *            设备ID，用于唯一标识一个设备。
	 * @return
	 * @throws NorthApiException
	 */
	public static QueryDeviceCapabilitiesOutDTO queryDeviceCapabilities(String gatewayId, String appId, String deviceId)
			throws NorthApiException {
		NorthApiClient northApiClient = initApiClient();
		DataCollection dataCollection = new DataCollection(northApiClient);

		Authentication authentication = new Authentication(northApiClient);
		AuthOutDTO authOutDTO = authentication.getAuthToken();
		String accessToken = authOutDTO.getAccessToken();

		QueryDeviceCapabilitiesInDTO qdcInDTO = new QueryDeviceCapabilitiesInDTO();
		qdcInDTO.setDeviceId(deviceId);
		qdcInDTO.setGatewayId(gatewayId);
		qdcInDTO.setAppId(appId);

		System.out.println("====== query device capabilities ======");
		QueryDeviceCapabilitiesOutDTO qddhOutDTO = dataCollection.queryDeviceCapabilities(qdcInDTO, accessToken);
		System.out.println(qddhOutDTO.toString());
		return qddhOutDTO;
	}

	/**
	 * 3.10.1 创建设备组
	 * 
	 * 支持第三方应用增加设备组，支持在本应用或授权应用下增加设备组。
	 * 
	 * @param name
	 *            设备组名称，仅限大小写字母和数字。
	 * @param description
	 *            设备组的描述信息
	 * @param appId
	 *            如果是本应用的设备，此参数值可以填写null，否则填写授权应用的appId。
	 * @param maxDevNum
	 *            设备组设备最大数量，默认最小值为0。当值为0时，表示对设备数量不做限制。
	 * @param deviceIds
	 *            添加到设备组的设备ID列表。
	 * @return
	 * @throws NorthApiException
	 */
	public static CreateDeviceGroupOutDTO createDeviceGroup(String name, String description, String appId,
			Integer maxDevNum, List<String> deviceIds) throws NorthApiException {
		NorthApiClient northApiClient = initApiClient();
		DeviceGroupManagement groupManagement = new DeviceGroupManagement(northApiClient);

		Authentication authentication = new Authentication(northApiClient);
		AuthOutDTO authOutDTO = authentication.getAuthToken();
		String accessToken = authOutDTO.getAccessToken();

		CreateDeviceGroupInDTO cdgInDTO = new CreateDeviceGroupInDTO();
		cdgInDTO.setName(name);
		cdgInDTO.setDescription(description);
		cdgInDTO.setMaxDevNum(maxDevNum);
		cdgInDTO.setAppId(appId);
		cdgInDTO.setDeviceIds(deviceIds);

		System.out.println("====== create device group ======");
		CreateDeviceGroupOutDTO cdgOutDTO = groupManagement.createDeviceGroup(cdgInDTO, accessToken);
		System.out.println(cdgOutDTO.toString());
		return cdgOutDTO;
	}

	/**
	 * 3.10.2 删除设备组
	 * 
	 * 支持第三方应用删除指定设备组，支持删除本应用或授权应用下的设备组。
	 * 
	 * @param devGroupId
	 *            设备组ID，在增加设备组后由物联网平台返回获得。
	 * @param appId
	 *            如果是本应用的设备组，此参数值可以填写null，否则填写授权应用的appId。
	 * @return
	 * @throws NorthApiException
	 */
	public static boolean deleteDeviceGroup(String devGroupId, String appId) throws NorthApiException {
		NorthApiClient northApiClient = initApiClient();
		DeviceGroupManagement groupManagement = new DeviceGroupManagement(northApiClient);

		Authentication authentication = new Authentication(northApiClient);
		AuthOutDTO authOutDTO = authentication.getAuthToken();
		String accessToken = authOutDTO.getAccessToken();

		System.out.println("====== delete device group ======");
		groupManagement.deleteDeviceGroup(devGroupId, appId, accessToken);
		return true;
	}

	public static ModifyDeviceGroupOutDTO modifyDeviceGroup(String devGroupId, String appId, String name,
			String description, Integer maxDevNum) throws NorthApiException {
		NorthApiClient northApiClient = initApiClient();
		DeviceGroupManagement groupManagement = new DeviceGroupManagement(northApiClient);

		Authentication authentication = new Authentication(northApiClient);
		AuthOutDTO authOutDTO = authentication.getAuthToken();
		String accessToken = authOutDTO.getAccessToken();

		ModifyDeviceGroupInDTO mdgInDTO = new ModifyDeviceGroupInDTO();
		mdgInDTO.setName(name);
		mdgInDTO.setDescription(description);
		mdgInDTO.setMaxDevNum(maxDevNum);

		System.out.println("====== modify device group ======");
		ModifyDeviceGroupOutDTO mdgOutDTO = groupManagement.modifyDeviceGroup(mdgInDTO, devGroupId, appId, accessToken);
		System.out.println(mdgOutDTO.toString());
		return mdgOutDTO;
	}

	/**
	 * 3.10.4 查询设备组详情
	 * 
	 * 支持第三方应用查询满足条件的设备组信息，支持查询本应用或授权应用下的设备组。
	 * 
	 * @param accessAppId
	 *            如果是本应用的设备组，此参数值可以填写null，否则填写授权应用的appId。
	 * @param pageNo
	 *            分页查询参数。 值为空时，查询内容不分页。 值为大于等于0的整数时，分页查询。 值为0时查询第一页。
	 * @param pageSize
	 *            每页设备组记录数量，默认值为1。
	 * @param name
	 *            设备组名称。
	 * @return
	 * @throws NorthApiException
	 */
	public static QueryDeviceGroupsOutDTO queryDeviceGroups(String accessAppId, Integer pageNo, Integer pageSize,
			String name) throws NorthApiException {
		NorthApiClient northApiClient = initApiClient();
		DeviceGroupManagement groupManagement = new DeviceGroupManagement(northApiClient);

		Authentication authentication = new Authentication(northApiClient);
		AuthOutDTO authOutDTO = authentication.getAuthToken();
		String accessToken = authOutDTO.getAccessToken();

		QueryDeviceGroupsInDTO qdgInDTO = new QueryDeviceGroupsInDTO();
		qdgInDTO.setName(name);
		qdgInDTO.setAccessAppId(accessAppId);
		qdgInDTO.setPageNo(pageNo);
		qdgInDTO.setPageSize(pageSize);

		System.out.println("====== query device groups ======");
		QueryDeviceGroupsOutDTO qdgOutDTO = groupManagement.queryDeviceGroups(qdgInDTO, accessToken);
		System.out.println(qdgOutDTO.toString());
		return qdgOutDTO;
	}

	/**
	 * 3.10.5 查询指定设备组
	 * 
	 * 支持第三方应用很据设备组ID查询指定设备组信息，支持查询本应用或授权应用下的设备组。
	 * 
	 * @param devGroupId
	 *            设备组ID，在增加设备组后由物联网平台返回获得。
	 * @param accessAppId
	 *            如果是本应用的设备组，此参数值可以填写null，否则填写授权应用的appId
	 * @return
	 * @throws NorthApiException
	 */
	public static QuerySingleDeviceGroupOutDTO querySingleDeviceGroup(String devGroupId, String accessAppId)
			throws NorthApiException {
		NorthApiClient northApiClient = initApiClient();
		DeviceGroupManagement groupManagement = new DeviceGroupManagement(northApiClient);

		Authentication authentication = new Authentication(northApiClient);
		AuthOutDTO authOutDTO = authentication.getAuthToken();
		String accessToken = authOutDTO.getAccessToken();

		System.out.println("====== query single device group ======");
		QuerySingleDeviceGroupOutDTO qsdgOutDTO = groupManagement.querySingleDeviceGroup(devGroupId, accessAppId,
				accessToken);
		System.out.println(qsdgOutDTO.toString());
		return qsdgOutDTO;
	}

	/**
	 * 3.10.6 查询指定设备组成员
	 * 
	 * 支持第三方应用根据设备组ID查询指定设备组内设备列表，支持查询本应用或授权应用下的设备组。
	 * 
	 * @param devGroupId
	 *            设备组ID，在增加设备组后由物联网平台返回获得。
	 * @param accessAppId
	 *            如果是本应用的设备组，此参数值可以填写null，否则填写授权应用的appId
	 * @param pageNo
	 *            分页查询参数，默认值为0。 值为空时，查询内容不分页。 值为大于等于0的整数时，分页查询。 值为0时查询第一页。
	 * @param pageSize
	 *            每页设备记录数量，默认值为10。
	 * @return
	 * @throws NorthApiException
	 */
	public static QueryDeviceGroupMembersOutDTO queryDeviceGroupMembers(String devGroupId, String accessAppId,
			Integer pageNo, Integer pageSize) throws NorthApiException {
		NorthApiClient northApiClient = initApiClient();
		DeviceGroupManagement groupManagement = new DeviceGroupManagement(northApiClient);

		Authentication authentication = new Authentication(northApiClient);
		AuthOutDTO authOutDTO = authentication.getAuthToken();
		String accessToken = authOutDTO.getAccessToken();

		QueryDeviceGroupMembersInDTO qdgmInDTO = new QueryDeviceGroupMembersInDTO();
		qdgmInDTO.setDevGroupId(devGroupId);
		qdgmInDTO.setAccessAppId(accessAppId);
		qdgmInDTO.setPageNo(pageNo);
		qdgmInDTO.setPageSize(pageSize);

		System.out.println("====== query single device group members ======");
		QueryDeviceGroupMembersOutDTO qsdgOutDTO = groupManagement.queryDeviceGroupMembers(qdgmInDTO, accessToken);
		System.out.println(qsdgOutDTO.toString());
		return qsdgOutDTO;
	}

	/**
	 * 3.10.7 增加设备组成员
	 * 
	 * 支持第三方应用向指定设备组添加设备，并将设备服务能力统计到该设备组的服务能力表中，支持向本应用或授权应用下的设备组添加设备。
	 * 
	 * @param devGroupId
	 *            设备组ID，在增加设备组后由物联网平台返回获得。
	 * @param deviceIds
	 *            要添加到设备组的设备ID列表。
	 * @param accessAppId
	 *            如果是本应用的设备组，此参数值可以填写null，否则填写授权应用的appId。
	 * @return
	 */
	public static DeviceGroupWithDeviceListDTO addDevicesToGroup(String devGroupId, List<String> deviceIds,
			String accessAppId) throws NorthApiException {
		NorthApiClient northApiClient = initApiClient();
		DeviceGroupManagement groupManagement = new DeviceGroupManagement(northApiClient);

		Authentication authentication = new Authentication(northApiClient);
		AuthOutDTO authOutDTO = authentication.getAuthToken();
		String accessToken = authOutDTO.getAccessToken();

		DeviceGroupWithDeviceListDTO dgwdlDTO = new DeviceGroupWithDeviceListDTO();
		dgwdlDTO.setDevGroupId(devGroupId);
		dgwdlDTO.setDeviceIds(deviceIds);

		System.out.println("====== add device to group ======");
		DeviceGroupWithDeviceListDTO dgwdlOutDTO = groupManagement.addDevicesToGroup(dgwdlDTO, accessAppId,
				accessToken);
		System.out.println(dgwdlOutDTO.toString());
		return dgwdlOutDTO;
	}

	/**
	 * 3.10.8 删除设备组成员
	 * 
	 * 支持第三方应用从设备组删除设备，支持从本应用或授权应用下的设备组删除设备。
	 * 
	 * @param devGroupId
	 *            设备组ID，在增加设备组后由物联网平台返回获得。
	 * @param deviceIds
	 *            要从设备组删除的设备ID列表
	 * @param accessAppId
	 *            如果是本应用的设备组，此参数值可以填写null，否则填写授权应用的appId。
	 * @return
	 * @throws NorthApiException
	 */
	public static boolean deleteDevicesFromGroup(String devGroupId, List<String> deviceIds, String accessAppId)
			throws NorthApiException {
		NorthApiClient northApiClient = initApiClient();
		DeviceGroupManagement groupManagement = new DeviceGroupManagement(northApiClient);

		Authentication authentication = new Authentication(northApiClient);
		AuthOutDTO authOutDTO = authentication.getAuthToken();
		String accessToken = authOutDTO.getAccessToken();

		DeviceGroupWithDeviceListDTO dgwdlDTO = new DeviceGroupWithDeviceListDTO();
		dgwdlDTO.setDevGroupId(devGroupId);
		dgwdlDTO.setDeviceIds(deviceIds);

		System.out.println("====== delete device from group ======");
		groupManagement.deleteDevicesFromGroup(dgwdlDTO, accessAppId, accessToken);
		return true;
	}

	/**
	 * 3.11.1 查询版本包列表
	 * 
	 * 支持第三方应用查询满足指定条件的版本包列表信息。
	 * 
	 * @param fileType
	 *            版本包类型。 firmwarePackage：固件包 softwarePackage：软件包
	 * @param deviceType
	 *            版本包适用的设备类型。
	 * @param model
	 *            版本包适用的设备型号。
	 * @param manufacturerName
	 *            版本包适用的设备厂商名称。
	 * @param version
	 *            版本包的版本号。
	 * @param pageNo
	 *            分页查询参数，默认值0。 值为空时，查询内容不分页。 值为大于等于0的整数时，分页查询。 值为0时查询第一页。
	 * @param pageSize
	 *            查询结果分页时的每页结果数量，取值范围1-100，默认值10。
	 * @return
	 * @throws NorthApiException
	 */
	public static QueryUpgradePackageListOutDTO queryUpgradePackageList(String fileType, String deviceType,
			String model, String manufacturerName, String version, Integer pageNo, Integer pageSize)
			throws NorthApiException {
		NorthApiClient northApiClient = initApiClient();
		DeviceUpgrade deviceUpgrade = new DeviceUpgrade(northApiClient);

		Authentication authentication = new Authentication(northApiClient);
		AuthOutDTO authOutDTO = authentication.getAuthToken();
		String accessToken = authOutDTO.getAccessToken();

		QueryUpgradePackageListInDTO quplInDTO = new QueryUpgradePackageListInDTO();
		quplInDTO.setFileType(fileType);
		quplInDTO.setDeviceType(deviceType);
		quplInDTO.setModel(model);
		quplInDTO.setManufacturerName(manufacturerName);
		quplInDTO.setVersion(version);
		quplInDTO.setPageNo(pageNo);
		quplInDTO.setPageSize(pageSize);

		System.out.println("====== query upgrade package list ======");
		QueryUpgradePackageListOutDTO quplOutDTO = deviceUpgrade.queryUpgradePackageList(quplInDTO, accessToken);
		System.out.println(quplOutDTO.toString());
		return quplOutDTO;
	}

	/**
	 * 3.11.2 查询指定版本包
	 * 
	 * 支持第三方应用查询指定版本包信息。
	 * 
	 * @param fileId
	 *            版本包ID，在上传版本包后获得。
	 * @return
	 * @throws NorthApiException
	 */
	public static QueryUpgradePackageOutDTO queryUpgradePackage(String fileId) throws NorthApiException {
		NorthApiClient northApiClient = initApiClient();
		DeviceUpgrade deviceUpgrade = new DeviceUpgrade(northApiClient);

		Authentication authentication = new Authentication(northApiClient);
		AuthOutDTO authOutDTO = authentication.getAuthToken();
		String accessToken = authOutDTO.getAccessToken();

		System.out.println("====== query upgrade package ======");
		QueryUpgradePackageOutDTO qupOutDTO = deviceUpgrade.queryUpgradePackage(fileId, accessToken);
		System.out.println(qupOutDTO.toString());
		return qupOutDTO;
	}

	/**
	 * 3.11.3 删除指定版本包
	 * 
	 * 支持第三方应用删除指定的版本包文件。
	 * 
	 * @param fileId
	 *            版本包ID，在上传版本包后获得。
	 * @return
	 * @throws NorthApiException
	 */
	public static boolean deleteUpgradePackage(String fileId) throws NorthApiException {
		NorthApiClient northApiClient = initApiClient();
		DeviceUpgrade deviceUpgrade = new DeviceUpgrade(northApiClient);

		Authentication authentication = new Authentication(northApiClient);
		AuthOutDTO authOutDTO = authentication.getAuthToken();
		String accessToken = authOutDTO.getAccessToken();

		System.out.println("====== delete upgrade package ======");
		deviceUpgrade.deleteUpgradePackage(fileId, accessToken);
		return true;
	}

	/**
	 * 3.11.4 创建软件升级任务
	 * 
	 * 支持第三方应用对一个或多个NB-IoT设备进行软件升级，或者指定设备组的方式实现对批量设备进行软件升级。
	 * 
	 * @param fileId
	 *            要升级的目标版本包ID。
	 * @param targets
	 *            要进行升级的目标，具体参见OperateDevices结构体。
	 * @param policy
	 *            升级任务的执行策略，具体参见OperatePolicy结构体。
	 * @return
	 * @throws NorthApiException
	 */
	public static CreateUpgradeTaskOutDTO createSoftwareUpgradeTask(String fileId, OperateDevices targets,
			OperatePolicy policy) throws NorthApiException {
		NorthApiClient northApiClient = initApiClient();
		DeviceUpgrade deviceUpgrade = new DeviceUpgrade(northApiClient);

		Authentication authentication = new Authentication(northApiClient);
		AuthOutDTO authOutDTO = authentication.getAuthToken();
		String accessToken = authOutDTO.getAccessToken();

		CreateUpgradeTaskInDTO cutInDTO = new CreateUpgradeTaskInDTO();
		cutInDTO.setFileId(fileId);
		cutInDTO.setPolicy(policy);
		cutInDTO.setTargets(targets);

		System.out.println("====== create Software upgrade task ======");
		CreateUpgradeTaskOutDTO cutOutDTO = deviceUpgrade.createSoftwareUpgradeTask(cutInDTO, accessToken);
		System.out.println(cutOutDTO.toString());
		return cutOutDTO;
	}

	/**
	 * 3.11.5 创建固件升级任务
	 * 
	 * 支持第三方应用对一个或多个NB-IoT设备进行固件升级，或者指定设备组的方式实现对批量设备进行固件升级。
	 * 
	 * @param fileId
	 *            要升级的目标版本包ID。
	 * @param targets
	 *            要进行升级的目标，具体参见OperateDevices结构体。
	 * @param policy
	 *            升级任务的执行策略，具体参见OperatePolicy结构体。
	 * @return
	 * @throws NorthApiException
	 */
	public static CreateUpgradeTaskOutDTO createFirmwareUpgradeTask(String fileId, OperateDevices targets,
			OperatePolicy policy) throws NorthApiException {
		NorthApiClient northApiClient = initApiClient();
		DeviceUpgrade deviceUpgrade = new DeviceUpgrade(northApiClient);

		Authentication authentication = new Authentication(northApiClient);
		AuthOutDTO authOutDTO = authentication.getAuthToken();
		String accessToken = authOutDTO.getAccessToken();

		CreateUpgradeTaskInDTO cutInDTO = new CreateUpgradeTaskInDTO();
		cutInDTO.setFileId(fileId);
		cutInDTO.setPolicy(policy);
		cutInDTO.setTargets(targets);

		System.out.println("====== create firmware upgrade task ======");
		CreateUpgradeTaskOutDTO cutOutDTO = deviceUpgrade.createFirmwareUpgradeTask(cutInDTO, accessToken);
		System.out.println(cutOutDTO.toString());
		return cutOutDTO;
	}

	/**
	 * 3.11.6 查询指定升级任务结果
	 * 
	 * 支持第三方应用查询固件或软件升级任务的操作结果。
	 * 
	 * @param operationId
	 *            操作任务ID，在创建操作任务后由物联网平台返回获得。
	 * @return
	 * @throws NorthApiException
	 */
	public static QueryUpgradeTaskOutDTO queryUpgradeTask(String operationId) throws NorthApiException {
		NorthApiClient northApiClient = initApiClient();
		DeviceUpgrade deviceUpgrade = new DeviceUpgrade(northApiClient);

		Authentication authentication = new Authentication(northApiClient);
		AuthOutDTO authOutDTO = authentication.getAuthToken();
		String accessToken = authOutDTO.getAccessToken();

		System.out.println("====== query upgrade task ======");
		QueryUpgradeTaskOutDTO qutOutDTO = deviceUpgrade.queryUpgradeTask(operationId, accessToken);
		System.out.println(qutOutDTO.toString());
		return qutOutDTO;
	}

	/**
	 * 3.11.7 查询指定升级任务子任务详情
	 * 
	 * 支持第三方应用查看固件或软件升级任务的内容详情
	 * 
	 * @param operationId
	 *            操作任务ID，在创建操作任务后由物联网平台返回获得。
	 * @param subOperationStatus
	 *            子任务状态，不指定，则查询该任务下所有子任务执行详情。 wait：等待 processing：正在执行 fail：失败
	 *            success：成功 stop：停止
	 * @param pageNo
	 *            分页查询参数，默认值0。 值为空时，查询内容不分页。 值为大于等于0的整数时，分页查询。 值为0时查询第一页。
	 * @param pageSize
	 *            查询结果分页时的每页结果数量，取值范围1-100，默认值10。
	 * @return
	 * @throws NorthApiException
	 */
	public static QueryUpgradeSubTaskOutDTO queryUpgradeSubTask(String operationId, String subOperationStatus,
			Integer pageNo, Integer pageSize) throws NorthApiException {
		NorthApiClient northApiClient = initApiClient();
		DeviceUpgrade deviceUpgrade = new DeviceUpgrade(northApiClient);

		Authentication authentication = new Authentication(northApiClient);
		AuthOutDTO authOutDTO = authentication.getAuthToken();
		String accessToken = authOutDTO.getAccessToken();

		QueryUpgradeSubTaskInDTO qustInDTO = new QueryUpgradeSubTaskInDTO();
		qustInDTO.setSubOperationStatus(subOperationStatus);
		qustInDTO.setPageNo(pageNo);
		qustInDTO.setPageSize(pageSize);

		System.out.println("====== query upgrade sub task ======");
		QueryUpgradeSubTaskOutDTO qustOutDTO = deviceUpgrade.queryUpgradeSubTask(qustInDTO, operationId, accessToken);
		System.out.println(qustOutDTO.toString());
		return qustOutDTO;
	}

	/**
	 * 3.11.8 查询升级任务列表
	 * 
	 * 支持第三方应用查看固件或软件升级任务的任务列表。
	 * 
	 * @param operationType
	 *            操作类型。 firmware_upgrade software_upgrade
	 * @param operationStatus
	 *            操作任务的状态。 wait：等待 processing：正在执行 failed：失败 success：成功
	 *            stop：停止
	 * @param deviceType
	 *            操作任务针对的设备类型。
	 * @param model
	 *            操作任务针对的设备型号。
	 * @param manufacturerName
	 *            操作任务针对的设备厂家名称。
	 * @param deviceId
	 *            操作任务针对的设备ID。
	 * @param pageNo
	 *            分页查询参数，默认值0。 值为空时，查询内容不分页。 值为大于等于0的整数时，分页查询。 值为0时查询第一页。
	 * @param pageSize
	 *            查询结果分页时的每页结果数量，取值范围1-100，默认值10。
	 * @return
	 * @throws NorthApiException
	 */
	public static QueryUpgradeTaskListOutDTO queryUpgradeTaskList(String operationType, String operationStatus,
			String deviceType, String model, String manufacturerName, String deviceId, Integer pageNo, Integer pageSize)
			throws NorthApiException {
		NorthApiClient northApiClient = initApiClient();
		DeviceUpgrade deviceUpgrade = new DeviceUpgrade(northApiClient);

		Authentication authentication = new Authentication(northApiClient);
		AuthOutDTO authOutDTO = authentication.getAuthToken();
		String accessToken = authOutDTO.getAccessToken();

		QueryUpgradeTaskListInDTO qutlInDTO = new QueryUpgradeTaskListInDTO();
		qutlInDTO.setOperationType(operationType);
		qutlInDTO.setOperationStatus(operationStatus);
		qutlInDTO.setDeviceType(deviceType);
		qutlInDTO.setModel(model);
		qutlInDTO.setManufacturerName(manufacturerName);
		qutlInDTO.setDeviceId(deviceId);
		qutlInDTO.setPageNo(pageNo);
		qutlInDTO.setPageSize(pageSize);

		System.out.println("====== query upgrade sub task ======");
		QueryUpgradeTaskListOutDTO qutlOutDTO = deviceUpgrade.queryUpgradeTaskList(qutlInDTO, accessToken);
		System.out.println(qutlOutDTO.toString());
		return qutlOutDTO;
	}

	public static void main(String args[]) {
		try {
			// regDirectDevice("", "", null, null, 180, false);

			//queryDeviceStatus("421637ab-51b8-46f7-a2e4-e61fdc3e95c9");
			//querySingleDeviceInfo("69858528-77ba-4e7b-99d1-bd475e60840f", null, null); 
			
			/**
			 * ---------------------initialize
			 * northApiClient------------------------
			 */
			NorthApiClient northApiClient = initApiClient();
			DeviceManagement deviceManagement = new DeviceManagement(northApiClient);

			/**
			 * ---------------------get accessToken at first------------------------
			 */
			Authentication authentication = new Authentication(northApiClient);
			AuthOutDTO authOutDTO = authentication.getAuthToken();
			String accessToken = authOutDTO.getAccessToken();
 
			QueryDeviceRealtimeLocationInDTO qdrlInDTO = new QueryDeviceRealtimeLocationInDTO();
			qdrlInDTO.setDeviceId("3f011e5e-b388-43d2-b40a-2b22c070123e");
			qdrlInDTO.setHorAcc(1000);
			QueryDeviceRealtimeLocationOutDTO qdrlOutDTO;
			try {
				qdrlOutDTO = deviceManagement.queryDeviceRealtimeLocation(qdrlInDTO, null, accessToken);
				System.out.println(qdrlOutDTO.toString()); 
			} catch (NorthApiException e) {
				System.out.println(e.toString());
			}
			/**
			 * ---------------------register a new device------------------------
			 *//*
			System.out.println("======register a new device======");
			Random random = new Random();
			String nodeid = "testdemo" + (random.nextInt(9000000) + 1000000); 
			RegDirectDeviceOutDTO rddod = regDirectDevice(nodeid,  nodeid, null, null, 180, false);

			if (rddod != null) {
				System.out.println("\n======RegDirectDeviceOutDTO======" +rddod.toString());
				String deviceId = rddod.getDeviceId();
				
				QuerySingleDeviceInfoOutDTO  qsdiOutDTO = querySingleDeviceInfo(deviceId,null,null);
				System.out.println("\n======QuerySingleDeviceInfoOutDTO======" +qsdiOutDTO.toString());

				*//**
				 * ---------------------modify device info------------------------
				 *//*
				// use verifyCode as the device name
				System.out.println("\n======modify device info======");
				//modifyDeviceInfo(deviceId, rddod.getVerifyCode());

				*//**
				 * ---------------------query device status------------------------
				 *//*
				System.out.println("\n======query device status======");
				QueryDeviceStatusOutDTO qdsOutDTO = deviceManagement.queryDeviceStatus(deviceId, null, accessToken);
				System.out.println(qdsOutDTO.toString());

				*//**
				 * ---------------------query device real-time
				 * location------------------------
				 *//*
				// note: querying device real-time location has several conditions,
				// thus, this API may return error if the conditions are not
				// matched.
				System.out.println("\n======query device real-time location======");
				//queryDeviceLocation(deviceId);

				*//**
				 * ---------------------modify device shadow------------------------
				 *//*
				System.out.println("\n======modify device shadow======");
				//modifyDeviceShadow(deviceManagement, accessToken, deviceId);

				*//**
				 * ---------------------query device shadow------------------------
				 *//*
				System.out.println("\n======query device shadow======");
				QueryDeviceShadowOutDTO qdshadowOutDTO = deviceManagement.queryDeviceShadow(deviceId, null, accessToken);
				System.out.println(qdshadowOutDTO.toString());

				*//**
				 * ---------------------refresh device key------------------------
				 *//*
				// note: refreshing device key has several conditions,
				// thus, this API may return error if the cond2.8.4 查询设备服务能力itions are not
				// matched.
				System.out.println("\n======refresh device key======");
				//refreshDeviceKey(deviceManagement, accessToken, deviceId);

				*//** ---------------------delete device------------------------ *//*
				System.out.println("\n======delete device======");
				deviceManagement.deleteDirectDevice(deviceId, true, null, accessToken);
				System.out.println("delete device succeeded");
				
				QueryDeviceCapabilitiesOutDTO qdcOutDTO = queryDeviceCapabilities(null,null,"3f011e5e-b388-43d2-b40a-2b22c070123e");
				System.out.println("QueryDeviceCapabilitiesOutDTO"+ qdcOutDTO.toString());
			}*/

		} catch (NorthApiException e) {
			e.printStackTrace();
			System.out.println(e.getError_code());
			System.out.println(e.getError_desc());
		}
	}

}
