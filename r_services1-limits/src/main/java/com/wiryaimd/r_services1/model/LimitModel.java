package com.wiryaimd.r_services1.model;

public class LimitModel {

    private int minimum;
    private int maximum;

    public LimitModel(int minimum, int maximum) {
        this.minimum = minimum;
        this.maximum = maximum;
    }

    public int getMinimum() {
        return minimum;
    }

    public int getMaximum() {
        return maximum;
    }
}
