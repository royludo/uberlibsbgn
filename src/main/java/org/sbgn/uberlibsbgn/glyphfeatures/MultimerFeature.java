package org.sbgn.uberlibsbgn.glyphfeatures;

import org.sbgn.uberlibsbgn.AbstractUGlyph;

public interface MultimerFeature extends HasPropertyChangeListener {

    AbstractUGlyph setMultimer(boolean isMultimer);

    boolean isMultimer();
}
