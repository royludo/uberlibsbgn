package org.sbgn.uberlibsbgn.indexing;

import org.sbgn.uberlibsbgn.AbstractUGlyph;
import org.sbgn.uberlibsbgn.UGlyphClass;
import org.sbgn.uberlibsbgn.glyphfeatures.CompositeChangeEvent;
import org.sbgn.uberlibsbgn.glyphfeatures.EventType;

import java.beans.PropertyChangeEvent;
import java.util.*;
import java.util.function.Predicate;

public class GlyphClassIndex extends AbstractGlyphIndex {

    private Map<UGlyphClass, Set<AbstractUGlyph>> glyphClassMap;

    public GlyphClassIndex() {
        super(DefaultIndexes.CLASS.getIndexKey(), Collections.singleton(EventType.NONE));

        this.glyphClassMap = new HashMap<>();
        // init the map right away, so there is no need to check for existence of keys later
        for(UGlyphClass clazz: UGlyphClass.values()) {
            this.glyphClassMap.put(clazz, new HashSet<>());
        }
    }

    @Override
    public void parse(AbstractUGlyph uGlyph) {
        this.glyphClassMap.get(uGlyph.getUGlyphClass()).add(uGlyph);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // NOP, we're not subscribed to any property change event
    }

    @Override
    public void compositeChildAdded(CompositeChangeEvent e) {
        for(AbstractUGlyph addedGlyph: e.getChildren()) {
            glyphClassMap.get(addedGlyph.getUGlyphClass()).add(addedGlyph);
        }
    }

    @Override
    public void compositeChildRemoved(CompositeChangeEvent e) {
        for(AbstractUGlyph removedGlyph: e.getChildren()) {
            glyphClassMap.get(removedGlyph.getUGlyphClass()).remove(removedGlyph);
        }
    }
}
