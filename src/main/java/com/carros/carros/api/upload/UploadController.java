package com.carros.carros.api.upload;


import com.carros.carros.domain.upload.FirebasesStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.carros.carros.api.upload.UploadInput;
import com.carros.carros.api.upload.UploadInput;
import java.io.IOException;

@RestController
@RequestMapping("/api/v1/upload")
public class UploadController {
    @Autowired
    private FirebasesStoreService uploadService;


    public ResponseEntity upload(@RequestBody UploadInput uploadInput ) throws IOException {
        String url = uploadService.upload(uploadInput);
        return ResponseEntity.ok(new UploadOutput(url));
    }
}
