package com.github.dmalch.components;

import org.gjt.sp.jedit.textarea.JEditTextArea;
import org.hamcrest.FeatureMatcher;
import org.hamcrest.Matcher;
import org.netbeans.jemmy.operators.ComponentOperator;
import org.netbeans.jemmy.operators.JFrameOperator;
import org.netbeans.jemmy.operators.Operator;

public class MainWindowImpl implements MainWindow {
    private JFrameOperator frameOperator;

    public MainWindowImpl(final JFrameOperator frameOperator) {
        this.frameOperator = frameOperator;
    }

    @Override
    public MainWindow typeText(final String text) {
        final JEditTextArea source = getTextArea();

        for (final Character c : text.toCharArray()) {
            source.userInput(c);
        }

        return this;
    }

    private JEditTextArea getTextArea() {
        final ComponentOperator jEditText = frameOperator.createSubOperator(new Operator.Finder(JEditTextArea.class));
        return (JEditTextArea) jEditText.getSource();
    }

    @Override
    public MainWindow then(final Matcher<MainWindow> matcher) {
        matcher.matches(this);
        return this;
    }

    @Override
    public String editorText() {
        return getTextArea().getText();
    }

    public static Matcher<MainWindow> editor(final Matcher<String> matcher) {
        return new FeatureMatcher<MainWindow, String>(matcher, "editor text", "editor text") {
            @Override
            protected String featureValueOf(final MainWindow actual) {
                return actual.editorText();
            }
        };
    }
}
