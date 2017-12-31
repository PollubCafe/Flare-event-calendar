package pl.pollub.cs.pentagoncafe.flare.controller;
/** Twórca: Konrad Gryczko
 *  Data Start 2017/12/13
 */
import org.springframework.web.bind.annotation.*;
import pl.pollub.cs.pentagoncafe.flare.domain.User;
import pl.pollub.cs.pentagoncafe.flare.repository.UserRepository;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //pobieranie Danych
    @GetMapping("/")
    public List<User> getAll(){
        return this.userRepository.findAll();
    }

    @GetMapping("/{id}")
    public User getOneById(@PathVariable("id") String id){
        return this.userRepository.findOne(id);
    }

    @GetMapping("/email/{email:.+}")
    public User getOneByEmail(@PathVariable("email") String email){
        return this.userRepository.findByEmail(email);
    }

    @GetMapping("/nick/{nick}")
    public User getOneByNick(@PathVariable("nick") String nick){
        return this.userRepository.findByNick(nick);
    }

    //Dodawanie Danych
    @PutMapping
    public void insert(@RequestBody User user){
        this.userRepository.insert(user);
    }

    //Aktualuzacia
    @PostMapping()
    public void update(@RequestBody User user){
        this.userRepository.save(user);
    }

    //Kasowanie
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id){
        this.userRepository.delete(id);
    }

}
