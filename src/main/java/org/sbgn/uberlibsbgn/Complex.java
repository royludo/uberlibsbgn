package org.sbgn.uberlibsbgn;

import org.sbgn.uberlibsbgn.glyphfeatures.*;

import java.util.List;

public class Complex extends AbstractUGlyph<Complex> implements ICompositeFeature, IMultimerFeature, ILabelFeature {

    private CompositeFeature<Complex> compositeFeature;
    private MultimerFeature<Complex> multimerFeature;
    private LabelFeature<Complex> labelFeature;

    public Complex() {
        super("complex");
        this.compositeFeature = new CompositeFeature<>(this);
        this.multimerFeature = new MultimerFeature<>(this);
        this.labelFeature = new LabelFeature<>(this);
    }

    @Override
    public List<AbstractUGlyph> getAllChildren() {
        return compositeFeature.getAllChildren();
    }

    @Override
    public List<AbstractUGlyph> getFirstLevelChildren() {
        return compositeFeature.getFirstLevelChildren();
    }

    @Override
    public Complex multimer() {
        return (Complex) this.multimerFeature.multimer();
    }

    @Override
    public Complex multimer(boolean isMultimer) {
        return(Complex) this.multimerFeature.multimer(isMultimer);
    }

    @Override
    public boolean isMultimer() {
        return this.multimerFeature.isMultimer();
    }

    @Override
    public Complex setLabel(String label) {
        return (Complex) labelFeature.setLabel(label);
    }

    @Override
    public String getLabel() {
        return labelFeature.getLabel();
    }
}
