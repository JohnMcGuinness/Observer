package com.github.johnmcguiness.observer;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GroupStore<T, U> {

    private final Map<Observer.Priority, Set<Subscriber<T, U>>> groups = new HashMap<>();
    private final String name;
    
    public GroupStore(String name) {
        this.name = name;
        groups.put(Observer.Priority.LOW, new HashSet<>());
        groups.put(Observer.Priority.MEDIUM, new HashSet<>());
        groups.put(Observer.Priority.HIGH, new HashSet<>());
    }
    
    public String name() {
        return this.name;
    }
    
    public void add(Subscriber<T, U> subscriber, Observer.Priority priority) {
         groups.get(getPriority(priority)).add(subscriber);
    }
    
    public void add(Subscriber<T, U> subscriber) {
         groups.get(getPriority(null)).add(subscriber);
    }

    public void remove(Subscriber<T, U> subscriber) {
        
        if(groups.get(Observer.Priority.HIGH).contains(subscriber)) {
            groups.get(Observer.Priority.HIGH).remove(subscriber);
        }
        else if(groups.get(Observer.Priority.MEDIUM).contains(subscriber)) {
            groups.get(Observer.Priority.MEDIUM).remove(subscriber);
        }
        if(groups.get(Observer.Priority.LOW).contains(subscriber)) {
            groups.get(Observer.Priority.LOW).remove(subscriber);
        }
    }
    
    private Observer.Priority getPriority(Observer.Priority priority) {
        return priority == null ?  Observer.Priority.MEDIUM : priority;
    }

    public boolean contains(Subscriber<T, U> subscriber) {
        
        return getSubscribers().contains(subscriber);
    }
    
    public void reset() {
        groups.values().forEach(set -> set.clear());
    }
    
    public int getSize() {
        return getSubscribers().size();
    }
    
    public List<Subscriber<T, U>> getSubscribers() {
        
        Set<Subscriber<T, U>> subs = groups
            .values()
            .stream()
            .reduce(new HashSet<>(), (s1, s2) -> {
                s1.addAll(s2);
                return s1;
            });
        
        return List.copyOf(subs);
    }
}
