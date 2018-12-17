package org.sbgn.uberlibsbgn;

import org.sbgn.uberlibsbgn.glyphfeatures.LabelFeature;
import org.sbgn.uberlibsbgn.glyphfeatures.MultimerFeature;
import org.sbgn.uberlibsbgn.glyphfeatures.LabelFeatureImpl;
import org.sbgn.uberlibsbgn.glyphfeatures.MultimerFeatureImpl;

import java.awt.geom.Rectangle2D;
import java.beans.PropertyChangeListener;

public class SimpleChemical extends AbstractUGlyph<SimpleChemical> implements MultimerFeature, LabelFeature {

    private MultimerFeature multimerFeature;
    private LabelFeature labelFeature;

    protected SimpleChemical() {
        super("simple chemical");
        this.multimerFeature = new MultimerFeatureImpl(this);
        this.labelFeature = new LabelFeatureImpl(this);
    }

    @Override
    public SimpleChemical multimer() {
        return (SimpleChemical) this.multimerFeature.multimer();
    }

    @Override
    public SimpleChemical multimer(boolean isMultimer) {
        return(SimpleChemical) this.multimerFeature.multimer(isMultimer);
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
