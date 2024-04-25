package com.jsp.Controller;

import java.util.List;

import org.hibernate.query.criteria.internal.expression.function.AggregationFunction.MAX;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.jsp.DAO.EmployeDao;
import com.jsp.Dto.Employee;

@RestController
public class EmployeController {

	@Autowired
	EmployeDao dao;
	
	//API to insert employee object to db
	@RequestMapping("/insert")
	public ModelAndView getEmployee()
	{
		ModelAndView mv =new ModelAndView();
		mv.addObject("employee", new Employee());
		mv.setViewName("index1.jsp");
		return mv;
	}
	
	@RequestMapping("/save")
	public ModelAndView saveEmployee(@ModelAttribute Employee employee)
	{
		dao.saveEmployee(employee);
		ModelAndView v= new ModelAndView();
		v.setViewName("index.jsp");
		return v;
	}
	
	//API for searching an object in db based on id
	@RequestMapping("/search")
	public ModelAndView searchEmployee()
	{
		ModelAndView mv= new ModelAndView();
		mv.addObject("employee", new Employee());
		mv.setViewName("get.jsp");
		return mv;
	}
	
	@RequestMapping("/display")
	public ModelAndView findEmployee(@ModelAttribute Employee employee)
	{
		ModelAndView mv=new ModelAndView();
		Employee emp= dao.findById(employee.getId());
		mv.addObject("employee", emp);
		mv.setViewName("display.jsp");
		return mv;
	}
	
	@RequestMapping("/delete")
	public ModelAndView deleteEmployee()
	{
		ModelAndView mv=new ModelAndView();
		mv.addObject("employee", new Employee());
		mv.setViewName("delete.jsp");
		return mv;
	}
	
	@RequestMapping("/remove")
	public String removeEmployee(@ModelAttribute Employee employee)
	{
		dao.deleteEmployee(employee.getId());
		return "employee details deleted successfully";
	}
	
	@RequestMapping("/displayall")
	public ModelAndView displayAllEmployee()
	{
		ModelAndView mv= new ModelAndView();
		List<Employee> employee=dao.getAllEmployee();
		mv.addObject("employeelist", employee);
		mv.setViewName("displayAll.jsp");
		return mv;
	}
	
	//API to update employee details in db
	@RequestMapping("/update")
	public ModelAndView updateEmployee()
	{
		ModelAndView mv= new ModelAndView();
		mv.addObject("employee", new Employee());
		mv.setViewName("update.jsp");
		return mv;
	}
	
	@RequestMapping("/modify")
	public String modifyEmployeeDetails(@ModelAttribute Employee employee)
	{
	  dao.updateEmployee(employee);	
	  return "EMPLOYEE DETAILS UPDATE SUCCESSFULLY";
	}
}

