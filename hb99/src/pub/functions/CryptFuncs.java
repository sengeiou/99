package pub.functions;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
 
public class CryptFuncs {
	
	private static final String secret = "hnoty";
	
	private static Class<?> base64DecoderClass;
	private static Method base64DecodeBuffer;
	
	private static Class<?> base64EncoderClass;
	private static Method base64Encode;
	
	static {
		try {
			base64DecoderClass = Class.forName("sun.misc.BASE64Decoder");
			base64DecodeBuffer = base64DecoderClass.getMethod("decodeBuffer", String.class);

			base64EncoderClass = Class.forName("sun.misc.BASE64Encoder");
			base64Encode = base64EncoderClass.getMethod("encode", byte[].class);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getMd5(String text) {
		MessageDigest msgDigest = null;
		try {
			msgDigest = MessageDigest.getInstance("MD5");
		}
		catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		assert msgDigest != null;
		if (text == null) {
			text = "";
		}
		msgDigest.update(text.getBytes());
		byte[] bts = msgDigest.digest();
		String result = StrFuncs.byte2hex(bts);
		return result;
	}
	
	public static byte[] DecodeImage(byte[] data){
		byte[] resultData = null;
		if(data!=null&&data.length>secret.length())
		{
			ByteArrayOutputStream byteOutStream =null;
			try {
				byteOutStream = new ByteArrayOutputStream();
				byteOutStream.write(data, secret.length(), data.length-secret.length());
				
				byteOutStream.flush();
				resultData = byteOutStream.toByteArray();
			} catch (IOException e) { 
				e.printStackTrace();
			}finally{
				IoFuncs.tryClose(byteOutStream);
			}
		}else{
			resultData = data;
		}
		return resultData;
	}
	
	public static byte[] EncodeImage(byte[] data){
		byte[] resultData = null;
		if(data!=null)
		{
			ByteArrayOutputStream byteOutStream = null;
			try {
				byteOutStream = new ByteArrayOutputStream();
				byteOutStream.write(secret.getBytes());
				byteOutStream.write(data);
				byteOutStream.flush();
				resultData = byteOutStream.toByteArray();
			} catch (IOException e) { 
				e.printStackTrace();
			}finally{
				IoFuncs.tryClose(byteOutStream);
			}
		}
		return resultData;
	}
	
	public static String base64Encode(byte[] bytes) {
		try {
			Object encoder = base64EncoderClass.newInstance();
			return (String) base64Encode.invoke(encoder, new Object[] {bytes});
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static byte[] base64Decode(String s) {
		try {
			Object decoder = base64DecoderClass.newInstance();
			return (byte[]) base64DecodeBuffer.invoke(decoder, s);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
