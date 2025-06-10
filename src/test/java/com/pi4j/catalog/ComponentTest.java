package com.pi4j.catalog;

import com.pi4j.Pi4J;
import com.pi4j.context.Context;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public abstract class ComponentTest {
    protected Context pi4j;

    @BeforeEach
    public final void setUpPi4J() {
        pi4j = Pi4J.newAutoContext();
    }

    @AfterEach
    public void tearDownPi4J() {
        pi4j.shutdown();
    }
}
