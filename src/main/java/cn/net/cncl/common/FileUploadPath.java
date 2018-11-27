package cn.net.cncl.common;

import java.io.File;

import org.springframework.util.ResourceUtils;

public class FileUploadPath {

	/*
	 * 获取文件上传地址
	 */
	public static File getFileUploadPath() throws Exception {
		File path = new File(ResourceUtils.getURL("classpath:").getPath(), Constant.STATIC_PATH + Constant.STATIC_FILE_PATH);

		// 如果路径不存在
		if (false == path.exists() || false == path.getParentFile().exists()) {
			path.getParentFile().mkdirs();
			path.mkdirs();
			return path;
		} else {
			System.out.println("已存在");
			return path;
		}
	}

	/*
	 * 获取图片类型
	 * 
	 * allowedFileExtensions : [ 'jpg', 'png', 'gif', 'jpeg', 'bmp' ],
	 */
	public static String getType(String type) {
		if (type.equals(Constant.IMAGE_PNG))
			return ".png";
		else if (type.equals(Constant.IMAGE_GIF))
			return ".gif";
		else if (type.equals(Constant.IMAGE_BMP))
			return ".bmp";
		else if (type.equals(Constant.IMAGE_JPG_JPEG))
			return ".jpg";
		else
			return null;
	}

}
