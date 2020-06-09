package com.springboot.Service;


import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.DomainModel.employee1;
import com.springboot.dao.springbootDao;
import com.springboot.model.Employee;



@Service
public class springbootService
{
	@Autowired
	springbootDao springbootdao;

	public ArrayList<employee1> getShowEmployeelistService() {
		System.out.println("I am in Show Employee List in Service....");
		java.util.List<Employee> emplist=springbootdao.getShowEmployeelistDao();
		ArrayList<employee1> emp=new ArrayList<>();
		for(Employee e:emplist)
		{
			emp.add(setEmployeelistData(e));
		}
		return emp ;
	}

	private employee1 setEmployeelistData(Employee e) 
	{
		employee1 emp=new employee1(e.getId(), e.getName(), e.getPhoneno(),e.getDepartment(), e.getStatus(), e.getCreateddtm(), e.getCreatedby(), e.getUpdateddtm(),e.getUpdateby(), e.getCountry().getCid(), e.getCountry().getCountryname());
		return emp;
	}

	
}
