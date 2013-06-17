package com.github.dmalch.components;

import org.gjt.sp.jedit.gui.EnhancedButton;
import org.netbeans.jemmy.ComponentChooser;
import org.netbeans.jemmy.operators.ComponentOperator;
import org.netbeans.jemmy.operators.JButtonOperator;
import org.netbeans.jemmy.operators.JFrameOperator;
import org.netbeans.jemmy.util.NameComponentChooser;

public class AbstractContainer {
    protected final JFrameOperator frameOperator;

    public AbstractContainer(final JFrameOperator frameOperator) {
        this.frameOperator = frameOperator;
    }

    public static ComponentChooser byNameInToolbar(final String name) {
        return new JButtonOperator.Finder(EnhancedButton.class, new NameComponentChooser(name));
    }

    protected JButtonOperator findButton(final ComponentChooser chooser) {
        return new JButtonOperator(frameOperator, chooser);
    }

    protected ComponentOperator findComponent(final ComponentChooser chooser) {
        return new ComponentOperator(frameOperator, chooser);
    }
}
