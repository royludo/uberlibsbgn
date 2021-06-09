import javafx.scene.paint.Color;
import org.eclipse.rdf4j.model.impl.SimpleValueFactory;
import org.sbgn.ArcClazz;
import org.sbgn.Language;
import org.sbgn.uberlibsbgn.*;
import org.sbgn.uberlibsbgn.style.StyleUtils;

public class Test3NotesExtStyles {
    public static void main(String args[]) {

        UMap map = new UMap(Language.PD);

        Macromolecule m = map.getFactory().macromolecule();
        Macromolecule m2 = map.getFactory().macromolecule();

        m.addNote("<p>test</p>");
        m.addNote("<html>another note</html>");

        map.addNote("<p>map note!</p>");

        System.out.println(m.getNotes());
        System.out.println(map.getNotes());

        UArc arc = m.addArcTo(ArcClazz.CONSUMPTION, m);
        arc.setStrokeColor(Color.ANTIQUEWHITE);
        arc.getStrokeColor().ifPresent(color -> System.out.println(color.getOpacity()));

        map.style().setBackgroundColor(Color.TEAL);
        map.style().getBackgroundColor().ifPresent(c-> System.out.println(StyleUtils.webString(c)));

        System.out.println("---------- RDF ----------");
        m.bqbiolIs(SimpleValueFactory.getInstance().createIRI("http://test"));
        m2.bqbiolIs(SimpleValueFactory.getInstance().createIRI("http://test2222"));
        m.bqbiolIs(SimpleValueFactory.getInstance().createIRI("http://otherrrr"));
        System.out.println(m.getAllStatements());
        System.out.println(m2.getAllStatements());
    }
}
