package com.github.dmalch.components;

import org.hamcrest.Matcher;

public interface Editor {
    Editor typeText(final String text);

    Editor then(final Matcher<Editor> matcher);

    Editor clickUndo();

    String editorText();
}
