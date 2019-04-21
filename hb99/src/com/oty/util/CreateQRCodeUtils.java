package com.oty.util;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Iterator;

import javax.imageio.ImageIO;

import org.apache.commons.lang.StringUtils;

import com.swetake.util.Qrcode;

import net.sf.json.JSONObject;

/**
 * 二维码
 */
public class CreateQRCodeUtils {
	// LOGO宽度
	private static final int WIDTH = 45;
	// LOGO高度
	private static final int HEIGHT = 45;
	// 二维码尺寸
	private static final int QRCODE_SIZE = 175;

	/**
	 * 获取带参HTTP二维码
	 * 
	 * @param http
	 *            链接url
	 * @param obj
	 *            参数
	 * @param isNeedLogo
	 *            是否需要logo
	 * @param httpUrl
	 *            网络url
	 * @param imgUrl
	 *            本地Url
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static byte[] getHttpCreateQRCode(String http, JSONObject obj, boolean isNeedLogo, String httpUrl,
			String imgUrl, String remark) throws Exception {
		// 生成二维码中要存储的信息
		StringBuffer qrData = new StringBuffer(http);
		Iterator<String> keys = obj.keys();
		qrData.append("?time=" + System.currentTimeMillis());
		while (keys.hasNext()) {
			String key = keys.next();
			String value = obj.getString(key);
			qrData.append("&" + key + "=" + value);
		}
		return getCreateQRCode(qrData, isNeedLogo, httpUrl, imgUrl, remark);
	}

	/**
	 * 获取参数二维码
	 * 
	 * @param obj
	 * @param isNeedLogo
	 *            是否需要logo
	 * @param httpUrl
	 *            网络url
	 * @param imgUrl
	 *            本地Url
	 * @return
	 * @throws Exception
	 */
	public static byte[] getJsonCreateQRCode(JSONObject obj, boolean isNeedLogo, String httpUrl, String imgUrl,
			String remark) throws Exception {
		return getCreateQRCode(new StringBuffer(obj.toString()), isNeedLogo, httpUrl, imgUrl, remark);
	}

	/**
	 * 获取参数二维码
	 * 
	 * @param str
	 * @param isNeedLogo
	 *            是否需要logo
	 * @param httpUrl
	 *            网络url
	 * @param imgUrl
	 *            本地Url
	 * @return
	 * @throws Exception
	 */
	public static byte[] getStringCreateQRCode(String str, boolean isNeedLogo, String httpUrl, String imgUrl,
			String remark) throws Exception {
		return getCreateQRCode(new StringBuffer(str), isNeedLogo, httpUrl, imgUrl, remark);
	}

	/**
	 * 获取带参二维码 可选是否带logo
	 * 
	 * @param isNeedLogo
	 *            是否需要logo
	 * @param httpUrl
	 *            网络url
	 * @param imgUrl
	 *            本地Url
	 * @param remark
	 *            备注
	 * @return
	 * @throws Exception
	 */
	private static byte[] getCreateQRCode(StringBuffer data, boolean isNeedLogo, String httpUrl, String imgUrl,
			String remark) throws Exception {
		Qrcode qrcode = new Qrcode();
		qrcode.setQrcodeErrorCorrect('M');// 纠错等级（分为L、M、H三个等级）
		qrcode.setQrcodeEncodeMode('B');// N代表数字，A代表a-Z，B代表其它字符
		qrcode.setQrcodeVersion(10);// 版本

		// 设置一下二维码的像素
		int width = QRCODE_SIZE;
		int height = QRCODE_SIZE;
		BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		// 绘图
		Graphics2D gs = bufferedImage.createGraphics();
		gs.setBackground(Color.WHITE);
		gs.setColor(Color.BLACK);
		gs.clearRect(0, 0, width, height);// 清除下画板内容

		// 设置下偏移量,如果不加偏移量，有时会导致出错。
		int pixoff = 2;

		byte[] d = data.toString().getBytes();
		if (d.length > 0 && d.length < 120) {
			boolean[][] s = qrcode.calQrcode(d);
			for (int i = 0; i < s.length; i++) {
				for (int j = 0; j < s.length; j++) {
					if (s[j][i]) {
						gs.fillRect(j * 3 + pixoff, i * 3 + pixoff, 3, 3);
					}
				}
			}
		}
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		gs.dispose();
		bufferedImage.flush();
		if (isNeedLogo) {
			insertImage(bufferedImage, httpUrl, imgUrl, true);
		}
		if (ImageIO.write(bufferedImage, "JPG", out)) {
			byte[] create = create(remark, bufferedImage, 455, 455);
			return create;
		}
		return out.toByteArray();
	}

