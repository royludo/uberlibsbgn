import org.sbgn.uberlibsbgn.AbstractUGlyph;
import org.sbgn.uberlibsbgn.UMap;
import org.sbgn.uberlibsbgn.USBGNEntity;
import org.sbgn.uberlibsbgn.io.SBGNMLReader;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.util.function.Function;

public class MainTestParsing {

    public static void main (String[] args) {

        SBGNMLReader reader = new SBGNMLReader();
        try {
            UMap uMap = reader.readFromFile(new File("samples/droso.sbgn"));
            uMap.getAllGlyphs().forEach(u -> {
                System.out.println(u.getId());});
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
