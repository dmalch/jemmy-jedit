package com.github.dmalch.components;

import org.hamcrest.Matcher;

public interface MainWindow {
    MainWindow typeText(final String text);

    MainWindow then(final Matcher<MainWindow> matcher);

    String editorText();
}