	/**
	 * 插入LOGO
	 * 
	 * @param source
	 *            二维码图片
	 * @param httpImgUrlPath
	 *            LOGO网络图片地址 必选一
	 * @param imgPath
	 *            LOGO本地图片地址 必选一
	 * @param needCompress
	 *            是否压缩
	 * @throws Exception
	 */
	private static void insertImage(BufferedImage source, String httpImgUrlPath, String imgPath, boolean needCompress)
			throws Exception {
		if (StringUtils.isBlank(httpImgUrlPath) && StringUtils.isBlank(imgPath)) {
			System.err.println("logo地址必填一个");
			return;
		}
		Image src = null;
		if (StringUtils.isNotBlank(imgPath)) {
			File file = new File(imgPath);
			if (!file.exists()) {
				System.err.println("" + imgPath + "   该文件不存在！");
				return;
			}
			src = ImageIO.read(new File(imgPath));
		} else {
			InputStream inputStream = new URL(httpImgUrlPath).openStream();
			src = ImageIO.read(inputStream);
		}
		int width = src.getWidth(null);
		int height = src.getHeight(null);
		if (needCompress) { // 压缩LOGO
			if (width > WIDTH) {
				width = WIDTH;
			}
			if (height > HEIGHT) {
				height = HEIGHT;
			}
			Image image = src.getScaledInstance(width, height, Image.SCALE_SMOOTH);
			BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			Graphics g = tag.getGraphics();
			g.drawImage(image, 0, 0, null); // 绘制缩小后的图
			g.dispose();
			src = image;
		}
		// 插入LOGO
		Graphics2D graph = source.createGraphics();
		int x = (QRCODE_SIZE - width) / 2;
		int y = (QRCODE_SIZE - height) / 2;
		graph.drawImage(src, x, y, width, height, null);
		Shape shape = new RoundRectangle2D.Float(x, y, width, width, 6, 6);
		graph.setStroke(new BasicStroke(3f));
		graph.draw(shape);
		graph.dispose();
	}

	/**
	 * 添加 底部图片文字
	 * 
	 * @param str
	 *            生产的图片文字
	 * @param bi
	 *            原图
	 * @param width
	 *            定义生成图片宽度
	 * @param height
	 *            定义生成图片高度
	 * @return
	 * @return
	 * @throws IOException
	 */
	public static byte[] create(String str, BufferedImage bufferedImage, int width, int height) {
		try {
			Image image = bufferedImage;

			BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			Graphics2D g2 = bi.createGraphics();
			g2.setBackground(Color.WHITE);
			g2.clearRect(0, 0, width, height);
			g2.drawImage(image, 0, 0, width - 10, height - 26, null); // 这里减去25是为了防止字和图重合
			/** 设置生成图片的文字样式 * */
			Font font = new Font(null, Font.BOLD, 25);
			g2.setFont(font);
			g2.setPaint(Color.BLACK);

			/** 设置字体在图片中的位置 在这里是居中* */
			FontRenderContext context = g2.getFontRenderContext();
			Rectangle2D bounds = font.getStringBounds(str, context);
			double x = (width - bounds.getWidth()) / 2;
			// double y = (height - bounds.getHeight()) / 2; //Y轴居中
			double y = (height - bounds.getHeight());
			double ascent = -bounds.getY();
			double baseY = y + ascent;
			/** 防止生成的文字带有锯齿 * */
			g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
			/** 在图片上生成文字 * */
			g2.drawString(str, (int) x, (int) baseY);
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			if (ImageIO.write(bi, "jpg", out)) {
				return out.toByteArray();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		try {
			BufferedImage image = ImageIO.read(new FileInputStream("E:\\aaa.png"));
			byte[] create = create("地源系统管道阀门", image, 455, 455);
			File file = new File("E:\\aaa.jpg");
			FileOutputStream out = new FileOutputStream(file);
			out.write(create);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
