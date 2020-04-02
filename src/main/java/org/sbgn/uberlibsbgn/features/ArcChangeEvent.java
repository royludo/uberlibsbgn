package org.sbgn.uberlibsbgn.features;

import org.sbgn.uberlibsbgn.UArc;

import java.util.EventObject;

public class ArcChangeEvent extends EventObject {
    private UArc arc;

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ArcChangeEvent(Object source, UArc arc) {
        super(source);
        this.arc = arc;
    }

    public UArc getArc() {
        return arc;
    }
}
