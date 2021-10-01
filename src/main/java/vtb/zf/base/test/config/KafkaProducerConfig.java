package vtb.zf.base.test.config;

import org.springframework.kafka.support.serializer.JsonSerializer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.LongSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;
import vtb.zf.base.test.dto.AbstractDto;
import vtb.zf.base.test.dto.Country;
import vtb.zf.base.test.dto.Provider;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String kafkaServer;

    @Value("${spring.kafka.consumer-country.producer-id}")
    private String kafkaCountryProducerId;

    @Value("${spring.kafka.consumer-provider.producer-id}")
    private String kafkaProviderProducerId;

    @Bean
    public Map<String, Object> producerConfigs(String kafkaProducerId) {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServer);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, LongSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        props.put(ProducerConfig.CLIENT_ID_CONFIG, kafkaProducerId);
        return props;
    }

    @Bean
    public ProducerFactory<Long, Country> producerFactoryCountry() {
        return new DefaultKafkaProducerFactory<>(producerConfigs(kafkaCountryProducerId));
    }

    @Bean
    public KafkaTemplate<Long, Country> kafkaTemplateCountry() {
        KafkaTemplate<Long, Country> template = new KafkaTemplate<>(producerFactoryCountry());
        template.setMessageConverter(new StringJsonMessageConverter());
        return template;
    }

    @Bean
    public ProducerFactory<Long, Provider> producerFactoryProvider() {
        return new DefaultKafkaProducerFactory<>(producerConfigs(kafkaProviderProducerId));
    }

    @Bean
    public KafkaTemplate<Long, Provider> kafkaTemplateProvider() {
        KafkaTemplate<Long, Provider> template = new KafkaTemplate<>(producerFactoryProvider());
        template.setMessageConverter(new StringJsonMessageConverter());
        return template;
    }
}
