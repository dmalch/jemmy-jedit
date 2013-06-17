package com.github.dmalch;

import org.junit.Test;

public class SettingsTest extends AbstractJEditTest {

    @Test
    public void testOpenCloseDialog() throws Exception {
        openEditor()
                .clickOptions()
                .clickClose();
    }

    @Test
    public void testOpemadad() throws Exception {
        openEditor()
                .clickOptions()
                .goToTextArea();
    }
}
