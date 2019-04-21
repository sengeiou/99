package com.oty.util; 
 
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

import org.apache.log4j.Logger;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException; 

import pub.functions.DateFuncs;
import pub.functions.PropsFuncs; 

public class OssUtils {
	
    private static Logger logger = Logger.getLogger(OssUtils.class);  
    // accessKeyId和accessKeySecret是OSS的访问密钥，您可以在控制台上创建和查看，
    // 创建和查看访问密钥的链接地址是：https://ak-console.aliyun.com/#/。
    // 注意：accessKeyId和accessKeySecret前后都没有空格，从控制台复制时请检查并去除多余的空格。 
	//阿里云API的内或外网域名
	public static String endpoint = PropsFuncs.ossProps.getProperty("endpoint");
	//阿里云API的密钥Access Key ID
	private static String accessKeyId = PropsFuncs.ossProps.getProperty("key");
	//阿里云API的密钥Access Key Secret
	private static String accessKeySecret = PropsFuncs.ossProps.getProperty("secret");  
    // Bucket用来管理所存储Object的存储空间，详细描述请参看“开发人员指南 > 基本概念 > OSS基本概念介绍”。
    // Bucket命名规范如下：只能包括小写字母，数字和短横线（-），必须以小写字母或者数字开头，长度必须在3-63字节之间。
	private static String bucketName = PropsFuncs.ossProps.getProperty("bucketName");  
    // Object是OSS存储数据的基本单元，称为OSS的对象，也被称为OSS的文件。详细描述请参看“开发人员指南 > 基本概念 > OSS基本概念介绍”。
    // Object命名规范如下：使用UTF-8编码，长度必须在1-1023字节之间，不能以“/”或者“\”字符开头。   
    
    /**
     * 上传字符串
     */
    public void uploadString(String content, String key){  
    	OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);  
    	try {  
            if (ossClient.doesBucketExist(bucketName)) {
        		logger.info("您已经创建Bucket：" + bucketName); 
            } else {
            	logger.info("您的Bucket不存在，创建Bucket：" + bucketName); 
                ossClient.createBucket(bucketName);
            } 
        	ossClient.putObject(bucketName, key, new ByteArrayInputStream(content.getBytes()));
        	logger.info("Object：" + key + "存入OSS成功"); 
        } catch (OSSException oe) {
            oe.printStackTrace();
        } catch (ClientException ce) {
            ce.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ossClient.shutdown();
        } 
    }
    
    /**
     * 上传字符串
     */
    public boolean uploadBytes(byte[] content, String key){ 
    	OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);  
    	try {  
            if (ossClient.doesBucketExist(bucketName)) {
        		logger.info("您已经创建Bucket：" + bucketName); 
            } else {
            	logger.info("您的Bucket不存在，创建Bucket：" + bucketName); 
                ossClient.createBucket(bucketName);
            } 
        	ossClient.putObject(bucketName, key, new ByteArrayInputStream(content)); 
        	logger.info("Object：" + key + "存入OSS成功"); 
        } catch (OSSException oe) {
            oe.printStackTrace();
            return false;
        } catch (ClientException ce) {
            ce.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            ossClient.shutdown();
        } 
        return true;
    }
    
    /**
     * 上传网络流
     */
    public void uploadNetwork(String url, String key) throws MalformedURLException, IOException{  
    	OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);  
    	try {  
            if (ossClient.doesBucketExist(bucketName)) {
        		logger.info("您已经创建Bucket：" + bucketName); 
            } else {
            	logger.info("您的Bucket不存在，创建Bucket：" + bucketName); 
                ossClient.createBucket(bucketName);
            } 
        	InputStream inputStream = new URL(url).openStream();
        	ossClient.putObject(bucketName, key, inputStream); 
        	logger.info("Object：" + key + "存入OSS成功"); 
        } catch (OSSException oe) {
        	logger.error(oe.getMessage()); 
            oe.printStackTrace();
        } catch (ClientException ce) {
        	logger.error(ce.getMessage()); 
            ce.printStackTrace();
        } catch (Exception e) {
        	logger.error(e.getMessage()); 
            e.printStackTrace();
        } finally {
            ossClient.shutdown();
        } 
    }
    
    /**
     * 上传文件流
     */
    public void uploadFile(InputStream inputStream, String key) throws FileNotFoundException {  
    	OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);  
    	try {  
            if (ossClient.doesBucketExist(bucketName)) {
        		logger.info("您已经创建Bucket：" + bucketName); 
            } else {
            	logger.info("您的Bucket不存在，创建Bucket：" + bucketName); 
                ossClient.createBucket(bucketName);
            }  
        	ossClient.putObject(bucketName, key, inputStream);  
        	logger.info("Object：" + key + "存入OSS成功"); 
        } catch (OSSException oe) {
            oe.printStackTrace();
        } catch (ClientException ce) {
            ce.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ossClient.shutdown();
        } 
    } 
   
    /**
     * 获取统一key
     * @param type 类型  admin端0  manager端1 手机端 2
     * @param userId 用户id
     * @param fileName 文件名
     * @return
     */
    public static String getKey(String uuid, String fileName){
    	String str = ""; 
    	Date date = new Date(); 
    	str += DateFuncs.toString(date, "yyyy-MM-dd");
    	str += "/"; 
    	str += uuid;
    	str += (date.getTime()+""); 
    	str += fileName.substring(fileName.lastIndexOf("."));
    	return str;
    }  

	public static String getUrl(String key) {
		return "https://" + OssUtils.bucketName + "." + OssUtils.endpoint + "/" + key;
	}
    
    public static void main(String[] args){
    	new OssUtils().uploadBytes("11211111212".getBytes(), getKey("11111111","111111.111.111.11.jpg"));
    }
    
}
