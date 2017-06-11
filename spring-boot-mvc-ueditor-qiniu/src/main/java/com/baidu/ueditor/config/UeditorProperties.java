package com.baidu.ueditor.config;

import com.qiniu.common.Zone;
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
	
	private String zone = "autoZone";			//七牛机房  华东：zone0 华北：zone1 华南：zone2 北美：zoneNa0

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

	public String getZone() {
		return zone;
	}

	public void setZone(String zone) {
		this.zone = zone;
	}

	public Zone getZoneObj(){
//		华东：zone0 华北：zone1 华南：zone2 北美：zoneNa0
		switch (zone){
			case "zone0": return Zone.zone0();
			case "zone1": return Zone.zone1();
			case "zone2": return Zone.zone2();
			case "zoneNa0": return Zone.zoneNa0();
			default:return Zone.autoZone();
		}
	}
}
