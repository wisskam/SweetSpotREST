package com.sweetspotrecipies.sweetspot.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="ingredient")
public class Ingredient implements java.io.Serializable{
    private Integer id;
    private String name;
    private double amount;
    private String unit;
    private Recipe recipe;
    private Date createdAt;

    public Ingredient(Integer id, String name, double amount, Recipe recipe, Date createdAt) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.recipe = recipe;
        this.createdAt = createdAt;
    }

    public Ingredient() {
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

    @Column(name = "amount")
    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Column(name = "unit")
    public String getUnit() {
        return unit;
    }
    public void setUnit(String unit) {
        this.unit = unit;
    }

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    public Recipe getRecipe() {
        return recipe;
    }
    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "createdAt", length = 19)
    public Date getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }



}
