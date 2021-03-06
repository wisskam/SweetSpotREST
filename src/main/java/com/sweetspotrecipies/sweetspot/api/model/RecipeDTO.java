package com.sweetspotrecipies.sweetspot.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class RecipeDTO {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("description")
    private String description;
    @JsonProperty("steps")
    private String steps;
    @JsonProperty("imageUrl")
    private String imageUrl;
    @JsonProperty("published")
    private Boolean published;
    @JsonProperty("modifiedAt")
    private Date modifiedAt;

    @JsonProperty("bookName")
    private String bookName;
    @JsonProperty("bookAuthorFirstName")
    private String bookAuthorFirstName;
    @JsonProperty("bookAuthorLastName")
    private String bookAuthorLastName;
    @JsonProperty("bookId")
    private Integer bookId;


    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getSteps() { return steps;}
    public void setSteps(String steps) { this.steps = steps; }

    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getBookName() {
        return bookName;
    }
    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookAuthorFirstName() {
        return bookAuthorFirstName;
    }
    public void setBookAuthorFirstName(String bookAuthorFirstName) {
        this.bookAuthorFirstName = bookAuthorFirstName;
    }

    public String getBookAuthorLastName() { return bookAuthorLastName; }
    public void setBookAuthorLastName(String bookAuthorLastName) { this.bookAuthorLastName = bookAuthorLastName; }

    public Integer getBookId() {
        return bookId;
    }
    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Boolean getPublished() { return published; }
    public void setPublished(Boolean published) { this.published = published; }

    public Date getModifiedAt() { return modifiedAt; }
    public void setModifiedAt(Date modifiedAt) { this.modifiedAt = modifiedAt; }

}
