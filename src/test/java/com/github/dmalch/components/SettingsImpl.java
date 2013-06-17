package com.github.dmalch.components;

import org.hamcrest.Matcher;
import org.netbeans.jemmy.operators.JFrameOperator;

public class SettingsImpl extends AbstractContainer implements Settings {
    public SettingsImpl(final JFrameOperator frameOperator) {
        super(frameOperator);
    }

    @Override
    public Settings then(final Matcher<Settings> matcher) {
        return null;
    }
}
