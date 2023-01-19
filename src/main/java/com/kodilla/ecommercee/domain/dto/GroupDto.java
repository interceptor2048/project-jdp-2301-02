package com.kodilla.ecommercee.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class GroupDto {

    private Long id;
    private String name;

    public GroupDto() {
    }
}