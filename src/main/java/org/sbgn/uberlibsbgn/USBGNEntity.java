package org.sbgn.uberlibsbgn;

import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.Statement;
import org.eclipse.rdf4j.model.Value;
import org.sbgn.uberlibsbgn.features.NotesFeature;
import org.sbgn.uberlibsbgn.features.NotesFeatureImpl;
import org.sbgn.uberlibsbgn.rdf.RDFFeature;
import org.sbgn.uberlibsbgn.rdf.RDFFeatureImpl;

import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
    Equivalent to SBGNBase, manages features shared by absolutely everything in sbgnml: Notes and Extensions
 */
public abstract class USBGNEntity implements NotesFeature, RDFFeature {

    private String id;
    private NotesFeature notesFeature;
    private RDFFeature rdfFeature;

    protected USBGNEntity(String id) {
        this.id = id;
        this.notesFeature = new NotesFeatureImpl();
        this.rdfFeature = new RDFFeatureImpl(this);
    }

    public String getId() {
        return id;
    }

    public abstract UMap getMap();

    @Override
    public List<String> getNotes() {
        return notesFeature.getNotes();
    }

    @Override
    public void addNote(String html) throws IllegalArgumentException {
        notesFeature.addNote(html);
    }

    @Override
    public void addStatement(IRI predicate, Value object) {
        rdfFeature.addStatement(predicate, object);
    }

    @Override
    public void bqbiolIs(IRI object) {
        rdfFeature.bqbiolIs(object);
    }

    @Override
    public Set<Statement> getAllStatements() {
        return rdfFeature.getAllStatements();
    }
}
