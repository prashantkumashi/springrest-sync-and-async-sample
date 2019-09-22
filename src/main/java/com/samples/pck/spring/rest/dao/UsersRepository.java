package com.samples.pck.spring.rest.dao;

import org.springframework.data.repository.CrudRepository;

import com.samples.pck.spring.rest.entity.Users;

public interface UsersRepository extends CrudRepository<Users, Long>{

}
