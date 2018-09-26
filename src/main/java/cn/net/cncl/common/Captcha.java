package cn.net.cncl.common;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 验证码 CAPTCHA<br>
 * 全自动区分计算机和人类的图灵测试<br>
 * Completely Automated Public Turing test to tell Computers and Humans
 * Apart<br>
 * 是一种区分用户是计算机还是人的公共全自动程序。可以防止：恶意破解密码、刷票、论坛灌水，有效防止某个黑客对某一个特定注册用户用特定程序暴力破解方式进行不断的登陆尝试，实际上用验证码是现在很多网站通行的方式，我们利用比较简易的方式实现了这个功能。这个问题可以由计算机生成并评判，但是必须只有人类才能解答。由于计算机无法解答CAPTCHA的问题，所以回答出问题的用户就可以被认为是人类。
 */
public class Captcha {

	private final static Captcha Captcha = new Captcha();

	final static int imageWidth = 200;
	final static int imageHeight = 100;
	final static int fontSize = 60;

	static Random random = new Random();

	/**
	 * 创建验证随机数
	 */
	public static List<Integer> getCaptchaNumber() {
		List<Integer> randList = new ArrayList<Integer>();
		for (int i = 0; i < 4; i++) {
			randList.add(random.nextInt(10));
		}
		return randList;
	}

	public static BufferedImage getCaptchaImage(List<Integer> randList) {

		// 创建一张背景图片
		// int width,int height,单位：像素
		BufferedImage image = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);

		// 创建画板
		Graphics2D graphics = image.createGraphics();

		// 设置画笔
		graphics.setColor(Captcha.getColor());
		graphics.fillRect(0, 0, imageWidth, imageHeight);

		// 将随机数写入图片
		graphics.setFont(new Font("微软雅黑", Font.BOLD | Font.ITALIC, fontSize));
		for (int i = 0; i < randList.size(); i++) {
			graphics.setColor(Captcha.getColor());
			graphics.drawString(randList.get(i) + "", i * (imageWidth / randList.size()) + 5, (imageHeight - fontSize) / 2 + fontSize + (random.nextInt(21) - 10));
		}

		// 画线
		// drawLine() x1 y1 起点，x2 y2 终点
		for (int i = 0; i < 4; i++) {
			graphics.setColor(Captcha.getColor());
			graphics.drawLine(0, random.nextInt(imageHeight + 1), imageWidth, random.nextInt(imageHeight + 1));
		}

		return image;
	}

	/**
	 * 设置随机颜色
	 */
	public Color getColor() {
		int rgb = 0;
		int r = 0, g = 0, b = 0;

		for (int i = 0; i < 3; i++) {
			rgb = (int) (Math.random() * (255 - 1));
			if (i == 0)
				r = rgb;
			if (i == 1)
				g = rgb;
			if (i == 2)
				b = rgb;
		}

		Color color = new Color(r, g, b);
		return color;
	}

	public static void main(String[] args) {
	}

}
