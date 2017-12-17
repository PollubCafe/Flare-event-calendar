package pl.pollub.cs.pentagoncafe.flare.controller;
/** Twórca: Konrad Gryczko
 *  Data Start 2017/12/12
 */
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.*;
import pl.pollub.cs.pentagoncafe.flare.domain.Event;
import pl.pollub.cs.pentagoncafe.flare.repository.EventRepository;
import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {

    private EventRepository eventRepository;

    public EventController(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    //Pobieranie danych
    @GetMapping("/")
    public List<Event> allEvent(){
        return eventRepository.findAll();
    }

    @GetMapping("/{id}")
    public Event eventById(@PathVariable("id") String id){
        return eventRepository.findOne(id);
    }

    @GetMapping("/title/{title}")
    public List<Event> eventByName(@PathVariable("title") String title){
        return eventRepository.findByTitle(title);
    }

    @GetMapping("/year/{year}")
    public List<Event> eventByYear(@PathVariable("year") int year){
        return eventRepository.findByYear(year);
    }

    @GetMapping("/week/{week}")
    public List<Event> eventByWeek(@PathVariable("week") int week){
        return eventRepository.findByWeek(week);
    }

    @GetMapping("/weekA/{week}")
    public List<Event> eventByWeekA(@PathVariable("week") int week){
        return eventRepository.findByWeekAfter(week);
    }

    @GetMapping("/weekB/{week}")
    public List<Event> eventByWeekB(@PathVariable("week") int week){
        return eventRepository.findByWeekBefore(week);
    }

    @GetMapping("/creator/{creator}")
    public List<Event> eventByCreator(@PathVariable("creator") ObjectId creator){
        return eventRepository.findByCreator(creator);
    }

    @GetMapping("/status/{status}")
    public List<Event> eventByStatus(@PathVariable("status") String status){
        return eventRepository.findByStatus(status);
    }

    @GetMapping("/town/{town}")
    public List<Event> eventByTown(@PathVariable("town") String town){
        return eventRepository.findByTown(town);
    }

    @GetMapping("/zipcode/{zip}")
    public List<Event> eventByZip(@PathVariable("zip") int zip){
        return eventRepository.findByZipCode(zip);
    }

    //Dodawanie
    @PutMapping
    public void insert(@RequestBody Event event){
        this.eventRepository.insert(event);
    }

    //Aktualizacja
    @PostMapping
    public void update(@RequestBody Event event){
        this.eventRepository.save(event);
    }

    //Kasowanie
    @DeleteMapping("/{id}")
    public void delate(@PathVariable("id") String id){
        this.eventRepository.delete(id);
    }
}
