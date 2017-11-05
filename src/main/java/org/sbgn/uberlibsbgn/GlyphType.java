package org.sbgn.uberlibsbgn;

import org.sbgn.GlyphClazz;

/**
 * Respects a bit more the structure of the SBGN UML tree
 */
public enum GlyphType {
    PROCESS,
    EPN,
    LOGIC_GATE,
    COMPARTMENT,
    AUXILIARY_UNIT;


    public static GlyphType fromGlyphClazz(GlyphClazz clazz) {
        switch(clazz) {
            case OR:
            case AND:
            case NOT:
                return LOGIC_GATE;

            case PROCESS:
            case OMITTED_PROCESS:
            case UNCERTAIN_PROCESS:
                return PROCESS;

            case COMPARTMENT:
                return COMPARTMENT;

            case MACROMOLECULE:
            case MACROMOLECULE_MULTIMER:
            case SIMPLE_CHEMICAL:
            case SIMPLE_CHEMICAL_MULTIMER:
            case UNSPECIFIED_ENTITY:
            case COMPLEX:
            case COMPLEX_MULTIMER:
            case PHENOTYPE:
            case NUCLEIC_ACID_FEATURE:
            case NUCLEIC_ACID_FEATURE_MULTIMER:
            case PERTURBING_AGENT:
            case SOURCE_AND_SINK:
                return EPN;

            case STATE_VARIABLE:
            case UNIT_OF_INFORMATION:
            case ENTITY:
                return AUXILIARY_UNIT;

        }
        throw new IllegalStateException();
    }
}
