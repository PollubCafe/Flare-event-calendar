package pl.pollub.cs.pentagoncafe.flare.Event;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import pl.pollub.cs.pentagoncafe.flare.DTO.request.CreatedEventRequestDTO;
import pl.pollub.cs.pentagoncafe.flare.DTO.response.PageResponseDTO;
import pl.pollub.cs.pentagoncafe.flare.DTO.response.SimplifiedEventResponseDTO;
import pl.pollub.cs.pentagoncafe.flare.EventCalendarApplication;
import pl.pollub.cs.pentagoncafe.flare.domain.User;
import pl.pollub.cs.pentagoncafe.flare.mapper.EventToSimplifiedEventResponseDTOMapper;
import pl.pollub.cs.pentagoncafe.flare.repository.EventRepository;
import pl.pollub.cs.pentagoncafe.flare.repository.UserRepository;

import java.text.SimpleDateFormat;
import java.util.*;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {EventCalendarApplication.class},properties = "spring.profiles.active=test")
@AutoConfigureMockMvc
public class EventControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    EventToSimplifiedEventResponseDTOMapper eventToSimplifiedEventResponseDTOMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventRepository eventRepository;

    @Before
    public void initializeDatabase(){
        userRepository.findByNick("Barabasz").orElseGet(() -> userRepository.save(
                new User(
                        "Janusz",
                        "Tracz",
                        "janusz@o2.pl",
                        "Barabasz",
                        "nieOdmawiaSieKiedyPieniadzWzywa"))
        );
    }

    @Test
    public void whenICreateEventThisEventWillBeInFirstPage() throws Exception {
        //given

        Calendar futureCalendar = Calendar.getInstance();
        futureCalendar.setTime(new Date());
        futureCalendar.add(Calendar.HOUR_OF_DAY, 1);


        CreatedEventRequestDTO createdEventRequestDTO = new CreatedEventRequestDTO(
                "Urodziny Jarka",
                "Zrobmy Jarkowi najlepszy urodziny wszechczasow!",
                2,
                futureCalendar.getTime(),
                false,
                "Zamosc",
                "12-123",
                "Jakastam",
                "lubelskie",
                "",
                "2",
                ""
        );

        Gson gson = new Gson();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<CreatedEventRequestDTO> requestEntity = new HttpEntity<>(createdEventRequestDTO,headers);
        //when
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(this.createURLWithPort("/api/events"),requestEntity, String.class);

        //then status equal
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        SimplifiedEventResponseDTO simplifiedEventResponseDTO = gson.fromJson(responseEntity.getBody(),SimplifiedEventResponseDTO.class);

        //then basic data equal...
        assertNotNull(simplifiedEventResponseDTO.getId());
        assertEquals(createdEventRequestDTO.getTitle(),simplifiedEventResponseDTO.getTitle());
        assertEquals(createdEventRequestDTO.getDescription(),simplifiedEventResponseDTO.getDescription());
        assertEquals(createdEventRequestDTO.getDuration(),simplifiedEventResponseDTO.getDuration());
        assertEquals(createdEventRequestDTO.isOnlyForRegisteredUsers(),simplifiedEventResponseDTO.isOnlyForRegisteredUsers());
        assertEquals(createdEventRequestDTO.isOnlyForRegisteredUsers(),simplifiedEventResponseDTO.isOnlyForRegisteredUsers());

        //then dateOfApproval equal...
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(createdEventRequestDTO.getDateOfEventApproval());
        assertEquals(calendar.get(Calendar.YEAR),simplifiedEventResponseDTO.getYearOfEventApproval());
        assertEquals(calendar.get(Calendar.DAY_OF_MONTH),simplifiedEventResponseDTO.getDayOfEventApproval());
        assertEquals(new SimpleDateFormat("MMM").format(calendar.getTime()),simplifiedEventResponseDTO.getMouthOfEventApproval());
        assertEquals(calendar.get(Calendar.YEAR),simplifiedEventResponseDTO.getYearOfEventApproval());
        assertEquals(eventToSimplifiedEventResponseDTOMapper.parseDate(calendar),simplifiedEventResponseDTO.getHourOfEventApproval());

        //then address equal...
        String parsedAddress = this.parseAddress(
                createdEventRequestDTO.getAddress_street(),
                createdEventRequestDTO.getAddress_blockNumber(),
                createdEventRequestDTO.getAddress_houseNumber(),
                createdEventRequestDTO.getAddress_zipCode(),
                createdEventRequestDTO.getAddress_town());
        assertEquals(parsedAddress,simplifiedEventResponseDTO.getAddress());

        //then this object is in page content...
        ResponseEntity<String> responsePageEntity = restTemplate.getForEntity(this.createURLWithPort("/api/events?pageNumber=0"), String.class);

        JSONObject pageResponseDTOJsonObject = new JSONObject(responsePageEntity.getBody());
        JSONArray pageResponseDTOContentJsonObject = pageResponseDTOJsonObject.getJSONArray("content");
        SimplifiedEventResponseDTO simplifiedEventResponseDTOFromPage = gson.fromJson(pageResponseDTOContentJsonObject.get(0).toString(), SimplifiedEventResponseDTO.class);
        assertEquals(simplifiedEventResponseDTOFromPage,simplifiedEventResponseDTO);
    }

    private String parseAddress(String address_street,String address_blockNumber,String address_houseNumber,String address_ZipCode,String address_Town){
        StringBuilder addressStringBuilder =  new StringBuilder("ul.").append(address_street).append(" ");
        if(address_blockNumber!=null && !address_blockNumber.equals(""))
            addressStringBuilder.append(address_blockNumber).append(" m.");
        return addressStringBuilder.append(address_houseNumber).append(", ")
                .append(address_ZipCode).append(" ")
                .append(address_Town).toString();
    }

    @After
    public void clearDatabase(){
        userRepository.deleteAll();
        eventRepository.deleteAll();
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
