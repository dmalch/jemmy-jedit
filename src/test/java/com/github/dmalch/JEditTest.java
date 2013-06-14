package com.github.dmalch;

import com.github.dmalch.components.MainWindow;
import com.github.dmalch.components.MainWindowImpl;
import org.junit.Test;
import org.netbeans.jemmy.ClassReference;
import org.netbeans.jemmy.operators.JFrameOperator;

import java.lang.reflect.InvocationTargetException;

import static com.github.dmalch.components.MainWindowImpl.editor;
import static org.hamcrest.Matchers.containsString;

public class JEditTest {

    @Test
    public void testTypeText() throws Exception {
        final String expectedText = "sample text";

        openEditor()
                .typeText(expectedText)
                .then(editor(containsString(expectedText)));
    }

    private MainWindow openEditor() throws InvocationTargetException, NoSuchMethodException, ClassNotFoundException, InterruptedException {
        new ClassReference("org.gjt.sp.jedit.jEdit").startApplication();
        return new MainWindowImpl(new JFrameOperator());
    }
}
