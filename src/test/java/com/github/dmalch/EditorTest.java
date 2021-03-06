package com.github.dmalch;

import org.junit.Test;

import static com.github.dmalch.matchers.Matchers.editorText;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.isEmptyString;

public class EditorTest extends AbstractJEditTest {

    @Test
    public void testTypeText() throws Exception {
        final String expectedText = givenText();

        openEditor()
                .closeTipsPopupIfExists()
                .typeText(expectedText)
                .then(editorText(containsString(expectedText)));
    }

    @Test
    public void testUndoType() throws Exception {
        final String expectedText = givenText();

        openEditor()
                .closeTipsPopupIfExists()
                .typeText(expectedText)
                .clickUndo()
                .then(editorText(isEmptyString()));
    }

    @Test
    public void testRedoType() throws Exception {
        final String expectedText = givenText();

        openEditor()
                .closeTipsPopupIfExists()
                .typeText(expectedText)
                .clickUndo()
                .clickRedo()
                .then(editorText(containsString(expectedText)));
    }

}
