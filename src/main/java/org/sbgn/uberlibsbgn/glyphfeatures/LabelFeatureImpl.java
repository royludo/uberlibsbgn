package org.sbgn.uberlibsbgn.glyphfeatures;

import org.sbgn.bindings.Bbox;
import org.sbgn.bindings.Label;
import org.sbgn.uberlibsbgn.AbstractUGlyph;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.awt.geom.Rectangle2D;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Optional;

public class LabelFeatureImpl implements LabelFeature {

    private AbstractUGlyph uGlyph;

    private BboxFeature bboxFeature = null; // optional field here

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
    @Nonnull
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

    @Override
    public boolean labelHasBbox() {
        return this.bboxFeature != null;
    }

    @Nonnull
    @Override
    public Rectangle2D getBbox() {
        if(this.bboxFeature != null) {
            return this.bboxFeature.getBbox();
        }
        else {
            return null;
        }
    }

    @Override
    public AbstractUGlyph setBbox(Rectangle2D rect) {
        // event will be thrown by the bboxFeature
        this.bboxFeature = new BboxFeatureImpl(this.uGlyph, "labelbbox") {
            @Override
            public Bbox getSbgnBbox() {
                return uGlyph.getGlyph().getLabel().getBbox();
            }

            @Override
            public void setSbgnBbox(Bbox sbgnBbox) {
                uGlyph.getGlyph().getLabel().setBbox(sbgnBbox);
            }
        };

        this.bboxFeature.setBbox(rect);
        return this.uGlyph;
    }
}
