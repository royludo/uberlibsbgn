package org.sbgn.uberlibsbgn.glyphfeatures;

import org.sbgn.uberlibsbgn.AbstractUGlyph;
import org.sbgn.uberlibsbgn.UArc;

import java.util.EventObject;
import java.util.List;

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
