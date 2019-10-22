package org.sbgn.uberlibsbgn.indexing;

public enum DefaultIndexes {
    LABEL("_label"),
    CLASS("_class");

    private String indexKey;

    private DefaultIndexes(String indexKey) {
        this.indexKey = indexKey;
    }

    public String getIndexKey() {
        return this.indexKey;
    }
}
