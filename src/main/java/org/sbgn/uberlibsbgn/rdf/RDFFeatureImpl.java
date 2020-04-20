package org.sbgn.uberlibsbgn.rdf;

import org.eclipse.rdf4j.model.*;
import org.eclipse.rdf4j.model.impl.SimpleValueFactory;
import org.sbgn.uberlibsbgn.USBGNEntity;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class RDFFeatureImpl implements RDFFeature {


    private USBGNEntity usbgnEntity;
    private String metaId; // # + id, which is used in the rdf
    private IRI thisIRI;
    private final String LOCAL_NS = "http://localnamespace.sbgn.org";
    private final String BQBIOL_NS = "http://biomodels.net/biology-qualifiers/";
    private final String BQBIOL_IS = BQBIOL_NS + "is";


    public RDFFeatureImpl(USBGNEntity usbgnEntity) {
        this.usbgnEntity = usbgnEntity;
        this.metaId = "#" + usbgnEntity.getId();
        ValueFactory vf = SimpleValueFactory.getInstance();
        this.thisIRI = vf.createIRI(LOCAL_NS,metaId);
    }


    @Override
    public void addStatement(IRI predicate, Value object) {
        this.usbgnEntity.getMap().rdf().add(thisIRI, predicate, object);
    }

    @Override
    public void bqbiolIs(IRI object) {
        ValueFactory vf = SimpleValueFactory.getInstance();
        this.addStatement(vf.createIRI(BQBIOL_IS), object);
    }

    @Override
    public Set<Statement> getAllStatements() {
        return this.usbgnEntity.getMap().rdf().filter(thisIRI, null, null);
    }
}
