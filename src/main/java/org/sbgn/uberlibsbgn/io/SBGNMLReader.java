package org.sbgn.uberlibsbgn.io;

import org.sbgn.Language;
import org.sbgn.Main;
import org.sbgn.SbgnUtil;
import org.sbgn.bindings.Glyph;
import org.sbgn.bindings.Map;
import org.sbgn.bindings.Sbgn;
import org.sbgn.uberlibsbgn.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

public class SBGNMLReader {

    final Logger logger = LoggerFactory.getLogger(SBGNMLReader.class);

    public UMap readFromFile(File file) throws JAXBException, IOException, SAXException {

        File tmpConverted = File.createTempFile("converted", "sbgn");

        // first read and convert to a new file as only SbgnUtil.readFromFile has complete
        // version update and check capabilities
        logger.trace("first validation");
        try {
            Sbgn sbgn = SbgnUtil.readFromFile(file);
            SbgnUtil.writeToFile(sbgn, tmpConverted);
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new IOException("SBGN input file is not valid, cannot be converted to new version");
        }

        // now validate and read the up to date (v0.3) sbgn
        logger.trace("second validation");
        if(SbgnUtil.isValid(tmpConverted)) {
            logger.trace("read into Sbgn object");
            Sbgn sbgn = SbgnUtil.readFromFile(tmpConverted);
            return parseSBGN(sbgn);
        }
        else {
            throw new IOException("SBGN input file is not valid even after version update");
        }
    }

    public UMap parseSBGN(Sbgn sbgn) {
        logger.trace("process into UMap object");

        Map map = sbgn.getMap().get(0);
        Language language = Language.fromString(map.getLanguage());

        UMap uMap = new UMap(language);
        uMap.getProperties().setProperty(MapProperties.ENABLE_POSITION_CHANGE_EVENTS.toString(), "false");

        // first pass to get compartments
        // start by creating top level compartments that do not depend on anything, then included lower levels
        LinkedList<Glyph> compartmentList = new LinkedList<>();
        for(Glyph glyph: map.getGlyph()) {
            if(glyph.getClazz().equals("compartment")) {

                if(glyph.getCompartmentRef() == null) { // top level
                    // put at the start of the queue
                    //compartmentList.addFirst(glyph);
                    Compartment compartment = uMap.getFactory().compartment(glyph);
                }
                else { // compartment is included in another compartment

                    // try to process
                    Glyph ref = (Glyph) glyph.getCompartmentRef();
                    if(uMap.getGlyph(ref.getId()) != null) { // parent is already in uMap, so we can include right here
                        System.out.println(ref.getId()+" already there");
                        Compartment subCompartment = uMap.getFactory().compartment(glyph);
                        Compartment parent = (Compartment) uMap.getGlyph(ref.getId());
                        parent.add(subCompartment);
                    }
                    else {
                        // if unable
                        compartmentList.add(glyph);
                    }


                }
            }
        }
        logger.trace("Leftover compartments: "+compartmentList.size());

        // at this point we have a list of non added remaining compartments
        // iterate over the list until all of them have included
        // TODO risk of infinite loop here if there is an orphan compartment pointing to a non existent parent
        while(compartmentList.size() != 0) {
            logger.trace("remaining compartment list size: "+compartmentList.size());
            Glyph currentGlyph = compartmentList.getFirst();
            Glyph ref = (Glyph) currentGlyph.getCompartmentRef();
            if(uMap.getGlyph(ref.getId()) != null) {
                Compartment subCompartment = uMap.getFactory().compartment(currentGlyph);
                Compartment parent = (Compartment) uMap.getGlyph(ref.getId());
                parent.add(subCompartment);
                compartmentList.removeFirst();
            }
            else { // put first in last position
                compartmentList.addLast(compartmentList.removeFirst());
            }
        }

        // second pass for remaining glyphs

        return uMap;
    }

}
