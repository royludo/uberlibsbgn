package org.sbgn.uberlibsbgn;

import org.sbgn.uberlibsbgn.glyphfeatures.CompositeChangeEvent;
import org.sbgn.uberlibsbgn.glyphfeatures.CompositeChangeListener;
import org.sbgn.uberlibsbgn.glyphfeatures.CompositeFeature;
import org.sbgn.uberlibsbgn.glyphfeatures.EventType;
import org.sbgn.uberlibsbgn.indexing.GlyphClassIndex;
import org.sbgn.uberlibsbgn.indexing.Index;
import org.sbgn.uberlibsbgn.indexing.LabelIndex;
import org.sbgn.uberlibsbgn.traversing.DepthFirstAll;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.*;

/**
 * Index management and all other structures built on the side should updated first when a USomething is changed.
 * Then after everything is in a consistent state and all information is processed, the original libsbgn
 * structures should be updated.
 *
 * The flow should be: USomething gets changed by user -> index get updated -> libsbgn gets updated
 *
 * An index manager could manage one single index type. We could have several index managees, so users can
 * create custom ones and add it themselves to the map.
 */
public class IndexManager implements PropertyChangeListener, CompositeChangeListener {

    /*
    responsible for maintaining correct datastructures
    ensure uniqueness of ids

    glyphclass index
    id index
    label index, exact and regexp search
    inclusion tree, depth and breadth first iterating through all map (as the composite feature
    allow it from specific component)
    quadtree for collision and inclusion detection
     */


    private Map<String, AbstractUGlyph> idMap;
    private Map<String, Index> indexes;
    private Map<EventType, Set<String>> eventTypeMap;

    private CompositeFeature mapRoot;

    final Logger logger = LoggerFactory.getLogger(IndexManager.class);

    public IndexManager(CompositeFeature mapRoot) {
        logger.trace("Create IndexManager");
        this.idMap = new HashMap<>();
        this.indexes = new HashMap<>();
        this.mapRoot = mapRoot;

        this.eventTypeMap = new HashMap<>();
        for(EventType eventType: EventType.values()) {
            eventTypeMap.put(eventType, new HashSet<>());
        }

        logger.trace("Init indexes");
        // init indexes
        this.addIndex(new LabelIndex());
        this.addIndex(new GlyphClassIndex());
    }

    public void addIndex(Index index) {
        logger.trace("Add index: {}", index.getIndexKey());

        if(this.indexes.containsKey(index.getIndexKey())) {
            throw new IllegalArgumentException("The key '"+index.getIndexKey()+"' is already used, please choose another.");
        }

        // subscribe the index to all its chosen event types
        for(EventType evtype: index.getEventTypes()) {
            eventTypeMap.get(evtype).add(index.getIndexKey());
        }

        if(this.mapRoot.hasChildren()) {

            logger.trace("Parse map");
            DepthFirstAll depthSearch = new DepthFirstAll(this.mapRoot);
            for(AbstractUGlyph uGlyph: depthSearch) {
                index.parse(uGlyph);
            }

        }

        this.indexes.put(index.getIndexKey(), index);
        this.mapRoot.addCompositeChangeListener(index);
    }

    public void removeIndex(String indexLabel) {
        logger.trace("Remove index: {}", indexLabel);

        Index removedIndex = this.getIndex(indexLabel);
        for(EventType evtype: removedIndex.getEventTypes()) {
            this.eventTypeMap.get(evtype).remove(indexLabel);
        }

        this.mapRoot.removeCompositeChangeListener(this.getIndex(indexLabel));
        this.indexes.remove(indexLabel);
    }

    public Index getIndex(String indexLabel) {
        return indexes.get(indexLabel);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

        logger.trace("property change EVENT: {}", evt);

        EventType eventType = EventType.fromEventKey(evt.getPropertyName());

        // notify indexes which subscribed to ALL
        for(String indexKey: this.eventTypeMap.get(EventType.ALL)) {
            this.indexes.get(indexKey).propertyChange(evt);
        }
        // notify indexes which subscribed to this specific event
        for(String indexKey: this.eventTypeMap.get(eventType)) {
            this.indexes.get(indexKey).propertyChange(evt);
        }

        /*AbstractUGlyph sourceGlyph = (AbstractUGlyph) evt.getSource();
        switch(evt.getPropertyName()) {
            case "class": break;
            // update class
            case "id":
                this.idMap.remove((String) evt.getOldValue());
                this.idMap.put((String) evt.getNewValue(), sourceGlyph);
                break;
        }*/

    }

    /*public Map<String, AbstractUGlyph> getIdMap() {
        return idMap;
    }*/

    @Override
    public void compositeChildAdded(CompositeChangeEvent e) {
        logger.trace("composite child added {}", e);

        for(Index index: this.indexes.values()) {
            index.compositeChildAdded(e);
        }

        for(AbstractUGlyph addedGlyph: e.getChildren()) {
            idMap.put(addedGlyph.getId(), addedGlyph);
        }
    }

    @Override
    public void compositeChildRemoved(CompositeChangeEvent e) {
        logger.trace("composite child removed {}", e);

        for(Index index: this.indexes.values()) {
            index.compositeChildRemoved(e);
        }

        for(AbstractUGlyph removedGlyph: e.getChildren()) {
            idMap.remove(removedGlyph.getId(), removedGlyph);
        }
    }

    public AbstractUGlyph getGlyph(String id) {
        return this.idMap.get(id);
    }

    public Collection<AbstractUGlyph> getAllGlyphs() {
        return this.idMap.values();
    }
}
