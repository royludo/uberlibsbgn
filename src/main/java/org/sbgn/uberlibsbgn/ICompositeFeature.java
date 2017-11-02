package org.sbgn.uberlibsbgn;

import sun.reflect.generics.tree.Tree;

import java.util.List;

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

    default void addChild(AbstractUGlyph child) {
        this.getFirstLevelChildren().add(child);
    }
}
