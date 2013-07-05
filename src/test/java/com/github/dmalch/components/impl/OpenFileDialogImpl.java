package com.github.dmalch.components.impl;

import com.github.dmalch.components.OpenFileDialog;
import org.netbeans.jemmy.operators.JButtonOperator;
import org.netbeans.jemmy.operators.JDialogOperator;
import org.netbeans.jemmy.operators.JFrameOperator;
import org.netbeans.jemmy.operators.JTextFieldOperator;
import org.netbeans.jemmy.util.NameComponentChooser;

import java.nio.file.Path;

public class OpenFileDialogImpl extends AbstractContainer implements OpenFileDialog {
    private final JDialogOperator dialog;

    public OpenFileDialogImpl(final JFrameOperator frameOperator) {
        super(frameOperator);
        dialog = new JDialogOperator(frameOperator, "File Browser - Open");
    }

    @Override
    public OpenFileDialog openFile(final Path textFile) {
        findTextField(byName("filename")).typeText(textFile.toString());
        findButton("Open").clickMouse();
        return this;
    }

    private JTextFieldOperator findTextField(final NameComponentChooser nameChooser) {
        return new JTextFieldOperator(dialog, nameChooser);
    }

    protected JButtonOperator findButton(final String text) {
        return findButtonIn(dialog, text, 0);
    }
}
