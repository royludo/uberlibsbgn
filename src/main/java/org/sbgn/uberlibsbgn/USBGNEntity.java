package org.sbgn.uberlibsbgn;

import java.util.UUID;

/**
    Equivalent to SBGNBase, manages features shared by absolutely everything in sbgnml: Notes and Extensions
 */
public class USBGNEntity {

    private String id;

    public USBGNEntity() {
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }
}
