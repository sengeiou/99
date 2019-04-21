package cmcciot.onenet.nbapi.sdk.config;

import cmcciot.onenet.nbapi.sdk.exception.NBStatus;
import cmcciot.onenet.nbapi.sdk.exception.OnenetNBException;

import java.io.IOException;
import java.util.Properties;

public class Config {

	public static String domainName;

	static {
		Properties properties = new Properties();
		try {
			properties.load(Config.class.getClassLoader().getResourceAsStream("config.properties"));
			domainName = (String) properties.get("domainName");
		} catch (IOException e) {
			throw new OnenetNBException(NBStatus.LOAD_CONFIG_ERROR);
		}
	}

	public static String getDomainName() {
		return domainName;
	}

}
