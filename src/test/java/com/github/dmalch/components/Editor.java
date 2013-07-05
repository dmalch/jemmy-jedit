package com.github.dmalch.components;

import java.awt.*;
import java.nio.file.Path;

public interface Editor extends Assertable<Editor> {
    Editor typeText(final String text);

    Editor clickUndo();

    Editor clickRedo();

    SettingsDialog clickOptions();

    OpenFileDialog clickOpenFile();

    TipPopup tipPopup();

    Editor closeTipsPopupIfExists();

    String editorText();

    Color editorBackgroundColor();

    Editor openFile(Path textFile);

    Editor saveFile();
}
