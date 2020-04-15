package org.sbgn.uberlibsbgn.features;

import org.sbgn.bindings.SBGNBase;
import org.sbgn.uberlibsbgn.AbstractUGlyph;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.validation.SchemaFactory;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

public class NotesFeatureImpl implements NotesFeature {

    //private AbstractUGlyph uGlyph;

    private List<String> notes;

    final Logger logger = LoggerFactory.getLogger(NotesFeatureImpl.class);

    public NotesFeatureImpl() {
        //this.uGlyph = uGlyph;
        this.notes = new ArrayList<>();
    }

    @Override
    public List<String> getNotes() {
        return this.notes;
    }

    @Override
    public void addNote(String html) throws IllegalArgumentException {
        try {
            validateHtmlString(html);
        } catch (SAXException | IOException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Invalid html string provided");
        }
        this.notes.add(html);
    }

    private void validateHtmlString(String html) throws IOException, SAXException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db;
        try {
            db = dbf.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        db.parse(new InputSource(new StringReader(html)));
    }
}
