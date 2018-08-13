package com.plateau.upload.sample;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class EurekaFeignUploadServerApplication {

    @RestController
    public class UploadController {

        @PostMapping(value = "/uploadFile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
        public String handleFileUpload(@RequestPart(value = "file") MultipartFile file) throws IOException {
            System.out.println(new String(file.getBytes(), "UTF-8"));
            return file.getOriginalFilename();
        }
        
        @PostMapping(value = "/formPost")
        public String handleFormPost(@RequestParam("test") String test, @RequestParam("test1") String test1) {
            System.out.println(test);
            System.out.println(test1);
            return test+test1;
        }
        
        @PostMapping(value = "/upload/files")
        public String handleFilesUpload(@RequestPart MultipartFile file1, @RequestPart MultipartFile file2, @RequestPart MultipartFile file3) throws IOException {
            System.out.println(new String(file1.getBytes(), "UTF-8"));
            System.out.println(new String(file2.getBytes(), "UTF-8"));
            System.out.println(new String(file3.getBytes(), "UTF-8"));
            return file1.getOriginalFilename() + "-" + file2.getOriginalFilename() + "-" + file3.getOriginalFilename();
        }
        
        @PostMapping(value = "/files/content")
        public String handleStringAndFilesUpload(@RequestPart String content, @RequestPart MultipartFile file, @RequestPart MultipartFile[] files) throws IOException {
            System.out.println(content);
            System.out.println(new String(file.getBytes(), "UTF-8"));
            System.out.println(new String(files[0].getBytes(), "UTF-8"));
            System.out.println(new String(files[1].getBytes(), "UTF-8"));
            return file.getOriginalFilename() + "-" + files[0].getOriginalFilename() + "-" + files[1].getOriginalFilename();
        }

    }
    
	public static void main(String[] args) {
		SpringApplication.run(EurekaFeignUploadServerApplication.class, args);
	}
}
