package org.sbgn.uberlibsbgn;

import org.sbgn.uberlibsbgn.features.*;
import org.sbgn.uberlibsbgn.indexing.GlyphClassIndex;
import org.sbgn.uberlibsbgn.indexing.Index;
import org.sbgn.uberlibsbgn.indexing.LabelIndex;
import org.sbgn.uberlibsbgn.indexing.NeighborsIndex;
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
public class IndexManager implements PropertyChangeListener, CompositeChangeListener, ArcChangeListener {

    /*
    responsible for maintaining correct datastructures
    ensure uniqueness of ids

    glyphclass index
    id index
    label index, exact and regexp search
    inclusion tree, depth and breadth first iterating through all map (as the composite feature
    neighbor index,
    reaction index
    allow it from specific component)
    quadtree for collision and inclusion detection
     */


    private Map<String, AbstractUGlyph> glyphIdMap;
    private Map<String, UArc> arcIdMap;
    private Map<FeatureType, Set<AbstractUGlyph>> featureToGlyphMap;
    private Map<String, Index> indexes;
    private Map<EventType, Set<String>> eventTypeMap;
    private Map<FeatureType, Set<String>> featureToIndexMap;

    private CompositeFeature mapRoot;

    final Logger logger = LoggerFactory.getLogger(IndexManager.class);

