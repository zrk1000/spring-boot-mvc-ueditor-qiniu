# spring-boot-mvc-ueditor-qiniu
spring boot 、springMVC环境集成百度ueditor富文本编辑器，使用七牛云存储图片

依赖库版本：
- spring boot ：1.5.3.RELEASE
- qiniu-sdk :   [7.2.0, 7.2.99]
- 基于ueditor 1.4.3.3版本源码修改

此版本更新说明：
- 将spring-boot-mvc-ueditor-qiniu源码与demo放入同一个maven项目中
- 更新了spring boot版本，七牛sdk版本
- 增加了ueditor.zone属性，可配置七牛服务器区域Zone

**源码：https://github.com/zrk1000/spring-boot-mvc-ueditor-qiniu**</br>
**博客：http://blog.csdn.net/zrk1000/article/details/53283964**</br>


在spring boot环境中，只需引入jar，配置文件中添加config.json配置及七牛相关配置即可使用。</br>

##使用：

1、引入jar (源码在github，请自行编译后再引入)：
```
<!--注意：此版本与上个版本maven坐标不同,-->
<dependency>
    <groupId>com.zrk1000</groupId>
    <artifactId>spring-boot-mvc-ueditor-qiniu</artifactId>
    <version>ueditor1.4.3.3-qiniu7.2.X</version>
</dependency>
```
2、spring boot启动类添加扫描: @ComponentScan(basePackages = {“com.baidu”})：
```
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
```
3、application.properties中添加ueditor的config.json配置，和七牛相关配置：
```
spring.thymeleaf.enabled=true

spring.http.multipart.max-file-size=500MB
spring.http.multipart.max-request-size=20MB

ueditor.config={"imageActionName":"uploadimage","imageFieldName":"upfile","imageMaxSize":2048000,"imageAllowFiles":[".png",".jpg",".jpeg",".gif",".bmp"],"imageCompressEnable":true,"imageCompressBorder":1600,"imageInsertAlign":"none","imageUrlPrefix":"","imagePathFormat":"/ueditor/jsp/upload/image/{yyyy}{mm}{dd}/{time}{rand:6}","scrawlActionName":"uploadscrawl","scrawlFieldName":"upfile","scrawlPathFormat":"/ueditor/jsp/upload/image/{yyyy}{mm}{dd}/{time}{rand:6}","scrawlMaxSize":2048000,"scrawlUrlPrefix":"","scrawlInsertAlign":"none","snapscreenActionName":"uploadimage","snapscreenPathFormat":"/ueditor/jsp/upload/image/{yyyy}{mm}{dd}/{time}{rand:6}","snapscreenUrlPrefix":"","snapscreenInsertAlign":"none","catcherLocalDomain":["127.0.0.1","localhost","img.baidu.com"],"catcherActionName":"catchimage","catcherFieldName":"source","catcherPathFormat":"/ueditor/jsp/upload/image/{yyyy}{mm}{dd}/{time}{rand:6}","catcherUrlPrefix":"","catcherMaxSize":2048000,"catcherAllowFiles":[".png",".jpg",".jpeg",".gif",".bmp"],"videoActionName":"uploadvideo","videoFieldName":"upfile","videoPathFormat":"/ueditor/jsp/upload/video/{yyyy}{mm}{dd}/{time}{rand:6}","videoUrlPrefix":"","videoMaxSize":102400000,"videoAllowFiles":[".flv",".swf",".mkv",".avi",".rm",".rmvb",".mpeg",".mpg",".ogg",".ogv",".mov",".wmv",".mp4",".webm",".mp3",".wav",".mid"],"fileActionName":"uploadfile","fileFieldName":"upfile","filePathFormat":"/ueditor/jsp/upload/file/{yyyy}{mm}{dd}/{time}{rand:6}","fileUrlPrefix":"","fileMaxSize":51200000,"fileAllowFiles":[".png",".jpg",".jpeg",".gif",".bmp",".flv",".swf",".mkv",".avi",".rm",".rmvb",".mpeg",".mpg",".ogg",".ogv",".mov",".wmv",".mp4",".webm",".mp3",".wav",".mid",".rar",".zip",".tar",".gz",".7z",".bz2",".cab",".iso",".doc",".docx",".xls",".xlsx",".ppt",".pptx",".pdf",".txt",".md",".xml"],"imageManagerActionName":"listimage","imageManagerListPath":"/ueditor/jsp/upload/image/","imageManagerListSize":20,"imageManagerUrlPrefix":"","imageManagerInsertAlign":"none","imageManagerAllowFiles":[".png",".jpg",".jpeg",".gif",".bmp"],"fileManagerActionName":"listfile","fileManagerListPath":"/ueditor/jsp/upload/file/","fileManagerUrlPrefix":"","fileManagerListSize":20,"fileManagerAllowFiles":[".png",".jpg",".jpeg",".gif",".bmp",".flv",".swf",".mkv",".avi",".rm",".rmvb",".mpeg",".mpg",".ogg",".ogv",".mov",".wmv",".mp4",".webm",".mp3",".wav",".mid",".rar",".zip",".tar",".gz",".7z",".bz2",".cab",".iso",".doc",".docx",".xls",".xlsx",".ppt",".pptx",".pdf",".txt",".md",".xml"]}
ueditor.access-key=8nU0zA9aTvfHBZs0fPZZWd8gpnFRtOkOPkiTB6M0
ueditor.secret-key=400iGAeaeJyjgSm26-wT8R-HQYZbBR1el_cDiRIq
#七牛机房  华东：zone0 华北：zone1 华南：zone2 北美：zoneNa0
# ueditor.zone也可不配置，会自动识别区域
ueditor.zone=zone0
#zrk-test 华东
ueditor.bucket=zrk-test
ueditor.base-url=http://od710rrnd.bkt.clouddn.com
#zrk-test-huabei 华北
#ueditor.bucket=zrk-test-huabei
#ueditor.base-url=http://ordwj6hok.bkt.clouddn.com
ueditor.upload-dir-prefix=biz/img/
```


