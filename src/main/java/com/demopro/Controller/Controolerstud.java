package com.demopro.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.demopro.Student;
import com.demopro.Repository.repointerface;

@RestController
public class Controolerstud {
//	@GetMapping("/s")
//	public String get() {
//        return "Hi";
//	}
	@Autowired
	repointerface repo;
	@PostMapping("/student")
	public ResponseEntity<String> poststud(@RequestBody Student st) {
		repo.save(st);
		return ResponseEntity.status(HttpStatus.CREATED).body("successfully posted the Student data");
		
	}
	@GetMapping("/students")
	public List<Student> getall(){
		return repo.findAll();		
	}
	
	@GetMapping("/studentid/{id}")
	public Student getstud(@PathVariable("id") int id) {
		Student st=repo.findById(id).orElse(null);
		return st;		
	}
	@GetMapping("/studentname/{name}")
	public List<Student> getstudbyname(@PathVariable("name") String name ) {
		List<Student> li= repo.findByName(name);
		return li;
	}
	
	@GetMapping("/studentdept/{dept}")
	public List<Student> getstudbydept(@PathVariable("dept") String d) {
		return repo.findByDept(d);
	}
	
	@DeleteMapping("/deletestud/{id}")
	public String deletebyid(@PathVariable("id") int id) {
		repo.deleteById(id);
		return "Deleted the Student id : "+id;
		
	}
	
	@PutMapping("/updatestud")
	public String updated(@RequestBody Student st) {
		Student s=repo.findById(st.getId()).orElse(null);
		if(s!=null) {
			if(!st.getName().equalsIgnoreCase(s.getName())) {
				s.setName(st.getName());
			}
			if(!st.getDept().equalsIgnoreCase(s.getDept())) {
				s.setDept(st.getDept());
			}
			 repo.save(s);
			 return "Successfully updated Student id : "+st.getId();
			
		}
		else {
			return "No id value is matched with "+st.getId();
		}
	
	}
	}
