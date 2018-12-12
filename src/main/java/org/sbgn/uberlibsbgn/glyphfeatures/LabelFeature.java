package org.sbgn.uberlibsbgn.glyphfeatures;

import org.sbgn.bindings.Label;
import org.sbgn.uberlibsbgn.AbstractUGlyph;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class LabelFeature<T extends AbstractUGlyph & ILabelFeature> implements ILabelFeature {

    private AbstractUGlyph<T> uGlyph;

    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    public LabelFeature(AbstractUGlyph<T> uGlyph) {
        this.uGlyph = uGlyph;
    }

    @Override
    public AbstractUGlyph<T> setLabel(String newLabel) {
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
