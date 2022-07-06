package com.example.HSA.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

@RequestMapping("/api/v1/images")
@RestController
public class SiteImageController {
    @Autowired
    private ServletContext ServerletContext;
    private static final String UPLOAD_DIR = System.getProperty("user.dir")+"\\src\\main\\www\\html\\images\\";



    @GetMapping
    public ResponseEntity<?> getImages() {
        File folder = new File(UPLOAD_DIR);
        File[] listOfFiles = folder.listFiles();
        String[] fileNames = new String[listOfFiles.length];
        for (int i = 0; i < listOfFiles.length; i++) {
            fileNames[i] = listOfFiles[i].getName();
            System.out.println(fileNames[i]);
        }
        return ResponseEntity.status(HttpStatus.OK).body(fileNames);
    }



    @PostMapping("/{imageName}")
    public ResponseEntity<?> handleFileUpload(@PathVariable("imageName") String imageName, @RequestParam("image") MultipartFile file) throws IOException {

        Files.createDirectories(Paths.get(UPLOAD_DIR));
        try {
            if(!file.getOriginalFilename().equals("") && file.getOriginalFilename() != null) {
                File original = new File(UPLOAD_DIR + imageName);
                System.out.println(original.getAbsolutePath());
                    original.delete();
                file.transferTo(new File(UPLOAD_DIR+imageName));

            }

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while uploading");
        }
        return ResponseEntity.status(HttpStatus.OK).body(file.getOriginalFilename());
    }
}
