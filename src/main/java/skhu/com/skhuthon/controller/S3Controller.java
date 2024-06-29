package skhu.com.skhuthon.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import skhu.com.skhuthon.service.S3Service;

@RestController
@RequiredArgsConstructor
public class S3Controller {

    private final S3Service s3Service;

    @PostMapping("/s3/upload")
    public String s3Upload(@RequestPart(value = "image", required = false)MultipartFile image){
        String profileImage = s3Service.upload(image);
        return "저장 완료";
    }

    @DeleteMapping("/s3/delete")
    public ResponseEntity<?> s3delete(@RequestParam String addr){
        s3Service.deleteImageFromS3(addr);
        return ResponseEntity.ok(null);
    }
}
