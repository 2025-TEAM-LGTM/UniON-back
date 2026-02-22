package com.union.demo.controller;

import com.union.demo.dto.response.PresignResDto;
import com.union.demo.global.common.ApiResponse;
import com.union.demo.utill.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/presign")
public class UploadController {
    private final S3Uploader s3Uploader;

    @PostMapping
    public ResponseEntity<ApiResponse<PresignResDto>> presign(
            @RequestParam String contentType
    ){
        S3Uploader.PresignResult result=s3Uploader.createPresignedUrl(contentType);

        PresignResDto data=PresignResDto.builder()
                .key(result.key())
                .presignedUrl(result.presignedUrl())
                .build();

        return ResponseEntity.ok(ApiResponse.ok(data));
    }


}