import javafx.scene.paint.Color;
import org.eclipse.rdf4j.model.impl.SimpleIRI;
import org.eclipse.rdf4j.model.impl.SimpleValueFactory;
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
        Macromolecule m2 = map.getFactory().macromolecule().build();

        m.addNote("<p>test</p>");
        m.addNote("<html>another note</html>");

        map.addNote("<p>map note!</p>");

        System.out.println(m.getNotes());
        System.out.println(map.getNotes());

        UArc arc = m.addArcTo(ArcClazz.CONSUMPTION, m);
        arc.setStrokeColor(Color.ANTIQUEWHITE);
        arc.getStrokeColor().ifPresent(color -> System.out.println(color.getOpacity()));

        map.style().setBackgroundColor(Color.TEAL);
        map.style().getBackgroundColor().ifPresent(c-> System.out.println(Util.webString(c)));

        System.out.println("---------- RDF ----------");
        m.bqbiolIs(SimpleValueFactory.getInstance().createIRI("http://test"));
        m2.bqbiolIs(SimpleValueFactory.getInstance().createIRI("http://test2222"));
        m.bqbiolIs(SimpleValueFactory.getInstance().createIRI("http://otherrrr"));
        System.out.println(m.getAllStatements());
        System.out.println(m2.getAllStatements());
    }
}
