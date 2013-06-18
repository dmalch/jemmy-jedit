package com.github.dmalch.components;

import java.awt.*;

public interface Editor extends Assertable<Editor> {
    Editor typeText(final String text);

    Editor clickUndo();

    Editor clickRedo();

    Settings clickOptions();

    TipPopup tipPopup();

    Editor closeTipsPopupIfExists();

    String editorText();

    Color editorBackgroundColor();
}
