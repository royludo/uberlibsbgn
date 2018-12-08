package org.sbgn.uberlibsbgn;

public class DefaultUMapFactory {

    private static UMap defaultMap;

    static {
        defaultMap = new UMap();
    }

    public static UMap getDefaultUMap() {
        return defaultMap;
    }
}
