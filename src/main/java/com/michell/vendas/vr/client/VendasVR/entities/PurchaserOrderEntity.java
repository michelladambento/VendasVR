package com.michell.vendas.vr.client.VendasVR.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "pedido_compra")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PurchaserOrderEntity {

    @Id
    @Column(name= "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pedido_compra_sequence")
    @SequenceGenerator(name="pedido_compra_sequence", sequenceName = "seq_pedido_compra_id", initialValue = 1, allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private CustomerEntity customer;

    @Column(name = "data_pedido")
    private LocalDate orderDateAt;

    @Column(name = "total_pedido")
    private Double orderTotal;

    @OneToMany(mappedBy = "purchaserOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductItemEntity> productItens;


}
