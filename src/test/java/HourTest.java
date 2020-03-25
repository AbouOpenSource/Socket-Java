import static org.junit.Assert.*;

import com.github.AbouOpenSource.Utils.Hour;
import org.junit.Test;

public class HourTest {

    @Test
    public final void testCloseTo() {

        Hour hour =new Hour(12,34,56);
        Hour hour1 = new Hour(12,35,0);
        assertTrue(hour.closeTo(hour1, 5));
    }


    @Test
    public final void testGapSecond() {

        Hour hour =new Hour(10,0,0);
        Hour hour1 = new Hour(11,01,01);
        assertEquals(3661, hour.gapInSeconds(hour1));
    }



}