    public IndexManager(CompositeFeature mapRoot) {
        logger.trace("Create IndexManager");
        this.glyphIdMap = new HashMap<>();
        this.arcIdMap = new HashMap<>();
        this.indexes = new HashMap<>();
        this.mapRoot = mapRoot;
        this.mapRoot.addCompositeChangeListener(this); // TODO check if this doesn't break stuff

        this.eventTypeMap = new HashMap<>();
        for(EventType eventType: EventType.values()) {
            eventTypeMap.put(eventType, new HashSet<>());
        }

        this.featureToGlyphMap = new HashMap<>();
        this.featureToIndexMap = new HashMap<>();
        for(FeatureType featureType: FeatureType.values()) {
            featureToGlyphMap.put(featureType, new HashSet<>());
            featureToIndexMap.put(featureType, new HashSet<>());
        }

        logger.trace("Init indexes");
        // init indexes
        this.addIndex(new LabelIndex());
        this.addIndex(new GlyphClassIndex());
        this.addIndex(new NeighborsIndex());
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

        for(FeatureType featureType: index.getFeatureTypes()) {
            this.featureToIndexMap.get(featureType).add(index.getIndexKey());
        }


        // subscribe to all entities with relevant feature needed by the index
        if(this.mapRoot.hasChildren()) {
            logger.trace("Subscribe and parse map");
            DepthFirstAll depthSearch = new DepthFirstAll(this.mapRoot);
            for(AbstractUGlyph uGlyph: depthSearch) {
                index.parse(uGlyph);
                coupleIndexToGlyph(index, uGlyph);
            }

            for(UArc uArc: this.arcIdMap.values()) {
                for(FeatureType featureType: index.getFeatureTypes()) {
                    logger.trace("for feature {}", featureType);
                    switch (featureType) {
                        case All:
                            uArc.addPropertyChangeListener(index);
                    }
                }
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

        for(FeatureType featureType: removedIndex.getFeatureTypes()) {
            this.featureToIndexMap.get(featureType).remove(removedIndex.getIndexKey());
        }

        for(FeatureType featureType: removedIndex.getFeatureTypes()) {
            Set<AbstractUGlyph> concernedGlyphs = this.featureToGlyphMap.get(featureType);
            for(AbstractUGlyph uGlyph: concernedGlyphs) {
                switch (featureType) {
                    case All:
                        uGlyph.removePropertyChangeListener(removedIndex);
                        if(uGlyph instanceof CompositeFeature) {
                            logger.trace("glyph is composite");
                            ((CompositeFeature) uGlyph).removeCompositeChangeListener(removedIndex);
                        }
                        if(uGlyph instanceof ArcFeature) {
                            logger.trace("glyph has arcs");
                            ((ArcFeature) uGlyph).removeArcChangeListener(removedIndex);
                        }
                }
            }

            for(UArc uArc: this.arcIdMap.values()) {
                switch (featureType) {
                    case All:
                        uArc.removePropertyChangeListener(removedIndex);
                }
            }
        }

        this.mapRoot.removeCompositeChangeListener(this.getIndex(indexLabel));
        this.indexes.remove(indexLabel);
    }

    public Index getIndex(String indexLabel) {
        return indexes.get(indexLabel);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

        logger.trace("dispatching property change EVENT: {}", evt);

        EventType eventType = EventType.fromEventKey(evt.getPropertyName());
/*
        // notify indexes which subscribed to ALL
        for(String indexKey: this.eventTypeMap.get(EventType.ALL)) {
            this.indexes.get(indexKey).propertyChange(evt);
        }
        // notify indexes which subscribed to this specific event
        for(String indexKey: this.eventTypeMap.get(eventType)) {
            this.indexes.get(indexKey).propertyChange(evt);
        }*/

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

    //TODO only trigger for appropriate events (NOT arc events)
    @Override
    public void compositeChildAdded(CompositeChangeEvent e) {
        logger.trace("dispatching composite child added {}", e);

       /* for(Index index: this.indexes.values()) {
            index.compositeChildAdded(e);
        }*/

        for(AbstractUGlyph addedGlyph: e.getChildren()) {
            logger.trace("compositeChildAdded");
            glyphIdMap.put(addedGlyph.getId(), addedGlyph);

            // subscribe each appropriate index to the new glyph
            Set<FeatureType> glyphFeatureSet = getFeaturesFromGlyph(addedGlyph);
            Set<Index> relevantIndexSet = new HashSet<>(); // the set will prevent having the same index duplicated
            for(FeatureType glyphFeatureType: glyphFeatureSet) {
                for(String indexId: this.featureToIndexMap.get(glyphFeatureType)){
                    relevantIndexSet.add(this.indexes.get(indexId));
                }
            }

            for(Index index: relevantIndexSet) {
                coupleIndexToGlyph(index, addedGlyph);
            }
        }
    }

    @Override
    public void compositeChildRemoved(CompositeChangeEvent e) {
        logger.trace("dispatching composite child removed {}", e);

        /*for(Index index: this.indexes.values()) {
            index.compositeChildRemoved(e);
        }*/

        for(AbstractUGlyph removedGlyph: e.getChildren()) {
            glyphIdMap.remove(removedGlyph.getId(), removedGlyph);

            //unsubscribe indexes from this glyph
            Set<FeatureType> glyphFeatureSet = getFeaturesFromGlyph(removedGlyph);
            Set<Index> relevantIndexSet = new HashSet<>(); // the set will prevent having the same index duplicated
            for(FeatureType glyphFeatureType: glyphFeatureSet) {
                for(String indexId: this.featureToIndexMap.get(glyphFeatureType)){
                    relevantIndexSet.add(this.indexes.get(indexId));
                }
            }

            for(Index index: relevantIndexSet) {
                decoupleIndexFromGlyph(index, removedGlyph);
            }
        }
    }

    public AbstractUGlyph getGlyph(String id) {
        return this.glyphIdMap.get(id);
    }

    public Collection<AbstractUGlyph> getAllGlyphs() {
        return this.glyphIdMap.values();
    }

    @Override
    public void arcAddedAsSource(ArcChangeEvent e) {

    }

    @Override
    public void arcAddedAsTarget(ArcChangeEvent e) {

    }

    // remove arc from index if both source and target don't exist
    // if one of them exist, arc might just be changed to another glyph
    // if both removed, means that arc was deleted
    @Override
    public void arcRemovedAsSource(ArcChangeEvent e) {

    }

    @Override
    public void arcRemovedAsTarget(ArcChangeEvent e) {

    }

    @Override
    public void arcCreated(UArc uArc) {
        if(!this.arcIdMap.containsKey(uArc.getId())) {
            this.arcIdMap.put(uArc.getId(), uArc);
        }
    }

    @Override
    public void arcDeleted(UArc uArc) {

    }

    // TODO refactor, get appropriate featuretype from the glyph, and use them to get the relevant indexes
    private void coupleIndexToGlyph(Index index, AbstractUGlyph uGlyph) {
        for(FeatureType featureType: index.getFeatureTypes()) {
            logger.trace("for feature {}", featureType);
            switch (featureType) {
                case All:
                    uGlyph.addPropertyChangeListener(index);
                    if(uGlyph instanceof CompositeFeature) {
                        logger.trace("glyph is composite");
                        ((CompositeFeature) uGlyph).addCompositeChangeListener(index);
                    }
                    if(uGlyph instanceof ArcFeature) {
                        logger.trace("glyph has arcs");
                        ((ArcFeature) uGlyph).addArcChangeListener(index);
                    }
            }
        }
    }

    private void decoupleIndexFromGlyph(Index index, AbstractUGlyph uGlyph) {
        for(FeatureType featureType: index.getFeatureTypes()) {
            logger.trace("for feature {}", featureType);
            switch (featureType) {
                case All:
                    uGlyph.removePropertyChangeListener(index);
                    if(uGlyph instanceof CompositeFeature) {
                        logger.trace("glyph is composite");
                        ((CompositeFeature) uGlyph).removeCompositeChangeListener(index);
                    }
                    if(uGlyph instanceof ArcFeature) {
                        logger.trace("glyph has arcs");
                        ((ArcFeature) uGlyph).removeArcChangeListener(index);
                    }
            }
        }
    }

    private Set<FeatureType> getFeaturesFromGlyph(AbstractUGlyph uGlyph) {
        Set<FeatureType> result = new HashSet<>();
        result.add(FeatureType.All); // ALL concerns absolutely everything, so auto include
        return result;
    }
}
