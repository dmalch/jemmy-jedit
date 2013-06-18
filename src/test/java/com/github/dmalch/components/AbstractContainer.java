package com.github.dmalch.components;

import org.netbeans.jemmy.operators.JFrameOperator;

public class AbstractContainer {
    protected final JFrameOperator frameOperator;

    public AbstractContainer(final JFrameOperator frameOperator) {
        this.frameOperator = frameOperator;
    }
}
