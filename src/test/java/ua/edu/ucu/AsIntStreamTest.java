package ua.edu.ucu;

import org.junit.Before;
import org.junit.Test;
import ua.edu.ucu.stream.AsIntStream;
import ua.edu.ucu.stream.IntStream;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class AsIntStreamTest {
    private IntStream stream;
    private IntStream emptystream;

    @Before
    public void init() {
        this.stream = AsIntStream.of(1, 2, 3, 4, 5, 6);
        this.emptystream = AsIntStream.of();
    }

    @Test
    public void testAverage() {
        double expResult = 3.5;
        assertEquals(expResult, this.stream.average(), 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAverageempty() {
        this.emptystream.average();
    }

    @Test
    public void testMax() {
        int expResult = 6;
        int actualResult = this.stream.max();
        assertEquals(expResult, actualResult);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMaxempty() {
        this.emptystream.max();
    }

    @Test
    public void testMin() {
        int expResult = 1;
        int actualResult = this.stream.min();
        assertEquals(expResult, actualResult);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMinempty() {
        this.emptystream.min();
    }

    @Test
    public void testCount() {
        int expResult = 6;
        long actualResult = this.stream.count();
        assertEquals(expResult, actualResult);
    }

    @Test
    public void testSum() {
        int expResult = 21;
        int actualResult = this.stream.sum();
        assertEquals(expResult, actualResult);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSumempty() {
        this.emptystream.sum();
    }

    @Test
    public void testFilter() {
        String expected = "[3, 4, 5, 6]";
        assertEquals(expected, Arrays.toString(this.stream.filter(x -> x > 2).toArray()));
    }

    @Test
    public void testForeach() {
        String expected = "123456";
        StringBuilder str = new StringBuilder();
        this.stream.forEach(x -> str.append(x));
        assertEquals(expected, str.toString());
    }

    @Test
    public void ttestMap() {
        String expected = "[1, 4, 9, 16, 25, 36]";
        assertEquals(expected, Arrays.toString(this.stream.map(x -> x * x).toArray()));
    }

    @Test
    public void testFlatMap() {
        String expected = "[0, 1, 2, 1, 2, 3, 2, 3, 4, 3, 4, 5, 4, 5, 6, 5, 6, 7]";
        String actual = Arrays.toString(this.stream.flatMap
                (x -> AsIntStream.of(x - 1, x, x + 1)).toArray());
        assertEquals(expected, actual);
    }

    @Test
    public void testReduce() {
        int expected = 21;
        assertEquals(expected, this.stream.reduce(0, (sum, x) -> sum += x));
    }


}
