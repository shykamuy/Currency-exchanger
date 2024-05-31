package ru.skillbox.currency.exchange.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skillbox.currency.exchange.dto.CurrencyDto;
import ru.skillbox.currency.exchange.response.CurrencyData;
import ru.skillbox.currency.exchange.response.CurrencyResponse;
import ru.skillbox.currency.exchange.service.CurrencyService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/currency")
public class CurrencyController {
    private final CurrencyService service;
    private final CurrencyResponse currencyResponse;

    @GetMapping(value = "/{id}")
    ResponseEntity<CurrencyDto> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping
    ResponseEntity<CurrencyResponse> getAll() {
        currencyResponse.setCurrencies(service.getAll());
        return ResponseEntity.ok(currencyResponse);
    }

    @GetMapping(value = "/convert")
    ResponseEntity<Double> convertValue(@RequestParam("value") Long value, @RequestParam("numCode") Long numCode) {
        return ResponseEntity.ok(service.convertValue(value, numCode));
    }

    @PostMapping("/create")
    ResponseEntity<CurrencyDto> create(@RequestBody CurrencyDto dto) {
        return ResponseEntity.ok(service.create(dto));
    }
}
