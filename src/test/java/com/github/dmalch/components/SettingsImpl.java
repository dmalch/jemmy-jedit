package com.github.dmalch.components;

import org.hamcrest.Matcher;
import org.netbeans.jemmy.ComponentChooser;
import org.netbeans.jemmy.operators.JButtonOperator;
import org.netbeans.jemmy.operators.JDialogOperator;
import org.netbeans.jemmy.operators.JFrameOperator;
import org.netbeans.jemmy.operators.JTreeOperator;

import javax.swing.*;

import static org.hamcrest.MatcherAssert.assertThat;

public class SettingsImpl extends AbstractContainer implements Settings {

    private final JDialogOperator dialog;

    public SettingsImpl(final JFrameOperator frameOperator) {
        super(frameOperator);
        dialog = new JDialogOperator(frameOperator, "Global Options : jEdit: General");
    }

    @Override
    public Settings goToTextArea() {
        final JTreeOperator tree = findTree(new JTreeOperator.Finder(JTree.class));
        return this;
    }

    @Override
    public Editor clickClose() {
        findButton("Cancel").clickMouse();
        return new EditorImpl(frameOperator);
    }

    @Override
    public Settings then(final Matcher<Settings> matcher) {
        assertThat(this, matcher);
        return this;
    }

    protected JButtonOperator findButton(final String text) {
        return new JButtonOperator(dialog, text);
    }

    private JTreeOperator findTree(final ComponentChooser chooser) {
        return new JTreeOperator(dialog);
    }
}
