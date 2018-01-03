package pl.pollub.cs.pentagoncafe.flare;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import pl.pollub.cs.pentagoncafe.flare.controller.EventController;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebMvcTest(EventController.class)
public class EventControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void whenICreateEventThisEventWillBeInFirstPage(){
        
    }
}
