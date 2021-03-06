package com.github.dmalch.components.impl;

import com.github.dmalch.components.Editor;
import com.github.dmalch.components.TipPopup;
import com.google.common.base.Optional;
import org.netbeans.jemmy.TimeoutExpiredException;
import org.netbeans.jemmy.operators.JButtonOperator;
import org.netbeans.jemmy.operators.JDialogOperator;
import org.netbeans.jemmy.operators.JFrameOperator;

import static com.google.common.base.Optional.absent;
import static com.google.common.base.Optional.of;

public class TipPopupImpl extends AbstractContainer implements TipPopup {
    private Optional<JDialogOperator> dialog;

    public TipPopupImpl(final JFrameOperator frameOperator) {
        super(frameOperator);
        dialog = findDialog(frameOperator);
    }

    @Override
    public Editor clickClose() {
        findButton("Close").clickMouse();
        return new EditorImpl(frameOperator);
    }

    @Override
    public boolean isVisible() {
        return dialog.isPresent() && dialog.get().isVisible();
    }

    private Optional<JDialogOperator> findDialog(final JFrameOperator frameOperator) {
        try {
            return of(new JDialogOperator(frameOperator, "Tip"));
        } catch (TimeoutExpiredException e) {
            return absent();
        }
    }

    protected JButtonOperator findButton(final String text) {
        return findButtonIn(dialog.get(), text, 0);
    }
}
