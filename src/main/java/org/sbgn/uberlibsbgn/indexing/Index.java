package org.sbgn.uberlibsbgn.indexing;

import org.sbgn.uberlibsbgn.AbstractUGlyph;
import org.sbgn.uberlibsbgn.glyphfeatures.ArcChangeListener;
import org.sbgn.uberlibsbgn.glyphfeatures.CompositeChangeListener;
import org.sbgn.uberlibsbgn.glyphfeatures.EventType;
import org.sbgn.uberlibsbgn.glyphfeatures.FeatureType;

import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.Set;

public interface Index extends PropertyChangeListener, CompositeChangeListener, ArcChangeListener {

    void parse(AbstractUGlyph uGlyph);
    String getIndexKey();
    Set<EventType> getEventTypes();
    Set<FeatureType> getFeatureTypes();
}
