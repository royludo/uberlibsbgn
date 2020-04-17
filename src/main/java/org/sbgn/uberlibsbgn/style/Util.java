package org.sbgn.uberlibsbgn.style;

import javafx.scene.paint.Color;

public class Util {

    /**
     * From https://stackoverflow.com/a/56733608
     * @param val
     * @return
     */
    private static String format(double val) {
        String in = Integer.toHexString((int) Math.round(val * 255));
        return in.length() == 1 ? "0" + in : in;
    }

    /**
     * From https://stackoverflow.com/a/56733608
     * @param c Color
     * @return #ARGB css string
     */
    public static String webString(Color c) {
        return "#" + (format(c.getRed()) + format(c.getGreen()) + format(c.getBlue()) + format(c.getOpacity()))
                .toUpperCase();
    }
}
