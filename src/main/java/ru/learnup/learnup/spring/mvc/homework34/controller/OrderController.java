package ru.learnup.learnup.spring.mvc.homework34.controller;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.learnup.learnup.spring.mvc.homework34.entity.User;
import ru.learnup.learnup.spring.mvc.homework34.mapper.OrderMapper;
import ru.learnup.learnup.spring.mvc.homework34.model.OrderDto;
import ru.learnup.learnup.spring.mvc.homework34.service.OrderService;
import ru.learnup.learnup.spring.mvc.homework34.services.MessageSender;
import ru.learnup.learnup.spring.mvc.homework34.view.OrderView;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;
    private final OrderMapper orderMapper;
    private final MessageSender messageSender;

    public OrderController(OrderService orderService,
                           OrderMapper orderMapper,
                           MessageSender messageSender) {
        this.orderService = orderService;
        this.orderMapper = orderMapper;
        this.messageSender = messageSender;
    }

    @GetMapping
    public Flux<OrderView> getOrders() {
        List<OrderDto> ordersDto = orderService.getOrders();
        List<OrderView> orderViews = ordersDto
                .stream()
                .map(orderMapper::mapToView)
                .collect(Collectors.toList());

//        Flux<OrderView> orders = Flux.fromIterable(orderViews);

        return Flux.fromIterable(orderViews);
    }

    @GetMapping("/{orderId}")
    public Mono<OrderView> getOrder(@PathVariable(name = "orderId")
                                          int orderId) {
    OrderDto order = orderService.findById(orderId);
    return Mono.just(orderMapper.mapToView(order));
    }

    @PostMapping
    public Mono<OrderView> createOrder(@RequestBody OrderView order) {
        OrderDto dto = orderMapper.mapFromView(order);
        messageSender.sendMessage("message");
        return Mono.just(orderMapper.mapToView(
                orderService.createOrder(dto)
        ));
    }

    @PutMapping("/{orderId}")
    @PreAuthorize("hasAuthority(\"ROLE_USER\") or hasAuthority(\"ROLE_ADMIN\")")
    public Mono<OrderView> updateOrder(@PathVariable(name = "orderId")
                                             int orderId,
                                 @RequestBody OrderView order,
                                 @AuthenticationPrincipal User user) {
        System.out.println(user);
        if (order.getUser().getId() == user.getId()) {
            OrderDto dto = orderMapper.mapFromView(order);
            return  Mono.just(orderMapper.mapToView(
                    orderService.createOrder(dto)
            ));
        } else {
            throw new RuntimeException("Access denied!");
        }
    }

    @DeleteMapping("/{orderId}")
    public Mono<Void>delete(@PathVariable(name = "orderId")
                                   int orderId) {
        orderService.delete(orderId);
        return Mono.just("Order was deleted").then();
    }

}
