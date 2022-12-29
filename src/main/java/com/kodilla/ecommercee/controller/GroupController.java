package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.GroupDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/v1/groups")
public class GroupController {

    @GetMapping
    public List<GroupDto> getAllGroups() {
        ArrayList<GroupDto> groups = new ArrayList<>();
        groups.add(new GroupDto(1L, "RTV"));
        return groups;
    }
}
