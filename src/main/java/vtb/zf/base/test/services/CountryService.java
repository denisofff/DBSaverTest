package vtb.zf.base.test.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import vtb.zf.base.test.converters.CountryConverter;
import vtb.zf.base.test.dto.Country;
import vtb.zf.base.test.entities.CountryEntity;
import vtb.zf.base.test.repository.CountriesRepository;

@Service
@Slf4j
public class CountryService extends AbstractService<Country, CountriesRepository, CountryConverter, CountryEntity> {
    @Value("${spring.kafka.country-out-topic}")
    private String outTopicName;

    @Override
    String getOutTopicName() {
        return outTopicName;
    }

    @Autowired
    public CountryService(CountriesRepository countriesRepository, CountryConverter countryConverter, KafkaTemplate<Long, Country> kafkaTemplate, ObjectMapper objectMapper) {
        super(countriesRepository, countryConverter, kafkaTemplate, objectMapper);
    }

    @Override
    public CountryEntity seekEqual(Country object) {
        return repository.findByCodeLat3(object.getCodeLat3()).orElse(null);
    }

    @KafkaListener(id = "${spring.kafka.consumer-country.group-id}", topics = {"${spring.kafka.country-in-topic}"}, containerFactory = "singleCountryFactory")
    public void consumeObject(Country object) {
        super.consumeObject(object);
    }
}
