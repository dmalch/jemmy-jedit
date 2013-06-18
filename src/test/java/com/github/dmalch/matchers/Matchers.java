package com.github.dmalch.matchers;

import com.github.dmalch.components.Editor;
import org.hamcrest.FeatureMatcher;
import org.hamcrest.Matcher;

import java.awt.*;

public class Matchers {
    public static Matcher<Editor> editorText(final Matcher<String> matcher) {
        return new FeatureMatcher<Editor, String>(matcher, "editor text", "editor text") {
            @Override
            protected String featureValueOf(final Editor actual) {
                return actual.editorText();
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
