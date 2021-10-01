package vtb.zf.base.test.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import vtb.zf.base.test.converters.CountryConverter;
import vtb.zf.base.test.converters.ProviderConverter;
import vtb.zf.base.test.services.CountryService;
import vtb.zf.base.test.services.ProviderService;

@Component
public class DBController {
    @Bean
    public CountryConverter countryConverter() {return new CountryConverter(); };

    @Bean
    public ProviderConverter providerConverter() {return new ProviderConverter(); };

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Autowired
    public CountryService countryService;

    @Autowired
    public ProviderService providerService;
}
