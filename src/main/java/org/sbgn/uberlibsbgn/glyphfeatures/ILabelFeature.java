package org.sbgn.uberlibsbgn.glyphfeatures;

import org.sbgn.uberlibsbgn.AbstractUGlyph;

public interface ILabelFeature extends HasPropertyChangeListener {

    AbstractUGlyph setLabel(String label);

    String getLabel();

    boolean hasLabel();
}
