package com.github.dmalch.components;

import org.netbeans.jemmy.operators.JButtonOperator;
import org.netbeans.jemmy.operators.JDialogOperator;
import org.netbeans.jemmy.operators.JFrameOperator;

public class TipPopupImpl extends AbstractContainer implements TipPopup {
    private final JDialogOperator dialog;

    public TipPopupImpl(final JFrameOperator frameOperator) {
        super(frameOperator);
        dialog = new JDialogOperator(frameOperator, "Tip");
    }

    @Override
    public Editor clickClose() {
        findButton("Close").clickMouse();
        return new EditorImpl(frameOperator);
    }

    protected JButtonOperator findButton(final String text) {
        return new JButtonOperator(dialog, text);
    }
}
