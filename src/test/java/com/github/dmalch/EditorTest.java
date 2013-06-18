package com.github.dmalch;

import org.apache.commons.lang.RandomStringUtils;
import org.junit.Test;

import static com.github.dmalch.components.EditorImpl.editorText;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.isEmptyString;

public class EditorTest extends AbstractJEditTest {

    @Test
    public void testTypeText() throws Exception {
        final String expectedText = givenText();

        openEditor()
                .typeText(expectedText)
                .then(editorText(containsString(expectedText)));
    }

    @Test
    public void testUndoType() throws Exception {
        final String expectedText = givenText();

        openEditor()
                .typeText(expectedText)
                .clickUndo()
                .then(editorText(isEmptyString()));
    }

    @Test
    public void testRedoType() throws Exception {
        final String expectedText = givenText();

        openEditor()
                .typeText(expectedText)
                .clickUndo()
                .clickRedo()
                .then(editorText(containsString(expectedText)));
    }

    private String givenText() {
        return RandomStringUtils.randomAlphanumeric(10);
    }
}
