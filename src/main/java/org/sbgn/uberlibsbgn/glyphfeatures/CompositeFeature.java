package org.sbgn.uberlibsbgn.glyphfeatures;

import org.sbgn.uberlibsbgn.AbstractUGlyph;
import org.sbgn.uberlibsbgn.EPN;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
    List<EPN> getChildren();

    /**
     * If composite feature is associated with a glyph, return it. No glyph if composite is map root.
     * @return
     */
    Optional<AbstractUGlyph> getGlyph();

    EPN addChild(EPN child);

    EPN removeChild(EPN child);

    Predicate<EPN> getIncludePermission();

    default boolean hasChildren(){
        return !this.getChildren().isEmpty();
    }

    /**
     * Can the child be included in this glyph
     * @param child
     * @return
     */
    default boolean canInclude(EPN child) { return this.getIncludePermission().test(child);}

    boolean accept(IHierarchicalVisitor v);
    void accept(IVisitor simpleVisitor);
}
