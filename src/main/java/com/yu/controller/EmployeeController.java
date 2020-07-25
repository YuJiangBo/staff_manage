package com.yu.controller;

import com.yu.dao.DepartmentDao;
import com.yu.dao.EmployeeDao;
import com.yu.pojo.Department;
import com.yu.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
//        System.out.println(employees);
        return "emp/list";
    }

    @GetMapping("/emp")
    public String toAddPage(Model model){
        Collection<Department> departments = departmentDao.getDepartment();
        model.addAttribute("depts",departments);
        return "emp/add";
    }

//    @PostMapping("/emp")
//    public String addEmp(Employee employee){
//        System.out.println("/emp:"+employee);
//        employeeDao.save(employee);
//        return "redirect:/emps";
//    }
    @PostMapping("/emp")
    public String addEmp(){
        return "redirect:/emps";
    }

//    update
    @GetMapping("/emp/{id}")
    public String toUpdatePage(@PathVariable("id") Integer id, Model model){
        //获取所有部门
        Collection<Department> departments = departmentDao.getDepartment();
        model.addAttribute("dep",departments);
        //获取对应id的员工
        Employee employee = employeeDao.getEmployee(id);
        model.addAttribute("emp",employee);
        //获取该员工对应的部门
        Department depOne = departmentDao.getDepartment(employee.getDepartment().getId());
        model.addAttribute("depone",depOne);
        return "emp/update";
    }

    @PostMapping("/updateEmp")
    public String update(Employee employee){
//        System.out.println(employee);
//        System.out.println(employee.getDepartment().getId());
//        Integer id = employee.getDepartment().getId();
//        String departmentName = departmentDao.getDepartment(employee.getDepartment().getId()).getDepartmentName();
//        employee.getDepartment().setDepartmentName(departmentName);
//        employeeDao.save(employee);
//        System.out.println(employee);
//        System.out.println(employeeDao.getEmployees());
        return "redirect:/emps";
    }

    //    删除
    @GetMapping("/delemp/{id}")
    public String deleteEmp(@PathVariable("id") Integer id){
        employeeDao.delete(id);
        return "redirect:/emps";
    }
}
