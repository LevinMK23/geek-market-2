package com.geekbrains.geekmarketorders.service;

import com.geekbrains.geekmarketorders.entity.GeekOrder;
import com.geekbrains.geekmarketorders.entity.OrderItem;
import com.geekbrains.geekmarketorders.repository.OrderRepository;
import com.geekbrains.model.ApiOrder;
import com.geekbrains.model.ApiOrderItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.persistence.EntityExistsException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;


    public List<ApiOrder> getOrders() {
        return orderRepository.findAll()
                .stream()
                .map(order -> new ApiOrder()
                        .id(order.getId())
                        .price(order.getPrice())
                        .details(order.getItems().stream()
                                .map(i -> new ApiOrderItem()
                                        .productName(i.getName())
                                        .count(i.getCount())
                                        .price(i.getItemPrice()))
                                .collect(Collectors.toList())))
                .collect(Collectors.toList());
    }

    public ApiOrder getOrder(@PathVariable Long orderId) {

        GeekOrder order = orderRepository.findById(orderId)
                .orElseThrow(EntityExistsException::new);

        return new ApiOrder()
                .id(order.getId())
                .price(order.getPrice())
                .details(order.getItems().stream()
                        .map(i -> new ApiOrderItem()
                                .productName(i.getName())
                                .count(i.getCount())
                                .price(i.getItemPrice()))
                        .collect(Collectors.toList()));
    }

    public ApiOrder createOrder(ApiOrder body) {
        GeekOrder order = new GeekOrder();
        order.setItems(body.getDetails().stream().map(
                p -> OrderItem.builder()
                        .name(p.getProductName())
                        .itemPrice(p.getPrice())
                        .count(1)
                        .order(order)
                        .build()
        ).collect(Collectors.toList()));
        Double price = body.getDetails().stream()
                .map(ApiOrderItem::getPrice)
                .reduce(0., Double::sum);
        order.setPrice(price);
        orderRepository.save(order);
        return new ApiOrder()
                .id(order.getId())
                .price(order.getPrice())
                .details(order.getItems().stream()
                        .map(i -> new ApiOrderItem()
                                .productName(i.getName())
                                .count(i.getCount())
                                .price(i.getItemPrice()))
                        .collect(Collectors.toList()));
    }

}
