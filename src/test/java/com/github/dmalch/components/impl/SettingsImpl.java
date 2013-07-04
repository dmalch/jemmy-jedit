package com.github.dmalch.components.impl;

import com.github.dmalch.components.Editor;
import com.github.dmalch.components.SettingsDialog;
import org.gjt.sp.jedit.gui.ColorWellButton;
import org.netbeans.jemmy.operators.JButtonOperator;
import org.netbeans.jemmy.operators.JDialogOperator;
import org.netbeans.jemmy.operators.JFrameOperator;
import org.netbeans.jemmy.operators.JTreeOperator;

import java.awt.*;

public class SettingsImpl extends AbstractContainer implements SettingsDialog {

    public static final int BACKGROUND_COLOR_CHOOSER_INDEX = 1;
    private final JDialogOperator dialog;

    public SettingsImpl(final JFrameOperator frameOperator) {
        super(frameOperator);
        dialog = new JDialogOperator(frameOperator, "Global Options : jEdit: General");
    }

    @Override
    public SettingsDialog goToTextArea() {
        final JTreeOperator tree = findTree();
        final int textArea = tree.findRow("TextArea");
        tree.selectRow(textArea);
        return this;
    }

    @Override
    public SettingsDialog changeBackgroundColorTo(final Color color) {
        final ColorWellButton source = (ColorWellButton) chooseBackgroundColorButton().getSource();
        source.setSelectedColor(color);
        return this;
    }

    @Override
    public Editor clickOK() {
        findButton("OK").clickMouse();
        return editor();
    }

    @Override
    public Editor clickClose() {
        findButton("Cancel").clickMouse();
        return editor();
    }

    private EditorImpl editor() {
        return new EditorImpl(frameOperator);
    }

    private JTreeOperator findTree() {
        return new JTreeOperator(dialog);
    }

    private JButtonOperator chooseBackgroundColorButton() {
        return findButtonIn(dialog, colorChooser(), BACKGROUND_COLOR_CHOOSER_INDEX);
    }

    protected JButtonOperator findButton(final String text) {
        return findButtonIn(dialog, text, 0);
    }

    private static JButtonOperator.Finder colorChooser() {
        return new JButtonOperator.Finder(ColorWellButton.class);
    }
}
