package dk.kea.teacher.artifacts.ActiveMQ.ProducerPackage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import javax.jms.ConnectionFactory;

/**
 * Configuration of connection factory
 */
@Configuration
public class ActiveMQConnectionFactoryConfig
{
    // Fields
    @Value("${jsa.activemq.broker.url}")
    String brokerUrl;
    @Value("${jsa.activemq.broker.username}")
    String username;
    @Value("${jsa.activemq.broker.password}")
    String password;

    /**
     * Initiate connection factory
     * @return Connection factory
     */
    @Bean
    public ConnectionFactory connectionFactory() {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL(brokerUrl);
        connectionFactory.setUserName(username);
        connectionFactory.setPassword(password);

        return connectionFactory;
    }

    /**
     * Serialize message content to json using TextMessage
     * @return Converter
     */
    @Bean
    public MessageConverter jacksonJmsMessageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");

        return converter;
    }

    /**
     * Listener to Receiving Message
     * @param connectionFactory
     * @param configurer
     * @return Jms Listener container factory
     */
    @Bean
    public JmsListenerContainerFactory<?> jsaFactory(ConnectionFactory connectionFactory,
                                                     DefaultJmsListenerContainerFactoryConfigurer configurer) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        System.out.println("ActiveMQ config JmsListener container factory");
        //factory.setMessageConverter(jacksonJmsMessageConverter());
        factory.setMessageConverter(new JsonMessageConverter());
        configurer.configure(factory, connectionFactory);
        return factory;
    }

    /**
     * Jms Template is used for the last step in communicating with the broker
     * @return JmsTemplate
     */
    @Bean
    public JmsTemplate jmsTemplate(){
        JmsTemplate template = new JmsTemplate();
        template.setMessageConverter(new JsonMessageConverter());
        template.setConnectionFactory(connectionFactory());

        return template;
    }
}
