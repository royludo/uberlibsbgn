package org.sbgn.uberlibsbgn.glyphfeatures;

import org.sbgn.uberlibsbgn.AbstractUGlyph;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class MultimerFeature<T extends AbstractUGlyph & IMultimerFeature> implements IMultimerFeature {

    private AbstractUGlyph uGlyph;
    private boolean isMultimer;

    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    public MultimerFeature(AbstractUGlyph<T> uGlyph) {
        this.uGlyph = uGlyph;
        this.isMultimer = false;
    }

    private void setMultimer(boolean b) {
        boolean oldIsMultimer = this.isMultimer;
        this.isMultimer = b;

        this.pcs.firePropertyChange("multimer", oldIsMultimer, b);
    }

    @Override
    public AbstractUGlyph multimer() {

        if(this.isMultimer()) { // do nothing
            return this.uGlyph;
        }

        this.uGlyph.getGlyph().setClazz(this.uGlyph.getGlyph().getClazz()+" multimer");
        this.setMultimer(true);
        return this.uGlyph;
    }

    @Override
    public AbstractUGlyph multimer(boolean isMultimer) {
        if(this.isMultimer()) {
            if(!isMultimer) { // change to not multimer
                this.setMultimer(false);
                this.uGlyph.getGlyph().setClazz(this.uGlyph.getGlyph().getClazz().replace(" multimer", ""));
                return this.uGlyph;
            }
        }
        else {
            if(isMultimer) {  // change to multimer
                return this.multimer();
            }
        }
        return this.uGlyph;
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
