package org.sbgn.uberlibsbgn.indexing;

import org.sbgn.uberlibsbgn.features.EventType;
import org.sbgn.uberlibsbgn.features.FeatureType;

import java.util.Set;

abstract public class AbstractIndex implements Index {

    private String indexKey;
    private Set<EventType> eventTypes;
    private Set<FeatureType> featureTypes;

    public AbstractIndex(String indexKey, Set<EventType> eventTypes, Set<FeatureType> featureTypes) {
        this.indexKey = indexKey;
        // sanitize event choice
        if(eventTypes.contains(EventType.ALL) && eventTypes.size() > 1) {
            throw new IllegalArgumentException("If ALL is chosen it must be the only event subscribed.");
        }
        if(eventTypes.contains(EventType.NONE) && eventTypes.size() > 1) {
            throw new IllegalArgumentException("If NONE is chosen it must be the only event subscribed.");
        }
        this.eventTypes = eventTypes;
        this.featureTypes = featureTypes;
    }

    @Override
    public String getIndexKey() {
        return indexKey;
    }

    @Override
    public Set<EventType> getEventTypes() {
        return eventTypes;
    }

    @Override
    public Set<FeatureType> getFeatureTypes() {
        return featureTypes;
    }
}
