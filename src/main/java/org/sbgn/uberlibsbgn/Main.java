package org.sbgn.uberlibsbgn;

import org.sbgn.bindings.Sbgn;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static org.sbgn.uberlibsbgn.Predicates.*;

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

        List<AbstractUGlyph> list = new ArrayList<>();
        list.add(glyph);
        list.add(pr);
        list.add(sc);
        list.add(macro);
        list.add(m2);

        Predicate<AbstractUGlyph> isMacromolecule = new Predicate<AbstractUGlyph>() {
            @Override
            public boolean test(AbstractUGlyph abstractUGlyph) {

                return abstractUGlyph.getGlyph().getClazz().replace(" multimer", "").equals("macromolecule");
            }
        };


        System.out.println("Filter macromolecule");
        list.stream().filter(isMacromolecule).forEach(e -> System.out.println(e.getGlyph().getLabel().getText()));
        System.out.println("Not macromolecule");
        list.stream().filter(isOfClass("macromolecule")).forEach(e -> System.out.println(e.getGlyph().getClazz()));


    }

}
