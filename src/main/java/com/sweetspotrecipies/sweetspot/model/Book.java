package com.sweetspotrecipies.sweetspot.model;
import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "book")
public class Book {
    private Integer id;
    private Date createdAt;
    private Date modifiedAt;
    private String name;
    private String subtitle;
    private String description;
    private String imageUrl;

    private Set<Recipe> recipes = new HashSet<>();
//    private User user;



    public Book(Date modified) {
        this.modifiedAt = modified;
    }
    public Book(Date created, Date modified, String name, String description, String subtitle, String imageUrl /*, int userID */) {
        this.createdAt = created;
        this.modifiedAt = modified;
        this.name = name;
        this.description    = description;
        this.subtitle       = subtitle;
        this.imageUrl       = imageUrl;
//        this.userID = userID;
    }

    public Book() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public Integer getId() {
        return this.id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "createdAt", length = 19)
    public Date getCreated() {
        return this.createdAt;
    }
    public void setCreated(Date created) {
        this.createdAt = created;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modifiedAt", nullable = false, length = 19)
    public Date getModified() {
        return this.modifiedAt;
    }
    public void setModified(Date modified) {
        this.modifiedAt = modified;
    }

    @Column(name = "name")
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "description")
    public String getDescription() {
        return this.description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "subtitle")
    public String getSubtitle() {        return subtitle;    }
    public void setSubtitle(String subtitle) {        this.subtitle = subtitle;    }

    @Column(name = "imageUrl")
    public String getImageUrl() {return imageUrl;}
    public void setImageUrl(String imageUrl) {this.imageUrl = imageUrl;}

    @OneToMany(mappedBy = "book")
    public Set<Recipe> getRecipes() {
        return recipes;
    }
    public void setRecipes(Set<Recipe> recipes) {
        this.recipes = recipes;
    }

//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    public User getUser() { return user; }
//    public void setUser(User user) { this.user = user; }
}
