package com.yu.dao;

import com.yu.pojo.Department;
import com.yu.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class EmployeeDao {

    private static Map<Integer, Employee> employees = null;
    @Autowired
    private DepartmentDao departmentDao;

    static {
        employees = new HashMap<Integer, Employee>();
        employees.put(1001,new Employee(1001,"AA","A123456@qq.com",1,new Department(101,"教学部")) );
        employees.put(1002,new Employee(1002,"BB","B123456@qq.com",1,new Department(102,"市场部")) );
        employees.put(1003,new Employee(1003,"CC","C123456@qq.com",1,new Department(103,"教研部")) );
        employees.put(1004,new Employee(1004,"DD","D123456@qq.com",1,new Department(104,"运营部")) );
        employees.put(1005,new Employee(1005,"EE","E123456@qq.com",1,new Department(105,"后勤部")) );
    }

//    主键自增
    private static Integer initId = 1006;

//    增加员工
    public void save(Employee employee){
        if(employee.getId() == null){
            employee.setId(initId++);
        }
        employee.setDepartment(departmentDao.getDepartment(employee.getId()));
        employees.put(employee.getId(),employee);
    }

//  查询全部信息
    public Collection<Employee> getEmployees(){
        return employees.values();
    }

//    通过id查询员工
    public Employee getEmployee(Integer id){
        return employees.get(id);
    }

//    删
    public void delete(Integer id){
        employees.remove(id);
    }

    public void update(Integer id,Employee employee){
        employees.replace(id,employees.get(id),employee);
    }
}
