package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.Alien;
import com.example.demo.repo.ARepo;

@RestController       //@Controller + @ResponseBody//
public class AController
{
@Autowired 
ARepo repo;	
	
@RequestMapping("/")
public String home() 
{
	return "index.jsp";
}

//BY VIEW PAGE SAVE DATA IN DATABASE//

//@RequestMapping("/addAlien")
//public String addAlien(Alien al)
//{
//	repo.save(al);//save data in database
//	return "index.jsp";
//}


//BY POSTMAN SAVE DATA IN DATABASE//
@PostMapping("/alien")
public Alien addAlien(@RequestBody Alien al)
{
	repo.save(al);
	return al;
}


@GetMapping("/aliens")
//@ResponseBody
public List<Alien> getAliens()
{
return repo.findAll();	
}

//Delete Data
@DeleteMapping("/alien/{aid}")
public String deleteAlien(@PathVariable int aid)
{
	Alien a=repo.getOne(aid);
	repo.delete(a);
	return "deleted";
}

//Update Data
@PutMapping(path="/alien",consumes= {"application/json"})
public Alien saveOrUpdateAlien(@RequestBody Alien al)
{
	repo.save(al);
	return al;
}


}
