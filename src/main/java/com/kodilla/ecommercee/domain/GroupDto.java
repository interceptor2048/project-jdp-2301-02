package com.kodilla.ecommercee.domain;

public class GroupDto {
    private Long id;
    private String name;

    public GroupDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public GroupDto() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
