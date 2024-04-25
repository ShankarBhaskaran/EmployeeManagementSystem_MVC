package com.jsp.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.Dto.Employee;
import com.mysql.cj.Query;

@Repository
public class EmployeDao{

	@Autowired
	EntityManager manager;
	
	@Autowired
	EntityTransaction transaction;
	
	//to insert employee object in db
	public void saveEmployee(Employee employee)
	{
		transaction.begin();
		manager.persist(employee);
		transaction.commit();
	}
	
	//to delete an employee
	public void deleteEmployee(int id)
	{
		Employee e =manager.find(Employee.class, id);
		if(e !=null)
		{
			transaction.begin();
			manager.remove(e);
			transaction.commit();
		}
	}
	
	//to update employee details
	public void updateEmployee(Employee updateEmployee)
	{
		transaction.begin();
		manager.merge(updateEmployee);
		transaction.commit();
	}
	
	//to get all employee details
	public List<Employee> getAllEmployee()
	{
		javax.persistence.Query q=manager.createQuery("select e from Employee e");
		List <Employee> employee=q.getResultList();
		return employee;
	}
	
	public Employee findById(int id)
	{
		javax.persistence.Query q=manager.createQuery("select e from Employee e where e.id=?1");
		q.setParameter(1, id);
		List<Employee> e=q.getResultList();
		return e.get(0);
	}
	
}