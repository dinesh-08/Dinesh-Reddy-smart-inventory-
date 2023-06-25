package com.mybootapp.main.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mybootapp.main.dto.GodownDto;
import com.mybootapp.main.model.Godown;
import com.mybootapp.main.model.Manager;
import com.mybootapp.main.service.GodownService;
import com.mybootapp.main.service.ManagerService;

@RestController
@RequestMapping("/godown")
public class GodownController {
	@Autowired
	private GodownService godownservice;
	@Autowired
	private ManagerService managerservice;
	@PostMapping("/add/{managerId}")
	public ResponseEntity<?> postgodown(@PathVariable("managerId") int managerId,@RequestBody Godown godown)
	{
		
		Manager manager=managerservice.getById(managerId);
		if(manager==null)
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("invalid id");
		}
		
		 godown.setManager(manager);
		 godown= godownservice.insert(godown);
		 return ResponseEntity.status(HttpStatus.OK).body(godown);
	}
	@GetMapping("/all")
	public List<Godown> getAllgodowns()
	{
		List<Godown> list=godownservice.getAllgodowns();
		return list;
	} 
	@GetMapping("/one/{id}")
	public ResponseEntity<?> getgodownid(@PathVariable("id") int id)
	{
		
		Godown godown=godownservice.getgodown(id);
		if(godown==null)
		{
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("invalid id");
		}
		return ResponseEntity.status(HttpStatus.OK).body(godown);
		
	}
	@PutMapping("/update/{managerId}/{id}")
	public ResponseEntity<?> updategodown(@PathVariable("id") int id,
			@PathVariable("managerId") int managerId,@RequestBody Godown newgodown)
	{
		
		Manager manager=managerservice.getById(managerId);
		if(manager==null)
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("invalid id");
		}
		
		
	  Godown oldgodown=godownservice.getgodown(id);
	  if(oldgodown==null)
	  {
		  return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("invalid");
		  
	  }
	  newgodown.setManager(manager);
	  newgodown.setId(oldgodown.getId());
	  newgodown=godownservice.insert(newgodown);
	  return ResponseEntity.status(HttpStatus.OK).body(newgodown);
	  }
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deletegodown(@PathVariable("id") int id)
	{  
		
		Godown godown=godownservice.getgodown(id);
		if(godown==null)
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("invalid");
		}
		godownservice.deletegodown(godown);
		return ResponseEntity.status(HttpStatus.OK).body("godown deleted");
	}
	
	/// report api 4
//	@GetMapping("/report")
//    public List<GodownDto> godownReport() {
//        
//        List<Godown> list = godownservice.getAll();
//        List<GodownDto> listDto = new ArrayList<>();
//        /* convert the response into UI format */
//         list.stream().forEach(entry->{
//             GodownDto dto = new GodownDto(); //100X 200
//             dto.setGodownId(entry.getId());
//            dto.setGodownCapacity(entry.getCapacity());
//            dto.setGodownLocation(entry.getLocation());
//            dto.setGodownManager(entry.getManager().getName());
//             listDto.add(dto); //100X 200X
//         });
//
//        return listDto;
//	}
	@GetMapping("/report")
    public List<Godown> godownReport() {
        return godownservice.getAll();
    }

}
