package org.sbgn.uberlibsbgn.traversing;

import org.sbgn.uberlibsbgn.AbstractUGlyph;
import org.sbgn.uberlibsbgn.UMap;
import org.sbgn.uberlibsbgn.glyphfeatures.CompositeFeature;
import org.sbgn.uberlibsbgn.glyphfeatures.IHierarchicalVisitor;
import org.sbgn.uberlibsbgn.glyphfeatures.IVisitor;
import org.sbgn.uberlibsbgn.glyphfeatures.MapRootFeature;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class DepthFirstAll implements IVisitor, Iterable<AbstractUGlyph> {

    private CompositeFeature mapRoot;
    private Collection<AbstractUGlyph> result;

    public DepthFirstAll(CompositeFeature mapRoot) {
        this.mapRoot = mapRoot;
        this.result = new ArrayList<>();
        this.mapRoot.accept(this);

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
