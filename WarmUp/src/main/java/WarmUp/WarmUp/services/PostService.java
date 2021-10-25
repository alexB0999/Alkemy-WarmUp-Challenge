package WarmUp.WarmUp.services;

import WarmUp.WarmUp.models.entity.Post;
import WarmUp.WarmUp.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PostService {

    @Autowired
    PostRepository postRepository;

    public List<Post> findAll(){
         return postRepository.findAll();
    }

    public Optional<Post> findById(Long id){
        return postRepository.findById(id);
    }

    public void save (Post post){
        postRepository.save(post);
    }

    public void update (Long id, Post post) {
        postRepository.save(post);
    }

    public void delete (Long id) throws Exception {
        try{
            if (postRepository.existsById(id)){
                postRepository.deleteById(id);
            }else{
                throw new Exception("El objeto que desea eliminar no existe");
            }
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }

    }

    public boolean existsById (Long id){
        return postRepository.existsById(id);
    }

}
