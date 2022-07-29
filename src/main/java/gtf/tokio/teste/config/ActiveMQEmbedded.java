package gtf.tokio.teste.config;

import javax.jms.JMSException;

import org.apache.activemq.artemis.api.core.SimpleString;
import org.apache.activemq.artemis.core.config.impl.ConfigurationImpl;
import org.apache.activemq.artemis.core.server.ActiveMQServer;
import org.apache.activemq.artemis.core.server.ActiveMQServers;
import org.apache.activemq.artemis.core.settings.impl.AddressSettings;
import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class ActiveMQEmbedded {
    public static final String ACCEPTOR = "vm://1"; // diferente de 0 para rodar todos os tests.

    private static ActiveMQServer server;

    @Bean
    public void ActiveMQServers() throws Exception {

        AddressSettings addressSettings = new AddressSettings();
        addressSettings.setExpiryAddress(new SimpleString(JMSConfig.DEFAULT_EXPIRE));
        addressSettings.setAutoCreateExpiryResources(true);
        // Cria o nome da fila no interior de EXPIRE DEFAULT.
        log.info("---- EXP-PRE:{}", addressSettings.getExpiryQueuePrefix().toString());
        log.info("---- EXP-SUF:{}", addressSettings.getExpiryQueueSuffix().toString());
        // log.debug("---- EXP-ADDRESS:{}",
        // addressSettings.getExpiryAddress().toString());
        addressSettings.setDeadLetterAddress(new SimpleString(JMSConfig.DEFAULT_DEAD_LETTER));
        addressSettings.setAutoCreateDeadLetterResources(true);
        addressSettings.setMaxDeliveryAttempts(JMSConfig.MAX_DELIVERY_ATTEMPT);
        log.info("---- DDL-PRE:{}", addressSettings.getDeadLetterQueuePrefix().toString());
        log.info("---- DDL-SUF:{}", addressSettings.getDeadLetterQueueSuffix().toString());
        log.info("---- DDL-ADDRESS:{}", addressSettings.getDeadLetterAddress().toString());

        server = ActiveMQServers.newActiveMQServer(
                new ConfigurationImpl().setPersistenceEnabled(false).setJournalDirectory("target/data/journal")
                        .setSecurityEnabled(false)
                        .addAcceptorConfiguration("in-vm", ActiveMQEmbedded.ACCEPTOR)
                        .addAddressesSetting("#", addressSettings).setMessageExpiryScanPeriod(100L));

        server.start();
        log.info("ACTIVE MQ SERVER LOAD");
    }

    @Bean
    public static ActiveMQConnectionFactory activeMQconnectionFactory() throws JMSException {
        return new ActiveMQConnectionFactory(ACCEPTOR);
    }

    public static ActiveMQServer activeMQServer() {
        return server;
    }

    /**
     * Criado para resolver dependência do JMSConfig
     * 
     * @return
     */
    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
