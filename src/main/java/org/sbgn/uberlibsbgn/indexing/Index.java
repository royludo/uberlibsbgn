package org.sbgn.uberlibsbgn.indexing;

import org.sbgn.uberlibsbgn.AbstractUGlyph;
import org.sbgn.uberlibsbgn.glyphfeatures.CompositeChangeListener;

import java.beans.PropertyChangeListener;

public interface Index extends PropertyChangeListener, CompositeChangeListener {

    void parse(AbstractUGlyph uGlyph);
}
