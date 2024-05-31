package ru.skillbox.currency.exchange.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CurrencyData {
    private String name;
    private double value;
}
