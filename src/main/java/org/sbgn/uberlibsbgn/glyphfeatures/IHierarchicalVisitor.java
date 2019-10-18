package org.sbgn.uberlibsbgn.glyphfeatures;

import org.sbgn.uberlibsbgn.AbstractUGlyph;
import org.sbgn.uberlibsbgn.glyphfeatures.CompositeFeature;

public interface IHierarchicalVisitor {

    boolean visitEnter(AbstractUGlyph node);

    boolean visitExit(AbstractUGlyph node);

    boolean visitLeaf(AbstractUGlyph node);
}
