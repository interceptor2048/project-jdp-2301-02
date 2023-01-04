package com.kodilla.ecommercee;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class GenericEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String stringValue;

    public GenericEntity() {
    }

    public String getStringValue() {
        return stringValue;
    }

    public Long getId() {

        return id;
    }

    public GenericEntity(String stringValue) {

        this.stringValue = stringValue;
    }
}
