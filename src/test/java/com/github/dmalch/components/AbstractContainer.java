package com.github.dmalch.components;

import org.gjt.sp.jedit.gui.EnhancedButton;
import org.netbeans.jemmy.ComponentChooser;
import org.netbeans.jemmy.operators.ComponentOperator;
import org.netbeans.jemmy.operators.JButtonOperator;
import org.netbeans.jemmy.operators.JFrameOperator;
import org.netbeans.jemmy.operators.Operator;

import java.awt.*;

public class AbstractContainer {
    protected final JFrameOperator frameOperator;

    public AbstractContainer(final JFrameOperator frameOperator) {
        this.frameOperator = frameOperator;
    }

    public static Operator.Finder byNameInToolbar(final String name) {
        return new JButtonOperator.Finder(EnhancedButton.class, new ComponentChooser() {
            @Override
            public boolean checkComponent(final Component comp) {
                return name.equals(comp.getName());
            }

            @Override
            public String getDescription() {
                return null;
            }
        });
    }

    protected JButtonOperator findButton(final Operator.Finder chooser) {
        return new JButtonOperator(frameOperator, chooser);
    }

    protected ComponentOperator findComponent(final Operator.Finder chooser) {
        return new ComponentOperator(frameOperator, chooser);
    }
}
