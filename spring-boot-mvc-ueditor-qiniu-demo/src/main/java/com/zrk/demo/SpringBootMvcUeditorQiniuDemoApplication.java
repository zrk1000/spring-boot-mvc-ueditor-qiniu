package com.zrk.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@ComponentScan(basePackages = {"com.zrk","com.baidu"})
@SpringBootApplication
public class SpringBootMvcUeditorQiniuDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMvcUeditorQiniuDemoApplication.class, args);
	}
	
	@RequestMapping("/")
	public String index(){
		return "ueditor";
	}
	
}
