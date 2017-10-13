import org.sbgn.SbgnUtil;
import org.sbgn.SbgnVersionFinder;
import org.sbgn.bindings.SBGNBase;
import org.sbgn.bindings.Sbgn;

public class Main {
    public static void main(String args[]) {
        System.out.println("test");
        Sbgn sbgn = new Sbgn();

        UGlyph glyph = new UGlyph.Builder()
                .label("test")
                .build();
        System.out.println(glyph.getGlyph().getLabel().getText());

    }
}
