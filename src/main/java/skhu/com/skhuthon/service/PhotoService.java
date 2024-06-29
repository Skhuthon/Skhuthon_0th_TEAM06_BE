package skhu.com.skhuthon.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import skhu.com.skhuthon.domain.Photo;
import skhu.com.skhuthon.repository.PhotoRepository;

@Service
@RequiredArgsConstructor
public class PhotoService {

    private final PhotoRepository photorepository;
    private final S3Service s3Service;

    private final int MIN_RANDOM_NUM = 1;

    @Transactional
    public String upload(String title, String name, MultipartFile image, MultipartFile audio) {
        String imagePath = s3Service.upload(image);
        String audioPath = s3Service.upload(audio);
        Photo photo = Photo.builder()
                .title(title)
                .imagePath(imagePath)
                .audioPath(audioPath)
                .userName(name)
                .build();
        photorepository.save(photo);
        return "저장완료";
    }

    @Transactional
    public Photo findById(Long id) {
        return findPhotoById(id);
    }

    @Transactional
    public Photo findPhotoByRandom() {
        long photoCount = photorepository.count();
        int num = (int) (Math.floor(Math.random() * photoCount) + MIN_RANDOM_NUM);
        Optional<Photo> photo = photorepository.findById((long) num);
        return photo.orElseThrow(() -> new IllegalArgumentException("해당 사진이 없습니다."));
    }

    @Transactional
    public List<Photo> findAllByLike() {
        return photorepository.findAllByOrderByLikeCountDesc();
    }

    @Transactional
    public String deletePhoto(Long photoId, String name) {
        Photo photo = findPhotoById(photoId);
        validateUserName(name, photo.getUserName());

        s3Service.deleteImageFromS3(photo.getImagePath());
        s3Service.deleteImageFromS3(photo.getAudioPath());
        photorepository.delete(photo);
        return "삭제 완료";
    }

    @Transactional
    public void addLikeCount(Long photoId) {
        Photo photo = findPhotoById(photoId);
        photo.increaseLikeCount();
    }

    @Transactional
    public void deleteLikeCount(Long photoId) {
        Photo photo = photorepository.findById(photoId).orElseThrow(() -> new IllegalArgumentException("해당 사진이 없습니다."));
        photo.decreaseLikeCount();
    }

    private Photo findPhotoById(Long photoId) {
        return photorepository.findById(photoId).orElseThrow(() -> new IllegalArgumentException("해당 사진이 없습니다."));
    }

    private void validateUserName(String photoName, String userName) {
        if (!photoName.equals(userName)) {
            throw new IllegalArgumentException("사진을 삭제할 권한이 없습니다.");
        }
    }
}
