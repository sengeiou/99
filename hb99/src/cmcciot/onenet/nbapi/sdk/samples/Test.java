package cmcciot.onenet.nbapi.sdk.samples; 

import cmcciot.onenet.nbapi.sdk.entity.Device;
import cmcciot.onenet.nbapi.sdk.utils.HttpSendCenter;

public class Test {

	public static void main(String[] args) {
        String apiKey = "sqXKx=HXWNPsUb2=APQSVxLNV4U=";
        Device device = new Device("samples", "869976030142138", "869976030142138");
        String url = device.toUrl()+"/514852201"; 
      //  System.out.println(HttpSendCenter.get(apiKey, url).toString());
        System.out.println(HttpSendCenter.delete(apiKey, url).toString());
	}

}
