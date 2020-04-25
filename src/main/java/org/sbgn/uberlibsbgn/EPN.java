package org.sbgn.uberlibsbgn;

import org.sbgn.uberlibsbgn.features.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * All glyphs that can be part of the tree hierarchy of nodes
 * = that have a parent, so everything except units of info and such.
 */
public abstract class EPN extends AbstractUGlyph {

    private CompositeFeature parent;


    final Logger logger = LoggerFactory.getLogger(EPN.class);

    /**
     * Minimal possible constructor for a glyph.
     *
     * @param clazz
     */
    public EPN(String clazz, CompositeFeature parent, UMap parentMap, String id) {
        super(clazz, parentMap, id);
        this.parent = parent;
    }

    public CompositeFeature getParent() {
        return this.parent;
    }

    public boolean isAtRoot() {
        return this.getParent().getGlyph().isPresent();
    }

    public int getInclusionLevel() {
        if(this.getParent().getGlyph().isPresent()) {
            EPN currentElement = (EPN) this.getParent().getGlyph().get();
            int level = 1;
            while(!currentElement.isAtRoot()) {
                currentElement = (EPN) this.getParent().getGlyph().get();
                level++;
            }
            return level;
        }
        else {
            return 0;
        }
    }

    public CompositeFeature setParent(CompositeFeature compositeParent) {
        if(compositeParent.getGlyph().isPresent()) {
            AbstractUGlyph parentGlyph = compositeParent.getGlyph().get();
            logger.trace("Set parent to : {}", parentGlyph.getId());
        }
        else {
            logger.trace("Set parent to : map root");
        }


        CompositeFeature previousParent = this.getParent();
        // edge case: we set to the same parent as previous one
        if(previousParent.equals(compositeParent)) {
            logger.trace("New parent is the same as previous");
            return previousParent;
        }

        // if parent is set from the addchild method of the new parent, then avoid propagation
        if(compositeParent.getChildren().contains(this)) {
            logger.trace("Parent set from addchild()");
            this.parent = compositeParent;
            return compositeParent;
        }

        if(compositeParent.canInclude(this)) {
            // manage removal from previous parent first before adding to new parent
            previousParent.removeChild(this);
            compositeParent.add(this);
            this.parent = compositeParent;
            return compositeParent;
        }
        else {
            throw new IllegalArgumentException("Glyph "+this.getId()+" can't be included in parent "+compositeParent);
        }
    }

    public List<AbstractUGlyph> getSiblings() {
        List<AbstractUGlyph> result = new ArrayList<>();

        CompositeFeature parent = this.getParent();
        for(AbstractUGlyph uGlyph: parent.getChildren()) {
            if(!uGlyph.equals(this)) {
                result.add(uGlyph);
            }
        }

        return result;
    }

}
