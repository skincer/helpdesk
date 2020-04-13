package com.sck.helpdesk.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class SearchResult {

    public enum ResultType {
        TICKET,
        MESSAGE,
        CATEGORY,
        USER
    }

    private String name;

    @Enumerated(EnumType.STRING)
    private ResultType type;

    private String location;

    public SearchResult(String name, ResultType type, String location) {
        this.name = name;
        this.type = type;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ResultType getType() {
        return type;
    }

    public void setType(ResultType type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
