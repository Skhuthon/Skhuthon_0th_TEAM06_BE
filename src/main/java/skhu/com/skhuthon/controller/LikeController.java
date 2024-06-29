package skhu.com.skhuthon.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import skhu.com.skhuthon.service.LikeService;

@RestController
@RequiredArgsConstructor
@RequestMapping("like")
public class LikeController {

    private final LikeService likeService;

    @PostMapping("/{id}")
    public String addLike(@PathVariable("id") Long id) {
        likeService.addLike(id);
        return "좋아요 추가";
    }

    @DeleteMapping ("/{id}")
    public String deleteLike(@PathVariable("id") Long id) {
        likeService.deleteLike(id);
        return "삭제 완료";
    }
}
