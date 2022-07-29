package gtf.tokio.teste.jms.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import gtf.tokio.teste.config.JMSConfig;
import gtf.tokio.teste.jms.integration.ColaboradorIntegration;
import gtf.tokio.teste.jms.integration.EmpresaIntegration;
import gtf.tokio.teste.services.ColaboradorService;
import gtf.tokio.teste.services.EmpresaService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JMSListener {
    
    @Autowired
    private EmpresaService empresaService;
    
    @Autowired
    private ColaboradorService colaboradorService;

    @JmsListener(destination = JMSConfig.MESSAGE_TO_EMPRESA)
    public void empresaListener(@Payload EmpresaIntegration empresaIntegration, @Headers MessageHeaders messageHeaders) {
        log.info(empresaIntegration.getNome());
        empresaService.create(empresaIntegration);
    }

    @JmsListener(destination = JMSConfig.MESSAGE_TO_COLABORADOR)
    public void colaboradorListener(@Payload ColaboradorIntegration colaboradorIntegration, @Headers MessageHeaders messageHeaders) {
        log.info(colaboradorIntegration.getNome());
        colaboradorService.create(colaboradorIntegration);
    }

}
