package com.company.pms.pmsbase.utils;

import java.awt.*;

public class ScreenConfig {
    private static int width;
    private static int height;

    static {
        Dimension screenSize= Toolkit.getDefaultToolkit().getScreenSize();
        width = (int) screenSize.getWidth();
        height = (int) screenSize.getHeight();
    }

    public static int getWidth() {
        return width;
    }

    public static int getHeight() {
        return height;
    }
}
