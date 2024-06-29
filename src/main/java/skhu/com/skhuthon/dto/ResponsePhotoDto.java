package skhu.com.skhuthon.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponsePhotoDto {
    Long id;
    String title;
    String photoUrl;
    int likeCount;
}
