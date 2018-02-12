package pl.pollub.cs.pentagoncafe.flare.Event;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;
import pl.pollub.cs.pentagoncafe.flare.DTO.request.CreateEventRequestDTO;
import pl.pollub.cs.pentagoncafe.flare.domain.Event;
import pl.pollub.cs.pentagoncafe.flare.domain.User;
import pl.pollub.cs.pentagoncafe.flare.repository.event.EventRepository;
import pl.pollub.cs.pentagoncafe.flare.repository.user.UserRepository;
import pl.pollub.cs.pentagoncafe.flare.service.event.EventService;

import java.util.Date;

import static junit.framework.TestCase.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.profiles.active=test")
public class EventServiceIntegrationTest {
    @Autowired
    private EventService eventService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EventRepository eventRepository;

    @Before
    public void initializeDatabase(){
        userRepository.findByNick("Barabasz").orElseGet(() -> userRepository.save(
                User.builder().
                        name("Janusz").
                        surname("Tracz").
                        email("janusz@o2.pl").
                        nick("Barabasz").
                        password("nieOdmawiaSieKiedyPieniadzWzywa")
                        .build()
        ));
    }

    @Test
    public void whenICreateEventThisEventWillBeInFirstPage(){
        //given
        CreateEventRequestDTO createEventRequestDTO = new CreateEventRequestDTO(
                "Urodziny Jarka",
                "Zrobmy Jarkowi najlepszy urodziny wszechczasow!",
                1,
                new Date(),
                false,
                "Zamosc",
                "12-123",
                "Jakastam",
                "lubelskie",
                "",
                "2",
                ""
        );
        //when
        //Event createdEvent = eventService.createEvent(createEventRequestDTO);
        //then
        //Page<Event> page = eventService.getPageOfNotApprovedEventsByPageNumber(0);
        //assertTrue(page.getContent().contains(createdEvent));
    }

    @After
    public void clearDatabase(){
        userRepository.deleteAll();
        eventRepository.deleteAll();
    }
}
