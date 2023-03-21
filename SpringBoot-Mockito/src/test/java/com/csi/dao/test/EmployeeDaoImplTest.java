package com.csi.dao.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.csi.controller.EmployeeController;
import com.csi.dao.EmployeeDaoImpl;
import com.csi.model.Employee;
import com.csi.repo.EmployeeRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootTest
@RunWith(SpringRunner.class)
public class EmployeeDaoImplTest {

    @Autowired
    EmployeeDaoImpl employeeDaoImpl;

    @MockBean
    EmployeeRepo employeeRepoImpl;

    @Test
    public void saveData(){

        Employee employee = new Employee(121, "Tejas", "NSK", new Date(), "tej@gmil.com");
        employeeDaoImpl.saveData(employee);
        verify(employeeRepoImpl, times(1)).save(employee);

    }

    @Test
    public void getallData(){

        when(employeeRepoImpl.findAll()).thenReturn( Stream.of(new Employee(121, "Tejas", "Pune", new Date(), "tej@gmail.com")
        ,new Employee(122, "abc", "Pundhde", new Date(), "tej@gmail.com")
        ,new Employee(123, "cgf", "Pdgdune", new Date(), "trtej@gmail.com")
        ,new Employee(124, "Tejjas", "Pdgdune", new Date(), "jrej@gmail.com")
        ,new Employee(125, "gerg", "Prtune", new Date(), "tertj@gmail.com")
        ,new Employee(126, "dgdg", "Pungre", new Date(), "trttej@gmail.com")).collect(Collectors.toList()));

        assertEquals(6, employeeDaoImpl.getAllData().size());
    }

    @Test
    public void updateData(){

        Employee employee = new Employee(121, "Tejas", "NSK", new Date(), "tej@gmil.com");
        employeeDaoImpl.updateData(employee);
        verify(employeeRepoImpl, times(1)).save(employee);
    }

    @Test
    public void deleteDataById(){

        Employee employee = new Employee(121, "Tejas", "NSK", new Date(), "tej@gmil.com");
        employeeDaoImpl.deleteDataById(employee.getEmpId());
        verify(employeeRepoImpl, times(1)).deleteById(employee.getEmpId());

    }

}
