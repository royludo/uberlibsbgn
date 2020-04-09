package org.sbgn.uberlibsbgn.features;

import org.sbgn.uberlibsbgn.AbstractUGlyph;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class MultimerFeatureImpl implements MultimerFeature {

    private AbstractUGlyph uGlyph;
    private boolean isMultimer = false;

    private final PropertyChangeSupport pcs;

    public MultimerFeatureImpl(AbstractUGlyph uGlyph) {
        this.uGlyph = uGlyph;
        this.pcs = new PropertyChangeSupport(uGlyph);
    }

    @Override
    public void setMultimer(boolean isMultimer) {
        boolean oldIsMultimer = this.isMultimer;
        this.isMultimer = isMultimer;

        this.pcs.firePropertyChange(EventType.MULTIMER.getEventKey(), oldIsMultimer, isMultimer);
    }

    @Override
    public boolean isMultimer() {
        return this.isMultimer;
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
