package org.sbgn.uberlibsbgn.glyphfeatures;

import org.sbgn.uberlibsbgn.AbstractUGlyph;

import java.util.EventObject;
import java.util.List;

public class CompositeChangeEvent extends EventObject {

    private final List<AbstractUGlyph> children;
    private final List<Integer> indices;

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public CompositeChangeEvent(Object source, List<AbstractUGlyph> children) {
        super(source);
        this.indices = null;
        this.children = children;
    }

    /**
     * When removed, use indices to get to objects quicker.
     * @param source
     * @param indices
     * @param children
     */
    public CompositeChangeEvent(Object source, List<Integer> indices, List<AbstractUGlyph> children) {
        super(source);
        this.children = children;
        this.indices = indices;
    }

    public List<AbstractUGlyph> getChildren() {
        return children;
    }

    public List<Integer> getIndices() {
        return indices;
    }
}
