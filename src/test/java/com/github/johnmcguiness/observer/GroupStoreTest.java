package com.github.johnmcguiness.experiment;

import java.util.function.Function;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class GroupStoreTest {

    private final String name = "name";
    
    @Test
    public void canConstructWithName() {
        GroupStore store = new GroupStore(name);
        assertNotNull(store);
        assertEquals(name, store.name());
    }
    
    @Test
    public void canConstructWithoutName() {
        GroupStore store = new GroupStore(null);
        assertNotNull(store);
        assertNull(store.name());
    }
    
    @Test
    public void canAddASubscriberWithPriority() {

        GroupStore store = new GroupStore(name);
        assertEquals(0, store.getSize()); 
        store.add(new Subscriber(Function.identity(), 1), Observer.Priority.MEDIUM);
        assertEquals(1, store.getSize());
    }
    
    @Test
    public void canAddASubscriberWithoutPriority() {

        GroupStore store = new GroupStore(name);
        assertEquals(0, store.getSize()); 
        store.add(new Subscriber(t -> t, 1), null);
        assertEquals(1, store.getSize()); 
        store.add(new Subscriber(t -> t, 1));
        assertEquals(2, store.getSize());
    }
    
    @Test
    public void canRemoveASubscriber() {

        Subscriber<?, ?> subscriber = new Subscriber(Function.identity(), 1);
        
        GroupStore store = new GroupStore(name);
        assertEquals(0, store.getSize()); 
        store.add(subscriber);
        assertEquals(1, store.getSize()); 
        store.remove(subscriber);
        assertEquals(0, store.getSize());
    }
    
    @Test
    public void canCheckIfStoreContainsASubscriber() {

         Subscriber<?, ?> subscriber = new Subscriber(Function.identity(), 1);
         
        GroupStore store = new GroupStore(name);
        assertEquals(0, store.getSize()); 
        store.add(subscriber);
        assertEquals(1, store.getSize());
        
        assertTrue(store.contains(subscriber));
    }
}
