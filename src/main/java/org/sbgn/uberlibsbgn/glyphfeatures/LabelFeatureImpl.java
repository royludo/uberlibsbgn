package org.sbgn.uberlibsbgn.glyphfeatures;

import org.sbgn.bindings.Label;
import org.sbgn.uberlibsbgn.AbstractUGlyph;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class LabelFeatureImpl implements LabelFeature {

    private AbstractUGlyph uGlyph;

    private final PropertyChangeSupport pcs;

    public LabelFeatureImpl(AbstractUGlyph uGlyph) {
        this.uGlyph = uGlyph;
        this.pcs = new PropertyChangeSupport(uGlyph);
    }

    @Override
    public AbstractUGlyph setLabel(String newLabel) {
        String oldLabel = this.getLabel();
        Label sbgnLabel = new Label();
        sbgnLabel.setText(newLabel);
        this.uGlyph.getGlyph().setLabel(sbgnLabel);
        this.pcs.firePropertyChange("label", oldLabel, newLabel);
        return this.uGlyph;
    }

    @Override
    public String getLabel() {
        if(this.hasLabel()) {
            return this.uGlyph.getGlyph().getLabel().getText();
        }
        else {
            return "";
        }
    }

    @Override
    public boolean hasLabel() {
        if(this.uGlyph.getGlyph().getLabel() == null) {
            return false;
        }
        String label = this.uGlyph.getGlyph().getLabel().getText();
        return label != null && !label.isEmpty();
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
