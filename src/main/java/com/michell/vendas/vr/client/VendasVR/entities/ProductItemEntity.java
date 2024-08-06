package com.michell.vendas.vr.client.VendasVR.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "produto_item")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductItemEntity {

    @Id
    @Column(name= "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "produto_item_sequence")
    @SequenceGenerator(name="produto_item_sequence", sequenceName = "seq_produto_item_id", initialValue = 1, allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private PurchaserOrderEntity purchaserOrder;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private ProductEntity product;

    @Column(name = "quantidade")
    private Integer qtd;

    @Column(name = "preco_unitario")
    private Double unitPrice;

}
