package org.sbgn.uberlibsbgn.features;

import org.sbgn.uberlibsbgn.AbstractUGlyph;

public interface IHierarchicalVisitor {

    boolean visitEnter(AbstractUGlyph node);

    boolean visitExit(AbstractUGlyph node);

    boolean visitLeaf(AbstractUGlyph node);
}
