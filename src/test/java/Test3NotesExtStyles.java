import javafx.scene.paint.Color;
import org.sbgn.ArcClazz;
import org.sbgn.Language;
import org.sbgn.bindings.SBGNBase;
import org.sbgn.uberlibsbgn.Macromolecule;
import org.sbgn.uberlibsbgn.UArc;
import org.sbgn.uberlibsbgn.UMap;
import org.sbgn.uberlibsbgn.UnitOfInfo;
import org.sbgn.uberlibsbgn.style.Util;

public class Test3NotesExtStyles {
    public static void main(String args[]) {

        UMap map = new UMap(Language.PD);

        Macromolecule m = map.getFactory().macromolecule().build();

        m.addNote("<p>test</p>");
        m.addNote("<html>another note</html>");

        map.addNote("<p>map note!</p>");

        System.out.println(m.getNotes());
        System.out.println(map.getNotes());

        UArc arc = m.addArcTo(ArcClazz.CONSUMPTION, m);
        arc.setStrokeColor(Color.ANTIQUEWHITE);
        arc.getStrokeColor().ifPresent(color -> System.out.println(color.getOpacity()));

        map.setBackgroundColor(Color.TEAL);
        map.getBackgroundColor().ifPresent(c-> System.out.println(Util.webString(c)));

    }
}
