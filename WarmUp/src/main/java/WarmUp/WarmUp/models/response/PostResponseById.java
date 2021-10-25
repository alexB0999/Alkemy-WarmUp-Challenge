package WarmUp.WarmUp.models.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PostResponseById {

    private long id;

    private String title;

    private String content;

    private String image;

    private String category;

    private Date creation_date;

    private boolean isDeleted;

}
