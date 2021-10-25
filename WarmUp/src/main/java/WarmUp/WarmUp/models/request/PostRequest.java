package WarmUp.WarmUp.models.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PostRequest {

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    private String image;

    private String category;

    private Date creation_date;
}
