package com.union.demo.controller;

import com.union.demo.utill.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/uploads")
public class UploadController {

    private final S3Uploader s3Uploader;

    @PostMapping("/presign")
    public String presign(@RequestParam String contentType) {
        return s3Uploader.createPresignedUrl(contentType);
    }
}