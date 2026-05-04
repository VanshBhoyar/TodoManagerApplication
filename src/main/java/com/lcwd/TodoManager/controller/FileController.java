package com.lcwd.TodoManager.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.util.Arrays;

@RestController
@RequestMapping("/file")
public class FileController {
    Logger logger = LoggerFactory.getLogger(FileController.class);

    @PostMapping("/single")
    public String uploadFileHandler(@RequestParam("image")MultipartFile file){
        logger.info("Name : {}",file.getName());
        logger.info("Content Type : {}",file.getContentType());
        logger.info("Original Name : {}",file.getOriginalFilename());
        logger.info("File Size : {}",file.getSize());
        return "File Uploaded Successfully!";
    }

    @PostMapping("/multiple")
    public String uploadMultiple(@RequestParam("files") MultipartFile[] files){
        Arrays.stream(files).forEach(file->{
            logger.info("File Name: {}",file.getOriginalFilename());
            logger.info("Content type: {}",file.getContentType());
            System.out.println("++++++++++++++++++++++++++++++++++++++");
        });
        return "Handling Multiple Files";
    }

    @GetMapping("/serve-image")
    public void serverImageHandler(HttpServletResponse response){
        try{
            FileInputStream fileInputStream = new FileInputStream("Images/payment.jpeg");
            response.setContentType(MediaType.IMAGE_JPEG_VALUE);
            StreamUtils.copy(fileInputStream, response.getOutputStream());
        }catch (Exception e){
            e.getStackTrace();
        }
    }

}
