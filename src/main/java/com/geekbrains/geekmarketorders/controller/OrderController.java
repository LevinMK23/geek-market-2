package com.geekbrains.geekmarketorders.controller;

import com.geekbrains.geekmarketorders.entity.GeekOrder;
import com.geekbrains.geekmarketorders.entity.OrderItem;
import com.geekbrains.geekmarketorders.repository.OrderRepository;
import com.geekbrains.geekmarketorders.view.ApiOrderView;
import com.geekbrains.geekmarketorders.view.ApiOrdersDetailsView;
import com.geekbrains.geekmarketorders.view.ApiProductView;
import com.geekbrains.geekmarketorders.view.ApiProductsToOrderView;
import lombok.Getter;
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
    public ApiOrderView createOrder(@RequestBody List<ApiProductView> body) {
        GeekOrder order = new GeekOrder();
        order.setItems(body.stream().map(
                p -> OrderItem.builder()
                        .name(p.getTitle())
                        .itemPrice(p.getPrice())
                        .count(1)
                        .order(order)
                        .build()
        ).collect(Collectors.toList()));
        Double price = body.stream()
                .map(ApiProductView::getPrice)
                .reduce(0., Double::sum);
        order.setPrice(price);
        GeekOrder o = orderRepository.save(order);
        return ApiOrderView.builder()
                .orderId(o.getId())
                .summaryPrice(o.getPrice())
                .details(o.getItems()
                        .stream()
                        .map(i -> ApiOrdersDetailsView.builder()
                                .productName(i.getName())
                                .count(i.getCount())
                                .price(i.getItemPrice())
                                .build())
                        .collect(Collectors.toList()))
                .build();
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

    @GetMapping
    public List<ApiOrderView> getOrders() {
        return orderRepository.findAll()
                .stream()
                .map(
                        order -> ApiOrderView.builder()
                                .orderId(order.getId())
                                .summaryPrice(order.getPrice())
                                .details(order.getItems()
                                        .stream()
                                        .map(i -> ApiOrdersDetailsView.builder()
                                                .productName(i.getName())
                                                .count(i.getCount())
                                                .price(i.getItemPrice())
                                                .build())
                                        .collect(Collectors.toList()))
                                .build()
                )
                .collect(Collectors.toList());
    }

}
