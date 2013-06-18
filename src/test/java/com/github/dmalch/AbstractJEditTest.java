package com.github.dmalch;

import com.github.dmalch.components.Editor;
import com.github.dmalch.components.impl.EditorImpl;
import org.netbeans.jemmy.ClassReference;
import org.netbeans.jemmy.operators.JFrameOperator;

import java.lang.reflect.InvocationTargetException;

public abstract class AbstractJEditTest {
    protected Editor openEditor() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException {
        final String[] settings = {
                "-noserver",
                "-nosplash",
                "-nosettings"
        };

        new ClassReference("org.gjt.sp.jedit.jEdit").startApplication(settings);
        final JFrameOperator frameOperator = new JFrameOperator();
        frameOperator.getTimeouts().setTimeout("DialogWaiter.WaitDialogTimeout", 1000L);
        return new EditorImpl(frameOperator);
    }
}
