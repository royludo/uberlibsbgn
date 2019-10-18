package org.sbgn.uberlibsbgn.indexing;

import com.google.common.collect.HashMultimap;
import org.sbgn.uberlibsbgn.AbstractUGlyph;
import org.sbgn.uberlibsbgn.glyphfeatures.CompositeChangeEvent;
import org.sbgn.uberlibsbgn.glyphfeatures.LabelFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.PropertyChangeEvent;
import java.util.HashMap;

public class LabelWithOIndex implements Index {

    final Logger logger = LoggerFactory.getLogger(LabelWithOIndex.class);

    private HashMap<String, AbstractUGlyph> labelWithOMap;

    public LabelWithOIndex() {
        logger.trace("Create LabelWithOIndex");
        labelWithOMap = new HashMap<>();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if(evt.getPropertyName().equals("label")) {
            logger.trace("Label propertyChange");
            String oldLabel = (String) evt.getOldValue();
            String newLabel = (String) evt.getNewValue();
            AbstractUGlyph sourceGlyph = (AbstractUGlyph) evt.getSource();

            if(newLabel.contains("o")) {
                labelWithOMap.put(sourceGlyph.getId(), sourceGlyph);
            }

            if(oldLabel.contains("o")) {
                labelWithOMap.remove(sourceGlyph.getId(), sourceGlyph);
            }

            /*for(AbstractUGlyph glyph: labelMap.get(oldLabel)) {
                if(glyph.getId().equals(sourceGlyph.getId())) {

                    ((LabelFeature) sourceGlyph).setLabel(newLabel); // noope




                    break;
                }
            }*/
        }
    }

    @Override
    public void compositeChildAdded(CompositeChangeEvent e) {
        for(AbstractUGlyph addedGlyph: e.getChildren()) {
            if(addedGlyph instanceof LabelFeature && ((LabelFeature) addedGlyph).getLabel().contains("o")) {
                labelWithOMap.put(addedGlyph.getId(), addedGlyph);
            }
        }
    }

    @Override
    public void compositeChildRemoved(CompositeChangeEvent e) {
        for(AbstractUGlyph removedGlyph: e.getChildren()) {
            if(removedGlyph instanceof LabelFeature && ((LabelFeature) removedGlyph).getLabel().contains("o")) {
                labelWithOMap.remove(removedGlyph.getId(), removedGlyph);
            }
        }
    }
}
