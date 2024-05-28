package com.example.demoDatabase.file.restResource;

import com.example.demoDatabase.common.util.ResponseUtil;
import com.example.demoDatabase.file.service.FileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
public class FileRestResource {
    private final FileService fileService;

    public FileRestResource(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping(value = "/{file-name}")
    public Object uploadFile(@PathVariable("file-name") String fileName) {
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(fileService.loadFile(fileName));
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Object uploadFile(@RequestPart("file") MultipartFile file) {
        return ResponseUtil.get(fileService.uploadFile(file), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public Object deleteFile(@RequestParam("file-name") String fileName) {
        fileService.delete(fileName);
        return ResponseUtil.get("Delete successfully", HttpStatus.OK);
    }
}
