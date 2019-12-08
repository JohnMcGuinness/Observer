package com.github.johnmcguiness.observer;

import java.util.function.Function;

public final class Observer<T, U> {

    private final SubscriberRegistry registry = new SubscriberRegistry();
    private int index = 0;
	
    public enum Priority{
        HIGH,
        MEDIUM,
        LOW;
    }
    
    public void subscribe(Function<T, U> f, Config config) {
        
        if(f != null) {
            Subscriber subscriber = new Subscriber(f, index++);
            if(!registry.isRegistered(subscriber, config.group)) {
                registry.register(subscriber, config.group, config.priority);
            }
        }
        else {
            throw new NullPointerException("Call to Observer.subscribe without a subscriber");
        }
    }
    
    public U _notify(T t) {
        return null;
    }
}
