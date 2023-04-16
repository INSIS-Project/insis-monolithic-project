package com.isep.acme.bootstrapper;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.isep.acme.mapper.ProductMapper;
import com.isep.acme.repositories.ProductRepository;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
// @Profile("bootstrap")
public class ProductBootstrapper implements CommandLineRunner {

    @Autowired
    private ProductRepository pRepo;

    @Autowired
    private ProductMapper productMapper;
    
    @Autowired
    @Value("${spring.application.name}")
    private String applicationName;

    @Autowired
    private DirectExchange productRPCExchange;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void run(String... args) throws Exception {

       String response = (String) rabbitTemplate.convertSendAndReceive(
                                                productRPCExchange.getName(),
                                                "rpc", 
                                                applicationName);
                                                
        if (response == null) {
            log.info("No products to bootstrap.");
            return;
        }
        
        pRepo.saveAll(productMapper.toMessageList(response));
        log.info("Products bootstrapped successfully.");
    }
}
