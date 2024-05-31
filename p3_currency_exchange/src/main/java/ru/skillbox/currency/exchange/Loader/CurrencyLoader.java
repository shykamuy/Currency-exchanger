package ru.skillbox.currency.exchange.Loader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.skillbox.currency.exchange.mapper.CurrencyMapper;
import ru.skillbox.currency.exchange.repository.CurrencyRepository;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;
import java.util.stream.Collectors;

@Component
@EnableScheduling
public class CurrencyLoader {

    private static final String XML_URL = "https://cbr.ru/scripts/XML_daily.asp";
    RestTemplate restTemplate = new RestTemplate();
    String xmlData = restTemplate.getForObject(XML_URL, String.class);

    @Autowired
    private CurrencyRepository repository;
    @Autowired
    private CurrencyMapper mapper;

    @Scheduled(fixedDelay = 3600000)
    public void updateData() throws JAXBException {

        JAXBContext jaxbContext = JAXBContext.newInstance(CurrencyJaxb.class);
        Unmarshaller u = jaxbContext.createUnmarshaller();
        CurrencyJaxb currencyJaxb = (CurrencyJaxb) u.unmarshal(new StreamSource(new StringReader(xmlData)));
        System.out.println(currencyJaxb.getCurrencyDtos().get(0).getValue());
        repository.deleteAll();
        repository.saveAll(currencyJaxb.getCurrencyDtos().stream().map(cur -> {
            cur.setId(cur.getIsoNumCode());
            return mapper.convertToEntity(cur);
        }).collect(Collectors.toList()));
    }

}
