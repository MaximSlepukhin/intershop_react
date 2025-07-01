package com.github.maximslepukhin.intershop.controller;

import com.github.maximslepukhin.intershop.model.Item;
import com.github.maximslepukhin.intershop.model.Order;
import com.github.maximslepukhin.intershop.model.OrderItem;
import com.github.maximslepukhin.intershop.repository.ItemRepository;
import com.github.maximslepukhin.intershop.repository.OrderItemRepository;
import com.github.maximslepukhin.intershop.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.test.web.reactive.server.WebTestClient;


@AutoConfigureWebTestClient
public class OrderControllerTest extends AbstractIntegrationTest {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    private Order savedOrder;
    private Item savedItem;

    @BeforeEach
    void setup() {
        orderItemRepository.deleteAll().block();
        orderRepository.deleteAll().block();
        itemRepository.deleteAll().block();

        savedItem = itemRepository.save(new Item(
                null, "Ноутбук Lenovo", "15.6\", 16 ГБ RAM, SSD 512 ГБ", 80000, "images/lenovo.jpeg")).block();

        savedOrder = orderRepository.save(new Order(null, 160000, null)).block();

        OrderItem orderItem = new OrderItem(savedOrder.getId(), savedItem.getId(), 2);
        orderItemRepository.save(orderItem).block();
    }

    @Test
    void testShowOrders() {
        webTestClient.get()
                .uri("/orders")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .consumeWith(response -> {
                    String body = response.getResponseBody();
                    assert body != null;
                    assert body.contains("orders");
                });
    }

    @Test
    void testShowOrderById() {
        webTestClient.get()
                .uri("/orders/" + savedOrder.getId())
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .consumeWith(response -> {
                    String body = response.getResponseBody();
                    assert body != null;
                    assert body.contains("order");
                });
    }
}
