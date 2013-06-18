package com.github.dmalch;

import org.junit.Test;

import static com.github.dmalch.components.EditorImpl.editorColor;
import static java.awt.Color.CYAN;
import static org.hamcrest.Matchers.is;

public class SettingsTest extends AbstractJEditTest {

    @Test
    public void testOpenCloseDialog() throws Exception {
        openEditor()
                .clickOptions()
                .clickClose();
    }

    @Test
    public void testChangeBackgroundColor() throws Exception {
        openEditor()
                .closeTipsPopup()
                .clickOptions()
                .goToTextArea()
                .changeBackgroundColorTo(CYAN)
                .clickOK()
                .then(editorColor(is(CYAN)));
    }
}
