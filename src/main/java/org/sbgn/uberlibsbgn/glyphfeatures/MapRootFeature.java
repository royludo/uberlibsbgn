package org.sbgn.uberlibsbgn.glyphfeatures;

import org.sbgn.uberlibsbgn.AbstractUGlyph;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

/**
 * A CompositeFeatureImpl that has no include conditions (can include everything) and no associated glyph, as
 * it is used for a UMap.
 */
public class MapRootFeature extends AbstractCompositeFeature {


    final Logger logger = LoggerFactory.getLogger(MapRootFeature.class);

    public MapRootFeature() {
        super(s -> true);
        logger.trace("Create MapRootFeature");

    }

    @Override
    public boolean accept(IHierarchicalVisitor v) {
        if (this.hasChildren()) {
            for (AbstractUGlyph child : this.getChildren()) {
                if (!child.accept(v)) {
                    break;
                }
            }
        }

        return false;
    }

    @Override
    public void accept(IVisitor simpleVisitor) {
        if (this.hasChildren()) {
            for (AbstractUGlyph child : this.getChildren()) {
                child.accept(simpleVisitor);
            }
        }
    }

}
