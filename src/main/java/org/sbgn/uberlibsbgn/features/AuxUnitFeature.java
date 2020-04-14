package org.sbgn.uberlibsbgn.features;

import org.sbgn.uberlibsbgn.AbstractUGlyph;

public interface AuxUnitFeature extends HasPropertyChangeListener {

    String getKey();
    void setKey(String key);
    String getValue();
    void setValue(String value);
    String getSeparator();

    AbstractUGlyph getParent();
}
