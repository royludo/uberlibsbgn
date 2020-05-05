import org.sbgn.uberlibsbgn.*;
import org.sbgn.uberlibsbgn.features.BboxFeature;
import org.sbgn.uberlibsbgn.indexing.DefaultIndexes;
import org.sbgn.uberlibsbgn.indexing.GlyphClassIndex;
import org.sbgn.uberlibsbgn.indexing.LabelIndex;
import org.sbgn.uberlibsbgn.io.SBGNMLReader;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.util.function.Function;

public class MainTestParsing {

    public static void main (String[] args) {

        SBGNMLReader reader = new SBGNMLReader();
        UMap uMap = null;
        try {
            uMap = reader.readFromFile(new File("samples/droso.sbgn"));
            for(AbstractUGlyph uGlyph: uMap.getAllGlyphs()) {
                System.out.println(uGlyph.getId()+" :__: "+ Utilities.glyphString(uGlyph));
                System.out.println(uGlyph.getBbox());
            }

        } catch (JAXBException | IOException | SAXException e) {
            e.printStackTrace();
        }

        assert uMap != null;

        GlyphClassIndex classIndex = (GlyphClassIndex) uMap.getIndexManager().getIndex(DefaultIndexes.CLASS.getIndexKey());
        System.out.println("--"+classIndex.getGlyphs(UGlyphClass.COMPARTMENT)+"--");
    }
}
