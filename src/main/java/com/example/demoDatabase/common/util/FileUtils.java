package com.example.demoDatabase.common.util;

import com.example.demoDatabase.common.exception.WBHBussinessExeption;
import lombok.experimental.UtilityClass;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@UtilityClass
public class FileUtils {
    public static final Path ROOTPATH = Paths.get("images");

    public static String saveFile(MultipartFile file) {
        if (init()) {
            try {
                Files.copy(file.getInputStream(), ROOTPATH.resolve(file.getOriginalFilename()));
                return file.getOriginalFilename();
            } catch (IOException e) {
                throw new WBHBussinessExeption("Can not save file: " + file.getOriginalFilename());
            }
        }
        throw new WBHBussinessExeption("Can not save file: " + file.getOriginalFilename());
    }

    public static void deleteFile(String fileName) {
        try {
            Path file = FileUtils.ROOTPATH.resolve(fileName);
            Files.delete(file);
        } catch (Exception e) {
            throw new WBHBussinessExeption(e.getMessage());
        }
    }

    private static boolean init() {
        try {
            if (!Files.exists(ROOTPATH)) {
                Files.createDirectory(ROOTPATH);
            }
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
