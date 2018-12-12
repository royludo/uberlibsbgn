package org.sbgn.uberlibsbgn;

import org.sbgn.uberlibsbgn.glyphfeatures.*;

import java.beans.PropertyChangeListener;
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
    public List<AbstractUGlyph> getChildren() {
        return compositeFeature.getChildren();
    }

    @Override
    public AbstractUGlyph addChild(AbstractUGlyph child) {
        return compositeFeature.addChild(child);
    }

    @Override
    public AbstractUGlyph removeChild(AbstractUGlyph child) {
        return compositeFeature.removeChild(child);
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

    @Override
    public void addCompositeChangeListener(CompositeChangeListener listener) {
        compositeFeature.addCompositeChangeListener(listener);
    }

    @Override
    public void removeCompositeChangeListener(CompositeChangeListener listener) {
        compositeFeature.removeCompositeChangeListener(listener);
    }
}
