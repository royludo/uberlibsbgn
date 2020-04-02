package org.sbgn.uberlibsbgn;

import org.sbgn.uberlibsbgn.features.*;

import java.awt.geom.Rectangle2D;
import java.beans.PropertyChangeListener;

public class SimpleChemical extends EPN<SimpleChemical> implements MultimerFeature, LabelFeature {

    private MultimerFeature multimerFeature;
    private LabelFeature labelFeature;

    protected SimpleChemical(CompositeFeature parent) {
        super("simple chemical", parent);
        this.multimerFeature = new MultimerFeatureImpl(this);
        this.labelFeature = new LabelFeatureImpl(this, EventType.LABEL);
    }

    @Override
    public SimpleChemical setMultimer(boolean isMultimer) {
        return(SimpleChemical) this.multimerFeature.setMultimer(isMultimer);
    }

    @Override
    public boolean isMultimer() {
        return this.multimerFeature.isMultimer();
    }

    @Override
    public SimpleChemical setLabel(String label) {
        return (SimpleChemical) this.labelFeature.setLabel(label);
    }

    @Override
    public String getLabel() {
        return this.labelFeature.getLabel();
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
    protected SimpleChemical self() {
        return this;
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        super.addPropertyChangeListener(listener);
        labelFeature.addPropertyChangeListener(listener);
        multimerFeature.addPropertyChangeListener(listener);
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        super.removePropertyChangeListener(listener);
        labelFeature.removePropertyChangeListener(listener);
        multimerFeature.removePropertyChangeListener(listener);
    }
}
