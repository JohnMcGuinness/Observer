package com.github.johnmcguiness.experiment;

import java.util.function.Function;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

/**
 *
 * @author exbsjv
 */
public class SubsciberTest {
    
    @Test
    public void canConstructWhenPassedAFunction() {
        assertNotNull(new Subscriber(Function.identity(), 1));
    }
    
    @Test(expected = NullPointerException.class)
    public void throwsWhenPassedNull() {
        new Subscriber(null, 0);
    }
    
    @Test
    public void canPublish() {

        final String str = "Hello";
        final String expected = "Received: " + str;
        
//        assertEquals(expected, new Subscriber(s -> "Received: " + s).publish("Hello"));
    }
}
