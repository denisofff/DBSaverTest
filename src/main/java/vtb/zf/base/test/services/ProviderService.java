package vtb.zf.base.test.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import vtb.zf.base.test.converters.ProviderConverter;
import vtb.zf.base.test.dto.Provider;
import vtb.zf.base.test.entities.ProviderEntity;
import vtb.zf.base.test.repository.ProvidersRepository;

@Service
@Slf4j
public class ProviderService extends AbstractService<Provider, ProvidersRepository, ProviderConverter, ProviderEntity> {
    @Value("${spring.kafka.provider-out-topic}")
    private String outTopicName;

    @Override
    String getOutTopicName() {
        return outTopicName;
    }

    @Autowired
    public ProviderService(ProvidersRepository countriesRepository, ProviderConverter countryConverter, KafkaTemplate<Long, Provider> kafkaTemplate, ObjectMapper objectMapper) {
        super(countriesRepository, countryConverter, kafkaTemplate, objectMapper);
    }

    @Override
    public ProviderEntity seekEqual(Provider object) {
        return repository.findByCode(object.getCode()).orElse(null);
    }

    @KafkaListener(id = "${spring.kafka.consumer-provider.group-id}", topics = {"${spring.kafka.provider-in-topic}"}, containerFactory = "singleCountryFactory")
    public void consumeObject(Provider object) {
        super.consumeObject(object);
    }
}
