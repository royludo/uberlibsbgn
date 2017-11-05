package org.sbgn.uberlibsbgn.glyphfeatures;

import org.sbgn.uberlibsbgn.AbstractUGlyph;

public interface ILabelFeature {

    AbstractUGlyph setLabel(String label);

    String getLabel();

    default boolean hasLabel() {
        return getLabel() != null && !getLabel().isEmpty();
    }
}
