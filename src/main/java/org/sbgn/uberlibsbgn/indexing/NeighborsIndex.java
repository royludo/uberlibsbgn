package org.sbgn.uberlibsbgn.indexing;

import org.sbgn.uberlibsbgn.AbstractUGlyph;
import org.sbgn.uberlibsbgn.UArc;
import org.sbgn.uberlibsbgn.Utilities;
import org.sbgn.uberlibsbgn.glyphfeatures.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.PropertyChangeEvent;
import java.util.*;

public class NeighborsIndex extends AbstractIndex {

    private Map<AbstractUGlyph, Set<AbstractUGlyph>> neighbors;

    final Logger logger = LoggerFactory.getLogger(NeighborsIndex.class);

    public NeighborsIndex() {
        super(DefaultIndexes.NEIGHBORS.getIndexKey(), Collections.singleton(EventType.ARCCHANGE), Collections.singleton(FeatureType.All));
        logger.trace("Neigbors index created");
        this.neighbors = new HashMap<>();
    }

    @Override
    public void parse(AbstractUGlyph uGlyph) {
        if(uGlyph instanceof ArcFeature) {
            for(UArc arc: ((ArcFeature) uGlyph).getIncomingArcs()) {
                this.setNeighborsFromArc(arc);
            }
            for(UArc arc: ((ArcFeature) uGlyph).getOutgoingArcs()) {
                this.setNeighborsFromArc(arc);
            }

        }
    }

    @Override
    public void arcAddedAsSource(ArcChangeEvent e) {
        this.setNeighborsFromArc(e.getArc());
    }

    @Override
    public void arcAddedAsTarget(ArcChangeEvent e) {
        this.setNeighborsFromArc(e.getArc());
    }

    @Override
    public void arcRemovedAsSource(ArcChangeEvent e) {

    }

    @Override
    public void arcRemovedAsTarget(ArcChangeEvent e) {

    }

    @Override
    public void arcCreated(UArc uArc) {

    }

    @Override
    public void arcDeleted(UArc uArc) {

    }

    public void print() {

        for(AbstractUGlyph uGlyph: this.neighbors.keySet()) {
            System.out.print(Utilities.glyphString(uGlyph)+" -> [");
            for(AbstractUGlyph neighbor: this.neighbors.get(uGlyph)) {
                System.out.print(Utilities.glyphString(neighbor)+" ");
            }
            System.out.println("]");

        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent propertyChangeEvent) {

    }

    @Override
    public void compositeChildAdded(CompositeChangeEvent e) {

    }

    @Override
    public void compositeChildRemoved(CompositeChangeEvent e) {

    }

    private void setNeighborsFromArc(UArc arc) {
        AbstractUGlyph source = arc.getSource();
        AbstractUGlyph target = arc.getTarget();

        if (!neighbors.containsKey(source)) {
            neighbors.put(source, new HashSet<>());
        }
        neighbors.get(source).add(target);

        if (!neighbors.containsKey(target)) {
            neighbors.put(target, new HashSet<>());
        }
        neighbors.get(target).add(source);
    }
}
