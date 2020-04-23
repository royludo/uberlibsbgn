package org.sbgn.uberlibsbgn;

import org.apache.xpath.operations.Bool;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeSupport;
import java.util.Properties;
import java.util.function.BooleanSupplier;
import java.util.function.Predicate;

public class ConditionalPropertyChangeSupport extends PropertyChangeSupport {

    private BooleanSupplier booleanSupplier;

    /**
     * Constructs a {@code PropertyChangeSupport} object.
     *
     * @param sourceBean The bean to be given as the source for any events.
     */
    public ConditionalPropertyChangeSupport(Object sourceBean, BooleanSupplier booleanSupplier) {
        super(sourceBean);
        this.booleanSupplier = booleanSupplier;
    }

    @Override
    public void firePropertyChange(PropertyChangeEvent event) {
        if(booleanSupplier.getAsBoolean()) {
            super.firePropertyChange(event);
        }
    }
}
