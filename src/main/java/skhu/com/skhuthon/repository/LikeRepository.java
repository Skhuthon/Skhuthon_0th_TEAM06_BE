package skhu.com.skhuthon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import skhu.com.skhuthon.domain.Like;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {

    int countLikesByPhotoId(Long photoId);

}
