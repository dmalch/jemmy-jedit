package com.github.dmalch.components.impl;

import org.netbeans.jemmy.ComponentChooser;
import org.netbeans.jemmy.operators.ContainerOperator;
import org.netbeans.jemmy.operators.JButtonOperator;
import org.netbeans.jemmy.operators.JFrameOperator;
import org.netbeans.jemmy.util.NameComponentChooser;

public class AbstractContainer {
    protected final JFrameOperator frameOperator;

    public AbstractContainer(final JFrameOperator frameOperator) {
        this.frameOperator = frameOperator;
    }

    protected JButtonOperator findButtonIn(final ContainerOperator operator, final ComponentChooser chooser, final int index) {
        return new JButtonOperator(operator, chooser, index);
    }

    protected JButtonOperator findButtonIn(final ContainerOperator operator, final String text, final int index) {
        return new JButtonOperator(operator, text, index);
    }

    protected static NameComponentChooser byName(final String filename) {
        return new NameComponentChooser(filename);
    }
}
