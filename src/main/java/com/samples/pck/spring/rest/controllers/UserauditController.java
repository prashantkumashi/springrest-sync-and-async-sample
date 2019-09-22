package com.samples.pck.spring.rest.controllers;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.samples.pck.spring.rest.entity.Useraudit;
import com.samples.pck.spring.rest.service.UserauditService;

@RestController
public class UserauditController {
	
	@Autowired
	UserauditService auditService;
	
	@GetMapping(value="/useraudit", produces= {"application/json", "application/xml"})
	public @ResponseBody ResponseEntity<List<Useraudit>> getUserauditByUseId(@RequestParam("userId") Long userId){
		ResponseEntity<List<Useraudit>> response = null;
		List<Useraudit> audit = null;
		try {
		
			audit = auditService.getUserauditByUserId(userId);
		
		}catch(NoSuchElementException nsee) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No such element found");
		}
//		catch(IllegalAccessException iaex) {
//			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Incorrect access of illegal access to the resource!");
//		}
		catch(Exception ex) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error. Please contact application administrator!");
		}
		
		response = new ResponseEntity<List<Useraudit>>(audit, HttpStatus.OK);
		return response;		
	}
	
	@PostMapping(value = "/useraudit", consumes = {"application/json", "application/xml"}, produces= {"application/json", "application/xml"})
	public @ResponseBody ResponseEntity<Useraudit> addUser(@RequestBody Useraudit useraudit){
		ResponseEntity<Useraudit> response = null;
		Useraudit audit = null;
		try {
		
			audit = auditService.addUserauditInline(useraudit);
		
//		}catch(NoSuchElementException nsee) {
//			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No such element found");
		}
		catch(IllegalAccessException iaex) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Incorrect access of illegal access to the resource!");
		}
		catch(Exception ex) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error. Please contact application administrator!");
		}
		
		response = new ResponseEntity<Useraudit>(audit, HttpStatus.OK);
		return response;		
	}
	
}
