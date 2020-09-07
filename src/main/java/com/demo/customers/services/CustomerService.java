package com.demo.customers.services;

import com.demo.customers.dto.CustomerDto;
import com.demo.customers.model.entity.CustomerEntity;
import com.demo.customers.model.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository repo;

    /**
     *
     * @param customerDto
     * @return
     */
    public CustomerDto setCustomerNew(CustomerDto customerDto){
        CustomerEntity customer = repo.save(new CustomerEntity(customerDto.getName(), customerDto.getAge()));
        CustomerDto rs = CustomerDto.builder()
                .id(customer.getId())
                .name(customer.getName())
                .age(customer.getAge())
                .active(customer.isActive())
                .build();

        return rs;
    }

    /**
     *
     * @return
     */
    public List<CustomerDto> getCustomerAll(){
        List<CustomerEntity> custm = repo.findAll();
        List<CustomerDto> rs = new ArrayList();

        for(CustomerEntity item : custm){
            CustomerDto customer = CustomerDto.builder()
                    .id(item.getId())
                    .name(item.getName())
                    .age(item.getAge())
                    .active(item.isActive())
                    .build();

            rs.add(customer);
        }

        return rs;
    }

    /**
     *
     * @param age
     * @return
     */
    public List<CustomerDto> findByAge(int age){
        List<CustomerEntity> customerEntities = repo.findByAge(age);

        List<CustomerDto> rs = new ArrayList<>();

        for(CustomerEntity i : customerEntities){
            CustomerDto customer = CustomerDto.builder()
                    .name(i.getName())
                    .age(i.getAge())
                    .active(i.isActive())
                    .build();

            rs.add(customer);
        }

        return rs;
    }

    /**
     *
     * @param id
     */
    public void getDeleteCustomer(Long id){
        repo.deleteById(id);
    }

    /**
     *
     * @param id
     * @param customerDto
     * @return
     */
    public ResponseEntity<CustomerDto> updateCustomer(Long id, CustomerDto customerDto){

        Optional<CustomerEntity> customerData = repo.findById(id);

        if (customerData.isPresent()){
            CustomerEntity rs = customerData.get();

            rs.setId(customerDto.getId());
            rs.setName(customerDto.getName());
            rs.setAge(customerDto.getAge());
            rs.setActive(customerDto.isActive());

            CustomerEntity respEntity = repo.save(rs);

            CustomerDto response = CustomerDto.builder()
                    .id(respEntity.getId())
                    .name(respEntity.getName())
                    .age(respEntity.getAge())
                    .active(respEntity.isActive())
                    .build();
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
