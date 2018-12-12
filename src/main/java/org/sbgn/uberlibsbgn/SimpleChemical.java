package org.sbgn.uberlibsbgn;

import org.sbgn.uberlibsbgn.glyphfeatures.ILabelFeature;
import org.sbgn.uberlibsbgn.glyphfeatures.IMultimerFeature;
import org.sbgn.uberlibsbgn.glyphfeatures.LabelFeature;
import org.sbgn.uberlibsbgn.glyphfeatures.MultimerFeature;

import java.beans.PropertyChangeListener;

public class SimpleChemical extends AbstractUGlyph<SimpleChemical> implements IMultimerFeature, ILabelFeature {

    private MultimerFeature<SimpleChemical> multimerFeature;
    private LabelFeature<SimpleChemical> labelFeature;

    public SimpleChemical() {
        super("simple chemical");
        this.multimerFeature = new MultimerFeature<>(this);
        this.labelFeature = new LabelFeature<>(this);
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
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        labelFeature.addPropertyChangeListener(listener);
        multimerFeature.addPropertyChangeListener(listener);
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        labelFeature.removePropertyChangeListener(listener);
        multimerFeature.removePropertyChangeListener(listener);
    }
}
