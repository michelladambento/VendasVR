package com.michell.vendas.vr.client.VendasVR.services;

import com.michell.vendas.vr.client.VendasVR.converters.CustomerConverter;
import com.michell.vendas.vr.client.VendasVR.converters.OrderConverter;
import com.michell.vendas.vr.client.VendasVR.dtos.request.OrderRequestDTO;
import com.michell.vendas.vr.client.VendasVR.dtos.response.OrderResponseDTO;
import com.michell.vendas.vr.client.VendasVR.entities.CustomerEntity;
import com.michell.vendas.vr.client.VendasVR.entities.OrderEntity;
import com.michell.vendas.vr.client.VendasVR.exceptions.CustomerNotFoundException;
import com.michell.vendas.vr.client.VendasVR.exceptions.DateOrderValidException;
import com.michell.vendas.vr.client.VendasVR.exceptions.TotalOrderValidException;
import com.michell.vendas.vr.client.VendasVR.repositories.CustomerRepository;
import com.michell.vendas.vr.client.VendasVR.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private OrderConverter orderConverter;

    @Autowired
    private CustomerConverter customerConverter;

    public OrderEntity saveOrder(OrderEntity order){
        return orderRepository.saveAndFlush(order);
    }

//    public OrderResponseDTO saveOrder(OrderRequestDTO orderRequestDTO){
//            CustomerRequestDTO customerRequestDTO = orderRequestDTO.getCustomer();
//            Long customerId = customerRequestDTO.getId();
//            Optional<CustomerEntity> optCustomerEntity = customerRepository.findById(customerId);
//            if(!optCustomerEntity.isPresent())
//                throw new CustomerNotFoundException(customerId);
//            checkDateIsValid(optCustomerEntity.get(), orderRequestDTO);
//            checkTotalOrder(optCustomerEntity.get(), orderRequestDTO);
//
//            CustomerEntity customerEntity = optCustomerEntity.get();
//            Double purchaseLimit = customerEntity.getPurchaseLimit();
//            Double totalOrder = orderRequestDTO.getTotalOrder();
//            Double purchaseLimitUpdated = (purchaseLimit - totalOrder);
//            customerEntity.setPurchaseLimit(purchaseLimitUpdated);
//            customerRepository.saveAndFlush(customerEntity);
////            ajustar aqui
////            CustomerResponseDTO customerDTO = customerConverter.toCustomerResponseDTO(customerEntity);
////            orderRequestDTO.setCustomer(customerDTO);
//
////            CustomerEntity customerEntity = customerConverter.toCustomerEntity(orderRequestDTO.getCustomer());
//
////            OrderEntity orderEntity = orderConverter.toOrderEntity(orderRequestDTO);
////            OrderEntity orderEntitySaved = saveOrder(orderEntity);
////            return orderConverter.toOrderResponseDTO(orderEntitySaved);
//        return null;
//    }

    private void checkDateIsValid(CustomerEntity customerEntity, OrderRequestDTO orderRequestDTO){
        LocalDate closingDateAt = customerEntity.getClosingDateAt();
        LocalDate orderDateAt = orderRequestDTO.getOrderDateAt();
        if (orderDateAt.isAfter(closingDateAt))
            throw new DateOrderValidException(closingDateAt);
    }

    private void checkTotalOrder(CustomerEntity customerEntity, OrderRequestDTO orderRequestDTO){
        Double purchaseLimit = customerEntity.getPurchaseLimit();
        Double totalOrder = orderRequestDTO.getTotalOrder();
        LocalDate closingDateAt = customerEntity.getClosingDateAt();
        if(totalOrder > purchaseLimit)
            throw new TotalOrderValidException(purchaseLimit, closingDateAt);
    }

//    private updatePurchaseLimit( Double purchaseLimit,  Double totalOrder){
//
//    }

}
