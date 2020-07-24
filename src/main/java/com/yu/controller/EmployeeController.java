package com.yu.controller;

import com.yu.dao.DepartmentDao;
import com.yu.dao.EmployeeDao;
import com.yu.pojo.Department;
import com.yu.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeDao employeeDao;
    @Autowired
    private DepartmentDao departmentDao;

    @RequestMapping("/emps")
    public String list(Model model){
        Collection<Employee> employees = employeeDao.getEmployees();
        model.addAttribute("emps",employees);
        return "emp/list";
    }

    @GetMapping("/toadd")
    public String toAddPage(Model model){
        Collection<Department> departments = departmentDao.getDepartment();
        model.addAttribute("depts",departments);
        return "emp/add";
    }

    @PostMapping("/emp")
    public String addEmp(Employee employee){
        employeeDao.save(employee);
        return "redirect:/emps";
    }
}
