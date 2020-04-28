import org.sbgn.uberlibsbgn.AbstractUGlyph;
import org.sbgn.uberlibsbgn.UMap;
import org.sbgn.uberlibsbgn.USBGNEntity;
import org.sbgn.uberlibsbgn.Utilities;
import org.sbgn.uberlibsbgn.features.BboxFeature;
import org.sbgn.uberlibsbgn.io.SBGNMLReader;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.util.function.Function;

public class MainTestParsing {

    public static void main (String[] args) {

        SBGNMLReader reader = new SBGNMLReader();
        try {
            UMap uMap = reader.readFromFile(new File("samples/droso.sbgn"));
            for(AbstractUGlyph uGlyph: uMap.getAllGlyphs()) {
                System.out.println(uGlyph.getId()+" :__: "+ Utilities.glyphString(uGlyph));
                System.out.println(uGlyph.getBbox());
            }

        } catch (JAXBException | IOException | SAXException e) {
            e.printStackTrace();
        }
    }
}
