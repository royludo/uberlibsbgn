import org.sbgn.Language;
import org.sbgn.bindings.SBGNBase;
import org.sbgn.uberlibsbgn.Macromolecule;
import org.sbgn.uberlibsbgn.UMap;

public class Test3NotesExtStyles {
    public static void main(String args[]) {

        UMap map = new UMap(Language.PD);

        Macromolecule m = map.getFactory().macromolecule().build();

        m.addNote("<p>test</p>");
        m.addNote("<html>another note</html>");

        map.addNote("<p>map note!</p>");

        System.out.println(m.getNotes());
        System.out.println(map.getNotes());

    }
}
