package org.sbgn.uberlibsbgn.glyphfeatures;

import org.sbgn.uberlibsbgn.AbstractUGlyph;

public interface IMultimerFeature extends HasPropertyChangeListener {

    AbstractUGlyph multimer();

    AbstractUGlyph multimer(boolean isMultimer);

    boolean isMultimer();
}
