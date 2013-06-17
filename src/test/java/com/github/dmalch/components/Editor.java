package com.github.dmalch.components;

public interface Editor extends Assertable<Editor> {
    Editor typeText(final String text);

    Editor clickUndo();

    Editor clickRedo();

    Settings clickOptions();

    String editorText();
}
