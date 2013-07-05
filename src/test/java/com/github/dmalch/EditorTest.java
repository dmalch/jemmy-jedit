package com.github.dmalch;

import org.junit.Test;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;

import static com.github.dmalch.matchers.Matchers.*;
import static com.google.common.collect.Lists.newArrayList;
import static org.apache.commons.lang.RandomStringUtils.randomAlphanumeric;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

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

    @Test
    public void testEditorShouldOpenFile() throws Exception {
        final String expectedText = givenText();
        final Path textFile = givenTextFileWith(expectedText);
        openEditor()
                .closeTipsPopupIfExists()
                .openFile(textFile)
                .then(waitForEditorText(containsString(expectedText)));
    }

    @Test
    public void testEditorShouldOpenEditAndSaveFile() throws Exception {
        final String expectedText = givenText();
        final Path textFile = givenTextFileWith(expectedText);
        final String newText = givenText();
        openEditor()
                .closeTipsPopupIfExists()
                .openFile(textFile)
                .then(waitForEditorText())
                .typeText(newText)
                .saveFile();

        assertThat(textFile, fileContent(contains(equalTo(newText + expectedText))));
    }

    private Path givenTextFileWith(final String expectedText) throws IOException {
        final Path tempFile = Files.createTempFile("jemmy", "textfile");

        Files.write(tempFile, newArrayList(expectedText), Charset.defaultCharset());

        return tempFile;
    }

    private String givenText() {
        return randomAlphanumeric(10);
    }
}
