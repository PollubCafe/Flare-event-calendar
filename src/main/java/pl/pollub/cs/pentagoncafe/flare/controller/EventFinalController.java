package pl.pollub.cs.pentagoncafe.flare.controller;
/** Twórca: Konrad Gryczko
 *  Data Start 2017/12/17
 */
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.*;
import pl.pollub.cs.pentagoncafe.flare.domain.EventFinal;
import pl.pollub.cs.pentagoncafe.flare.repository.EventFinalRepository;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/eventFinal")
public class EventFinalController {

    private final EventFinalRepository eventFinalRepository;

    public EventFinalController(EventFinalRepository eventFinalRepository) {
        this.eventFinalRepository = eventFinalRepository;
    }

    //Pobieranie
    @GetMapping("/")
    public List<EventFinal> eventFinals(){
        return this.eventFinalRepository.findAll();
    }

    @GetMapping("/{id}")
    public EventFinal eventFinalById(@PathVariable("id")ObjectId id){
        return this.eventFinalRepository.findOne(id.toString());
    }

    @GetMapping("/date/{date}")
    public List<EventFinal> eventFinalsByDate(@PathVariable("date")Date date){
        return this.eventFinalRepository.findByData(date);
    }

    @GetMapping("/eventId/{id}")
    public EventFinal eventFinalByCreator(@PathVariable("id")ObjectId id){
        return this.eventFinalRepository.findByEventid(id);
    }

    //dodawanie
    @PutMapping
    public void addEventFinal(@RequestBody EventFinal eventFinal){
        this.eventFinalRepository.insert(eventFinal);
    }

    //aktualizacja
    @PostMapping
    public void updatedEventFinal(@RequestBody EventFinal eventFinal){
        this.eventFinalRepository.save(eventFinal);
    }

    //Kasowanie
    @DeleteMapping("/{id}")
    public void deleateEventFinal(@PathVariable("id") ObjectId id){
        this.eventFinalRepository.delete(id.toString());
    }
}
