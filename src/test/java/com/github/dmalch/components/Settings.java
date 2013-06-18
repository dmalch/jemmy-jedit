package com.github.dmalch.components;

import java.awt.*;

public interface Settings extends Assertable<Settings> {
    Settings goToTextArea();

    Settings changeBackgroundColorTo(final Color color);

    Editor clickOK();

    Editor clickClose();
}
