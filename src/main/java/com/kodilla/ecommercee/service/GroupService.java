package com.kodilla.ecommercee.service;
import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.exception.GroupNotFoundException;
import com.kodilla.ecommercee.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;

    public List<Group> getAllGroups() {
        return  groupRepository.findAll();
    }

    public Group getGroupById(Long id) throws GroupNotFoundException {
        return groupRepository.findById(id).orElseThrow(() -> new GroupNotFoundException(
                "Group update not found id: " + id));
    }

    public Group saveGroup(final Group group) {
        return groupRepository.save(group);
    }

    public void deleteGroup(Long id) {
        groupRepository.deleteById(id);
    }
    public Group updateGroup(final Group group, final Long id) throws GroupNotFoundException {
        Optional<Group> groupEntity = groupRepository.findById(id);
        Group groupForUpdate = groupEntity.orElseThrow(() -> new GroupNotFoundException(
                "Group update not found id: " + id));
        groupForUpdate.setName(group.getName());
        return groupRepository.save(groupForUpdate);
    }
}