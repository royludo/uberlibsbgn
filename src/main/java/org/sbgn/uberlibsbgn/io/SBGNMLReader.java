package org.sbgn.uberlibsbgn.io;

import org.sbgn.Language;
import org.sbgn.SbgnUtil;
import org.sbgn.bindings.Map;
import org.sbgn.bindings.Sbgn;
import org.sbgn.uberlibsbgn.UMap;

import javax.xml.bind.JAXBException;
import java.io.File;

public class SBGNMLReader {

    public UMap readFromFile(File file) throws JAXBException {
        Sbgn sbgn = SbgnUtil.readFromFile(file);
        return parseSBGN(sbgn);
    }

    public UMap parseSBGN(Sbgn sbgn) {
        Map map = sbgn.getMap().get(0);
        Language language = Language.fromString(map.getLanguage());

        UMap uMap = new UMap(language);


        

        return uMap;
    }

}
