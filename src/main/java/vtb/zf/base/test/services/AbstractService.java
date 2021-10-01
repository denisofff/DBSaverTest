package vtb.zf.base.test.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import vtb.zf.base.test.converters.AbstractConverter;
import vtb.zf.base.test.dto.AbstractDto;
import vtb.zf.base.test.entities.AbstractEntity;
import vtb.zf.base.test.repository.AbstractRepository;

@Service
public abstract class AbstractService<D extends AbstractDto, R extends AbstractRepository, C extends AbstractConverter, E extends AbstractEntity> {
    protected R repository;
    protected KafkaTemplate<Long, D> kafkaTemplate;
    protected ObjectMapper objectMapper;
    protected C converter;

    //@Value("${spring.kafka.country-out-topic}")
    abstract String getOutTopicName();

    @Autowired
    public AbstractService(R repository, C converter, KafkaTemplate<Long, D> kafkaTemplate, ObjectMapper objectMapper ){
        this.repository = repository;
        this.converter = converter;
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    public void saveObject(AbstractEntity object) {
        repository.save(object);
    }

    public abstract E seekEqual(D object);

    public void sendObject(D object) {
        kafkaTemplate.send(getOutTopicName(), object);
    }

    //@KafkaListener(id = "${spring.kafka.consumer.group-id}", topics = {"${spring.kafka.country-in-topic}"}, containerFactory = "singleFactory")
    public void consumeObject(D object) {
        E vObject = seekEqual(object);
        // заполнить vObject из Object
        if (vObject == null) {
            vObject = (E) converter.entityFromDto(object);
        } else {
            vObject = (E) converter.entityFromDto(object, vObject);
        }

        saveObject(vObject);
        sendObject((D) converter.dtoFromEntity(vObject));
    }

    private String writeValueAsString(D object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException("Writing value to JSON failed: " + object.toString());
        }
    }
}
