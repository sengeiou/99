package com.oty.util;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;

import javax.imageio.ImageIO;

import pub.functions.StrFuncs;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/*
 * 图片处理 
 * */
public class ImageHelper {

	/*
	 * 根据尺寸图片居中裁剪 目前支持：100x100 280x280 160x120 400x300 400*225种
	 */
	public static void cutCenterImage(byte[] content, String dest, int w, int h)
			throws Exception {
		BufferedImage bi = ImageIO.read(new ByteArrayInputStream(content));
		/* 原始图像的宽度和高度 */
		int rh = bi.getHeight();
		int rw = bi.getWidth();

		// 剪切图片右上角坐标
		int x = 0;
		int y = 0;
		// 剪切的宽与高度
		int cw = w;
		int ch = h;

		if ((rh * 1.0 / rw) > (h * 1.0 / w)) {
			if (rh > h) {
				y = (rh - (int) (rw * h * 1.0 / w)) / 2;
				cw = rw;
				ch = rh - 2 * y;
			}
		} else if ((rh * 1.0 / rw) < (h * 1.0 / w)) {
			if (rw > w) {
				x = (rw - (int) (rh * w * 1.0 / h)) / 2;
				cw = rw - 2 * x;
				ch = rh;
			}
		} else if ((rh * 1.0 / rw) == (h * 1.0 / w)) {
			cw = rw;
			ch = rh;
		}
		if (cw > w || ch > h) {// 如果是大了，那么就要压缩
			bi = zoomImage(bi, cw, ch, x, y, w * 1.0 / cw);
		}
		/* 输出到文件流 */
		FileOutputStream newimage = new FileOutputStream(dest);
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(newimage);
		JPEGEncodeParam jep = JPEGCodec.getDefaultJPEGEncodeParam(bi);
		/* 压缩质量 */
		jep.setQuality(0.9f, true);
		encoder.encode(bi, jep);
		/* 近JPEG编码 */
		newimage.close(); 
		// 1.获取原始图片的长(rh)、宽(rw)
		// 2.如果 (rh/rw) > h / w，说明原图过高，需要从顶部、底部切除一部分。顶部、顶部切除的数量为 (rh - rw * h / w )/2 px
		// 3.如果 (rh/rw) < h / w，说明原图过宽，需要从左、右切除一部分。左右各切除的数量为 (rw - rh * w / h )/2 px
		// 4.如果 (rh/rw) = h / w，不用切
		// 5.按照切割后的图片大小，按比例压缩到 w,h的大小
	}

	/*
	 * 根据尺寸图片居中裁剪 fileType :图片的后缀名 如:jgp gif png 返回byte[]
	 */
	public static byte[] cutCenterImageByte(byte[] content, String fileType,
			int w, int h) throws Exception {
		if (StrFuncs.isEmpty(fileType)) {
			fileType = "jpg";
		}
		BufferedImage bi = ImageIO.read(new ByteArrayInputStream(content));
		/* 原始图像的宽度和高度 */
		int rh = bi.getHeight();
		int rw = bi.getWidth();

		// 剪切图片右上角坐标
		int x = 0;
		int y = 0;
		// 剪切的宽与高度
		int cw = w;
		int ch = h;

		if ((rh * 1.0 / rw) > (h * 1.0 / w)) {
			if (rh > h) {
				y = (rh - (int) (rw * h * 1.0 / w)) / 2;
				cw = rw;
				ch = rh - 2 * y;
			}
		} else if ((rh * 1.0 / rw) < (h * 1.0 / w)) {
			if (rw > w) {
				x = (rw - (int) (rh * w * 1.0 / h)) / 2;
				cw = rw - 2 * x;
				ch = rh;
			}
		} else if ((rh * 1.0 / rw) == (h * 1.0 / w)) {
			cw = rw;
			ch = rh;
		}
		if (cw > w || ch > h) {// 如果是大了，那么就要压缩
			bi = zoomImage(bi, cw, ch, x, y, w * 1.0 / cw);
		}
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ImageIO.write(bi, fileType, out);
		byte[] b = out.toByteArray();
		return b;
	}

	/*
	 * 以最大的宽度进行对图片进行剪切
	 */
	public static byte[] cutMaxHImageByte(byte[] content, String fileType,
			int max_w) throws Exception {
		if (StrFuncs.isEmpty(fileType)) {
			fileType = "jpg";
		}
		BufferedImage bi = ImageIO.read(new ByteArrayInputStream(content));
		/* 原始图像的宽度和高度 */
		int rh = bi.getHeight();
		int rw = bi.getWidth();

		int h = 0;
		int w = 0;
		if (max_w >= rw) {
			h = rh;
			w = rw;
		} else {
			return content;// 少于就不处理
			// h = max_w*rh/rw;
			// w = max_w;
		}

		// 剪切图片右上角坐标
		int x = 0;
		int y = 0;
		// 剪切的宽与高度
		int cw = max_w;
		int ch = h;

		if ((rh * 1.0 / rw) > (h * 1.0 / w)) {
			if (rh > h) {
				y = (rh - (int) (rw * h * 1.0 / w)) / 2;
				cw = rw;
				ch = rh - 2 * y;
			}
		} else if ((rh * 1.0 / rw) < (h * 1.0 / w)) {
			if (rw > w) {
				x = (rw - (int) (rh * w * 1.0 / h)) / 2;
				cw = rw - 2 * x;
				ch = rh;
			}
		} else if ((rh * 1.0 / rw) == (h * 1.0 / w)) {
			cw = rw;
			ch = rh;
		}
		if (cw > w || ch > h) {// 如果是大了，那么就要压缩
			bi = zoomImage(bi, cw, ch, x, y, w * 1.0 / cw);
		}
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ImageIO.write(bi, fileType, out);
		byte[] b = out.toByteArray();
		return b;
	}

	/*
	 * 输出图像到目的地
	 */
	public static void outImg(byte[] content, String dest) throws Exception {
		BufferedImage bi = ImageIO.read(new ByteArrayInputStream(content));
		/* 输出到文件流 */
		FileOutputStream newimage = new FileOutputStream(dest);
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(newimage);
		JPEGEncodeParam jep = JPEGCodec.getDefaultJPEGEncodeParam(bi);
		/* 压缩质量 */
		jep.setQuality(0.9f, true);
		encoder.encode(bi, jep);
		/* 近JPEG编码 */
		newimage.close();
	}

	/*
	 * 图片剪切与缩放
	 */
	private static BufferedImage zoomImage(BufferedImage bufImg, int w, int h,
			int x, int y, double scale) {
		// 剪切
		Image image = null;
		if (x > 0 || y > 0) {
			ImageFilter filter = new CropImageFilter(x, y, w, h);
			image = Toolkit.getDefaultToolkit().createImage(
					new FilteredImageSource(bufImg.getSource(), filter));
		}
		w = (int) (w * scale);
		h = (int) (h * scale);
		BufferedImage bufferedImage = new BufferedImage(w, h,
				BufferedImage.TYPE_INT_RGB);
		if (image == null) {
			bufferedImage.getGraphics()
					.drawImage(
							bufImg.getScaledInstance(w, h,
									java.awt.Image.SCALE_SMOOTH), 0, 0, null);
		} else {
			bufferedImage.getGraphics().drawImage(
					image.getScaledInstance(w, h, java.awt.Image.SCALE_SMOOTH),
					0, 0, null);
		}
		return bufferedImage;
	}

}
