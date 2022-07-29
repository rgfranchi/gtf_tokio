package gtf.tokio.teste.config;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jms.annotation.JmsListenerConfigurer;
import org.springframework.jms.config.JmsListenerEndpointRegistrar;
import org.springframework.jms.config.JmsListenerEndpointRegistry;
import org.springframework.jms.config.SimpleJmsListenerContainerFactory;

@Configuration
public class JMSListenerConfig implements JmsListenerConfigurer {
    /**
     * Realiza conexão com servidor.
     * 
     * @return
     */
    @Bean
    @Primary
    public ActiveMQConnectionFactory connectionFactoryEmbeddedMQ() {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(
                ActiveMQEmbedded.ACCEPTOR);
        return activeMQConnectionFactory;
    }

    /**
     * Construtor da conexão simplificada<br>
     * Utilizar o jmsListenerContainerFactory como padrão<br>
     * ou incluir no @JmsListener containerFactory = "nomeDoConteinerFactory")
     * 
     * @return
     */
    @Bean
    @Primary
    public SimpleJmsListenerContainerFactory jmsListenerContainerFactoryEmbeddedMQ() {
        SimpleJmsListenerContainerFactory factory = new SimpleJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactoryEmbeddedMQ());
        return factory;
    }

    @Bean
    public JmsListenerEndpointRegistry jmsListenerEndpoint() {
        return new JmsListenerEndpointRegistry();
    }

    @Override
    public void configureJmsListeners(JmsListenerEndpointRegistrar registrar) {
        registrar.setEndpointRegistry(jmsListenerEndpoint());
    }    
}
