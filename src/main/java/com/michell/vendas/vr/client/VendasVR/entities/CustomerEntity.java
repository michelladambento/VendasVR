package com.michell.vendas.vr.client.VendasVR.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name= "cliente")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerEntity {

    @Id
    @Column(name= "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cliente_sequence")
    @SequenceGenerator(name="cliente_sequence", sequenceName = "seq_cliente_id", initialValue = 1, allocationSize = 1)
    private Long id;

    @Column(name="nome", nullable = false)
    private String customerName;

    @Column(name="limite_compra", nullable = false)
    private Double purchaseLimit;

    @Column(name="data_fechamento", nullable = false)
    private LocalDate closingDateAt;

    @OneToMany(mappedBy = "customer")
    private List<PurchaserOrderEntity> purchaserOrder;

}
