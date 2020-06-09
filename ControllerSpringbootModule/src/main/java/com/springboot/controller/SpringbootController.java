package com.springboot.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.DomainModel.employee1;
import com.springboot.Service.springbootService;

@RestController
@CrossOrigin
@RequestMapping("api")
@ComponentScan("com.springboot")
public class SpringbootController {

	@Autowired
	springbootService springbootservice;


	//Show Employee List Rest API 1 
	@GetMapping("showAllEmployeeList")
	public ResponseEntity<ArrayList<employee1>> getShowEmployeelistController()
	{
		System.out.println("I am in Show All Empoyeelist Controller...");
		ArrayList<employee1> emplist=springbootservice.getShowEmployeelistService();
		System.out.println(emplist);
		return new ResponseEntity<ArrayList<employee1>>(emplist,HttpStatus.OK);
	}
	
	/*
	// Show All Employee which have status Active OR Inactive "Rest API 2"
	@SuppressWarnings("unchecked")
	@GetMapping(value = "/status/{status}")
	public ResponseEntity<List<Employee>> statusemployeelist(@PathVariable("status") String status) {
		System.out.println("I am In Show Status.......");
		System.out.println("Status >> " + status);
		Session session = sf.openSession();
		Criteria criteria = session.createCriteria(Employee.class);
		criteria.add(Restrictions.eq("status", status));
		List<Employee> employeelist = criteria.list();
		return new ResponseEntity<List<Employee>>(employeelist, HttpStatus.OK);
	}

	// Show All Employee which have id "Rest API 3"
	@SuppressWarnings("unchecked")
	@GetMapping(value = "{id}")
	public ResponseEntity<List<Employee>> idemployeelist(@PathVariable("id") int id) {
		System.out.println("I am In Show employee by id.......");
		System.out.println("Status >> " + id);
		Session session = sf.openSession();
		Criteria criteria = session.createCriteria(Employee.class);
		criteria.add(Restrictions.eq("id", id));
		List<Employee> employeelist = criteria.list();
		return new ResponseEntity<List<Employee>>(employeelist, HttpStatus.OK);
	}

	// Show All Employee which have name "Rest API 4"
	@SuppressWarnings("unchecked")
	@GetMapping(value = "/showemployeesByname/{name}")
	public ResponseEntity<List<Employee>> idemployeelist(@PathVariable("name") String name) {
		System.out.println("I am In Show employee by name.......");
		System.out.println("Status >> " + name);
		Session session = sf.openSession();
		Criteria criteria = session.createCriteria(Employee.class);
		criteria.add(Restrictions.eq("name", name));
		List<Employee> employeelist = criteria.list();
		System.out.println(employeelist);
		return new ResponseEntity<List<Employee>>(employeelist, HttpStatus.OK);
	}

	// Show All Employee which have date today "Rest API 5"
	@SuppressWarnings("unchecked")
	@GetMapping(value = "/showemployeesBeforeToday")
	public ResponseEntity<List<Employee>> todayemployeelist() throws ParseException {
		System.out.println("I am In Show employee before today.......");
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		Date dateobj = new Date();
		String date=df.format(dateobj);
		System.out.println("fdte >> " + date);
		Session session = sf.openSession();
		Criteria criteria = session.createCriteria(Employee.class);
		criteria.add(Restrictions.le("createddtm", date));
		List<Employee> employeelist = criteria.list();
		System.out.println(employeelist);
		return new ResponseEntity<List<Employee>>(employeelist, HttpStatus.OK);
	}

	
	 //add Employee"Rest API 5"
	 @PostMapping(value = "/addemployee")
	 public ResponseEntity<String> addemployee(@RequestBody employee1 employee1) 
	 { 
		System.out.println("I am in Add employee......");
		System.out.println(employee1);
		Country country=new Country(employee1.getCid(),employee1.getCountryname());
		Employee employee=new Employee(country, employee1.getName(), employee1.getPhoneno(), employee1.getDepartment(), employee1.getStatus(), employee1.getCreateddtm(), employee1.getCreatedby(),employee1.getUpdateddtm(), employee1.getUpdatedby());
		Session session=sf.openSession();
		Transaction tx=session.beginTransaction();
		session.save(employee);
		tx.commit();
		return new ResponseEntity<String>("Employee added in db successfully", HttpStatus.OK); 
	  }
	  
	 //add Country "Rest API 6"
	 @PostMapping(value = "/addcountry")
	 public ResponseEntity<String> addcountry(@RequestBody employee1 employee) 
	 { 
		 System.out.println("I am In Add country.......");
		System.out.println(employee);
		Country country=new Country(employee.getCountryname());
		Session session=sf.openSession();
		Transaction tx=session.beginTransaction();
		session.save(country);
		tx.commit();
		return new ResponseEntity<String>("Country added in db Successfully", HttpStatus.OK);
	 }
	  
	 //updateCountry "Rest API 6"
	 @PutMapping(value = "/updatecountry") 
	 public ResponseEntity<String> updatecountry(@RequestBody employee1 employee) 
	 {
		 System.out.println("I am In update country.......");
		System.out.println(employee); 
		Session session=sf.openSession();
		Transaction tx=session.beginTransaction();
		Country country=session.load(Country.class, employee.getCid());
		country.setCountryname(employee.getCountryname());
		session.saveOrUpdate(country);
		tx.commit();
		 return new ResponseEntity<String>("Country updated Successfully",HttpStatus.OK); 
	 }
	  
	 //delete Country by country name"Rest API 8"
	  
	 @SuppressWarnings("unchecked")
	@DeleteMapping(value = "deletecountry") 
	 public ResponseEntity<String> deletecountry(@RequestBody employee1 employee) 
	 { 
		 System.out.println("I am In Delete Country.......");
		 System.out.println(employee.getCountryname()); 
		 Session session = sf.openSession();
		 Criteria criteria = session.createCriteria(Country.class);
		 criteria.add(Restrictions.eq("countryname", employee.getCountryname()));
		 List<Country> countrylist = criteria.list();
		 for(Country country:countrylist)
		 {
			 Transaction tx=session.beginTransaction();
			 session.delete(country);
			 tx.commit();
		 }
		 return new ResponseEntity<String>("Country deleted Successfully",HttpStatus.OK); 
	} 
	 
	//delete employee by id name"Rest API 9"
	 
	 @SuppressWarnings("unchecked")
	@DeleteMapping(value = "/deleteemployee/{id}") 
	 public ResponseEntity<String> deleteemployee(@PathVariable("id") int id) 
	 { 
		 System.out.println("I am In Delete employee by id.......");
		 System.out.println(id); 
		 Session session = sf.openSession();
		 Criteria criteria = session.createCriteria(Employee.class);
		 criteria.add(Restrictions.eq("id",id));
		 List<Employee> countrylist = criteria.list();
		 for(Employee employee:countrylist)
		 {
			 Transaction tx=session.beginTransaction();
			 session.delete(employee);
			 tx.commit();
		 }
		 return new ResponseEntity<String>("Employee deleted Successfully",HttpStatus.OK); 
	  }
	// Show All Employee "Rest API 1"
		@SuppressWarnings("unchecked")
		@GetMapping("/setEmployee/{emp}")
		public List<Employee> setemployeelist(@PathVariable("emp") int emp) 
		{
			System.out.println("I am In Set Employee List..... >> "+emp);
			Session session = sf.openSession();
			Criteria criteria = session.createCriteria(Employee.class);
			criteria.add(Restrictions.eq("id", emp));
			List<Employee> employeelist = criteria.list();
			System.out.println(employeelist);
			return employeelist;
		}
 

	*/
}
