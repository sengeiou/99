package com.oty.util;

import com.swetake.util.Qrcode;
import jp.sourceforge.qrcode.QRCodeDecoder;
import jp.sourceforge.qrcode.exception.DecodingFailedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class QRCodeUtils {
    private final static Logger logger = LoggerFactory.getLogger(QRCodeUtils.class);
    
    /**
     * 生成指定大小、带logo的二维码
     * @param content 二维码内容
     * @param destFile 目标文件
     * @param size 二维码尺寸，取值范围1-40，值越大尺寸越大，可存储的信息越大
     * @param logoImgPath logo图片路径
     * @throws IOException
     */
    public static void qrCodeEncode(String content, File destFile, int size,String logoImgPath) throws IOException {
        BufferedImage bufImg = null;
        Qrcode qrcode = new Qrcode();
        // 设置二维码排错率，可选L(7%)、M(15%)、Q(25%)、H(30%)，排错率越高可存储的信息越少，但对二维码清晰度的要求越小
        qrcode.setQrcodeErrorCorrect('M');
        qrcode.setQrcodeEncodeMode('B');
        // 设置设置二维码尺寸，取值范围1-40，值越大尺寸越大，可存储的信息越大
        qrcode.setQrcodeVersion(size);
        // 获得内容的字节数组，设置编码格式
        byte[] contentBytes = content.getBytes("UTF-8");
        
        int codeImgSize = 120;
        bufImg = new BufferedImage(codeImgSize, codeImgSize, BufferedImage.TYPE_INT_RGB);
        Graphics2D gs = bufImg.createGraphics();
        // 设置背景颜色
        gs.setBackground(Color.WHITE);
        gs.clearRect(0, 0, codeImgSize, codeImgSize);

        // 设定图像颜色> BLACK
        gs.setColor(Color.BLACK);
        // 设置偏移量，不设置可能导致解析出错
        int pixoff = 2;
        // 输出内容> 二维码
        if (contentBytes.length > 0 && contentBytes.length < 500) {
            boolean[][] codeOut = qrcode.calQrcode(contentBytes);
            for (int i = 0; i < codeOut.length; i++) {
                for (int j = 0; j < codeOut.length; j++) {
                    if (codeOut[j][i]) {
                        gs.fillRect(j * 2 + pixoff, i * 2 + pixoff, 2, 2);
                    }
                }
            }
        } else {
            logger.error("QRCode content bytes length = " + contentBytes.length + " not in [0, 800].");
            throw new RuntimeException("QRCode content bytes length = " + contentBytes.length + " not in [0, 800].");
        }
        
       
        BufferedImage logoImg = ImageIO.read(new File(logoImgPath));
        int logosize = codeImgSize/2-logoImg.getWidth()/2;
        logger.debug("logosize="+logosize);
        gs.drawImage(logoImg, logosize, logosize, null);
        
        
        gs.dispose();
        bufImg.flush();

        ImageIO.write(bufImg, "png", destFile);

    }
    
    /**
     * 生成指定大小的二维码
     * @param content 二维码内容
     * @param destFile 目标文件
     * @param size 二维码尺寸，取值范围1-40，值越大尺寸越大，可存储的信息越大
     * @throws IOException
     */
    public static void qrCodeEncode(String content, File destFile, int size) throws IOException {
        BufferedImage bufImg = null;
        Qrcode qrcode = new Qrcode();
        // 设置二维码排错率，可选L(7%)、M(15%)、Q(25%)、H(30%)，排错率越高可存储的信息越少，但对二维码清晰度的要求越小
        qrcode.setQrcodeErrorCorrect('M');
        qrcode.setQrcodeEncodeMode('B');
        // 设置设置二维码尺寸，取值范围1-40，值越大尺寸越大，可存储的信息越大
        qrcode.setQrcodeVersion(size);
        // 获得内容的字节数组，设置编码格式
        byte[] contentBytes = content.getBytes("UTF-8");
        
        int imgSize = 150;
        bufImg = new BufferedImage(imgSize, imgSize, BufferedImage.TYPE_INT_RGB);
        Graphics2D gs = bufImg.createGraphics();
        // 设置背景颜色
        gs.setBackground(Color.WHITE);
        gs.clearRect(0, 0, imgSize, imgSize);

        // 设定图像颜色> BLACK
        gs.setColor(Color.BLACK);
        // 设置偏移量，不设置可能导致解析出错
        int pixoff = 2;
        // 输出内容> 二维码
        if (contentBytes.length > 0 && contentBytes.length < 500) {
            boolean[][] codeOut = qrcode.calQrcode(contentBytes);
            for (int i = 0; i < codeOut.length; i++) {
                for (int j = 0; j < codeOut.length; j++) {
                    if (codeOut[j][i]) {
                        gs.fillRect(j * 2 + pixoff, i * 2 + pixoff, 2, 2);
                    }
                }
            }
        } else {
            logger.error("QRCode content bytes length = " + contentBytes.length + " not in [0, 800].");
            throw new RuntimeException("QRCode content bytes length = " + contentBytes.length + " not in [0, 800].");
        }
        
        gs.dispose();
        bufImg.flush();

        ImageIO.write(bufImg, "png", destFile);


    }

    
    
    /**
     * 解析二维码，返回解析内容
     * 
     * @param imageFile
     * @return
     */
    public static String qrCodeDecode(File imageFile) {
        String decodedData = null;
        QRCodeDecoder decoder = new QRCodeDecoder();
        BufferedImage image = null;
        try {
            image = ImageIO.read(imageFile);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        try {
            decodedData = new String(decoder.decode(new J2SEImage(image)), "GBK");
            System.out.println("Output Decoded Data is：" + decodedData);
        } catch (DecodingFailedException dfe) {
            System.out.println("Error: " + dfe.getMessage());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return decodedData;
    }

    // 图片转化成base64字符串
    public static String getImageStr(String imgFile) {
        InputStream in = null;
        byte[] data = null;
        // 读取图片字节数组
        try {
            in = new FileInputStream(imgFile);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data).replaceAll("[\\s*\t\n\r]", "");// 返回Base64编码过的字节数组字符串
    }

    public static void main(String[] args) {
        String FilePath = "D:/pictest/erweima1.jpg";
        String logoPath ="D:/pictest/small.png";
        // 二维码内容
        String encodeddata = "aa:http://wx.yhcysc.com//login/plugin_submit.jhtml?pluginId=wxCommLoginPlugin?memberCode=59542b1b3badde1bda1837e7d6c7b1c1";
        try {
            QRCodeUtils.qrCodeEncode(encodeddata, new File(FilePath),10,logoPath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 解码
        String qrFilepath="D:/pictest/qrcode_for_gh_19d62be285ed_258.jpg";
         String reText = QRCodeUtils.qrCodeDecode(new File(qrFilepath));
        System.out.println(reText);

    }
}
