package com.geekbrains.geekmarketorders.view;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiOrderView {

    private Long orderId;

    private Double summaryPrice;

    private List<ApiOrdersDetailsView> details;

}
