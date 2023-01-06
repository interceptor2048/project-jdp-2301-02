package com.kodilla.ecommercee;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/group")
@CrossOrigin("*")
public class GroupController {
    @GetMapping
    public ResponseEntity<List<GroupDto>> getGroupList() {
        return ResponseEntity.ok(new ArrayList<>());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GroupDto> addGroup(@RequestBody GroupDto groupDto) {
        return ResponseEntity.ok(new GroupDto(2L, "Ubrania"));
    }

    @GetMapping(value = "{groupId}")
    public ResponseEntity<GroupDto> getGroupById(@PathVariable Long groupId) {
        return ResponseEntity.ok(new GroupDto(4L, "Dodatki"));
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GroupDto> updateGroup(@RequestBody GroupDto groupDto) {
        return ResponseEntity.ok(new GroupDto(4L, "Najnowsze dodatki"));
    }
}
