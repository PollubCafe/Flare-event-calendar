package pl.pollub.cs.pentagoncafe.flare;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;
import pl.pollub.cs.pentagoncafe.flare.DTO.request.CreatedEventRequestDTO;
import pl.pollub.cs.pentagoncafe.flare.domain.Event;
import pl.pollub.cs.pentagoncafe.flare.repository.UserRepository;
import pl.pollub.cs.pentagoncafe.flare.service.EventService;

import java.util.Date;

import static junit.framework.TestCase.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EventServiceTest {
    @Autowired
    private EventService eventService;

    @Test
    public void whenICreateEventThisEventWillBeInFirstPage(){
        //given
        CreatedEventRequestDTO createdEventRequestDTO = new CreatedEventRequestDTO(
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
        Event createdEvent = eventService.createEvent(createdEventRequestDTO);
        //then
        Page<Event> page = eventService.getPageOfNotApprovedEventsByPageNumber(0);
        assertTrue(page.getContent().contains(createdEvent));
    }
}
