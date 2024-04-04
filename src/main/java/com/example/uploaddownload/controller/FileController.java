package com.example.uploaddownload.controller;

import com.example.uploaddownload.service.FileStorageService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class FileController {

    @Autowired
    FileStorageService fileStorageService;

    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestParam MultipartFile file) throws IOException {
        return ResponseEntity.ok(fileStorageService.upload(file));
    }

    @GetMapping("/download")
    public ResponseEntity<byte[]> download(@RequestParam String file, HttpServletResponse response) throws IOException {
        return ResponseEntity.ok(fileStorageService.download(file, response));
    }
}
