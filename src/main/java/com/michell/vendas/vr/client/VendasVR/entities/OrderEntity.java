package com.michell.vendas.vr.client.VendasVR.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name= "pedido_compra")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderEntity {

    @Id
    @Column(name= "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pedido_sequence")
    @SequenceGenerator(name="pedido_sequence", sequenceName = "seq_pedido_id", initialValue = 1, allocationSize = 1)
    private Long id;

    @Column(name="data_compra", nullable = false)
    private LocalDate orderDateAt;

    @OneToOne
    @JoinColumn(name="id_cliente", referencedColumnName = "id", foreignKey = @ForeignKey(name = "cliente_fkey"))
    private CustomerEntity customer;

    @Column(name="total_pedido", nullable = false)
    private Double totalOrder;

}
