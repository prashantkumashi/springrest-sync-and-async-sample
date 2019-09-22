package com.samples.pck.spring.rest.controllers;

import java.util.Date;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.samples.pck.spring.rest.entity.Useraudit; 
import com.samples.pck.spring.rest.entity.Users;
import com.samples.pck.spring.rest.service.UserauditService;
import com.samples.pck.spring.rest.service.UsersService;

@RestController
public class UsersController {
	
	@Autowired
	UsersService userService;
	
	@Autowired
	UserauditService auditService;
	
	@GetMapping(value="/user/{id}", produces= {"application/json", "application/xml"})
	public @ResponseBody ResponseEntity<Users> getUserById(@PathVariable("id") Long userId){
		ResponseEntity<Users> response = null;
		Users user = null;
		try {
		
			user = userService.getUserById(userId);
		
		}catch(NoSuchElementException nsee) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No such element found");
		}
//		catch(IllegalAccessException iaex) {
//			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Incorrect access of illegal access to the resource!");
//		}
		catch(Exception ex) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error. Please contact application administrator!");
		}
		
		response = new ResponseEntity<Users>(user, HttpStatus.OK);
		return response;		
	}
	
	@PostMapping(value = "/user", consumes = {"application/json", "application/xml"}, produces= {"application/json", "application/xml"})
	public @ResponseBody ResponseEntity<Users> addUser(@RequestBody Users user){
		ResponseEntity<Users> response = null;
		Users rUser = null;
		try {
		
			rUser = userService.addUser(user);
			Useraudit audit = new Useraudit();
			audit.setUserId(rUser.getUserId());
			audit.setStatus("CREATED");
			audit.setAddedOn(new Date());
			
			//Call Useraudit service async call for committing the audit record
			auditService.addUseraudit(audit);
			
//		}catch(NoSuchElementException nsee) {
//			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No such element found");
		}
		catch(IllegalAccessException iaex) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Incorrect access of illegal access to the resource!");
		}
		catch(Exception ex) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error. Please contact application administrator!");
		}
		
		response = new ResponseEntity<Users>(rUser, HttpStatus.OK);
		return response;		
	}
	
	@PutMapping(value = "/user/{id}", consumes= {"application/json", "application/xml"}, produces= {"application/json", "application/xml"})
	public @ResponseBody ResponseEntity<Users> updateUser(@PathVariable("id") Long userId, @RequestBody Users user){
		ResponseEntity<Users> response = null;
		Users rUser = null;
		try {
			
			Users persistedUser = userService.getUserById(userId);
			
			System.out.println("Input \n"+user);
			System.out.println("Persisted \n"+persistedUser);
			
			if(!userId.equals(user.getUserId())) {
				System.out.println("Checking input if path value of userid and the userid data passed as user json is same......");
				throw new IllegalAccessException("Illegal access!!");
			}else if(!user.getUsername().equals(persistedUser.getUsername())) {
				System.out.println("Checking input data value of username and the persisted data is different......");
				throw new IllegalAccessException("Illegal access!!");
			}
			
			Useraudit audit = new Useraudit();
			audit.setUserId(persistedUser.getUserId());
			audit.setStatus("MODIFIED");
			audit.setAddedOn(new Date());
			audit.setOldValue(persistedUser.getUserType());
			audit.setNewValue(user.getUserType());
			
			rUser = userService.updateUser(user);
			
			//Call Useraudit service async call for committing the audit record
			auditService.addUseraudit(audit);
			
		}catch(NoSuchElementException nsee) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No such element found");
		}
		catch(IllegalAccessException iaex) {
			iaex.printStackTrace();
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Illegal access to the resource!!");
		}
		catch(Exception ex) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error. Please contact application administrator!");
		}
		System.out.println("Updated User \n"+rUser);
		response = new ResponseEntity<Users>(rUser, HttpStatus.OK);
		return response;		
	}
	
}
