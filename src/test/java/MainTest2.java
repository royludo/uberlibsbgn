import javafx.geometry.Orientation;
import javafx.geometry.Rectangle2D;
import org.sbgn.ArcClazz;
import org.sbgn.Language;
import org.sbgn.uberlibsbgn.*;
import org.sbgn.uberlibsbgn.Process;
import org.sbgn.uberlibsbgn.indexing.DefaultIndexes;
import org.sbgn.uberlibsbgn.indexing.Index;
import org.sbgn.uberlibsbgn.indexing.LabelIndex;
import org.sbgn.uberlibsbgn.indexing.NeighborsIndex;


public class MainTest2 {
    public static void main(String args[]) {

        UMap map = new UMap(Language.PD);

        Macromolecule m1 = map.getFactory().macromolecule();
        m1.setLabel("m1");
        Macromolecule m2 = map.getFactory().macromolecule();
        m2.setLabel("m2");
        Macromolecule m3 = map.getFactory().macromolecule();
        m3.setLabel("m3");

        System.out.println("----");
        m1.addArcTo(ArcClazz.PRODUCTION, m2);
        m1.addArcTo(ArcClazz.PRODUCTION, m3);
        m1.addArcTo(ArcClazz.CONSUMPTION, m3);

        NeighborsIndex neighborIndex= (NeighborsIndex) map.getIndexManager().getIndex(DefaultIndexes.NEIGHBORS.getIndexKey());
        neighborIndex.print();

        LabelIndex labelIndex = (LabelIndex) map.getIndexManager().getIndex(DefaultIndexes.LABEL.getIndexKey());
        labelIndex.print();
        m1.setLabel("newm1");
        labelIndex.print();
        m1.setLabel("m2");
        labelIndex.print();

        m1.addUnitOfInfo("key","val");

        System.out.println("unit of info");
        m1.getUnitOfInfoStream().forEach(u -> {
            System.out.println(u.getLabel());
            u.setKey("other key");
            System.out.println(u.getLabel()+" -"+u.getKey()+"- "+u.getValue());
            u.setLabel("nimp");
            System.out.println(u.getLabel()+" -"+u.getKey()+"- "+u.getValue());
        });

        Process p = map.getFactory().process();
        System.out.println("process bbox "+p.getBbox()+" port 1 "+p.getLHSPort()+" port2 "+p.getRHSPort());
        p.setBbox(new Rectangle2D(0,0,20,10));
        System.out.println("process bbox "+p.getBbox()+" port 1 "+p.getLHSPort()+" port2 "+p.getRHSPort());
        p.setPortDistance(2.5f);
        System.out.println("process bbox "+p.getBbox()+" port 1 "+p.getLHSPort()+" port2 "+p.getRHSPort());
        p.setPortOrientation(Orientation.VERTICAL);
        System.out.println("process bbox "+p.getBbox()+" port 1 "+p.getLHSPort()+" port2 "+p.getRHSPort());

        System.out.println("------- test label bbox moving when parent glyph moves ----------");
        System.out.println("m1 bbox "+m1.getBbox());
        System.out.println("m1 label bbox "+m1.getLabelBbox());
        m1.setBbox(new Rectangle2D(10,10,10,20));
        System.out.println("m1 bbox "+m1.getBbox());
        System.out.println("m1 label bbox "+m1.getLabelBbox());

        System.out.println("-------- test unit of info moving when parent moves ----------");
        UnitOfInfo u = (UnitOfInfo) m1.addUnitOfInfo("test", "bboxchange");
        System.out.println("u bbox: "+u.getBbox()+" u labelbbox"+u.getLabelBbox());
        m1.setBbox(new Rectangle2D(20, 20, 10, 20));
        System.out.println("u bbox: "+u.getBbox()+" u labelbbox"+u.getLabelBbox());

        System.out.println("-------- test children moving when parent complex moves ----------");
        Complex c = map.getFactory().complex();
        c.setLabel("c");
        c.add(m1);
        System.out.println("c bbox "+c.getBbox());
        System.out.println("m1 bbox "+m1.getBbox()+" m1 label bbox "+m1.getLabelBbox());
        System.out.println("u bbox: "+u.getBbox()+" u labelbbox"+u.getLabelBbox());
        c.setBbox(new Rectangle2D(-35, -35, 20,20));
        System.out.println("c bbox "+c.getBbox());
        System.out.println("m1 bbox "+m1.getBbox()+" m1 label bbox "+m1.getLabelBbox());
        System.out.println("u bbox: "+u.getBbox()+" u labelbbox"+u.getLabelBbox());

    }
}
