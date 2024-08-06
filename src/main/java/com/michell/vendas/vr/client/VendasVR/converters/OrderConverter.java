package com.michell.vendas.vr.client.VendasVR.converters;

import org.springframework.stereotype.Component;

@Component
public class OrderConverter {

//    @Autowired
//    private CustomerConverter customerConverter;
//
//    public OrderEntity toOrderEntity(OrderRequestDTO orderRequestDTO){
//        CustomerEntity customerEntity = customerConverter.toCustomerEntity(orderRequestDTO.getCustomer());
//        return OrderEntity.builder()
//                .id(orderRequestDTO.getId())
//                .orderDateAt(orderRequestDTO.getOrderDateAt())
//                .customer(customerEntity)
//                .totalOrder(orderRequestDTO.getTotalOrder())
//                .build();
//    }
//
//    public OrderResponseDTO toOrderResponseDTO(OrderEntity orderEntity){
//        CustomerResponseDTO customerResponseDTO = customerConverter.toCustomerResponseDTO(orderEntity.getCustomer());
//        return OrderResponseDTO.builder()
//                .id(orderEntity.getId())
//                .orderDateAt(orderEntity.getOrderDateAt())
//                .customer(customerResponseDTO)
//                .totalOrder(orderEntity.getTotalOrder())
//                .build();
//    }
//
//    public List<OrderResponseDTO> toOrderResponseDTO(List<OrderEntity> ordersEntities){
//        List<OrderResponseDTO> ordersResponseDTO = new ArrayList<>();
//        for (OrderEntity orderEntity : ordersEntities){
//            ordersResponseDTO.add(toOrderResponseDTO(orderEntity));
//        }
//        return ordersResponseDTO;
//    }
}
