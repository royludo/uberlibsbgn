package org.sbgn.uberlibsbgn;

import org.sbgn.SbgnUtil;
import org.sbgn.bindings.*;
import org.xml.sax.SAXException;

import javax.crypto.Mac;
import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static org.sbgn.uberlibsbgn.Predicates.*;

public class Main {
    public static void main(String args[]) {
        System.out.println("test");
        Sbgn sbgn = new Sbgn();

        /*UGlyph glyph = new UGlyph("macromolecule")
                .setLabel("test");
        System.out.println(glyph.getGlyph().getLabel().getText());*/

        SimpleChemical sc = new SimpleChemical().setLabel("I'm a simple chemical");
        System.out.println(sc.getGlyph().getLabel().getText()+" "+sc.getLabel());


        Process pr = new Process();

        Macromolecule macro = new Macromolecule().setLabel("I'm macro").multimer();
        Macromolecule m2 = new Macromolecule().setLabel("macro2");
        Macromolecule proxy = Macromolecule.create();
        //IMacromolecule im = macro.createI();
        //System.out.println(im.multimer());

        System.out.println(macro.getGlyph().getClazz()+" "+m2.isMultimer());

        List<AbstractUGlyph> list = new ArrayList<>();
       // list.add(glyph);
        list.add(pr);
        list.add(sc);
        list.add(macro);
        //list.add(m2);
        UMap map = new UMap(list);
        map.add(m2);

        Predicate<AbstractUGlyph> isMacromolecule = new Predicate<AbstractUGlyph>() {
            @Override
            public boolean test(AbstractUGlyph abstractUGlyph) {

                return abstractUGlyph.getGlyph().getClazz().replace(" multimer", "").equals("macromolecule");
            }
        };




        System.out.println("Filter macromolecule");
        list.stream().filter(isMacromolecule).forEach(e -> System.out.println(e.getGlyph().getLabel().getText()));
        System.out.println("Not macromolecule");
        list.stream().filter(hasClass("macromolecule").or(hasClass("process"))).forEach(e -> System.out.println(e.getGlyph().getClazz()));

        System.out.println(map.filterGlyphs(hasClass("macromolecule").or(hasClass("process"))));


        Complex c1 = new Complex().setLabel("c1").multimer();
        c1.addChild(m2);
        Complex c2 = new Complex().setLabel("c2");
        c1.addChild(c2);
        System.out.println(c1.getFirstLevelChildren());
        c2.addChild(macro);
        System.out.println(c1.getAllChildren());
        Compartment comp1 = new Compartment();
        comp1.addChild(c1);
        System.out.println(comp1.getAllChildren());
        System.out.println(c1.addChild(comp1)+" "+c1.addChild(macro));


        Glyph g1 = new Glyph();
        g1.setId("g1");
        Label l1 = new Label();
        l1.setText("m1");
        g1.setLabel(l1);
        g1.setClazz("macromolecule");

        Bbox b1 = new Bbox();
        b1.setH(10);
        b1.setW(10);
        b1.setX(Float.NEGATIVE_INFINITY);
        b1.setY(0);
        g1.setBbox(b1);

        Glyph g2 = new Glyph();
        g2.setId("g2");
        Label l2 = new Label();
        l2.setText("m2");
        g2.setLabel(l2);
        g2.setClazz("macromolecule");

        Bbox b2 = new Bbox();
        b2.setH(10);
        b2.setW(10);
        b2.setX(0);
        b2.setY(50);
        g2.setBbox(b2);

        Glyph p = new Glyph();
        p.setId("p");
        p.setClazz("process");

        Bbox b3 = new Bbox();
        b3.setH(10);
        b3.setW(10);
        b3.setX(0);
        b3.setY(25);
        p.setBbox(b3);

        Arc a1 = new Arc();
        a1.setId("a1");
        a1.setClazz("consumption");
        a1.setSource(g1);
        a1.setTarget(p);

        Arc.Start s1 = new Arc.Start();
        s1.setX(0);
        s1.setY(0);
        Arc.End e1 = new Arc.End();
        e1.setX(0);
        e1.setY(25);
        a1.setStart(s1);
        a1.setEnd(e1);

        Arc a2 = new Arc();
        a2.setId("a2");
        a2.setClazz("production");
        a2.setSource(p);
        a2.setTarget(g2);

        Arc.Start s2 = new Arc.Start();
        s2.setX(0);
        s2.setY(25);
        Arc.End e2 = new Arc.End();
        e2.setX(25);
        e2.setY(50);
        a2.setStart(s2);
        a2.setEnd(e2);

        Map maptest = new Map();
        maptest.setId("map");
        maptest.getGlyph().add(g1);
        maptest.getGlyph().add(p);
        maptest.getGlyph().add(g2);
        maptest.getArc().add(a1);
        maptest.getArc().add(a2);

        Sbgn testsbgn = new Sbgn();
        testsbgn.getMap().add(maptest);

       /* try {
            SbgnUtil.writeToFile(testsbgn, new File("/tmp/test.sbgn"));
        } catch (JAXBException e) {
            e.printStackTrace();
        }*/

        /*try {
            SbgnUtil.isValid(new File("/tmp/test.sbgn"));
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/



    }

}
