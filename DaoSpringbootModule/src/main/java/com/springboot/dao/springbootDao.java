package com.springboot.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springboot.model.Employee;

@Repository
public class springbootDao {

	@Autowired
	SessionFactory sf;
	

	
	@SuppressWarnings("unchecked")
	public List<Employee> getShowEmployeelistDao() {
		System.out.println("I am in Show Employee List Dao....");
		Session session=sf.openSession();
		Criteria criteria=session.createCriteria(Employee.class);
		List<Employee> list=criteria.list();
		System.out.println("Emp list >> "+list);
		return list;
	}

	

	
}
