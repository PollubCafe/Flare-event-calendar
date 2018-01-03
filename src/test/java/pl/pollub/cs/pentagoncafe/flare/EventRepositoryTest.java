package pl.pollub.cs.pentagoncafe.flare;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.pollub.cs.pentagoncafe.flare.domain.Address;
import pl.pollub.cs.pentagoncafe.flare.domain.Province;
import pl.pollub.cs.pentagoncafe.flare.domain.User;
import pl.pollub.cs.pentagoncafe.flare.repository.EventRepository;
import pl.pollub.cs.pentagoncafe.flare.repository.UserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class EventServiceTest {
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private UserRepository userRepository;
    

    @Test
    public void whenICreateEventThisEventWillBeInFirstPage(){
        //given
        User organizer = new User("Jarek","Bielec","jery0@o2.pl","Jery","poziomc");

        Address address = Address.builder()
                .town("Zamosc")
                .zipCode("12-123")
                .street("Jakastam")
                .province(Province.dolnośląskie)
                .houseNumber("12")
                .build();
        //when

        //then
    }
}
