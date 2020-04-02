package org.sbgn.uberlibsbgn.features;

public interface AuxUnitFeature extends HasPropertyChangeListener {

    String getKey();
    void setKey(String key);
    String getValue();
    void setValue(String value);
    String getSeparator();
}
