package com.github.dmalch;

import com.github.dmalch.components.Editor;
import com.github.dmalch.components.EditorImpl;
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
//        new ClassReference("org.netbeans.jemmy.explorer.GUIBrowser").startApplication();
        return new EditorImpl(new JFrameOperator());
    }
}
