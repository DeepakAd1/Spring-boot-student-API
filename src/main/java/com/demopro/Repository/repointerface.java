package com.demopro.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.demopro.Student;
@Component
public interface repointerface extends JpaRepository<Student,Integer>{

	public List<Student> findByName(String name);

	public List<Student> findByDept(String d);

}
