package org.sbgn.uberlibsbgn;

import org.sbgn.GlyphClazz;

public enum UGlyphClass {
    OR,
    AND,
    NOT,

    PROCESS,
    OMITTED_PROCESS,
    UNCERTAIN_PROCESS,

    COMPARTMENT,

    MACROMOLECULE,
    SIMPLE_CHEMICAL,
    UNSPECIFIED_ENTITY,
    COMPLEX,
    PHENOTYPE,
    NUCLEIC_ACID_FEATURE,
    PERTURBING_AGENT,
    SOURCE_AND_SINK,

    STATE_VARIABLE,
    UNIT_OF_INFORMATION,
    ENTITY;

    public static Class getClass(UGlyphClass clazz) {
        switch(clazz) {
            case MACROMOLECULE: return Macromolecule.class;
            case COMPLEX: return Complex.class;
            case SIMPLE_CHEMICAL: return SimpleChemical.class;
            case PROCESS: return Process.class;
        }
        throw new IllegalStateException();
    }

    public static UGlyphClass fromGlyphClazz(GlyphClazz clazz) {
        switch(clazz) {
            case OR:
                return OR;
            case AND:
                return AND;
            case NOT:
                return NOT;

            case PROCESS:
                return PROCESS;
            case OMITTED_PROCESS:
                return OMITTED_PROCESS;
            case UNCERTAIN_PROCESS:
                return UNCERTAIN_PROCESS;

            case COMPARTMENT:
                return COMPARTMENT;

            case MACROMOLECULE:
            case MACROMOLECULE_MULTIMER:
                return MACROMOLECULE;
            case SIMPLE_CHEMICAL:
            case SIMPLE_CHEMICAL_MULTIMER:
                return SIMPLE_CHEMICAL;
            case UNSPECIFIED_ENTITY:
                return UNSPECIFIED_ENTITY;
            case COMPLEX:
            case COMPLEX_MULTIMER:
                return COMPLEX;
            case PHENOTYPE:
                return PHENOTYPE;
            case NUCLEIC_ACID_FEATURE:
            case NUCLEIC_ACID_FEATURE_MULTIMER:
                return NUCLEIC_ACID_FEATURE;
            case PERTURBING_AGENT:
                return PERTURBING_AGENT;
            case SOURCE_AND_SINK:
                return SOURCE_AND_SINK;

            case STATE_VARIABLE:
                return STATE_VARIABLE;
            case UNIT_OF_INFORMATION:
                return UNIT_OF_INFORMATION;
            case ENTITY:
                return ENTITY;

        }
        throw new IllegalStateException();
    }
}
