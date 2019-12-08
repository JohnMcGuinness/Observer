package com.github.johnmcguiness.observer;

import java.util.Objects;
import java.util.function.Function;

public class Subscriber<T, U> {

    private final Function<T, U> f;
	private final int index;
    
    public Subscriber(Function<T, U> f, int index) {
        this.f = Objects.requireNonNull(f, "A subscriber requires a function");
		this.index = index;
    }

    public U publish(T t) {
        return f.apply(t);
    }

	public int index() {
		return this.index;
	}

	@Override
    public boolean equals(Object obj) {
        return (obj instanceof Subscriber) ? ((Subscriber) obj).f == f : false;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.f);
        return hash;
    }
}
