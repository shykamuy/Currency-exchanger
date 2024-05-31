package ru.skillbox.currency.exchange.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.var;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class CurrencyDto {
    NumberFormat format = NumberFormat.getNumberInstance(Locale.getDefault());

    private Long id;

    @XmlElement(name = "Name")
    private String name;

    @XmlElement(name = "Nominal")
    private Long nominal;

    @XmlElement(name = "Value")
    private String value;
    public Double getValue() {
        Number number;
        try {
             number = format.parse(value);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return number.doubleValue();
    }
    @XmlElement(name = "NumCode")
    private Long isoNumCode;

    @XmlElement(name = "CharCode")
    private String isoCharCode;
}