package WarmUp.WarmUp.controllers;

import WarmUp.WarmUp.models.entity.Post;
import WarmUp.WarmUp.models.request.PostRequest;
import WarmUp.WarmUp.models.response.Message;
import WarmUp.WarmUp.models.response.PostResponseById;
import WarmUp.WarmUp.models.response.PostResponseForList;
import WarmUp.WarmUp.services.PostService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    PostService postService;

    @GetMapping("/list")
    public ResponseEntity<List<Post>> getAll() throws Exception {
        List<Post> list = postService.findAll();
        List<PostResponseForList>response = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            response.add(PostResponseForList.builder()
                            .id(list.get(i).getId())
                            .title(list.get(i).getTitle())
                            .image((list.get(i).getImage()))
                            .category(list.get(i).getCategory())
                            .creation_date(list.get(i).getCreation_date())
                    .build());
        }
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Post> getOne(@PathVariable("id") Long id) throws Exception {
        if(!postService.existsById(id))
            return new ResponseEntity(new Message("No existe"), HttpStatus.NOT_FOUND);
        Optional<Post> post = postService.findById(id);

        PostResponseById response = PostResponseById.builder()
                .id(post.get().getId())
                .title(post.get().getTitle())
                .image(post.get().getImage())
                .category(post.get().getCategory())
                .creation_date(post.get().getCreation_date())
                .build();

        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> save(@RequestBody PostRequest postRequest) throws Exception {

        if(StringUtils.isBlank(postRequest.getTitle()))
            return new ResponseEntity(new Message("El Titulo es obligatorio"), HttpStatus.BAD_REQUEST);

        if(StringUtils.isBlank(postRequest.getContent()))
            return new ResponseEntity(new Message("El Contenido es obligatorio"), HttpStatus.BAD_REQUEST);

        Post post = Post.builder()
                .title(postRequest.getTitle())
                .content(postRequest.getContent())
                .image(postRequest.getImage())
                .category(postRequest.getCategory())
                .creation_date(postRequest.getCreation_date())
                .build();
        postService.save(post);
        return new ResponseEntity(new Message("Post creado"), HttpStatus.OK);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody PostRequest postRequest) throws Exception {

        if(!postService.existsById(id))
            return new ResponseEntity(new Message("No existe"), HttpStatus.NOT_FOUND);

        if(StringUtils.isBlank(postRequest.getTitle()))
            return new ResponseEntity(new Message("El Titulo es obligatorio"), HttpStatus.BAD_REQUEST);

        if(StringUtils.isBlank(postRequest.getContent()))
            return new ResponseEntity(new Message("El Contenido es obligatorio"), HttpStatus.BAD_REQUEST);

        Optional<Post> oldPost = postService.findById(id);
        Post post = oldPost.get();
        post.setTitle(postRequest.getTitle());
        post.setContent(postRequest.getContent());
        post.setImage(postRequest.getImage());
        post.setCategory(postRequest.getCategory());
        post.setCreation_date(postRequest.getCreation_date());

        postService.update(id, post);
        return new ResponseEntity(new Message("Post actualizado"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) throws Exception {
        if(!postService.existsById(id))
            return new ResponseEntity(new Message("No existe"), HttpStatus.NOT_FOUND);
        postService.delete(id);
        return new ResponseEntity(new Message("Post eliminado"), HttpStatus.OK);
    }

}
