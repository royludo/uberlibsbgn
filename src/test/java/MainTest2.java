import org.sbgn.ArcClazz;
import org.sbgn.Language;
import org.sbgn.uberlibsbgn.Macromolecule;
import org.sbgn.uberlibsbgn.Process;
import org.sbgn.uberlibsbgn.UMap;
import org.sbgn.uberlibsbgn.features.Orientation;
import org.sbgn.uberlibsbgn.indexing.DefaultIndexes;
import org.sbgn.uberlibsbgn.indexing.Index;
import org.sbgn.uberlibsbgn.indexing.LabelIndex;
import org.sbgn.uberlibsbgn.indexing.NeighborsIndex;

import java.awt.geom.Rectangle2D;

public class MainTest2 {
    public static void main(String args[]) {

        UMap map = new UMap(Language.PD);

        Macromolecule m1 = map.getFactory().macromolecule().build().setLabel("m1");
        Macromolecule m2 = map.getFactory().macromolecule().build().setLabel("m2");
        Macromolecule m3 = map.getFactory().macromolecule().build().setLabel("m3");

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

        Process p = map.getFactory().process().build();
        System.out.println("process bbox "+p.getBbox()+" port 1 "+p.getLHSPort()+" port2 "+p.getRHSPort());
        p.setBbox(new Rectangle2D.Float(0,0,20,10));
        System.out.println("process bbox "+p.getBbox()+" port 1 "+p.getLHSPort()+" port2 "+p.getRHSPort());
        p.setPortDistance(2.5f);
        System.out.println("process bbox "+p.getBbox()+" port 1 "+p.getLHSPort()+" port2 "+p.getRHSPort());
        p.setPortOrientation(Orientation.VERTICAL);
        System.out.println("process bbox "+p.getBbox()+" port 1 "+p.getLHSPort()+" port2 "+p.getRHSPort());


    }
}
