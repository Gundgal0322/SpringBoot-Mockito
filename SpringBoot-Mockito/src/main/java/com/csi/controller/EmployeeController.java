package com.csi.controller;

import com.csi.exception.RecordNotFoundException;
import com.csi.model.Employee;
import com.csi.service.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    EmployeeServiceImpl employeeServiceImpl;

    @PostMapping("/savedata")
    public ResponseEntity<String> saveData( @Valid @RequestBody Employee employee){
        employeeServiceImpl.saveData(employee);
        return ResponseEntity.ok("Data Inserted Successfully");
    }

    @GetMapping("/getdatabyid/{empId}")
    public Optional<Employee> getDataById(@PathVariable int empId){
      return  employeeServiceImpl.getDataById(empId);
    }

    @GetMapping("/getalldata")
    public ResponseEntity<List<Employee>> getAllData(){
        return ResponseEntity.ok(employeeServiceImpl.getAllData());
    }

    @PutMapping("/updatedata/{empId}")
    public ResponseEntity<Employee> updateData(@PathVariable int empId,@Valid @RequestBody Employee employee){

        Employee employee1 = employeeServiceImpl.getDataById(empId).orElseThrow(() -> new RecordNotFoundException("ID does not Exist"));

        employee1.setEmpName(employee.getEmpName());
        employee1.setEmpAddress(employee.getEmpAddress());
        employee1.setEmpDOB(employee.getEmpDOB());
        employee1.setEmpEmailId(employee.getEmpEmailId());

        return new ResponseEntity<>(employeeServiceImpl.updateData(employee1), HttpStatus.CREATED);
    }

    @DeleteMapping("/deletedatabyid/{empId}")
    public ResponseEntity<String> deleteDataById(@PathVariable int empId){
        employeeServiceImpl.deleteDataById(empId);
        return ResponseEntity.ok("Data Deleted Succesfully");

    }
}
