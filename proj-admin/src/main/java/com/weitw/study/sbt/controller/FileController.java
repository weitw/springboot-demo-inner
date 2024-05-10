package com.weitw.study.sbt.controller;


import org.dromara.x.file.storage.core.FileInfo;
import org.dromara.x.file.storage.core.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileStorageService fileStorageService;//注入实列

    @Bean
    public void start() {
        System.out.println("初始化了");
    }

    @PostMapping("/upload")
    public FileInfo upload(MultipartFile file) {
        return fileStorageService.of(file).upload();
    }


    /**
     * 上传并转换成缩略图
     * @param file
     * @return
     */
    @PostMapping("/upload-img")
    public FileInfo uploadImg(MultipartFile file) {
        return fileStorageService.of(file)
                        .image(img -> img.size(1000,1000))
                                .thumbnail(th -> th.size(200,200))
                .upload();
    }
}
