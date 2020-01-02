package org.sbgn.uberlibsbgn.glyphfeatures;

import org.sbgn.uberlibsbgn.AbstractUGlyph;
import org.sbgn.uberlibsbgn.UnitOfInfo;

import java.util.List;
import java.util.function.Predicate;

public class UnitOfInfoFeatureImpl implements UnitOfInfoFeature {

    private AbstractUGlyph uGlyph;
    //private Predicate<AbstractUGlyph> includeCondition;
    private List<UnitOfInfo> children;


    @Override
    public AbstractUGlyph addUnitOfInfo(String key, String value) {
        return null;
    }

    @Override
    public UnitOfInfo getUnitOfInfoWithKey(String key) {
        return null;
    }

    @Override
    public List<UnitOfInfo> getUnitsOfInfoWithRegex(String regex) {
        return null;
    }

    @Override
    public boolean hasUnitOfInfo() {
        return false;
    }

    @Override
    public boolean hasUnitOfInfoWithKey(String key) {
        return false;
    }

    @Override
    public boolean hasUnitOfInfoWithRegex(String regex) {
        return false;
    }

    @Override
    public UnitOfInfo removeUnitOfInfoWithKey(String key) {
        return null;
    }

    @Override
    public List<UnitOfInfo> removeUnitsOfInfoWithRegex(String regex) {
        return null;
    }
}
