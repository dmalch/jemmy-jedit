package com.github.dmalch;

import org.junit.Test;

import static com.github.dmalch.matchers.Matchers.editorColor;
import static java.awt.Color.CYAN;
import static org.hamcrest.Matchers.is;

public class SettingsTest extends AbstractJEditTest {

    @Test
    public void testOpenCloseDialog() throws Exception {
        openEditor()
                .closeTipsPopupIfExists()
                .clickOptions()
                .clickClose();
    }

    @Test
    public void testChangeBackgroundColor() throws Exception {
        openEditor()
                .closeTipsPopupIfExists()
                .clickOptions()
                .goToTextArea()
                .changeBackgroundColorTo(CYAN)
                .clickOK()
                .then(editorColor(is(CYAN)));
    }
}
