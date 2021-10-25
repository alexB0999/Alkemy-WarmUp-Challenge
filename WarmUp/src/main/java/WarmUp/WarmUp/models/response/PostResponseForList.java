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
public class PostResponseForList {

    private long id;

    private String title;

    private String image;

    private String category;

    private Date creation_date;

}
