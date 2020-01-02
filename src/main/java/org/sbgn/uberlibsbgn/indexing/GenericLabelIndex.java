package org.sbgn.uberlibsbgn.indexing;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.SetMultimap;
import org.sbgn.uberlibsbgn.AbstractUGlyph;
import org.sbgn.uberlibsbgn.glyphfeatures.CompositeChangeEvent;
import org.sbgn.uberlibsbgn.glyphfeatures.EventType;
import org.sbgn.uberlibsbgn.glyphfeatures.LabelFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.PropertyChangeEvent;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.function.Predicate;

public class GenericLabelIndex extends AbstractGlyphIndex {


    private SetMultimap<String, AbstractUGlyph> indexedGlyphs;
    private Predicate<String> predicate;

    final Logger logger = LoggerFactory.getLogger(GenericLabelIndex.class);


    public GenericLabelIndex(String indexKey, Predicate<String> predicate) {
        super(indexKey, Collections.singleton(EventType.LABEL));

        logger.trace("Create GenericLabelIndex with key: {}", indexKey);
        this.indexedGlyphs = HashMultimap.create();
        this.predicate = predicate;
    }

    @Override
    public void parse(AbstractUGlyph uGlyph) {
        if(uGlyph.hasLabelFeature() && predicate.test(((LabelFeature) uGlyph).getLabel())) {
            indexedGlyphs.put(uGlyph.getId(), uGlyph);
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if(evt.getPropertyName().equals(EventType.LABEL.getEventKey())) {
            logger.trace("Label propertyChange");
            String oldLabel = (String) evt.getOldValue();
            String newLabel = (String) evt.getNewValue();
            AbstractUGlyph sourceGlyph = (AbstractUGlyph) evt.getSource();

            if(predicate.test(newLabel) && predicate.negate().test(oldLabel)) {
                indexedGlyphs.put(sourceGlyph.getId(), sourceGlyph);
            }

            if(predicate.test(oldLabel) &&predicate.negate().test(newLabel) ) {
                indexedGlyphs.remove(sourceGlyph.getId(), sourceGlyph);
            }
        }
    }

    @Override
    public void compositeChildAdded(CompositeChangeEvent e) {
        for(AbstractUGlyph addedGlyph: e.getChildren()) {
            if(addedGlyph.hasLabelFeature() && predicate.test(((LabelFeature) addedGlyph).getLabel())) {
                indexedGlyphs.put(addedGlyph.getId(), addedGlyph);
            }
        }
    }

    @Override
    public void compositeChildRemoved(CompositeChangeEvent e) {
        for(AbstractUGlyph removedGlyph: e.getChildren()) {
            if(removedGlyph.hasLabelFeature() && predicate.test(((LabelFeature) removedGlyph).getLabel())) {
                indexedGlyphs.remove(removedGlyph.getId(), removedGlyph);
            }
        }
    }

    public Collection<AbstractUGlyph> getGlyphs() {
        return indexedGlyphs.values();
    }
}
