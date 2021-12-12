package com.sweetspotrecipies.sweetspot.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BookDTO {
    @JsonProperty("id")
    private Integer id;

    @JsonProperty("name")
    private String name;
    public Integer getId() {
        return id;
    }
    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }
    @JsonProperty("name")
    public String getName() {
        return name;
    }
    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }
}

