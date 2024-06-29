package skhu.com.skhuthon.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import skhu.com.skhuthon.domain.Photo;
import skhu.com.skhuthon.service.PhotoService;


@RestController
@RequiredArgsConstructor
@RequestMapping("photo")
public class PhotoController {

    private final PhotoService photoService;

    @GetMapping("/find/random")
    public Photo getPhotoByRandom() {

        return photoService.findPhotoByRandom();
    }


    @PostMapping("/upload")
    public String upload(HttpSession session, @RequestParam("title") String title, @RequestPart("image") MultipartFile image, @RequestPart("audio") MultipartFile audio) throws Exception{
        String name = (String) session.getAttribute("name");
        photoService.upload(title, name, image, audio);
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
    public String delete(HttpSession session,@PathVariable Long id){
        String name = (String) session.getAttribute("name");
        photoService.deletePhoto(id,name);
        return "삭제 완료";
    }
}