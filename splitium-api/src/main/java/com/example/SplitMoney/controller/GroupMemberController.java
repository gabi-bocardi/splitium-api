package com.example.SplitMoney.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.SplitMoney.model.Group;
import com.example.SplitMoney.model.GroupMember;
import com.example.SplitMoney.model.GroupMemberRepository;
import com.example.SplitMoney.model.GroupRepository;
import com.example.SplitMoney.model.User;
import com.example.SplitMoney.model.UserRepository;
import com.example.SplitMoney.request.NewMember;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class GroupMemberController {

	@Autowired
	private UserRepository userRepository;
    @Autowired
    private GroupRepository groupRepository;
	@Autowired
	private GroupMemberRepository groupMemberRepository;

	@PostMapping("/member")
	public ResponseEntity<GroupMember> createGroupMember(@RequestBody NewMember newMember) {
		Optional<Group> group = groupRepository.findById(newMember.groupId);
		Optional<User> user = userRepository.findByUsername(newMember.username);
		
		GroupMember groupMember = new GroupMember(user.get(), group.get(), newMember.balance);
		
		GroupMember savedGroupMember = groupMemberRepository.save(groupMember);
		return new ResponseEntity<>(savedGroupMember, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/member/{id}")
	public ResponseEntity<HttpStatus> deleteMember(@PathVariable("id") Long id) {
        try {
        	groupMemberRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	

}
