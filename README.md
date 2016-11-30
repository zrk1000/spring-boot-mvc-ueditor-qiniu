# spring-boot-mvc-ueditor-qiniu
spring boot 、springMVC环境集成百度ueditor富文本编辑器，使用七牛云存储图片

**源码：https://github.com/zrk1000/spring-boot-mvc-ueditor-qiniu**</br>
**demo：https://github.com/zrk1000/spring-boot-mvc-ueditor-qiniu-demo**</br>
**博客：http://blog.csdn.net/zrk1000/article/details/53283964**</br>


在spring boot环境中，只需引入jar，配置文件中添加config.json配置及七牛相关配置即可使用。</br>

##使用：

1、引入jar (源码在github，请自行编译后再引入)：
```
<dependency>
    <groupId>com.baidu</groupId>
    <artifactId>spring-boot-ueditor-mvc</artifactId>
    <version>1.4.3.3-SNAPSHOT</version>
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
#开启thymeleaf
spring.thymeleaf.enabled=true
#上传文件大小
spring.http.multipart.max-file-size=500MB
spring.http.multipart.max-request-size=20MB

#ueditor的config.json配置，原配置在ueditor资源目录jsp/config.json，拷到此处时请将原json去除掉注释，并压缩为一行，参考 “config纯净版.json”
ueditor.config={"imageActionName":"uploadimage","imageFieldName":"upfile","imageMaxSize":2048000,"imageAllowFiles":[".png",".jpg",".jpeg",".gif",".bmp"],"imageCompressEnable":true,"imageCompressBorder":1600,"imageInsertAlign":"none","imageUrlPrefix":"","imagePathFormat":"/ueditor/jsp/upload/image/{yyyy}{mm}{dd}/{time}{rand:6}","scrawlActionName":"uploadscrawl","scrawlFieldName":"upfile","scrawlPathFormat":"/ueditor/jsp/upload/image/{yyyy}{mm}{dd}/{time}{rand:6}","scrawlMaxSize":2048000,"scrawlUrlPrefix":"","scrawlInsertAlign":"none","snapscreenActionName":"uploadimage","snapscreenPathFormat":"/ueditor/jsp/upload/image/{yyyy}{mm}{dd}/{time}{rand:6}","snapscreenUrlPrefix":"","snapscreenInsertAlign":"none","catcherLocalDomain":["127.0.0.1","localhost","img.baidu.com"],"catcherActionName":"catchimage","catcherFieldName":"source","catcherPathFormat":"/ueditor/jsp/upload/image/{yyyy}{mm}{dd}/{time}{rand:6}","catcherUrlPrefix":"","catcherMaxSize":2048000,"catcherAllowFiles":[".png",".jpg",".jpeg",".gif",".bmp"],"videoActionName":"uploadvideo","videoFieldName":"upfile","videoPathFormat":"/ueditor/jsp/upload/video/{yyyy}{mm}{dd}/{time}{rand:6}","videoUrlPrefix":"","videoMaxSize":102400000,"videoAllowFiles":[".flv",".swf",".mkv",".avi",".rm",".rmvb",".mpeg",".mpg",".ogg",".ogv",".mov",".wmv",".mp4",".webm",".mp3",".wav",".mid"],"fileActionName":"uploadfile","fileFieldName":"upfile","filePathFormat":"/ueditor/jsp/upload/file/{yyyy}{mm}{dd}/{time}{rand:6}","fileUrlPrefix":"","fileMaxSize":51200000,"fileAllowFiles":[".png",".jpg",".jpeg",".gif",".bmp",".flv",".swf",".mkv",".avi",".rm",".rmvb",".mpeg",".mpg",".ogg",".ogv",".mov",".wmv",".mp4",".webm",".mp3",".wav",".mid",".rar",".zip",".tar",".gz",".7z",".bz2",".cab",".iso",".doc",".docx",".xls",".xlsx",".ppt",".pptx",".pdf",".txt",".md",".xml"],"imageManagerActionName":"listimage","imageManagerListPath":"/ueditor/jsp/upload/image/","imageManagerListSize":20,"imageManagerUrlPrefix":"","imageManagerInsertAlign":"none","imageManagerAllowFiles":[".png",".jpg",".jpeg",".gif",".bmp"],"fileManagerActionName":"listfile","fileManagerListPath":"/ueditor/jsp/upload/file/","fileManagerUrlPrefix":"","fileManagerListSize":20,"fileManagerAllowFiles":[".png",".jpg",".jpeg",".gif",".bmp",".flv",".swf",".mkv",".avi",".rm",".rmvb",".mpeg",".mpg",".ogg",".ogv",".mov",".wmv",".mp4",".webm",".mp3",".wav",".mid",".rar",".zip",".tar",".gz",".7z",".bz2",".cab",".iso",".doc",".docx",".xls",".xlsx",".ppt",".pptx",".pdf",".txt",".md",".xml"]}

#七牛云存储配置
ueditor.access-key=8nU0zA9aTvfHBZs0fPZZWd8gpnFRtOkOPkiTB6M0
ueditor.secret-key=400iGAeaeJyjgSm26-wT8R-HQYZbBR1el_cDiRIq
ueditor.bucket=zrk-test
#域名，可使用七牛提供的域名，或自己绑定的域名
ueditor.base-url=http://od710rrnd.bkt.clouddn.com
#文件上传到七牛的目录，默认为‘ueditor/file/’，请使用‘/’结尾
ueditor.upload-dir-prefix=biz/img/
```


