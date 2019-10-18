package org.sbgn.uberlibsbgn;

import org.sbgn.uberlibsbgn.glyphfeatures.LabelFeature;
import org.sbgn.uberlibsbgn.glyphfeatures.LabelFeatureImpl;
import org.sbgn.uberlibsbgn.glyphfeatures.MultimerFeatureImpl;

import java.awt.geom.Rectangle2D;
import java.beans.PropertyChangeListener;

public class UnitOfInfo extends AbstractUGlyph<UnitOfInfo> implements LabelFeature {

    private LabelFeature labelFeature;

    protected UnitOfInfo() {
        super("unit of information");
        this.labelFeature = new LabelFeatureImpl(this);
    }

    @Override
    public AbstractUGlyph setLabel(String label) {
        return labelFeature.setLabel(label);
    }

    @Override
    public String getLabel() {
        return labelFeature.getLabel();
    }

    @Override
    public boolean hasLabel() {
        return labelFeature.hasLabel();
    }

    @Override
    public boolean labelHasBbox() {
        return labelFeature.labelHasBbox();
    }

    @Override
    public Rectangle2D getLabelBbox() {
        return labelFeature.getLabelBbox();
    }

    @Override
    public AbstractUGlyph setLabelBbox(Rectangle2D rect) {
        return labelFeature.setLabelBbox(rect);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        super.addPropertyChangeListener(listener);
        labelFeature.addPropertyChangeListener(listener);
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        super.removePropertyChangeListener(listener);
        labelFeature.removePropertyChangeListener(listener);
    }
}
