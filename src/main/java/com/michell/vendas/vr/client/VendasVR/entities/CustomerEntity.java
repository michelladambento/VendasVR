package com.michell.vendas.vr.client.VendasVR.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name= "cliente")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="nome", nullable = false)
    private String customerName;

    @Column(name="limite_compra", nullable = false)
    private Double purchaseLimit;

    @Column(name="data_fechamento", nullable = false)
    private LocalDate closingDateAt;



}
