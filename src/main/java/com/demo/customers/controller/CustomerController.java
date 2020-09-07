package com.demo.customers.controller;

import com.demo.customers.dto.CustomerDto;
import com.demo.customers.services.CustomerService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    /**
     *
     * @param request
     * @return CustomerDto
     */
    @PostMapping("/customer")
    @ApiOperation(value = "Index.", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The request has succeeded."),
            @ApiResponse(code = 400, message = "Bad Request, params invalid."),
            @ApiResponse(code = 500, message = "The server encountered an unexpected condition which prevented it from fulfilling the request.")
    })
    public CustomerDto postCustomer(@RequestBody CustomerDto request){

        CustomerDto response = customerService.setCustomerNew(request);

        System.out.println(response);
        return response;
    }

    /**
     *
     * @return List<CustomerDto>
     */
    @GetMapping("/customers")
    @ApiOperation(value = "Index.", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The request has succeeded."),
            @ApiResponse(code = 400, message = "Bad Request, params invalid."),
            @ApiResponse(code = 500, message = "The server encountered an unexpected condition which prevented it from fulfilling the request.")
    })
    public List<CustomerDto> getCustomer(){

        List<CustomerDto> response = customerService.getCustomerAll();

        return response;
    }

    /**
     *
     * @param age
     * @return List<CustomerDto>
     */
    @GetMapping("customers/age/{age}")
    @ApiOperation(value = "Index.", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The request has succeeded."),
            @ApiResponse(code = 400, message = "Bad Request, params invalid."),
            @ApiResponse(code = 500, message = "The server encountered an unexpected condition which prevented it from fulfilling the request.")
    })
    public List<CustomerDto> getFindByAge(@PathVariable int age){

        List<CustomerDto> response = customerService.findByAge(age);

        return response;
    }

    /**
     *
     * @param id
     * @return ResponseEntity<String>
     */
    @DeleteMapping("/customer/{id}")
    @ApiOperation(value = "Index.", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The request has succeeded."),
            @ApiResponse(code = 400, message = "Bad Request, params invalid."),
            @ApiResponse(code = 500, message = "The server encountered an unexpected condition which prevented it from fulfilling the request.")
    })
    public ResponseEntity<String> deleteCustomer(@PathVariable Long id ){

        customerService.getDeleteCustomer(id);

        return new ResponseEntity<>("Customer has been deleted!", HttpStatus.OK);
    }

    /**
     *
     * @param id
     * @param request
     * @return ResponseEntity<CustomerDto>
     */
    @PutMapping("/customer/{id}")
    @ApiOperation(value = "Index.", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The request has succeeded."),
            @ApiResponse(code = 400, message = "Bad Request, params invalid."),
            @ApiResponse(code = 500, message = "The server encountered an unexpected condition which prevented it from fulfilling the request.")
    })
    public ResponseEntity<CustomerDto> putCustomer(@PathVariable Long id, @RequestBody CustomerDto request){

        return customerService.updateCustomer(id, request);
    }
}
