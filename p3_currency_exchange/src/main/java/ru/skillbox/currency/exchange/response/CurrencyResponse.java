package ru.skillbox.currency.exchange.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
@Setter
public class CurrencyResponse {

    List<CurrencyData> currencies;

}
