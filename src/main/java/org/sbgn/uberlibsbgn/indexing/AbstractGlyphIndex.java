package org.sbgn.uberlibsbgn.indexing;

import org.sbgn.uberlibsbgn.glyphfeatures.ArcChangeEvent;
import org.sbgn.uberlibsbgn.glyphfeatures.EventType;

import java.util.Set;

public abstract class AbstractGlyphIndex extends AbstractIndex {

    public AbstractGlyphIndex(String indexKey, Set<EventType> eventTypes) {
        super(indexKey, eventTypes);
    }

    @Override
    public void arcAddedAsSource(ArcChangeEvent e) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void arcAddedAsTarget(ArcChangeEvent e) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void arcRemovedAsSource(ArcChangeEvent e) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void arcRemovedAsTarget(ArcChangeEvent e) {
        throw new UnsupportedOperationException();
    }
}
