package org.sbgn.uberlibsbgn;

import org.sbgn.uberlibsbgn.glyphfeatures.ILabelFeature;
import org.sbgn.uberlibsbgn.glyphfeatures.IMultimerFeature;
import org.sbgn.uberlibsbgn.glyphfeatures.LabelFeature;
import org.sbgn.uberlibsbgn.glyphfeatures.MultimerFeature;

import javax.crypto.Mac;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Macromolecule extends AbstractUGlyph<Macromolecule> implements IMultimerFeature, ILabelFeature,
        IMacromolecule, ComplexIncludible {

    private MultimerFeature<Macromolecule> multimerFeature;
    private LabelFeature<Macromolecule> labelFeature;

    public Macromolecule() {
        super("macromolecule");
        this.multimerFeature = new MultimerFeature<>(this);
        this.labelFeature = new LabelFeature<>(this);
    }

    /**
     * Try delegation to dynamic proxy
     * @return
     */
    public static Macromolecule create() {
        Macromolecule macro = new Macromolecule();
        Object o = Proxy.newProxyInstance(Macromolecule.class.getClassLoader(),
                new Class[] { ILabelFeature.class, IMultimerFeature.class },
                (proxy, m, args) -> {
                    // if m is a method that you want to change the behaviour of, return something
                    // by default, delegate to the primary
                    return m.invoke(macro, args);
                });
        System.out.println("PROXY test "+
                (o instanceof Macromolecule)+" "+
                (o instanceof IMultimerFeature)+" "+
                (o instanceof ILabelFeature));
        return macro;
    }

    /**
     * other test
     * @return
     */
    public Macromolecule createI() {
        return (Macromolecule) this.getProxy(this.labelFeature, this.multimerFeature);
    }

    @Override
    public Macromolecule multimer() {
        return (Macromolecule) this.multimerFeature.multimer();
    }

    @Override
    public Macromolecule multimer(boolean isMultimer) {
        return(Macromolecule) this.multimerFeature.multimer(isMultimer);
    }

    @Override
    public boolean isMultimer() {
        return this.multimerFeature.isMultimer();
    }

    @Override
    public Macromolecule setLabel(String label) {
        return (Macromolecule) this.labelFeature.setLabel(label);
    }

    @Override
    public String getLabel() {
        return this.labelFeature.getLabel();
    }
}
