import org.sbgn.SbgnUtil;
import org.sbgn.SbgnVersionFinder;
import org.sbgn.bindings.SBGNBase;
import org.sbgn.bindings.Sbgn;

public class Main {
    public static void main(String args[]) {
        System.out.println("test");
        Sbgn sbgn = new Sbgn();

        UGlyph glyph = new UGlyph("macromolecule")
                .setLabel("test");
        System.out.println(glyph.getGlyph().getLabel().getText());

        SimpleChemical sc = new SimpleChemical().setLabel("I'm a simple chemical");
        System.out.println(sc.getGlyph().getLabel().getText());


        Process pr = new Process();

        Macromolecule macro = new Macromolecule().setLabel("I'm macro").multimer();
        Macromolecule m2 = new Macromolecule().setLabel("macro2");

        System.out.println(macro.getGlyph().getClazz()+" "+m2.isMultimer());

    }
}
