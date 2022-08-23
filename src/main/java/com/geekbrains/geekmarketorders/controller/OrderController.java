package com.geekbrains.geekmarketorders.controller;

import com.geekbrains.geekmarketorders.entity.GeekOrder;
import com.geekbrains.geekmarketorders.entity.OrderItem;
import com.geekbrains.geekmarketorders.repository.OrderRepository;
import com.geekbrains.geekmarketorders.view.ApiOrderView;
import com.geekbrains.geekmarketorders.view.ApiOrdersDetailsView;
import com.geekbrains.geekmarketorders.view.ApiProductView;
import com.geekbrains.geekmarketorders.view.ApiProductsToOrderView;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityExistsException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderRepository orderRepository;

    @PostMapping
    public String createOrder(@RequestBody ApiProductsToOrderView body) {
        GeekOrder order = new GeekOrder();
        order.setItems(body.getProducts().stream().map(
                p -> OrderItem.builder()
                        .name(p.getTitle())
                        .itemPrice(p.getPrice())
                        .count(1)
                        .order(order)
                        .build()
        ).collect(Collectors.toList()));
        Double price = body.getProducts().stream()
                .map(ApiProductView::getPrice)
                .reduce(0., Double::sum);
        order.setPrice(price);
        GeekOrder savedOrder = orderRepository.save(order);
        return "http://localhost:8191/api/v1/orders/" + savedOrder.getId();
    }

    @GetMapping("/{orderId}")
    public ApiOrderView getOrder(@PathVariable Long orderId, @RequestParam(defaultValue = "false") Boolean showDetails) {

        GeekOrder geekOrder = orderRepository.findById(orderId)
                .orElseThrow(EntityExistsException::new);

        List<ApiOrdersDetailsView> details = null;
        if (showDetails) {
            details = geekOrder.getItems()
                    .stream()
                    .map(i -> ApiOrdersDetailsView.builder()
                            .productName(i.getName())
                            .count(i.getCount())
                            .price(i.getItemPrice())
                            .build())
                    .collect(Collectors.toList());
        }

        return ApiOrderView.builder()
                .orderId(geekOrder.getId())
                .summaryPrice(geekOrder.getPrice())
                .details(details)
                .build();
    }

}
