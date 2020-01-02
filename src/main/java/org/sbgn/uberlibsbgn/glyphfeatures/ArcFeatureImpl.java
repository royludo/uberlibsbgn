package org.sbgn.uberlibsbgn.glyphfeatures;

import org.sbgn.uberlibsbgn.AbstractUGlyph;
import org.sbgn.uberlibsbgn.UArc;

import java.util.ArrayList;
import java.util.List;

public class ArcFeatureImpl implements ArcFeature {

    private AbstractUGlyph uGlyph;
    private List<UArc> incomingArcs, outgoingArcs;
    private List<ArcChangeListener> arcChangeListeners;

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

    @Override
    public boolean addIncomingArc(UArc arc) {
        this.incomingArcs.add(arc);

        ArcChangeEvent ace = new ArcChangeEvent(this.uGlyph, arc);
        for(ArcChangeListener listener: this.arcChangeListeners) {
            listener.arcAddedAsTarget(ace);
        }

        return true;
    }

    @Override
    public boolean addOutgoingArc(UArc arc) {
        this.outgoingArcs.add(arc);

        ArcChangeEvent ace = new ArcChangeEvent(this.uGlyph, arc);
        for(ArcChangeListener listener: this.arcChangeListeners) {
            listener.arcAddedAsSource(ace);
        }

        return true;
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
