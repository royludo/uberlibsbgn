package org.sbgn.uberlibsbgn.glyphfeatures;

import org.sbgn.uberlibsbgn.AbstractUGlyph;
import sun.reflect.generics.tree.Tree;

import java.util.List;
import java.util.function.Predicate;

public interface CompositeFeature extends HasCompositeChangeListener {

    /**
     * Unordered list of all descendants
     * @return
     */
    //List<AbstractUGlyph> getAllChildren(); // too broad, no the job of a single node

    /**
     * Unordered list of first level descendants
     * @return
     */
    List<AbstractUGlyph> getChildren();

    AbstractUGlyph addChild(AbstractUGlyph child) ;

    AbstractUGlyph removeChild(AbstractUGlyph child);

    Predicate<AbstractUGlyph> getIncludePermission();

    /**
     * Can the child be included in this glyph
     * @param child
     * @return
     */
    default boolean canBeIncluded(AbstractUGlyph child) { return this.getIncludePermission().test(child);}
}
