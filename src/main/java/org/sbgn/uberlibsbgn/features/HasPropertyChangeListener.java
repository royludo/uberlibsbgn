package org.sbgn.uberlibsbgn.features;

import java.beans.PropertyChangeListener;

public interface HasPropertyChangeListener {

    void addPropertyChangeListener(PropertyChangeListener listener);

    void removePropertyChangeListener(PropertyChangeListener listener);
}
