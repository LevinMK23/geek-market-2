package com.geekbrains.geekmarketorders.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private Integer count;

    @Column
    private Double itemPrice;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "item_id")
    private GeekOrder order;

}
