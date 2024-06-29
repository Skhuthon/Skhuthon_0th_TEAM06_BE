package skhu.com.skhuthon.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Photo {

    private static final int DEFAULT_LIKE_NUM = 0;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "photo_id")
    private Long id;

    @Column(name = "photo_title")
    private String title;

    @Column(name = "photo_path")
    private String imagePath;

    @Column(name = "like_count")
    private int likeCount = DEFAULT_LIKE_NUM;

    @OneToMany(mappedBy = "photo")
    @JsonIgnore
    private List<Like> likes = new ArrayList<>();

    public void increaseLikeCount(){
        this.likeCount++;
    }
    public void decreaseLikeCount(){
        this.likeCount--;
    }
}
