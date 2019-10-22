package org.sbgn.uberlibsbgn.indexing;

import org.sbgn.uberlibsbgn.glyphfeatures.EventType;

import java.util.List;
import java.util.Set;

abstract public class AbstractIndex implements Index {

    private String indexKey;
    private Set<EventType> eventTypes;

    public AbstractIndex(String indexKey, Set<EventType> eventTypes) {
        this.indexKey = indexKey;
        // sanitize event choice
        if(eventTypes.contains(EventType.ALL) && eventTypes.size() > 1) {
            throw new IllegalArgumentException("If ALL is chosen it must be the only event subscribed.");
        }
        if(eventTypes.contains(EventType.NONE) && eventTypes.size() > 1) {
            throw new IllegalArgumentException("If NONE is chosen it must be the only event subscribed.");
        }
        this.eventTypes = eventTypes;
    }

    @Override
    public String getIndexKey() {
        return indexKey;
    }

    @Override
    public Set<EventType> getEventTypes() {
        return eventTypes;
    }
}
