package org.sbgn.uberlibsbgn.traversing;

import org.sbgn.uberlibsbgn.AbstractUGlyph;
import org.sbgn.uberlibsbgn.glyphfeatures.CompositeFeature;
import org.sbgn.uberlibsbgn.glyphfeatures.IVisitor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class DepthFirstAll implements IVisitor, Iterable<AbstractUGlyph> {

    private CompositeFeature composite;
    private Collection<AbstractUGlyph> result;

    public DepthFirstAll(CompositeFeature composite) {
        this.composite = composite;
        this.result = new ArrayList<>();
        this.composite.accept(this);

    }

    @Override
    public void visit(AbstractUGlyph uGlyph) {
        this.result.add(uGlyph);
    }

    @Override
    public Iterator<AbstractUGlyph> iterator() {
        return this.result.iterator();
    }
}
