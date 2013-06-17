package com.github.dmalch;

import org.apache.commons.lang.RandomStringUtils;
import org.junit.Test;

import static com.github.dmalch.components.EditorImpl.editor;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.isEmptyString;

public class EditorTest extends AbstractJEditTest {

    @Test
    public void testTypeText() throws Exception {
        final String expectedText = givenText();

        openEditor()
                .typeText(expectedText)
                .then(editor(containsString(expectedText)));
    }

    @Test
    public void testUndoType() throws Exception {
        final String expectedText = givenText();

        openEditor()
                .typeText(expectedText)
                .clickUndo()
                .then(editor(isEmptyString()));
    }

    @Test
    public void testRedoType() throws Exception {
        final String expectedText = givenText();

        openEditor()
                .typeText(expectedText)
                .clickUndo()
                .clickRedo()
                .then(editor(containsString(expectedText)));
    }

    private String givenText() {
        return RandomStringUtils.randomAlphanumeric(10);
    }
}
