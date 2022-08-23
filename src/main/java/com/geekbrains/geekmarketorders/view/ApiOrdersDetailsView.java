package com.geekbrains.geekmarketorders.view;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiOrdersDetailsView {

    private String productName;

    private Integer count;

    private Double price;

}
