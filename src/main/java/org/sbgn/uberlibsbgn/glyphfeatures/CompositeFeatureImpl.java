package org.sbgn.uberlibsbgn.glyphfeatures;

import org.sbgn.uberlibsbgn.AbstractUGlyph;

import java.util.*;
import java.util.function.Predicate;

/**
 * Takes care of the nesting structure, maintains appropriate efficient data structure.
 */
public class CompositeFeatureImpl extends AbstractCompositeFeature {

    private AbstractUGlyph uGlyph;

    public CompositeFeatureImpl(AbstractUGlyph uGlyph, Predicate<AbstractUGlyph> includeCondition) {
        super(includeCondition);
        this.uGlyph = uGlyph;
    }

    @Override
    public boolean accept(IHierarchicalVisitor v) {
        if (v.visitEnter(this.uGlyph)) {
            if (this.hasChildren()) {
                for (AbstractUGlyph child : this.getChildren()) {
                    if (!child.accept(v)) {
                        break;
                    }
                }
            }
        }
        return v.visitExit(this.uGlyph);
    }

    @Override
    public void accept(IVisitor simpleVisitor) {
        simpleVisitor.visit(this.uGlyph);
        if(this.hasChildren()){
            for(AbstractUGlyph child: this.getChildren()) {
                child.accept(simpleVisitor);
            }
        }
    }

}
