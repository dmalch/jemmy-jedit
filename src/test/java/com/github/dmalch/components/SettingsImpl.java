package com.github.dmalch.components;

import org.gjt.sp.jedit.gui.ColorWellButton;
import org.netbeans.jemmy.operators.JButtonOperator;
import org.netbeans.jemmy.operators.JDialogOperator;
import org.netbeans.jemmy.operators.JFrameOperator;
import org.netbeans.jemmy.operators.JTreeOperator;

import java.awt.*;

public class SettingsImpl extends AbstractContainer implements Settings {

    private final JDialogOperator dialog;

    public SettingsImpl(final JFrameOperator frameOperator) {
        super(frameOperator);
        dialog = new JDialogOperator(frameOperator, "Global Options : jEdit: General");
    }

    @Override
    public Settings goToTextArea() {
        final JTreeOperator tree = findTree();
        final int textArea = tree.findRow("TextArea");
        tree.selectRow(textArea);
        return this;
    }

    @Override
    public Settings changeBackgroundColorTo(final Color color) {
        final ColorWellButton source = (ColorWellButton) chooseBackgroundColorButton().getSource();
        source.setSelectedColor(color);
        return this;
    }

    @Override
    public Editor clickOK() {
        findButton("OK").clickMouse();
        return new EditorImpl(frameOperator);
    }

    @Override
    public Editor clickClose() {
        findButton("Cancel").clickMouse();
        return new EditorImpl(frameOperator);
    }

    private JTreeOperator findTree() {
        return new JTreeOperator(dialog);
    }

    private JButtonOperator chooseBackgroundColorButton() {
        return new JButtonOperator(dialog, new JButtonOperator.Finder(ColorWellButton.class), 1);
    }

    protected JButtonOperator findButton(final String text) {
        return new JButtonOperator(dialog, text);
    }
}
