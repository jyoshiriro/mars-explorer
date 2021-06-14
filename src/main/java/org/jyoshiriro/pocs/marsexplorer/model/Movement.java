package org.jyoshiriro.pocs.marsexplorer.model;

public enum Movement {

    L("GoLeft"),
    R("GoRight"),
    M("GoAhead");

    private final String description;

    Movement(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
