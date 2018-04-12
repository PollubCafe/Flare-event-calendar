package pl.pollub.cs.pentagoncafe.flare.unit.service.event;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import pl.pollub.cs.pentagoncafe.flare.domain.Participation;
import pl.pollub.cs.pentagoncafe.flare.domain.Term;
import pl.pollub.cs.pentagoncafe.flare.domain.Vote;
import pl.pollub.cs.pentagoncafe.flare.mapper.EventMapper;
import pl.pollub.cs.pentagoncafe.flare.repository.event.EventRepository;
import pl.pollub.cs.pentagoncafe.flare.repository.participation.ParticipationRepository;
import pl.pollub.cs.pentagoncafe.flare.repository.user.UserRepository;
import pl.pollub.cs.pentagoncafe.flare.service.event.EventService;
import pl.pollub.cs.pentagoncafe.flare.service.event.EventServiceImpl;
import pl.pollub.cs.pentagoncafe.flare.service.event.related.TimePoint;
import pl.pollub.cs.pentagoncafe.flare.service.event.related.TimePointType;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class EventServiceImplTest {

    @MockBean
    private EventRepository eventRepository;
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private EventMapper eventMapper;


    private EventService eventService;

    @Before
    public void setup(){
        eventService = new EventServiceImpl(eventRepository,userRepository, eventMapper);
    }

    @Test
    public void shouldReturnCorrectNumberOfTimePoints(){

        Participation p1=new Participation();
        p1.setVotes(new HashSet<>(Arrays.asList(getDummyVote(0),getDummyVote(1),
                getDummyVote(2))));

        Participation p2=new Participation();
        p2.setVotes(new HashSet<>(Arrays.asList(getDummyVote(3),getDummyVote(4),
                getDummyVote(5),getDummyVote(6))));

        List<TimePoint> timePointsForParticipations =
                eventService.getTimePointsForParticipations(new HashSet<>(Arrays.asList(p1, p2)));

        assertEquals(14,timePointsForParticipations.size());

    }

    private Vote getDummyVote(int dayOfTheWeek){
        Vote dummyVote=new Vote();
        dummyVote.setDayOfWeek(dayOfTheWeek);
        dummyVote.setFrom(LocalTime.now());
        dummyVote.setTo(LocalTime.MIDNIGHT);

        return dummyVote;
    }

    /*
        |S1 S2 K2 K1|
    * */
    @Test
    public void testStatisticForSequenceS1S2K2K1(){

        LocalTime baseTime=LocalTime.now();
        TimePoint tp1=new TimePoint(0,baseTime, TimePointType.START);
        TimePoint tp2=new TimePoint(0,baseTime.plusHours(1), TimePointType.START);
        TimePoint tp3=new TimePoint(0,baseTime.plusHours(2), TimePointType.END);
        TimePoint tp4=new TimePoint(0,baseTime.plusHours(3), TimePointType.END);

        Set<Term> terms = eventService.generateStatisticForTimePoints(Arrays.asList(tp1, tp2, tp3, tp4), 2);

        assertEquals(3,terms.size());
        assertTrue(terms.contains(new Term(baseTime,baseTime.plusHours(1),1,0,50d)));
        assertTrue(terms.contains(new Term(baseTime.plusHours(1),baseTime.plusHours(2),2,0,100d)));
        assertTrue(terms.contains(new Term(baseTime.plusHours(2),baseTime.plusHours(3),1,0,50d)));
    }
    /*
        |S1 K1 S2 K2|
    * */
    @Test
    public void testStatisticForSequenceS1K1S2K2(){
        LocalTime baseTime=LocalTime.now();
        TimePoint tp1=new TimePoint(0,baseTime, TimePointType.START);
        TimePoint tp2=new TimePoint(0,baseTime.plusHours(1), TimePointType.END);
        TimePoint tp3=new TimePoint(0,baseTime.plusHours(2), TimePointType.START);
        TimePoint tp4=new TimePoint(0,baseTime.plusHours(3), TimePointType.END);

        Set<Term> terms = eventService.generateStatisticForTimePoints(Arrays.asList(tp1, tp2, tp3, tp4), 2);

        assertEquals(2,terms.size());
        assertTrue(terms.contains(new Term(baseTime,baseTime.plusHours(1),1,0,50d)));
        assertTrue(terms.contains(new Term(baseTime.plusHours(2),baseTime.plusHours(3),1,0,50d)));
    }


    /*
        |S1 K1| |S2 K2|
    * */
    @Test
    public void testStatisticForTimePointsFromManyDays(){
        LocalTime baseTime=LocalTime.now();
        TimePoint tp1=new TimePoint(0,baseTime, TimePointType.START);
        TimePoint tp2=new TimePoint(0,baseTime.plusHours(1), TimePointType.END);
        TimePoint tp3=new TimePoint(1,baseTime.plusHours(2), TimePointType.START);
        TimePoint tp4=new TimePoint(1,baseTime.plusHours(3), TimePointType.END);

        Set<Term> terms = eventService.generateStatisticForTimePoints(Arrays.asList(tp1, tp2, tp3, tp4), 2);

        assertEquals(2,terms.size());
        assertTrue(terms.contains(new Term(baseTime,baseTime.plusHours(1),1,0,50d)));
        assertTrue(terms.contains(new Term(baseTime.plusHours(2),baseTime.plusHours(3),1,1,50d)));
    }


    @Test
    public void testStatisticForPeriodsStartingAndEndingInTheSameTime(){
        LocalTime baseTime=LocalTime.now();
        TimePoint tp1=new TimePoint(0,baseTime, TimePointType.START);
        TimePoint tp2=new TimePoint(0,baseTime, TimePointType.START);
        TimePoint tp3=new TimePoint(0,baseTime.plusHours(2), TimePointType.END);
        TimePoint tp4=new TimePoint(0,baseTime.plusHours(2), TimePointType.END);

        Set<Term> terms = eventService.generateStatisticForTimePoints(Arrays.asList(tp1, tp2, tp3, tp4), 2);

        assertEquals(1,terms.size());
        assertTrue(terms.contains(new Term(baseTime,baseTime.plusHours(2),2,0,100d)));
    }
}