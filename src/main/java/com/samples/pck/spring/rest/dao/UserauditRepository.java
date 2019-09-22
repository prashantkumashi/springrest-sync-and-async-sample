package com.samples.pck.spring.rest.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.samples.pck.spring.rest.entity.Useraudit;

public interface UserauditRepository extends CrudRepository<Useraudit, Long>{
    List<Useraudit> findByUserId(Long userId);
}
