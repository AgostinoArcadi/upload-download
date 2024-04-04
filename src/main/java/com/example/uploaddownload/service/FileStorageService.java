package com.example.uploaddownload.service;

import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

@Service
public class FileStorageService {

    @Value("${fileDirectory}")
    private String directory;

    public String upload(MultipartFile file) throws IOException {

        String fileString = file.getOriginalFilename();
        String extension = FilenameUtils.getExtension(fileString);
        String randomFileName = UUID.randomUUID().toString();
        String completedName = randomFileName + "." + extension;

        File fileDirectory = new File(directory);

        if(!fileDirectory.exists()) {
            throw new IOException();
        }
        File directorySavedFile = new File(directory + File.separator + completedName);
        if(directorySavedFile.exists()) {
            throw new IOException();
        }
        file.transferTo(directorySavedFile);
        return completedName;
    }

    public byte[] download(String fileToDownload, HttpServletResponse response) throws IOException {

        File destinationFolder = new File(directory + File.separator + fileToDownload);

        if(!destinationFolder.exists()) {
            throw new IOException("File not found");
        }

        String extension = FilenameUtils.getExtension(fileToDownload);

        switch (extension) {
            case "gif":
                response.setContentType(MediaType.IMAGE_GIF_VALUE);
                break;
            case "jpg":
            case "jpeg":
                response.setContentType(MediaType.IMAGE_JPEG_VALUE);
                break;
            case "png":
                response.setContentType(MediaType.IMAGE_PNG_VALUE);
                break;
        }
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileToDownload + "\"");
        return IOUtils.toByteArray(new FileInputStream(destinationFolder));
    }

}
