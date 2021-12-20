package com.sweetspotrecipies.sweetspot.model;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="recipe")
public class Recipe implements java.io.Serializable{


    private Integer id;
    private String name;
    private String description;
    private String steps;
    private String imageUrl;
    private Book book;
//    private User author;
    private Set<Ingredient> ingredients = new HashSet<>();
    private Date createdAt;
    private Date modifiedAt;

    public Recipe(Integer id, String name, String description, String steps, String imageUrl, Book book, Date createdAt, Date modifiedAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.steps = steps;
        this.imageUrl = imageUrl;
        this.book = book;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public Recipe() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    @Lob
    @Column(name = "steps")
    public String getSteps() {
        return steps;
    }
    public void setSteps(String steps) {
        this.steps = steps;
    }

    @Column(name = "imageUrl")
    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "createdAt", length = 19)
    public Date getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modifiedAt", length = 19)
    public Date getModifiedAt() {
        return modifiedAt;
    }
    public void setModifiedAt(Date modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    @ManyToOne
    @JoinColumn(name = "book_id")
    public Book getBook() {
        return book;
    }
    public void setBook(Book book) {
        this.book = book;
    }

    @OneToMany(mappedBy = "recipe")
    public Set<Ingredient> getIngredients() {
        return ingredients;
    }
    public void setIngredients(Set<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", steps='" + steps + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", book=" + book +
                ", createdAt=" + createdAt +
                ", modifiedAt=" + modifiedAt +
                '}';
    }
}
