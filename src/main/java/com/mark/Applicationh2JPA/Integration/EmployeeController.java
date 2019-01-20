package com.mark.Applicationh2JPA.Integration;

import com.mark.Applicationh2JPA.Service.Business.EmployeeService;
import org.hibernate.service.spi.InjectService;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

/**
 * Created by U.8902078 on 19/01/2019.
 */
@RestController
public class EmployeeController {

    @Inject
    private EmployeeService employeeService;

   // @RequestMapping("/getAll")
    @GetMapping("/hello-world")
    @ResponseBody
    public String getAllAcounts(){
        return "success";
    }

    @RequestMapping("/getAll")
    public String success(){
        return employeeService.getAllEmployees();
    }

    @PostMapping("/create")
    public String createEmployee(@RequestBody String employee){
        return employeeService.createEmployee(employee);
    }

    @PostMapping("/update/{id}")
    public void updateEmployee(@PathVariable("id") int employeeId, @RequestBody String update){
        employeeService.updateEmployee(employeeId, update);
    }


}
