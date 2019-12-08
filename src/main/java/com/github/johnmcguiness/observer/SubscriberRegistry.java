package com.github.johnmcguiness.observer;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public final class SubscriberRegistry<T, U> {

    private final Map<String, GroupStore<T, U>> stores = new HashMap<>();

    private static final String DEFAULT_GROUP = "__default__";

    public void register(Subscriber<T, U> subscriber, String group, Observer.Priority priority) {
        getGroupStore(groupName(group), true).add(subscriber, priority);
    }

    public boolean isRegistered(Subscriber<T, U> subscriber, String group) {
        
        return Optional
                .ofNullable(getGroupStore(group, false))
                .map(s -> s.contains(subscriber))
                .orElse(false);
    }

    /**
     * 
     */
    private GroupStore<T, U> getGroupStore(String group, boolean createNew) {
        
        return createNew 
            ? stores.computeIfAbsent(group, name -> new GroupStore<>(name))
            : stores.get(groupName(group));
    }

    private String groupName(String group) {
        return group == null ? DEFAULT_GROUP : group;
    }
}
