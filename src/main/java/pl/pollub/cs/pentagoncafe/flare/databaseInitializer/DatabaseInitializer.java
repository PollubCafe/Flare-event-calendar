package pl.pollub.cs.pentagoncafe.flare.databaseInitializer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import pl.pollub.cs.pentagoncafe.flare.domain.Event;
import pl.pollub.cs.pentagoncafe.flare.domain.User;
import pl.pollub.cs.pentagoncafe.flare.repository.EventRepository;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Component
public class DatabaseInitializer implements ApplicationListener<ContextRefreshedEvent> {

    private final EventRepository eventRepository;

    @Autowired
    public DatabaseInitializer(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        eventRepository.deleteAll();

        User user = new User("Jarek", "Bielec", "jareczek@o2.pl", "Barabasz", "dlaczegoSieSprzedales");

        List<Event> events = IntStream.range(1, 1000)
                .mapToObj(eventNumber -> Event.builder()
                        .creator(user)
                        .title("Oddajemy hołd januszowi Traczowi")
                        .description("Oddajemy hołd januszowi Traczowi "+eventNumber)
                        .status("Nie wiem co tutaj wstawic")
                        .town("Lublin")
                        .zipCode(12123)
                        .bloc(1)
                        .houseNumber(2)
                        .week(4)
                        .year(2017)
                        .duration(100000)
                        .date(new Date())
                        .onlyForRegisteredUsers(true)
                        .build()
                ).collect(Collectors.toList());

        eventRepository.save(events);
    }
}
