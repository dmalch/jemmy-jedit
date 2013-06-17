package com.github.dmalch;

import com.github.dmalch.components.Editor;
import com.github.dmalch.components.EditorImpl;
import org.netbeans.jemmy.ClassReference;
import org.netbeans.jemmy.operators.JFrameOperator;

import java.lang.reflect.InvocationTargetException;

public abstract class AbstractJEditTest {
    protected Editor openEditor() throws InvocationTargetException, NoSuchMethodException, ClassNotFoundException, InterruptedException {
        new ClassReference("org.gjt.sp.jedit.jEdit").startApplication(new String[]{"-noserver"});
        return new EditorImpl(new JFrameOperator());
    }
}
