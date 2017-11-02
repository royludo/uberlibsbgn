package org.sbgn.uberlibsbgn;

import java.util.List;

public class Complex extends AbstractMultimerable<Complex> implements ICompositeFeature {

    private CompositeFeature<Complex> compositeFeature;

    public Complex() {
        super("complex");
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
