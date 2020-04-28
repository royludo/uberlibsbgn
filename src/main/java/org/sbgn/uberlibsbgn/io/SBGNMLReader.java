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


        // first pass, to get compartments
        // start by creating top level compartments that do not depend on anything, then include lower levels
        LinkedList<Glyph> compartmentList = new LinkedList<>();
        for(Glyph glyph: map.getGlyph()) {
            if(glyph.getClazz().equals("compartment")) {

                if(glyph.getCompartmentRef() == null) { // top level
                    uMap.getFactory().compartment(glyph);
                }
                else { // compartment is included in another compartment

                    // try to process
                    Glyph ref = (Glyph) glyph.getCompartmentRef();
                    if(uMap.getGlyph(ref.getId()) != null) { // parent is already in uMap, so we can include right here
                        Compartment subCompartment = uMap.getFactory().compartment(glyph);
                        Compartment parent = (Compartment) uMap.getGlyph(ref.getId());
                        parent.add(subCompartment);
                    }
                    else {
                        compartmentList.add(glyph);
                    }
                }
            }
        }
        logger.trace("Leftover compartments: "+compartmentList.size());

        // at this point we have a list of non added remaining compartments
        // iterate over the list until all of them have included
        // risk of infinite loop here if there is an compartment pointing to an invalid parent
        // if reference were pointing to a non existent glyph, the compartment would be considered top level
        int infinityPreventionCounter = 0;
        while(compartmentList.size() != 0) {
            logger.trace("remaining compartment list size: "+compartmentList.size());
            Glyph currentGlyph = compartmentList.getFirst();
            Glyph ref = (Glyph) currentGlyph.getCompartmentRef();
            if(uMap.getGlyph(ref.getId()) != null) {
                Compartment subCompartment = uMap.getFactory().compartment(currentGlyph);
                Compartment parent = (Compartment) uMap.getGlyph(ref.getId());
                parent.add(subCompartment);
                compartmentList.removeFirst();
                infinityPreventionCounter = 0;
            }
            else { // put first in last position
                compartmentList.addLast(compartmentList.removeFirst());

                infinityPreventionCounter++;
                if(infinityPreventionCounter > compartmentList.size()) {
                    // we have looped over all the list without removing something, we are stuck
                    logger.warn("Found "+compartmentList.size()+" orphan compartments pointing to invalid parent");
                    for(Glyph g: compartmentList) {
                        logger.warn("Compartment with id "+ g.getId() +" has invalid parent "+
                                ((Glyph) g.getCompartmentRef()).getId());
                    }
                    break;
                }
            }
        }


        // second pass for remaining glyphs

        return uMap;
    }

}
