package org.sbgn.uberlibsbgn;

import org.sbgn.uberlibsbgn.glyphfeatures.CompositeFeature;
import org.sbgn.uberlibsbgn.glyphfeatures.ICompositeFeature;

import java.util.List;
import java.util.function.Predicate;

public class Compartment extends AbstractUGlyph<Compartment> implements ICompositeFeature {

    private CompositeFeature<Compartment> compositeFeature;

    public Compartment() {
        super("compartment");
        this.compositeFeature = new CompositeFeature<>(this, abstractUGlyph -> true);
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
        return this.compositeFeature.getIncludePermission();
    }
}
