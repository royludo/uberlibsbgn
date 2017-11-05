package org.sbgn.uberlibsbgn;

import org.sbgn.uberlibsbgn.glyphfeatures.CompositeFeature;
import org.sbgn.uberlibsbgn.glyphfeatures.ICompositeFeature;

import java.util.List;

public class Compartment extends AbstractUGlyph<Compartment> implements ICompositeFeature {

    private CompositeFeature<Compartment> compositeFeature;

    public Compartment() {
        super("compartment");
        this.compositeFeature = new CompositeFeature<>(this);
    }

    @Override
    public List<AbstractUGlyph> getAllChildren() {
        return compositeFeature.getAllChildren();
    }

    @Override
    public List<AbstractUGlyph> getFirstLevelChildren() {
        return compositeFeature.getFirstLevelChildren();
    }
}
