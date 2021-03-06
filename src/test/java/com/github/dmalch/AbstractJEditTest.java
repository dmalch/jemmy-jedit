package com.github.dmalch;

import com.github.dmalch.components.Editor;
import com.github.dmalch.components.impl.EditorImpl;
import org.netbeans.jemmy.ClassReference;
import org.netbeans.jemmy.operators.JFrameOperator;

import java.lang.reflect.InvocationTargetException;

import static org.apache.commons.lang.RandomStringUtils.randomAlphanumeric;

public abstract class AbstractJEditTest {

    public static final long WAIT_TIMEOUT = 1000L;

    protected Editor openEditor() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException {
        final String[] settings = {
                "-noserver",
                "-nosplash",
                "-nosettings"
        };

        new ClassReference("org.gjt.sp.jedit.jEdit").startApplication(settings);
        final JFrameOperator frameOperator = new JFrameOperator();
        frameOperator.getTimeouts().setTimeout("DialogWaiter.WaitDialogTimeout", WAIT_TIMEOUT);
        return new EditorImpl(frameOperator);
    }

    protected String givenText() {
        return randomAlphanumeric(10);
    }
}
