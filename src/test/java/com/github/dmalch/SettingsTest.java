package com.github.dmalch;

import org.junit.Test;

public class SettingsTest extends AbstractJEditTest {

    @Test
    public void testOpenCloseDialog() throws Exception {

        openEditor()
                .clickOptions()
                .clickClose();
    }
}
