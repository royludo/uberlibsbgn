package org.sbgn.uberlibsbgn;

import org.sbgn.uberlibsbgn.glyphfeatures.LabelFeature;

public class Utilities {

    public static String glyphString(AbstractUGlyph abstractUGlyph) {
        String idorlabel;
        if(abstractUGlyph.hasLabelFeature()) {
            idorlabel = ((LabelFeature) abstractUGlyph).getLabel();
        }
        else {
            idorlabel = abstractUGlyph.getId();
        }
        return idorlabel;
    }
}
