package org.sbgn.uberlibsbgn;

import org.sbgn.uberlibsbgn.glyphfeatures.ILabelFeature;
import org.sbgn.uberlibsbgn.glyphfeatures.IMultimerFeature;

import java.lang.reflect.Proxy;

public interface IMacromolecule extends ILabelFeature, IMultimerFeature{


    /*ILabelFeature getLabelFeature();

    IMultimerFeature getMultimerFeature();*/

    default IMacromolecule getProxy(ILabelFeature labelFeature, IMultimerFeature multimerFeature ) {

        return  (IMacromolecule) Proxy.newProxyInstance(IMacromolecule.class.getClassLoader(),
                new Class[] { IMacromolecule.class, ILabelFeature.class, IMultimerFeature.class },
                (proxy, m, args) -> {
                    // if m is a method that you want to change the behaviour of, return something
                    // by default, delegate to the primary
                    try {
                        labelFeature.getClass().getMethod(m.getName(), m.getParameterTypes());
                        return m.invoke(labelFeature, args);
                    }
                    catch (NoSuchMethodException | SecurityException e) {
                        multimerFeature.getClass().getMethod(m.getName(), m.getParameterTypes());
                        return m.invoke(multimerFeature, args);
                    }
                });
    }
}
