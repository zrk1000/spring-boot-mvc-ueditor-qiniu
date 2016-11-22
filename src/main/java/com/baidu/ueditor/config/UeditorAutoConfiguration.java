package com.baidu.ueditor.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.baidu.ueditor.ActionEnter;
import com.baidu.ueditor.ConfigManager;
import com.baidu.ueditor.hunter.FileManager;
import com.baidu.ueditor.upload.StorageManager;

@Configuration
@EnableConfigurationProperties(UeditorProperties.class)
@ConditionalOnClass(ActionEnter.class)
@ConditionalOnProperty(prefix="ueditor",value="enabled",matchIfMissing=true)
public class UeditorAutoConfiguration {

	@Autowired
	private UeditorProperties ueditorProperties;
	
	@Bean
	@ConditionalOnMissingBean(ActionEnter.class)
	public ActionEnter actionEnter(){
		ActionEnter actionEnter = new ActionEnter( new ConfigManager(ueditorProperties.getConfig()));
		return actionEnter;
	}
	@PostConstruct
	public void storagemanager(){
		StorageManager.accessKey = FileManager.accessKey = ueditorProperties.getAccessKey();
		StorageManager.secretKey = FileManager.secretKey = ueditorProperties.getSecretKey();
		StorageManager.baseUrl  = FileManager.baseUrl = ueditorProperties.getBaseUrl();
		StorageManager.bucket = FileManager.bucket  = ueditorProperties.getBucket();
		StorageManager.baseUrl = FileManager.baseUrl  = ueditorProperties.getBaseUrl();
		StorageManager.uploadDirPrefix = FileManager.uploadDirPrefix  = ueditorProperties.getUploadDirPrefix();
		
		
	}
	
}
