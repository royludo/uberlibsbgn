package org.sbgn.uberlibsbgn.indexing;

import org.sbgn.uberlibsbgn.AbstractUGlyph;
import org.sbgn.uberlibsbgn.features.ArcChangeListener;
import org.sbgn.uberlibsbgn.features.CompositeChangeListener;
import org.sbgn.uberlibsbgn.features.EventType;
import org.sbgn.uberlibsbgn.features.FeatureType;

import java.beans.PropertyChangeListener;
import java.util.Set;

public interface Index extends PropertyChangeListener, CompositeChangeListener, ArcChangeListener {

    void parse(AbstractUGlyph uGlyph);
    String getIndexKey();
    Set<EventType> getEventTypes();
    Set<FeatureType> getFeatureTypes();
}
