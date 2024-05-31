package ru.skillbox.currency.exchange.Loader;


import lombok.Getter;
import ru.skillbox.currency.exchange.dto.CurrencyDto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "ValCurs")
@Getter
public class CurrencyJaxb {

    @XmlElement(name = "Valute")
    private List<CurrencyDto> currencyDtos;
}
