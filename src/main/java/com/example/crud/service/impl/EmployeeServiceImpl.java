package com.example.crud.service.impl;

import com.example.crud.exception.ResourceNotFoundException;
import com.example.crud.model.Employee;
import com.example.crud.repository.EmployeeRepository;
import com.example.crud.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(long id) {
//        Optional<Employee>employee=employeeRepository.findById(id);
//        if(employee.isPresent()){
//            return employee.get();
//        }
//        else {
//            throw new ResourceNotFoundException("Employee", "Id", id);
//        }

        //Using lambda expression
        return  employeeRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException("Employee", "Id", id));
    }

    @Override
    public Employee updateEmployee(Employee employee, long id) {
        //we need to check whether employee with given id is exist in DB or not
        Employee existEmployee= employeeRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Employee", "Id", id));

        existEmployee.setFirstName(employee.getFirstName());
        existEmployee.setLastName(employee.getLastName());
        existEmployee.setEmail(employee.getEmail());
        //save existing employee to DB
        employeeRepository.save(existEmployee);
        return existEmployee;
    }

    @Override
    public void deleteEmployee(long id) {
        //we need to check whether employee with given id is exist in DB or not
       employeeRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee", "Id", id));
       employeeRepository.deleteById(id);
    }
}
