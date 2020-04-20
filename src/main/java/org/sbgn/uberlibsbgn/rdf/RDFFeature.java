package org.sbgn.uberlibsbgn.rdf;

import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.Statement;
import org.eclipse.rdf4j.model.Value;

import java.util.List;
import java.util.Set;

public interface RDFFeature {

    void addStatement(IRI predicate, Value object);
    void bqbiolIs(IRI object);
    Set<Statement> getAllStatements();

}
