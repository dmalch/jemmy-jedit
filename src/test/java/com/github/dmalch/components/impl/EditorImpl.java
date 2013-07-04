package com.github.dmalch.components.impl;

import com.github.dmalch.components.Editor;
import com.github.dmalch.components.SettingsDialog;
import com.github.dmalch.components.TipPopup;
import org.gjt.sp.jedit.gui.EnhancedButton;
import org.gjt.sp.jedit.textarea.JEditTextArea;
import org.hamcrest.Matcher;
import org.netbeans.jemmy.ComponentChooser;
import org.netbeans.jemmy.operators.ComponentOperator;
import org.netbeans.jemmy.operators.JButtonOperator;
import org.netbeans.jemmy.operators.JFrameOperator;
import org.netbeans.jemmy.operators.Operator;

import java.awt.*;
import java.nio.file.Path;

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
    public SettingsDialog clickOptions() {
        findButton(byNameInToolbar("combined-options")).pushNoBlock();
        return new SettingsImpl(frameOperator);
    }

    @Override
    public Editor closeTipsPopupIfExists() {
        final TipPopup tipPopup = tipPopup();
        if (tipPopup.isVisible()) {
            return tipPopup.clickClose();
        }
        return this;
    }

    @Override
    public TipPopup tipPopup() {
        return new TipPopupImpl(frameOperator);
    }

    @Override
    public String editorText() {
        try {
            return getTextArea().getText();
        } catch (Exception e) {
            return "";
        }
    }

    @Override
    public Color editorBackgroundColor() {
        return getTextArea().getPainter().getBackground();
    }

    @Override
    public Editor openFile(final Path textFile) {
        clickOpenFile().openFile(textFile);
        return this;
    }

    @Override
    public OpenFileDialog clickOpenFile() {
        findButton(byNameInToolbar("open-file")).pushNoBlock();
        return new OpenFileDialogImpl(frameOperator);
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

    private JButtonOperator findButton(final ComponentChooser chooser) {
        return findButtonIn(frameOperator, chooser, 0);
    }

    private ComponentOperator findComponent(final ComponentChooser chooser) {
        return new ComponentOperator(frameOperator, chooser);
    }

    public static ComponentChooser byNameInToolbar(final String name) {
        return new JButtonOperator.Finder(EnhancedButton.class, byName(name));
    }
}
