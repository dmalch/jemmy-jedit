package com.github.dmalch.components;

import org.hamcrest.Matcher;

public interface Assertable<T> {
    T then(final Matcher<T> matcher);
}
