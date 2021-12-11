package com.sweetspotrecipies.sweetspot.model;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "book")
public class Book {
    private Integer id;
    private Date createdAt;
    private Date modifiedAt;
    private String name;
    private int userID;


    public Book(Date modified) {
        this.modifiedAt = modified;
    }
    public Book(Date created, Date modified, String firstName, int userID) {
        this.createdAt = created;
        this.modifiedAt = modified;
        this.name = firstName;
        this.userID = userID;
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
}
