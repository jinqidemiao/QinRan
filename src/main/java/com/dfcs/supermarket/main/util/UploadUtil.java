package com.dfcs.supermarket.main.util;

import java.io.File;
import java.io.FileOutputStream;

/**
 * 上传工具类
 *
 * @author miaozhuang
 * @date 2020/01/11
 */
public class UploadUtil {
	
	public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception {
		File targetFile = new File(filePath);
		if (! targetFile.exists()) {
			targetFile.mkdirs();
		}
		FileOutputStream out = new FileOutputStream(filePath + fileName);
		out.write(file);
		out.flush();
		out.close();
	}
	
}
