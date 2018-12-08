package org.sbgn.uberlibsbgn.glyphfeatures;

import org.sbgn.uberlibsbgn.AbstractUGlyph;
import sun.reflect.generics.tree.Tree;

import java.util.List;
import java.util.function.Predicate;

public interface ICompositeFeature {

    /**
     * Unordered list of all descendants
     * @return
     */
    List<AbstractUGlyph> getAllChildren();

    /**
     * Unordered list of first level descendants
     * @return
     */
    List<AbstractUGlyph> getFirstLevelChildren();

    boolean addChild(AbstractUGlyph child) ;

    Predicate<AbstractUGlyph> getIncludePermission();

    default boolean canBeIncluded(AbstractUGlyph child) { return this.getIncludePermission().test(child);}
}
