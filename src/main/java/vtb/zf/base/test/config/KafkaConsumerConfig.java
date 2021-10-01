package vtb.zf.base.test.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.converter.BatchMessagingMessageConverter;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;
import vtb.zf.base.test.dto.AbstractDto;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String kafkaServer;

    @Value("${spring.kafka.consumer-country.group-id}")
    private String kafkaCountryGroupId;

    @Value("${spring.kafka.consumer-provider.group-id}")
    private String kafkaProviderGroupId;

    @Bean
    public KafkaListenerContainerFactory<?> batchCountryFactory() {
        ConcurrentKafkaListenerContainerFactory<Long, AbstractDto> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerCountryFactory());
        factory.setConcurrency(1);
        factory.setBatchListener(true);
        factory.getContainerProperties().setPollTimeout(30000);
        factory.setMessageConverter(new BatchMessagingMessageConverter(converter()));
        return factory;
    }

    @Bean
    public KafkaListenerContainerFactory<?> batchProviderFactory() {
        ConcurrentKafkaListenerContainerFactory<Long, AbstractDto> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerProviderFactory());
        factory.setConcurrency(1);
        factory.setBatchListener(true);
        factory.getContainerProperties().setPollTimeout(30000);
        factory.setMessageConverter(new BatchMessagingMessageConverter(converter()));
        return factory;
    }

    @Bean
    public KafkaListenerContainerFactory<?> singleCountryFactory() {
        ConcurrentKafkaListenerContainerFactory<Long, AbstractDto> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerCountryFactory());
        factory.setBatchListener(false);
        factory.setMessageConverter(new StringJsonMessageConverter());
        return factory;
    }

    @Bean
    public KafkaListenerContainerFactory<?> singleProviderFactory() {
        ConcurrentKafkaListenerContainerFactory<Long, AbstractDto> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerProviderFactory());
        factory.setBatchListener(false);
        factory.setMessageConverter(new StringJsonMessageConverter());
        return factory;
    }

    @Bean
    public ConsumerFactory<Long, AbstractDto> consumerCountryFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfigs(kafkaCountryGroupId));
    }

    @Bean
    public ConsumerFactory<Long, AbstractDto> consumerProviderFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfigs(kafkaProviderGroupId));
    }

    @Bean
    public KafkaListenerContainerFactory<?> kafkaListenerContainerFactory() {
        return new ConcurrentKafkaListenerContainerFactory<>();
    }

    @Bean
    public Map<String, Object> consumerConfigs(String kafkaGroupId) {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServer);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, LongDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, kafkaGroupId);
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        return props;
    }

    @Bean
    public StringJsonMessageConverter converter() {
        return new StringJsonMessageConverter();
    }
}
