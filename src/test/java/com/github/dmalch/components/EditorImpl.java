package com.github.dmalch.components;

import org.gjt.sp.jedit.gui.EnhancedButton;
import org.gjt.sp.jedit.textarea.JEditTextArea;
import org.hamcrest.FeatureMatcher;
import org.hamcrest.Matcher;
import org.netbeans.jemmy.ComponentChooser;
import org.netbeans.jemmy.operators.ComponentOperator;
import org.netbeans.jemmy.operators.JButtonOperator;
import org.netbeans.jemmy.operators.JFrameOperator;
import org.netbeans.jemmy.operators.Operator;

import java.awt.*;

import static org.hamcrest.MatcherAssert.assertThat;

public class EditorImpl implements Editor {
    private final JFrameOperator frameOperator;

    public EditorImpl(final JFrameOperator frameOperator) {
        this.frameOperator = frameOperator;
    }

    @Override
    public Editor typeText(final String text) {
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
    public Editor then(final Matcher<Editor> matcher) {
        assertThat(this, matcher);
        return this;
    }

    @Override
    public Editor clickUndo() {
        final JButtonOperator undo = new JButtonOperator(frameOperator, buttonByName("undo"));
        undo.clickMouse();
        return this;
    }

    @Override
    public Editor clickRedo() {
        final JButtonOperator redo = new JButtonOperator(frameOperator, buttonByName("redo"));
        redo.clickMouse();
        return this;
    }

    @Override
    public String editorText() {
        return getTextArea().getText();
    }

    private JButtonOperator.Finder buttonByName(final String name) {
        return new JButtonOperator.Finder(EnhancedButton.class, new ComponentChooser() {
            @Override
            public boolean checkComponent(final Component comp) {
                return name.equals(comp.getName());
            }

            @Override
            public String getDescription() {
                return null;
            }
        });
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
