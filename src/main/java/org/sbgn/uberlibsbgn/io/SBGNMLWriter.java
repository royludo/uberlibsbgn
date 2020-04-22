package org.sbgn.uberlibsbgn.io;

import org.sbgn.SbgnUtil;
import org.sbgn.bindings.Sbgn;
import org.sbgn.uberlibsbgn.UMap;

import javax.xml.bind.JAXBException;
import java.io.File;

public class SBGNMLWriter {

    public void writeToFile(UMap map, File file) throws JAXBException {
        SbgnUtil.writeToFile(this.buildSbgn(map), file);
    }

    public Sbgn buildSbgn(UMap map) {

        Sbgn sbgn = new Sbgn();

        return sbgn;
    }
}
