package gtf.tokio.teste.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class JMSConfig {

    // FILA DE MENSAGENS
    public static final String MESSAGE_TO_EMPRESA = "MESSAGE_TO_EMPRESA";
    public static final String MESSAGE_TO_COLABORADOR = "MESSAGE_TO_COLABORADOR";

    // Recebe mensagem com tempo expirado (config Servidor).
    public static final String DEFAULT_EXPIRE = "DEFAULT_EXPIRE";

    // Armazena mensagens não entregue (config Servidor).
    public static final String DEFAULT_DEAD_LETTER = "DEFAULT_DEAD_LETTER";

    // Tentativa de entrega Maxima (config Servidor)
    public static final Integer MAX_DELIVERY_ATTEMPT = 3;



    /**
     * Serialização da mensagem.
     * 
     * @return MappingJackson2MessageConverter
     */
    @Bean
    public MessageConverter messageConverter(ObjectMapper objectMapper) {
        System.out.println("--------- MESSAGE CONVERTER ---------");
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");
        converter.setObjectMapper(objectMapper);
        return converter;
    }
}
