package org.sbgn.uberlibsbgn;

public enum MapProperties {

    ENABLE_POSITION_CHANGE_EVENTS("enablePositionChangeEvents")
    ;

    private String property;

    MapProperties(String property) {
        this.property = property;
    }


    @Override
    public String toString() {
        return this.property;
    }
}
