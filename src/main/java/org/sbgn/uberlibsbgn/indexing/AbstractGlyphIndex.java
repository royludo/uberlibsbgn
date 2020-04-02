package org.sbgn.uberlibsbgn.indexing;

import org.sbgn.uberlibsbgn.UArc;
import org.sbgn.uberlibsbgn.features.ArcChangeEvent;
import org.sbgn.uberlibsbgn.features.EventType;
import org.sbgn.uberlibsbgn.features.FeatureType;

import java.util.Set;

public abstract class AbstractGlyphIndex extends AbstractIndex {

    public AbstractGlyphIndex(String indexKey, Set<EventType> eventTypes, Set<FeatureType> featureTypes) {
        super(indexKey, eventTypes, featureTypes);
    }

    @Override
    public void arcAddedAsSource(ArcChangeEvent e) {
        //throw new UnsupportedOperationException();
    }

    @Override
    public void arcAddedAsTarget(ArcChangeEvent e) {
        //throw new UnsupportedOperationException();
    }

    @Override
    public void arcRemovedAsSource(ArcChangeEvent e) {
        //throw new UnsupportedOperationException();
    }

    @Override
    public void arcRemovedAsTarget(ArcChangeEvent e) {
        //throw new UnsupportedOperationException();
    }

    @Override
    public void arcCreated(UArc uArc) {
        //throw new UnsupportedOperationException();
    }

    @Override
    public void arcDeleted(UArc uArc) {
       // throw new UnsupportedOperationException();
    }
}
