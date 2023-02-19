package com.example.servicetwo.config;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Slf4j
@Configuration
@ConditionalOnExpression("${aws.enabled: true }")
public class AwsConfig {
    @Value("${aws.region}")
    String region;
//    @Bean
//    @Primary
//    public AmazonSQSAsync amazonSQSAsync() {
//        return AmazonSQSAsyncClientBuilder.standard()
//                .withRegion(region)
//                .build();
//    }

    @Bean
    @Primary
    public AmazonSQS sendSQS() {
        return AmazonSQSClientBuilder.standard()
                .withRegion(region)
                .build();
    }


}
