package pl.pollub.cs.pentagoncafe.flare.Event;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import pl.pollub.cs.pentagoncafe.flare.domain.Address;
import pl.pollub.cs.pentagoncafe.flare.domain.Event;
import pl.pollub.cs.pentagoncafe.flare.domain.Province;
import pl.pollub.cs.pentagoncafe.flare.domain.User;
import pl.pollub.cs.pentagoncafe.flare.repository.EventRepository;
import pl.pollub.cs.pentagoncafe.flare.repository.UserRepository;

import java.util.Date;

import static junit.framework.TestCase.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource(locations="classpath:application-test.properties")
@DataMongoTest
public class EventRepositoryTest {
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private UserRepository userRepository;

    private final int DEFAULT_PAGE_SIZE = 7;

    @Test
    public void whenICreateEventThisEventWillBeInFirstPage(){
        //given
        User organizer = User.builder().
                name("Jarek").
                surname("Bielec").
                email("jery0@o2.pl").
                nick("Jery").
                password("poziomc")
                .build();

        userRepository.save(organizer);

        Address address = Address.builder()
                .town("Zamosc")
                .zipCode("12-123")
                .street("Jakastam")
                .province(Province.dolnośląskie)
                .houseNumber("12")
                .build();

        Event event = Event.builder()
                .organizer(organizer)
                .address(address)
                .title("Urodziny Jarka")
                .description("Zorganizujmy wspaniale urodziny!")
                .duration(3)
                .dateOfEventApproval(new Date())
                .isApproved(false)
                .onlyForRegisteredUsers(false)
                .dateOfCreation(new Date())
                .build();
        //when

        Event createdEvent = eventRepository.save(event);
        organizer.getOrganizedEvents().add(createdEvent);
        userRepository.save(organizer);
        //then
        Page<Event> page = eventRepository.getPageOfNotApprovedEventsByPageNumber(
                new PageRequest(0,DEFAULT_PAGE_SIZE, Sort.Direction.DESC,"dateOfCreation"));
        assertTrue(page.getContent().contains(createdEvent));
    }

    @After
    public void clearDatabase(){
        userRepository.deleteAll();
        eventRepository.deleteAll();
    }
}
