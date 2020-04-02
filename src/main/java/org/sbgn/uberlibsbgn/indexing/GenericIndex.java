package org.sbgn.uberlibsbgn.indexing;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.SetMultimap;
import org.sbgn.uberlibsbgn.AbstractUGlyph;
import org.sbgn.uberlibsbgn.features.CompositeChangeEvent;
import org.sbgn.uberlibsbgn.features.EventType;
import org.sbgn.uberlibsbgn.features.FeatureType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.PropertyChangeEvent;
import java.util.Collections;
import java.util.Set;
import java.util.function.Predicate;

public class GenericIndex extends AbstractGlyphIndex {

    private SetMultimap<String, AbstractUGlyph> indexedGlyphs;
    private Predicate<AbstractUGlyph> predicate;

    final Logger logger = LoggerFactory.getLogger(GenericIndex.class);

    public GenericIndex(String indexKey, Predicate<AbstractUGlyph> predicate, Set<EventType> eventTypes) {
        super(indexKey, eventTypes, Collections.singleton(FeatureType.All));

        logger.trace("Create GenericIndex with key: {}", indexKey);
        this.indexedGlyphs = HashMultimap.create();
        this.predicate = predicate;

    }

    @Override
    public void parse(AbstractUGlyph uGlyph) {
        if(predicate.test(uGlyph)) {
            indexedGlyphs.put(uGlyph.getId(), uGlyph);
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        //if(evt.getPropertyName().equals(EventType.LABEL.getEventKey())) {
            logger.trace("PropertyChange");
            String oldLabel = (String) evt.getOldValue();
            String newLabel = (String) evt.getNewValue();
            AbstractUGlyph sourceGlyph = (AbstractUGlyph) evt.getSource();

            if(predicate.test(sourceGlyph)) {
                indexedGlyphs.put(sourceGlyph.getId(), sourceGlyph);
            }
            else {
                indexedGlyphs.remove(sourceGlyph.getId(), sourceGlyph);
            }
        //}
    }

    @Override
    public void compositeChildAdded(CompositeChangeEvent e) {
        for(AbstractUGlyph addedGlyph: e.getChildren()) {
            if(predicate.test(addedGlyph)) {
                indexedGlyphs.put(addedGlyph.getId(), addedGlyph);
            }
        }
    }

    @Override
    public void compositeChildRemoved(CompositeChangeEvent e) {
        for(AbstractUGlyph removedGlyph: e.getChildren()) {
            if(predicate.test(removedGlyph)) {
                indexedGlyphs.remove(removedGlyph.getId(), removedGlyph);
            }
        }
    }
}
