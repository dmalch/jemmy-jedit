package com.github.dmalch.components;

import org.gjt.sp.jedit.textarea.JEditTextArea;
import org.hamcrest.FeatureMatcher;
import org.hamcrest.Matcher;
import org.netbeans.jemmy.operators.JFrameOperator;
import org.netbeans.jemmy.operators.Operator;

import static org.hamcrest.MatcherAssert.assertThat;

public class EditorImpl extends AbstractContainer implements Editor {

    public EditorImpl(final JFrameOperator frameOperator) {
        super(frameOperator);
    }

    @Override
    public Editor typeText(final String text) {
        final JEditTextArea source = getTextArea();

        for (final Character c : text.toCharArray()) {
            source.userInput(c);
        }

        return this;
    }

    @Override
    public Editor clickUndo() {
        findButton(byNameInToolbar("undo")).clickMouse();
        return this;
    }

    @Override
    public Editor clickRedo() {
        findButton(byNameInToolbar("redo")).clickMouse();
        return this;
    }

    @Override
    public Settings clickOptions() {
        findButton(byNameInToolbar("combined-options")).clickMouse();
        return new SettingsImpl(frameOperator);
    }

    @Override
    public String editorText() {
        return getTextArea().getText();
    }

    @Override
    public Editor then(final Matcher<Editor> matcher) {
        assertThat(this, matcher);
        return this;
    }

    private JEditTextArea getTextArea() {
        return (JEditTextArea) findComponent(jEditTextArea()).getSource();
    }

    private Operator.Finder jEditTextArea() {
        return new Operator.Finder(JEditTextArea.class);
    }

    public static Matcher<Editor> editor(final Matcher<String> matcher) {
        return new FeatureMatcher<Editor, String>(matcher, "editor text", "editor text") {
            @Override
            protected String featureValueOf(final Editor actual) {
                return actual.editorText();
            }
        };
    }
}
