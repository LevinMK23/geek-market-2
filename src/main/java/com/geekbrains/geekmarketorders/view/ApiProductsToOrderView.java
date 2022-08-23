package com.geekbrains.geekmarketorders.view;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiProductsToOrderView {

    private List<ApiProductView> products;

}
