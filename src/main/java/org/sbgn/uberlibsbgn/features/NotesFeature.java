package org.sbgn.uberlibsbgn.features;

import org.w3c.dom.Element;

import java.util.List;

public interface NotesFeature {

    List<String> getNotes();
    void addNote(String html) throws IllegalArgumentException;
}
