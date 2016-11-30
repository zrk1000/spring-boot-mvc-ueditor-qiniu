package com.baidu.ueditor.upload;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;

import com.baidu.ueditor.define.AppInfo;
import com.baidu.ueditor.define.BaseState;
import com.baidu.ueditor.define.FileType;
import com.baidu.ueditor.define.State;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;

public class StorageManager {
	public static final int BUFFER_SIZE = 8192;
	
	public static String accessKey;
	public static String secretKey;
	public static String baseUrl;
	public static String bucket;
	public static String uploadDirPrefix;
	
	public static State saveBinaryFile(byte[] data, String path) {

		State state = null ;
		String key = uploadDirPrefix + getFileName(path);
		try {
			String uploadToken = Auth.create(accessKey, secretKey).uploadToken(bucket);
			Response response = new UploadManager().put(data, key, uploadToken);
			if (response.statusCode == 200) {
				state = new BaseState(true);
				state.putInfo("size", data.length);
				state.putInfo("title", path);
				state.putInfo("url", baseUrl + key);
			} else {
				state = new BaseState(false, AppInfo.IO_ERROR);
			}
		} catch (QiniuException e) {
			state = new BaseState(false, AppInfo.IO_ERROR);
		}
		return state;
	}

	public static State saveFileByInputStream(InputStream is, String path,long maxSize) {
		State state = null;
		File tmpFile = getTmpFile();
		
		byte[] dataBuf = new byte[ 2048 ];
		BufferedInputStream bis = new BufferedInputStream(is, StorageManager.BUFFER_SIZE);

		try {
			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(tmpFile), StorageManager.BUFFER_SIZE);
			int count = 0;
			while ((count = bis.read(dataBuf)) != -1) {
				bos.write(dataBuf, 0, count);
			}
			bos.flush();
			bos.close();

			if (tmpFile.length() > maxSize) {
				tmpFile.delete();
				return new BaseState(false, AppInfo.MAX_SIZE);
			}

			state = saveTmpFile(tmpFile, path);

			if (!state.isSuccess()) {
				tmpFile.delete();
			}

			return state;
			
		} catch (IOException e) {
		}
		return new BaseState(false, AppInfo.IO_ERROR);
	}

	public static State saveFileByInputStream(InputStream is, String path) {
		State state = null;

		File tmpFile = getTmpFile();

		byte[] dataBuf = new byte[ 2048 ];
		BufferedInputStream bis = new BufferedInputStream(is, StorageManager.BUFFER_SIZE);

		try {
			BufferedOutputStream bos = new BufferedOutputStream( new FileOutputStream(tmpFile), StorageManager.BUFFER_SIZE);

			int count = 0;
			while ((count = bis.read(dataBuf)) != -1) {
				bos.write(dataBuf, 0, count);
			}
			bos.flush();
			bos.close();

			state = saveTmpFile(tmpFile, path);

			if (!state.isSuccess()) {
				tmpFile.delete();
			}

			return state;
		} catch (IOException e) {
		}
		return new BaseState(false, AppInfo.IO_ERROR);
	}
	
	private static File getTmpFile() {
		File tmpDir = FileUtils.getTempDirectory();
		String tmpFileName = (Math.random() * 10000 + "").replace(".", "");
		return new File(tmpDir, tmpFileName);
	}

	private static State saveTmpFile(File tmpFile, String path) {
		State state = null ;
		String key = uploadDirPrefix + getFileName(path);
		try {
			String uploadToken = Auth.create(accessKey, secretKey).uploadToken(bucket);
			Response response = new UploadManager().put(tmpFile, key, uploadToken);
			if (response.statusCode == 200) {
				state = new BaseState(true);
				state.putInfo("size", tmpFile.length());
				state.putInfo("title", key);
				state.putInfo("url", baseUrl +"/"+ key);
			} else {
				state = new BaseState(false, AppInfo.IO_ERROR);
			}
		} catch (QiniuException e) {
			state = new BaseState(false, AppInfo.IO_ERROR);
		}
		return state;
	}
	
	private static String getFileName(String fileName) {
		String suffix =  FileType.getSuffixByFilename(fileName);
		return new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date())+ (int)(Math.random()*9000 +1000) + suffix;
	}
}
