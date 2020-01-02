package org.sbgn.uberlibsbgn;

import org.sbgn.ArcClazz;

public enum UArcClass {

    PRODUCTION,
    CONSUMPTION,
    CATALYSIS,
    MODULATION,
    STIMULATION,
    INHIBITION,
    ASSIGNMENT,
    INTERACTION,
    ABSOLUTE_INHIBITION,
    ABSOLUTE_STIMULATION,
    POSITIVE_INFLUENCE,
    NEGATIVE_INFLUENCE,
    UNKNOWN_INFLUENCE ,
    EQUIVALENCE_ARC,
    NECESSARY_STIMULATION,
    LOGIC_ARC;

    public static UArcClass fromArcClazz(ArcClazz clazz) {
        switch(clazz) {
            case PRODUCTION:
                return PRODUCTION;
            case CONSUMPTION:
                return CONSUMPTION;
            case CATALYSIS:
                return CATALYSIS;
            case MODULATION:
                return MODULATION;
            case STIMULATION:
                return STIMULATION;
            case INHIBITION:
                return INHIBITION;
            case ASSIGNMENT:
                return ASSIGNMENT;
            case INTERACTION:
                return INTERACTION;
            case ABSOLUTE_INHIBITION:
                return ABSOLUTE_INHIBITION;
            case ABSOLUTE_STIMULATION:
                return ABSOLUTE_STIMULATION;
            case POSITIVE_INFLUENCE:
                return POSITIVE_INFLUENCE;
            case NEGATIVE_INFLUENCE:
                return NEGATIVE_INFLUENCE;
            case UNKNOWN_INFLUENCE :
                return UNKNOWN_INFLUENCE;
            case EQUIVALENCE_ARC:
                return EQUIVALENCE_ARC;
            case NECESSARY_STIMULATION:
                return NECESSARY_STIMULATION;
            case LOGIC_ARC:
                return LOGIC_ARC;

        }
        throw new IllegalStateException();
    }
}
