package pl.pollub.cs.pentagoncafe.flare.controller;

import org.bson.types.ObjectId;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import pl.pollub.cs.pentagoncafe.flare.EventCalendarApplication;
import pl.pollub.cs.pentagoncafe.flare.domain.Address;
import pl.pollub.cs.pentagoncafe.flare.domain.Event;
import pl.pollub.cs.pentagoncafe.flare.domain.Participation;
import pl.pollub.cs.pentagoncafe.flare.domain.User;
import pl.pollub.cs.pentagoncafe.flare.domain.enums.EventStatus;
import pl.pollub.cs.pentagoncafe.flare.domain.enums.Province;
import pl.pollub.cs.pentagoncafe.flare.repository.event.EventRepository;
import pl.pollub.cs.pentagoncafe.flare.repository.participation.ParticipationRepository;
import pl.pollub.cs.pentagoncafe.flare.repository.user.UserRepository;

import java.nio.charset.Charset;
import java.time.Instant;
import java.util.*;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = EventCalendarApplication.class)
@TestPropertySource(properties = {"application-test.properties"})
public class EventControllerTest {

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    @Autowired
    private EventRepository eventRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    private ParticipationRepository participationRepository;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;
    private Address address;
    private Event newEvent;
    private Event approvedEvent;
    private Event endedEvent;
    private Event bannedEvent;
    private User user;
    private Participation participation;

    @Before
    public void setUp() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();

        address = Address.builder()
                .town("Lublin")
                .zipCode("55-555")
                .street("Test")
                .province(Province.lubelskie)
                .blockNumber("2")
                .houseNumber("44")
                .additionalInformation("aaa")
                .build();

        newEvent = Event.builder()
                .title("Test nowego")
                .description("some rand desc")
                .duration(2)
                .dateOfEndRegistration(Instant.ofEpochSecond(40000L))
                .status(EventStatus.REGISTRATION)
                .banned(false)
                .onlyForRegisteredUsers(false)
                .imageURL("abc")
                .dateOfCreation(Instant.now())
                .address(address)
                .build();

        approvedEvent = Event.builder()
                .title("Test zatwierdzonego")
                .description("some rand desc")
                .duration(2)
                .dateOfEndRegistration(Instant.ofEpochSecond(40000L))
                .status(EventStatus.APPROVED).banned(false)
                .banned(false)
                .onlyForRegisteredUsers(false)
                .imageURL("abc")
                .dateOfCreation(Instant.now())
                .address(address)
                .build();

        endedEvent = Event.builder()
                .title("Test zatwierdzonego")
                .description("some rand desc")
                .duration(2)
                .dateOfEndRegistration(Instant.ofEpochSecond(40000L))
                .status(EventStatus.CLOSED)
                .banned(false)
                .onlyForRegisteredUsers(false)
                .imageURL("abc")
                .dateOfCreation(Instant.now())
                .address(address)
                .build();

        bannedEvent = Event.builder()
                .title("Test zbanowanego")
                .description("some rand desc")
                .duration(2)
                .dateOfEndRegistration(Instant.ofEpochSecond(40000L))
                .status(EventStatus.REGISTRATION)
                .banned(true)
                .onlyForRegisteredUsers(false)
                .imageURL("abc")
                .dateOfCreation(Instant.now())
                .address(address)
                .build();

        participation = new Participation();
        participation.setEvent(approvedEvent);
        participation.setId(new ObjectId());

        user = User.builder().nick("testnick").organizedEvents(newEvent).participation(participation).build();
        participation.setUser(user);

        newEvent.setId(new ObjectId());
        approvedEvent.setId(new ObjectId());
        endedEvent.setId(new ObjectId());
        bannedEvent.setId(new ObjectId());
        user.setId(new ObjectId());

        newEvent.setOrganizer(user);
        Set<Participation> set = new HashSet<>();
        set.add(participation);
        newEvent.setParticipation(set);

        userRepository.save(user);
        eventRepository.save(newEvent);
        eventRepository.save(approvedEvent);
        eventRepository.save(endedEvent);
        eventRepository.save(bannedEvent);
        participationRepository.save(participation);
    }

    @After
    public void tearDown() throws Exception {
        eventRepository.delete(newEvent);
        eventRepository.delete(approvedEvent);
        eventRepository.delete(endedEvent);
        eventRepository.delete(bannedEvent);
        userRepository.delete(user);
        participationRepository.delete(participation);
    }

    @Test
    public void getNewEvents() throws Exception {
        mockMvc.perform(get("/api/event/new"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].title", is(newEvent.getTitle())));

    }

    @Test
    public void getApprovedEvents() throws Exception {

        mockMvc.perform(get("/api/event/approved"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].title", is(approvedEvent.getTitle())));
    }

    @Test
    public void getEndedEvents() throws Exception {
        mockMvc.perform(get("/api/event/ended"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].title", is(endedEvent.getTitle())));
    }

    @Test
    public void getUserAttendingEvents() throws Exception {
        mockMvc.perform(get("/api/event/testnick/attending"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].title", is(approvedEvent.getTitle())));
    }

    @Test
    public void getEventsOfUser() throws Exception {
        mockMvc.perform(get("/api/event/testnick/created"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].title", is(newEvent.getTitle())));
    }

//    @Test
//    public void adminListOfNewEvents() throws Exception {
//        mockMvc.perform(get("/api/event/admin/new"))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(contentType))
//                .andExpect(jsonPath("$", hasSize(2)));
//    }
//
//    @Test
//    public void adminListOfApprovedEvents() {
//    }
//
//    @Test
//    public void adminListOfEventsThatUserIsAttending() {
//    }
//
//    @Test
//    public void adminListEventsThatWasCreatedByUser() {
//    }
}