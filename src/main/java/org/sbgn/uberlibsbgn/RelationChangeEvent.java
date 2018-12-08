package org.sbgn.uberlibsbgn;

import java.util.EventObject;

public class RelationChangeEvent extends EventObject {

    AbstractUGlyph oldParent, newParent;

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public RelationChangeEvent(Object source, AbstractUGlyph oldParent, AbstractUGlyph newParent) {
        super(source);
        this.oldParent = oldParent;
        this.newParent = newParent;
    }

    public AbstractUGlyph getOldParent() {
        return oldParent;
    }

    public AbstractUGlyph getNewParent() {
        return newParent;
    }
}
