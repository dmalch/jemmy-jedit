package com.github.dmalch.components;

import java.awt.*;

public interface SettingsDialog {
    SettingsDialog goToTextArea();

    SettingsDialog changeBackgroundColorTo(final Color color);

    Editor clickOK();

    Editor clickClose();
}
