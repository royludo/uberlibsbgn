package org.sbgn.uberlibsbgn.glyphfeatures;

import org.sbgn.uberlibsbgn.AbstractUGlyph;
import org.sbgn.uberlibsbgn.UnitOfInfo;

import java.util.List;

public interface UnitOfInfoFeature {

    AbstractUGlyph addUnitOfInfo(String key, String value);

    UnitOfInfo getUnitOfInfoWithKey(String key);

    List<UnitOfInfo> getUnitsOfInfoWithRegex(String regex);

    boolean hasUnitOfInfo();

    boolean hasUnitOfInfoWithKey(String key);

    boolean hasUnitOfInfoWithRegex(String regex);

    UnitOfInfo removeUnitOfInfoWithKey(String key);

    List<UnitOfInfo> removeUnitsOfInfoWithRegex(String regex);
}
