package com.example.demoDatabase.file.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    Object loadFile(String fileName);

    Object uploadFile(MultipartFile file);

    void delete(String fileName);
}
