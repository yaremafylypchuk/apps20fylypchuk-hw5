package ua.edu.ucu;

import org.junit.Before;
import org.junit.Test;
import ua.edu.ucu.stream.AsIntStream;
import ua.edu.ucu.stream.IntStream;

import static org.junit.Assert.assertEquals;

public class AsIntStreamTest {
    private IntStream stream;
    private IntStream emptystream;

    @Before
    public void init(){
        this.stream = AsIntStream.of(1,2,3,4,5,6);
        this.emptystream = AsIntStream.of();
    }

    @Test
    public void testAverage(){
        double expResult = 3.5;
        assertEquals(expResult, this.stream.average(), 0);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testAverageempty(){
        this.emptystream.average();
    }
    @Test
    public void testMax(){
        int expResult = 6;
        int actualResult = this.stream.max();
        assertEquals(expResult, actualResult);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testMaxempty(){
        this.emptystream.max();
    }
    @Test
    public void testMin(){
        int expResult = 1;
        int actualResult = this.stream.min();
        assertEquals(expResult, actualResult);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testMinempty(){
        this.emptystream.min();
    }
    @Test
    public void testCount(){
        int expResult = 6;
        long actualResult = this.stream.count();
        assertEquals(expResult, actualResult);
    }
    @Test
    public void testSum(){
        int expResult = 21;
        int actualResult = this.stream.sum();
        assertEquals(expResult, actualResult);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testSumempty(){
        this.emptystream.sum();
    }


}
