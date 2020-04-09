package org.sbgn.uberlibsbgn;

import org.sbgn.uberlibsbgn.features.CompositeChangeListener;
import org.sbgn.uberlibsbgn.features.CompositeFeature;
import org.sbgn.uberlibsbgn.features.CompositeFeatureImpl;
import org.sbgn.uberlibsbgn.features.IHierarchicalVisitor;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class Compartment extends EPN<Compartment> implements CompositeFeature {

    private CompositeFeature compositeFeature;

    protected Compartment(CompositeFeature parent) {
        super("compartment", parent);
        this.compositeFeature = new CompositeFeatureImpl(this, epn -> true);
    }

    @Override
    public List<EPN> getChildren() {
        return compositeFeature.getChildren();
    }

    @Override
    public Optional<AbstractUGlyph> getGlyph() {
        return compositeFeature.getGlyph();
    }

    @Override
    public EPN add(EPN child) {
        return compositeFeature.add(child);
    }

    @Override
    public EPN removeChild(EPN child) {
        return compositeFeature.removeChild(child);
    }

    @Override
    public Predicate<EPN> getIncludePermission() {
        return this.compositeFeature.getIncludePermission();
    }

    @Override
    public void addCompositeChangeListener(CompositeChangeListener listener) {
        this.compositeFeature.addCompositeChangeListener(listener);
    }

    @Override
    public void removeCompositeChangeListener(CompositeChangeListener listener) {
        this.compositeFeature.removeCompositeChangeListener(listener);
    }

    @Override
    public List<CompositeChangeListener> getCompositeChangeListeners() {
        return compositeFeature.getCompositeChangeListeners();
    }

    @Override
    public boolean accept(IHierarchicalVisitor v) {
        return this.compositeFeature.accept(v);
    }
}
