package org.sbgn.uberlibsbgn.indexing;

import com.google.common.collect.*;
import org.sbgn.uberlibsbgn.AbstractUGlyph;
import org.sbgn.uberlibsbgn.glyphfeatures.CompositeChangeEvent;
import org.sbgn.uberlibsbgn.glyphfeatures.LabelFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.PropertyChangeEvent;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

public class LabelIndex implements Index {

    private SetMultimap<String, AbstractUGlyph> labelMap;

    final Logger logger = LoggerFactory.getLogger(LabelIndex.class);

    public LabelIndex() {
        logger.trace("Create LabelIndex");
        this.labelMap = HashMultimap.create();
    }


    public Set<AbstractUGlyph> getGlyphs(String label) {
        return labelMap.get(label);
    }

    /**
     * O(n)
     * @param regex
     * @return
     */
    public Set<AbstractUGlyph> getGlyphsWithRegexp(String regex) {
        Pattern p = Pattern.compile(regex);
        Set<AbstractUGlyph> result = new HashSet<>();

        for(String s: labelMap.keySet()) {
            if(p.matcher(s).matches()) {
                result.addAll(labelMap.get(s));
            }
        }

        return result;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if(evt.getPropertyName().equals("label")) {
            logger.trace("Label propertyChange");
            String oldLabel = (String) evt.getOldValue();
            String newLabel = (String) evt.getNewValue();
            AbstractUGlyph sourceGlyph = (AbstractUGlyph) evt.getSource();

            labelMap.remove(oldLabel, sourceGlyph);
            labelMap.put(newLabel, sourceGlyph);

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
            if(addedGlyph instanceof LabelFeature) {
                labelMap.put(((LabelFeature) addedGlyph).getLabel(), addedGlyph);
            }
        }
    }

    @Override
    public void compositeChildRemoved(CompositeChangeEvent e) {
        for(AbstractUGlyph removedGlyph: e.getChildren()) {
            if(removedGlyph instanceof LabelFeature) {
                labelMap.remove(((LabelFeature) removedGlyph).getLabel(), removedGlyph);
            }
        }
    }

    @Override
    public void parse(AbstractUGlyph uGlyph) {
        if(uGlyph instanceof LabelFeature) {
            labelMap.put(((LabelFeature) uGlyph).getLabel(), uGlyph);
        }
    }
}
