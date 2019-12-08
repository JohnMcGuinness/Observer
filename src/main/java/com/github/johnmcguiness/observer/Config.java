package com.github.johnmcguiness.observer;

public final class Config {

    public final String group;
    public final Observer.Priority priority;

    public static Config of(String group, Observer.Priority priority) {
        return new Config(group, priority);
    }
    
    private Config(String group, Observer.Priority priority) {
        this.group = group;
        this.priority = priority;
    }
}
