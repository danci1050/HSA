package com.example.HSA.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


@RequestMapping("/api/v1/upload")
@RestController
public class FileUploadController {


    @Autowired
    private ServletContext ServerletContext;


    @PostMapping
    public ResponseEntity<?> handleFileUpload(@RequestParam("file") MultipartFile file) throws IOException {



        String path = System.getProperty("user.dir") + "\\src\\main\\www\\html\\files\\";
        Files.createDirectories(Paths.get(path));
        try {

            file.transferTo(new File(path+file.getOriginalFilename()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while uploading");
        }
        return ResponseEntity.status(HttpStatus.OK).body(file.getOriginalFilename());
    }
}
