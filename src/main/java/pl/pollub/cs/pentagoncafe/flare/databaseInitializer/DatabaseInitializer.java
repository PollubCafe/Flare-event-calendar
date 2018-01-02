package pl.pollub.cs.pentagoncafe.flare.databaseInitializer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import pl.pollub.cs.pentagoncafe.flare.domain.User;
import pl.pollub.cs.pentagoncafe.flare.repository.UserRepository;

@Component
public class DatabaseInitializer implements ApplicationListener<ContextRefreshedEvent> {

    private final UserRepository userRepository;

    @Autowired
    public DatabaseInitializer(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        userRepository.deleteAll();

        User user = new User("Janusz", "Tracz", "janusz@o2.pl", "Barabasz", "nieOdmawiaSieKiedyPieniadzWzywa");


        userRepository.save(user);
    }
}