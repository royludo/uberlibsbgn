package org.sbgn.uberlibsbgn;

import org.sbgn.uberlibsbgn.glyphfeatures.*;

import java.util.List;
import java.util.function.Predicate;

public class Complex extends AbstractUGlyph<Complex> implements ICompositeFeature, IMultimerFeature, ILabelFeature,
        ComplexIncludible {

    private CompositeFeature<Complex> compositeFeature;
    private MultimerFeature<Complex> multimerFeature;
    private LabelFeature<Complex> labelFeature;

    public Complex() {
        super("complex");

        // define which kind of glyph are allowed to be included
        Predicate<AbstractUGlyph> p = abstractUGlyph ->
                abstractUGlyph instanceof ComplexIncludible;

        this.compositeFeature = new CompositeFeature<>(this, p);
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
    public boolean addChild(AbstractUGlyph child) {
        return compositeFeature.addChild(child);
    }

    @Override
    public Predicate<AbstractUGlyph> getIncludePermission() {
        return compositeFeature.getIncludePermission();
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
