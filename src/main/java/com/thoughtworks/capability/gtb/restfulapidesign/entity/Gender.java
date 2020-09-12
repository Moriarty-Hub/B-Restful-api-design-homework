package com.thoughtworks.capability.gtb.restfulapidesign.entity;

public enum Gender {
    MALE("male"), FEMALE("female");

    private String name;

    Gender(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
