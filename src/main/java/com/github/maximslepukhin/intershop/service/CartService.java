package com.github.maximslepukhin.intershop.service;

import com.github.maximslepukhin.intershop.enums.ActionType;
import reactor.core.publisher.Mono;

import java.util.Map;

public interface CartService {
    Mono<Map<Long, Integer>> changeCart(Mono<Map<Long, Integer>> cart, ActionType action, Long id);
}
