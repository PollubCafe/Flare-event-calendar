package pl.pollub.cs.pentagoncafe.flare.unit.service.event.related;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import pl.pollub.cs.pentagoncafe.flare.service.event.related.TimePoint;
import pl.pollub.cs.pentagoncafe.flare.service.event.related.TimePointType;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class TimePointTest {

    @Test
    public void testTimePointComparable(){
        TimePoint t1=new TimePoint(0, LocalTime.now(), TimePointType.START);
        TimePoint t2=new TimePoint(0, LocalTime.now().plusHours(1),TimePointType.START);
        TimePoint t3=new TimePoint(2, LocalTime.now(),TimePointType.START);

        List<TimePoint> timePointList = Stream.of(t3, t1, t2).sorted().collect(Collectors.toList());

        assertEquals(t1,timePointList.get(0));
        assertEquals(t2,timePointList.get(1));
        assertEquals(t3,timePointList.get(2));

    }

}