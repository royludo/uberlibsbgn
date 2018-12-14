package org.sbgn.uberlibsbgn;

import org.sbgn.uberlibsbgn.glyphfeatures.CompositeChangeListener;
import org.sbgn.uberlibsbgn.glyphfeatures.CompositeFeature;
import org.sbgn.uberlibsbgn.glyphfeatures.CompositeFeatureImpl;

import java.util.List;
import java.util.function.Predicate;

public class Compartment extends AbstractUGlyph implements CompositeFeature {

    private CompositeFeature compositeFeature;

    public Compartment() {
        super("compartment");
        this.compositeFeature = new CompositeFeatureImpl(this, abstractUGlyph -> true);
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
}
