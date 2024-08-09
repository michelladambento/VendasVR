package com.michell.vendas.vr.client.VendasVR.services;

import com.michell.vendas.vr.client.VendasVR.dtos.CustomerDTO;
import com.michell.vendas.vr.client.VendasVR.entities.CustomerEntity;
import com.michell.vendas.vr.client.VendasVR.entities.ProductEntity;
import com.michell.vendas.vr.client.VendasVR.exceptions.DefaultAlreadyExistException;
import com.michell.vendas.vr.client.VendasVR.exceptions.DefaultNotFoundException;
import com.michell.vendas.vr.client.VendasVR.repositories.CustomerRepository;
import com.michell.vendas.vr.client.VendasVR.repositories.CustomerSpecifications;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

public class CustomerServiceTest {

    @Mock
    private CustomerRepository repository;

    @Mock
    private CustomerSpecifications specifications;

    @InjectMocks
    private CustomerService customerService;

    private CustomerDTO customerDTO;

    private CustomerEntity customerEntity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        customerDTO = new CustomerDTO();
        customerDTO.setCustomerName("Michell Adam Bento");
        customerDTO.setClosingDateAt(LocalDate.now());
        customerDTO.setPurchaseLimit(100.0);

        customerEntity = new CustomerEntity();
        customerEntity.setCustomerName("Ana Auxiliadora Bento");
        customerEntity.setClosingDateAt(LocalDate.now());
        customerEntity.setPurchaseLimit(100.0);
    }

    @Test
    public void saveCustomerSuccessfully(){
        Specification<CustomerEntity> mockSpecification = (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("customerName"), customerDTO.getCustomerName());
        when(specifications.hasCustomerName(customerDTO.getCustomerName())).thenReturn(mockSpecification);
        when(repository.findOne(mockSpecification)).thenReturn(Optional.empty());
        customerService.saveCustomer(customerDTO);
        verify(repository).findOne(mockSpecification);
        verify(repository).saveAndFlush(Mockito.any(CustomerEntity.class)); // Verify save was called
    }

    @Test
    public void throwsExceptionWhenCustomerExists() {
        Specification<CustomerEntity> mockSpecification = (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("customerName"), customerDTO.getCustomerName());

        when(specifications.hasCustomerName(customerDTO.getCustomerName())).thenReturn(mockSpecification);
        when(repository.findOne(mockSpecification)).thenReturn(Optional.of(customerEntity)); // Product exists

        DefaultAlreadyExistException exception = assertThrows(DefaultAlreadyExistException.class, () -> {
            customerService.saveCustomer(customerDTO);
        });

        String message = String.format("Cliente (%s) já existe", customerDTO.getCustomerName());
        assertEquals(message, exception.getMessage());

        verify(repository).findOne(mockSpecification);
        verify(repository, never()).saveAndFlush(any(CustomerEntity.class)); //parametro never é pra especificar que o metodo nunca foi chamado.
    }

    @Test
    public void allCustomersFounded(){
        List<CustomerEntity> list = new ArrayList<>();
        list.add(customerEntity);
        when(repository.findAll()).thenReturn(list);
        List<CustomerEntity> allCustomers = customerService.findAllUser();
        assertFalse(allCustomers.isEmpty());
        verify(repository).findAll();
    }

    @Test
    public void customerDeletedSuccessfully(){
        when(repository.existsById(Mockito.any())).thenReturn(true);
        customerService.deleteCustomer(customerEntity.getId());
        verify(repository).deleteById(Mockito.any());
    }

    @Test
    public void CustomerNotFoundThrowExceptionWhenDelete(){
        when(repository.existsById(Mockito.any())).thenReturn(false);

        DefaultNotFoundException exception = assertThrows(DefaultNotFoundException.class, ()->{
            customerService.deleteCustomer(customerEntity.getId());
        });
        verify(repository).existsById(Mockito.any());
        verify(repository, never()).deleteById(Mockito.any());
    }

    @Test
    public void customerUpdatedSuccessfully(){
        customerService.updateCustomer(customerEntity, customerDTO);
        verify(repository).saveAndFlush(customerEntity);
        assertEquals("Michell Adam Bento", customerEntity.getCustomerName());
        assertEquals(100.0, customerEntity.getPurchaseLimit());
        assertEquals(LocalDate.now(), customerEntity.getClosingDateAt());
    }

    @Test
    public void customerFoundedByIdSuccessfully(){
        when(repository.findById(1L)).thenReturn(Optional.of(customerEntity));
        customerService.findById(1L);
        verify(repository).findById(1L);
    }

    @Test
    public void customerNotFoundThrowException(){
        when(repository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        DefaultNotFoundException exception = assertThrows(DefaultNotFoundException.class, () -> {
            customerService.findById(1L);
        });
        String message = String.format("Cliente ID:(%s) não encontrado.", 1L);
        assertEquals(message, exception.getMessage());
        verify(repository).findById(Mockito.anyLong());
    }

}
