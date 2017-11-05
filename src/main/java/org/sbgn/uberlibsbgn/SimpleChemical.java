package org.sbgn.uberlibsbgn;

import org.sbgn.uberlibsbgn.glyphfeatures.ILabelFeature;
import org.sbgn.uberlibsbgn.glyphfeatures.IMultimerFeature;
import org.sbgn.uberlibsbgn.glyphfeatures.LabelFeature;
import org.sbgn.uberlibsbgn.glyphfeatures.MultimerFeature;

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
}
