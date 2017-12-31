package pl.pollub.cs.pentagoncafe.flare.controller;
/** Twórca: Konrad Gryczko
 *  Data Start 2017/12/17
 */
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.*;
import pl.pollub.cs.pentagoncafe.flare.domain.EventStatistic;
import pl.pollub.cs.pentagoncafe.flare.repository.EventStatisticRepository;

import java.util.List;

@RestController
@RequestMapping("/api/EventStatistic")
public class EventStatisticController {

    private final EventStatisticRepository eventStatisticRepository;

    public EventStatisticController(EventStatisticRepository eventStatisticRepository) {
        this.eventStatisticRepository = eventStatisticRepository;
    }

    //Pobieranie Danych
    @GetMapping("/")
    public List<EventStatistic> eventStatistics(){
        return eventStatisticRepository.findAll();
    }

    @GetMapping("/{id}")
    public EventStatistic eventStatisticById(@PathVariable("id")ObjectId id){
        return eventStatisticRepository.findOne(id.toString());
    }

    @GetMapping("/eventId/{id}")
    public EventStatistic eventStatisticsByEventId(@PathVariable("id") ObjectId id){
        return eventStatisticRepository.findByEventid(id);
    }

    //Dodawanie
    @PutMapping
    public void addEventStatistic(@RequestBody EventStatistic eventStatistic){
        this.eventStatisticRepository.insert(eventStatistic);
    }

    //Aktualizadcja
    @PostMapping
    public void updateEventStatistic(@RequestBody EventStatistic eventStatistic){
        this.eventStatisticRepository.save(eventStatistic);
    }

    //Kasowanie
    @DeleteMapping("/{id}")
    public void delateEventStatistic(@PathVariable("id") ObjectId id){
        this.eventStatisticRepository.delete(id.toString());
    }

}
