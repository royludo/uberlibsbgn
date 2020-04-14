package org.sbgn.uberlibsbgn.features;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.SetMultimap;
import org.sbgn.uberlibsbgn.AbstractUGlyph;
import org.sbgn.uberlibsbgn.UnitOfInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class UnitOfInfoParentFeatureImpl implements UnitOfInfoParentFeature {

    private AbstractUGlyph uGlyph;
    //private Predicate<AbstractUGlyph> includeCondition;
    //private List<UnitOfInfo> children;
    private SetMultimap<String, String> stringContentMap;
    private Map<String, UnitOfInfo> keyMap, valueMap;

    // trick to bypass the protected constructor of UnitOfInfo, is it right ? is it a code smell ?
    private class UnitOfInfoChild extends UnitOfInfo {
        protected UnitOfInfoChild(AbstractUGlyph parentGlyph) {
            super(parentGlyph);
        }
    }

    final Logger logger = LoggerFactory.getLogger(UnitOfInfoParentFeatureImpl.class);

    public UnitOfInfoParentFeatureImpl(AbstractUGlyph uGlyph) {
        logger.trace("create unitOfInfoFeature");
        this.uGlyph = uGlyph;
        this.stringContentMap = HashMultimap.create();
        this.keyMap = new HashMap<>();
        this.valueMap = new HashMap<>();
    }

    @Override
    public AbstractUGlyph addUnitOfInfo(String key, String value) {
        UnitOfInfo u = new UnitOfInfoChild(this.uGlyph);
        u.setLabel(key+":"+value);

        this.stringContentMap.put(key, value);
        this.keyMap.put(key, u);
        this.valueMap.put(value, u);

        u.registerBboxToPropertySender(this.uGlyph);

        return u;
    }

    @Override
    public UnitOfInfo getUnitOfInfoWithKey(String key) {
        return null;
    }

    @Override
    public Stream<UnitOfInfo> getUnitOfInfoStream() {
        return this.keyMap.values().stream();
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
