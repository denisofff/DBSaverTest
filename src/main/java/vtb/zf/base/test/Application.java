package vtb.zf.base.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.annotation.EnableKafka;
import vtb.zf.base.test.controller.DBController;
import vtb.zf.base.test.dto.Provider;
import vtb.zf.base.test.entities.ProviderEntity;

@EnableKafka
@SpringBootApplication
public class Application {
    @Autowired
    private DBController controller;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


    @EventListener(ApplicationReadyEvent.class)
    private void Test(){
        Provider vProvider = new Provider();
        vProvider.setCode("SPB");
        ProviderEntity vProviderEntity =  controller.providerService.seekEqual(vProvider);
        System.out.println(vProviderEntity);
    }

}