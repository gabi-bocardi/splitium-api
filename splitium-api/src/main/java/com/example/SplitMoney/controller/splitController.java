package com.example.SplitMoney.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.SplitMoney.model.Group;
import com.example.SplitMoney.model.GroupMember;
import com.example.SplitMoney.model.GroupRepository;
import com.example.SplitMoney.model.Payment;
import com.example.SplitMoney.model.PaymentRepository;
import com.example.SplitMoney.model.User;
import com.example.SplitMoney.model.UserRepository;
import com.example.SplitMoney.model.memberRepository;
import com.example.SplitMoney.request.NewGroup;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class splitController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private memberRepository memberRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    // USER REST APIs
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userRepository.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }


    @GetMapping("/users/{id}") //used
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/users") //used
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User newUser = userRepository.save(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Long id, @RequestBody User user) {
        Optional<User> userData = userRepository.findById(id);

        if (userData.isPresent()) {
            User updatedUser = userData.get();
            updatedUser.setName(user.getName());
            updatedUser.setUsername(user.getUsername());
            updatedUser.setPassword(user.getPassword());
            return new ResponseEntity<>(userRepository.save(updatedUser), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") Long id) {
        try {
            userRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // GROUP REST APIs
    @GetMapping("/groups") 
    public ResponseEntity<List<Group>> getAllGroups() {
        List<Group> groups = groupRepository.findAll();
        return new ResponseEntity<>(groups, HttpStatus.OK);
    }

    @GetMapping("/groups/{id}") //used
    public ResponseEntity<Group> getGroupById(@PathVariable("id") Long id) {
        Optional<Group> group = groupRepository.findById(id);
        if (group.isPresent()) {
            return new ResponseEntity<>(group.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/groups")
    public ResponseEntity<Group> createGroup(@RequestBody NewGroup group) {
    	Group newGroup = new Group(group.name, group.total,group.description);
        Group savedGroup = groupRepository.save(newGroup);
        return new ResponseEntity<>(savedGroup, HttpStatus.CREATED);
    }

    @PutMapping("/groups/{id}")
    public ResponseEntity<Group> updateGroup(@PathVariable("id") Long id, @RequestBody Group group) {
        Optional<Group> groupData = groupRepository.findById(id);

        if (groupData.isPresent()) {
            Group updatedGroup = groupData.get();
            updatedGroup.setName(group.getName());
            return new ResponseEntity<>(groupRepository.save(updatedGroup), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/groups/{id}")
    public ResponseEntity<HttpStatus> deleteGroup(@PathVariable("id") Long id) {
        try {
        	Optional <Group> group = groupRepository.findById(id);
        	if(!group.isPresent()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        	}
        	
        	List <Payment> payments = paymentRepository.findByGroup(group.get());
        	paymentRepository.deleteAllInBatch(payments);
        	groupRepository.deleteById(id);
        	
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/users/{id}/groups") //used
    public ResponseEntity<List<Group>> getGroupByUserId(@PathVariable("id") Long id){
    	try {

    		Optional<User> userdata = userRepository.findById(id);
    		List<Group> groups = new ArrayList<>();

    		if(userdata.isPresent()) {


    			for(GroupMember gpmember: userdata.get().getGroupMember()) {

    				groups.add(gpmember.getGroup());

    			}

    			return new ResponseEntity<>(groups, HttpStatus.OK);
    		}else {
    			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    		}



    	}catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

