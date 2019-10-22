package org.sbgn.uberlibsbgn.indexing;

import org.sbgn.uberlibsbgn.AbstractUGlyph;
import org.sbgn.uberlibsbgn.glyphfeatures.CompositeChangeListener;
import org.sbgn.uberlibsbgn.glyphfeatures.EventType;

import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.Set;

public interface Index extends PropertyChangeListener, CompositeChangeListener {

    void parse(AbstractUGlyph uGlyph);
    String getIndexKey();
    Set<EventType> getEventTypes();
}
