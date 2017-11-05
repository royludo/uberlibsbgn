package org.sbgn.uberlibsbgn.glyphfeatures;

import org.sbgn.uberlibsbgn.AbstractUGlyph;

import java.util.ArrayList;
import java.util.List;

/**
 * Takes care of the nesting structure, maintains appropriate efficient data structure.
 * @param <T>
 */
public class CompositeFeature<T extends AbstractUGlyph & ICompositeFeature> implements ICompositeFeature {

    private List<AbstractUGlyph> directChildren;
    private AbstractUGlyph<T> uGlyph;

    public CompositeFeature(AbstractUGlyph<T> uGlyph) {
        this.uGlyph = uGlyph;
        this.directChildren = new ArrayList<>();
    }

    @Override
    public List<AbstractUGlyph> getAllChildren() {
        List<AbstractUGlyph> result = new ArrayList<>();
        for(AbstractUGlyph g: this.directChildren) {
            if(g instanceof ICompositeFeature) {
                result.addAll(((ICompositeFeature) g).getAllChildren());
            }
            result.add(g);
        }
        return result;
    }

    @Override
    public List<AbstractUGlyph> getFirstLevelChildren() {
        return this.directChildren;
    }
}
