package com.github.dmalch.matchers;

import com.github.dmalch.components.Editor;
import com.google.common.base.Stopwatch;
import com.google.common.base.Strings;
import org.hamcrest.FeatureMatcher;
import org.hamcrest.Matcher;

import java.awt.*;

import static com.github.dmalch.AbstractJEditTest.WAIT_TIMEOUT;
import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class Matchers {
    public static Matcher<Editor> editorText(final Matcher<String> matcher) {
        return new FeatureMatcher<Editor, String>(matcher, "editor text", "editor text") {
            @Override
            protected String featureValueOf(final Editor actual) {
                return actual.editorText();
            }
        };
    }

    public static Matcher<Editor> waitForEditorText(final Matcher<String> matcher) {
        return new FeatureMatcher<Editor, String>(matcher, "editor text", "editor text") {
            @Override
            protected String featureValueOf(final Editor actual) {
                String editorText = null;
                final Stopwatch stopWatch = new Stopwatch();
                stopWatch.start();
                while (Strings.isNullOrEmpty(editorText)) {
                    editorText = actual.editorText();
                    if (stopWatch.elapsed(MILLISECONDS) > WAIT_TIMEOUT) {
                        throw new RuntimeException("Timeout has been reached");
                    }
                }
                return editorText;
            }
        };
    }

    public static Matcher<Editor> editorColor(final Matcher<Color> matcher) {
        return new FeatureMatcher<Editor, Color>(matcher, "editor background", "editor background") {
            @Override
            protected Color featureValueOf(final Editor actual) {
                return actual.editorBackgroundColor();
            }
        };
    }
}
