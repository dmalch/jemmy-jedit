package com.github.dmalch.matchers;

import com.github.dmalch.components.Editor;
import com.google.common.base.Stopwatch;
import org.hamcrest.FeatureMatcher;
import org.hamcrest.Matcher;

import java.awt.*;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static com.github.dmalch.AbstractJEditTest.WAIT_TIMEOUT;
import static java.util.Collections.emptyList;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.hamcrest.Matchers.isEmptyString;
import static org.hamcrest.Matchers.not;

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
        return new WaitableFeatureMatcher<Editor, String>(matcher, "editor text", "editor text") {
            @Override
            public String getValueOf(final Editor actual) {
                return actual.editorText();
            }
        };
    }

    public static Matcher<Path> fileContent(final Matcher<Iterable<? extends String>> matcher) {
        return new WaitableFeatureMatcher<Path, List<String>>(matcher, "file content", "file content") {
            @Override
            public List<String> getValueOf(final Path actual) {
                return readLines(actual);
            }

            private List<String> readLines(final Path actual) {
                try {
                    return Files.readAllLines(actual, Charset.defaultCharset());
                } catch (IOException ignored) {
                }
                return emptyList();
            }
        };
    }

    public static Matcher<Editor> waitForEditorText() {
        return waitForEditorText(not(isEmptyString()));
    }

    public static Matcher<Editor> editorColor(final Matcher<Color> matcher) {
        return new FeatureMatcher<Editor, Color>(matcher, "editor background", "editor background") {
            @Override
            protected Color featureValueOf(final Editor actual) {
                return actual.editorBackgroundColor();
            }
        };
    }

    private abstract static class WaitableFeatureMatcher<U, T> extends FeatureMatcher<U, T> {

        private Matcher<? super T> subMatcher;

        private WaitableFeatureMatcher(final Matcher<? super T> subMatcher, final String featureDescription, final String featureName) {
            super(subMatcher, featureDescription, featureName);
            this.subMatcher = subMatcher;
        }

        @Override
        protected T featureValueOf(final U actual) {
            Boolean isMatched = false;
            final Stopwatch stopWatch = new Stopwatch();
            stopWatch.start();
            T t = null;
            while (!isMatched) {
                t = getValueOf(actual);
                isMatched = subMatcher.matches(t);
                if (stopWatch.elapsed(MILLISECONDS) > WAIT_TIMEOUT) {
                    break;
                }
            }
            return t;
        }

        public abstract T getValueOf(final U actual);
    }
}
