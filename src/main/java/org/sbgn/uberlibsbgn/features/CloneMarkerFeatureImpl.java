package org.sbgn.uberlibsbgn.features;

import org.sbgn.bindings.Glyph;
import org.sbgn.bindings.Label;
import org.sbgn.uberlibsbgn.AbstractUGlyph;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class CloneMarkerFeatureImpl implements CloneMarkerFeature {

    private AbstractUGlyph uGlyph;
    private LabelFeature labelFeature;
    private boolean hasCloneMarker = false;

    private final PropertyChangeSupport pcs;


    public CloneMarkerFeatureImpl(AbstractUGlyph uGlyph) {
        this.uGlyph = uGlyph;
        this.labelFeature = new LabelFeatureImpl(uGlyph, EventType.CLONEMARKERLABEL);
        this.pcs = new PropertyChangeSupport(this);
    }

    @Override
    public void setCloneMarker(boolean isCloneMarker) {
        boolean oldHasClone = this.hasCloneMarker;
        this.hasCloneMarker = isCloneMarker;

        this.pcs.firePropertyChange(EventType.CLONEMARKER.getEventKey(), oldHasClone, isCloneMarker);
    }

    @Override
    public boolean hasCloneMarker() {
        return this.hasCloneMarker;
    }

    @Override
    public LabelFeature getCloneLabel() {
        return this.labelFeature;
    }

    @Override
    public boolean hasCloneLabel() {
        return this.labelFeature.hasLabel();
    }

    @Override
    public void parseLibSBGNCloneMarker(Glyph.Clone cloneElement) {
        if(cloneElement != null) {
            this.setCloneMarker(true);

            if(cloneElement.getLabel() != null) {
                Label cloneLabel = cloneElement.getLabel();
                this.labelFeature.parseLibSbgnLabel(cloneLabel);
            }
        }
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.addPropertyChangeListener(listener);
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.removePropertyChangeListener(listener);
    }
}
