package com.github.dmalch;

import com.github.dmalch.components.MainWindow;
import org.junit.Test;
import org.netbeans.jemmy.ClassReference;
import org.netbeans.jemmy.operators.JFrameOperator;

import java.lang.reflect.InvocationTargetException;

public class JEditTest {

    @Test
    public void test1() throws Exception {
        openEditor();
    }

    private MainWindow openEditor() throws InvocationTargetException, NoSuchMethodException, ClassNotFoundException {
        new ClassReference("org.gjt.sp.jedit.jEdit").startApplication();
        final JFrameOperator frameOperator = new JFrameOperator("jEdit - Untitled-1");
        return new MainWindow(frameOperator);
    }
}
