package org.sbgn.uberlibsbgn.indexing;

import org.sbgn.uberlibsbgn.glyphfeatures.CompositeChangeEvent;
import org.sbgn.uberlibsbgn.glyphfeatures.EventType;

import java.beans.PropertyChangeEvent;
import java.util.Set;

public abstract class AbstractArcIndex extends AbstractIndex {


    public AbstractArcIndex(String indexKey, Set<EventType> eventTypes) {
        super(indexKey, eventTypes);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void compositeChildAdded(CompositeChangeEvent e) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void compositeChildRemoved(CompositeChangeEvent e) {
        throw new UnsupportedOperationException();
    }
}
