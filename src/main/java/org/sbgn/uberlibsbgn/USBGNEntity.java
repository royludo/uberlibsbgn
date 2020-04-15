package org.sbgn.uberlibsbgn;

import org.sbgn.uberlibsbgn.features.NotesFeature;
import org.sbgn.uberlibsbgn.features.NotesFeatureImpl;

import java.util.List;
import java.util.UUID;

/**
    Equivalent to SBGNBase, manages features shared by absolutely everything in sbgnml: Notes and Extensions
 */
public class USBGNEntity implements NotesFeature {

    private String id;
    private NotesFeature notesFeature;

    public USBGNEntity() {
        this.id = UUID.randomUUID().toString();
        this.notesFeature = new NotesFeatureImpl();
    }

    public String getId() {
        return id;
    }

    @Override
    public List<String> getNotes() {
        return notesFeature.getNotes();
    }

    @Override
    public void addNote(String html) throws IllegalArgumentException {
        notesFeature.addNote(html);
    }
}
