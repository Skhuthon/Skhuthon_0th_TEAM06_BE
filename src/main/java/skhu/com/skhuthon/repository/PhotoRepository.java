package skhu.com.skhuthon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import skhu.com.skhuthon.domain.Photo;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Long> {

    Optional<Photo> findById(Long id);

    List<Photo> findAllByOrderByLikeCountDesc();
}
