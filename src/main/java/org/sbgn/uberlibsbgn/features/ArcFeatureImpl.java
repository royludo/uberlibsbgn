package org.sbgn.uberlibsbgn.features;

import org.sbgn.ArcClazz;
import org.sbgn.uberlibsbgn.AbstractUGlyph;
import org.sbgn.uberlibsbgn.UArc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class ArcFeatureImpl implements ArcFeature {

    private AbstractUGlyph uGlyph;
    private List<UArc> incomingArcs, outgoingArcs;
    private List<ArcChangeListener> arcChangeListeners;

    final Logger logger = LoggerFactory.getLogger(ArcFeatureImpl.class);

    public ArcFeatureImpl(AbstractUGlyph uGlyph) {
        this.uGlyph = uGlyph;
        this.incomingArcs = new ArrayList<>();
        this.outgoingArcs = new ArrayList<>();
        this.arcChangeListeners = new ArrayList<>();
    }



    @Override
    public List<UArc> getArcs() {
        List<UArc> result = new ArrayList<>();
        result.addAll(this.incomingArcs);
        result.addAll(this.outgoingArcs);
        return result;
    }

    @Override
    public List<UArc> getIncomingArcs() {
        return this.incomingArcs;
    }

    @Override
    public List<UArc> getOutgoingArcs() {
        return this.outgoingArcs;
    }

    // should not be accessible for users
    @Override
    public void addIncomingArc(UArc arc) {
        logger.trace("add incoming arc {}", arc.getId());
        this.incomingArcs.add(arc);
        ArcChangeEvent ace = new ArcChangeEvent(this.uGlyph, arc);
        logger.trace("notify arcchangelistener arcAddedAsTarget");
        for(ArcChangeListener listener: this.arcChangeListeners) {
            listener.arcAddedAsTarget(ace);
        }
    }

    // should not be accessible for users
    @Override
    public void addOutgoingArc(UArc arc) {
        logger.trace("add outgoing arc {}", arc.getId());
        this.outgoingArcs.add(arc);
        ArcChangeEvent ace = new ArcChangeEvent(this.uGlyph, arc);
        logger.trace("notify arcchangelistener arcAddedAsSource");
        for(ArcChangeListener listener: this.arcChangeListeners) {
            listener.arcAddedAsSource(ace);
        }
    }

    @Override
    public UArc addArcTo(ArcClazz arcClazz, AbstractUGlyph glyph) {
        // create arc and notify
        UArc arc = new UArc(arcClazz, this.uGlyph, glyph, this.uGlyph.getMap().getIdManager().getNewId());
        logger.trace("add arc to glyph {}, create new arc {}", glyph.getId(), arc.getId());
        logger.trace("notify arcchangelistener arcCreated");
        for(ArcChangeListener listener: this.arcChangeListeners) {
            listener.arcCreated(arc);
        }

        // link arc and source and notify
        this.addOutgoingArc(arc);

        // link arc and target and notify
        ((ArcFeature) glyph).addIncomingArc(arc);

        return arc;
    }

    @Override
    public UArc addArcFrom(ArcClazz arcClazz, AbstractUGlyph glyph) {
        // create arc and notify
        UArc arc = new UArc(arcClazz, glyph, this.uGlyph, this.uGlyph.getMap().getIdManager().getNewId());
        logger.trace("add arc from glyph {}, create new arc {}", glyph.getId(), arc.getId());
        logger.trace("notify arcchangelistener arcCreated");
        for(ArcChangeListener listener: this.arcChangeListeners) {
            listener.arcCreated(arc);
        }

        // link arc and target and notify
        this.addIncomingArc(arc);

        // link arc and source and notify
        ((ArcFeature) glyph).addOutgoingArc(arc);

        return arc;
    }


    @Override
    public boolean removeArc(UArc removedArc) {
        // arc might be in both list at the same time, if self-looping on same glyph
        // TODO improve performance of this maybe ?
        ArcChangeEvent ace = new ArcChangeEvent(this.uGlyph, removedArc);
        if(this.incomingArcs.contains(removedArc)) {
            this.incomingArcs.remove(removedArc);

            for(ArcChangeListener listener: this.arcChangeListeners) {
                listener.arcRemovedAsTarget(ace);
            }
        }
        if(this.outgoingArcs.contains(removedArc)) {
            this.outgoingArcs.remove(removedArc);

            for(ArcChangeListener listener: this.arcChangeListeners) {
                listener.arcRemovedAsSource(ace);
            }
        }
        return true;
    }

    @Override
    public void addArcChangeListener(ArcChangeListener listener) {
        this.arcChangeListeners.add(listener);
    }

    @Override
    public void removeArcChangeListener(ArcChangeListener listener) {
        this.arcChangeListeners.remove(listener);
    }
}
