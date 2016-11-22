package com.baidu.ueditor.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 百度ueditor配置
 * @author zrk
 * @date 2016年11月10日 上午10:11:58
 */
@ConfigurationProperties(prefix = "ueditor")
public class UeditorProperties {
	
	private String config = "{}";
	
	private String accessKey;
	
	private String secretKey;
	
	private String bucket;
	
	private String baseUrl;
	
	private String uploadDirPrefix = "ueditor/file/";

	public String getConfig() {
		return config;
	}

	public void setConfig(String config) {
		this.config = config;
	}

	public String getAccessKey() {
		return accessKey;
	}

	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	public String getBucket() {
		return bucket;
	}

	public void setBucket(String bucket) {
		this.bucket = bucket;
	}

	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public String getUploadDirPrefix() {
		return uploadDirPrefix;
	}

	public void setUploadDirPrefix(String uploadDirPrefix) {
		this.uploadDirPrefix = uploadDirPrefix;
	}
	
	


}
