package org.sbgn.uberlibsbgn;

import java.util.function.Predicate;

/**
 * Contains function that are Predicate builders.
 */
public class Predicates {

    public static Predicate<AbstractUGlyph> hasClass(String clazz) {
        return abstractUGlyph -> abstractUGlyph
                .getGlyph()
                .getClazz()
                .replace(" multimer", "")
                .equals(clazz);
    }

    //TODO isProcess

    //TODO labelMatches regexp, string

    //TODO unitOfInfoMatches regexp, string

    //TODO hasOutgoingLinks
}
