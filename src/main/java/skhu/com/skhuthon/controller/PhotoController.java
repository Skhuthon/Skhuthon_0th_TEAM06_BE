package skhu.com.skhuthon.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import skhu.com.skhuthon.domain.Photo;
import skhu.com.skhuthon.service.PhotoService;
import skhu.com.skhuthon.service.S3Service;

@RestController
@RequiredArgsConstructor
@RequestMapping("photo")
public class PhotoController {

    private final PhotoService photoService;

    @GetMapping("/find/random")
    public Photo photo() {
        return photoService.findPhotoByRandom();
    }


    @PostMapping("/upload")
    public String upload(@RequestParam("title") String title, @RequestPart("image") MultipartFile file) throws Exception{
        photoService.upload(title, file);
        return "업로드 완료";
    }

    @GetMapping("/find/all")
    public List<Photo> findAllByLike(){
        return photoService.findAllByLike();
    }

    @GetMapping("/find/{id}")
    public Photo search(@PathVariable Long id){
        return photoService.findById(id);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        photoService.deletePhoto(id);
        return "삭제 완료";
    }

	/*
	@PostMapping("/photo/{id}/like")
	public ResponseEntity<Object> like(HttpSession session,@PathVariable int id){

	}
	*/

    //삭제
}