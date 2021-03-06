package org.sbgn.uberlibsbgn.features;

public enum EventType {
    ALL("all"),
    NONE("none"),

    LABEL("label"),
    LABELBBOX("labelbbox"),
    BBOX("bbox"),
    MULTIMER("multimer"),
    UNITOFINFOLABEL("unitofinfolabel"),
    UNITOFINFOBBOX("unitofinfobbox"),
    PORTPOSITION("portposition"),
    PORTORIENTATION("portorientation"),
    PORTDISTANCE("portdistance"),
    CLONEMARKER("clonemarker"),
    CLONEMARKERLABEL("clonemarkerlabel"),

    ARCCHANGE("arcchange"),
    CHILDADDED("childadded"),
    CHILDREMOVED("childremoved")
    ;

    private String eventKey;

    EventType(String eventKey) {
        this.eventKey = eventKey;
    }

    public String getEventKey() {
        return this.eventKey;
    }

    public static EventType fromEventKey(String eventKey) {
        switch(eventKey) {
            case "label": return LABEL;
            case "labelbbox": return LABELBBOX;
            case "bbox": return BBOX;
            case "multimer": return MULTIMER;

            case "portposition": return PORTPOSITION;
            case "portorientation": return PORTORIENTATION;
            case "portdistance": return PORTDISTANCE;
            default:
                throw new IllegalArgumentException("Event key: "+eventKey+" doesn't match any event");
        }
    }
}
