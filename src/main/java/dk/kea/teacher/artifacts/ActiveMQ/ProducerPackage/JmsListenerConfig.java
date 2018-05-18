package dk.kea.teacher.artifacts.ActiveMQ.ProducerPackage;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListenerConfigurer;
import org.springframework.jms.config.JmsListenerEndpointRegistrar;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;

/**
 * JMS Listener config to setup connection factory message converter
 */
@EnableJms
@Configuration
public class JmsListenerConfig implements JmsListenerConfigurer
{
    /**
     * Initiates default connection factory
     * @return factory
     */
    @Bean
    public DefaultMessageHandlerMethodFactory handlerMethodFactory() {
        DefaultMessageHandlerMethodFactory factory = new DefaultMessageHandlerMethodFactory();
        factory.setMessageConverter(messageConverter());
        return factory;
    }

    /**
     * Initiate message converter
     * @return message converter
     */
    @Bean
    public MessageConverter messageConverter() {
        System.out.println("MessageConverter JmsListnerConfig");
        return new MappingJackson2MessageConverter();
    }

    /**
     * Registrar new endpoints
     * @param registrar
     */
    @Override
    public void configureJmsListeners(JmsListenerEndpointRegistrar registrar) {
        registrar.setMessageHandlerMethodFactory(handlerMethodFactory());
    }
}