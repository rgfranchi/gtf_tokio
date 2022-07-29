package gtf.tokio.teste.jms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import gtf.tokio.teste.config.JMSConfig;
import gtf.tokio.teste.jms.integration.ColaboradorIntegration;
import gtf.tokio.teste.jms.integration.EmpresaIntegration;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SendQueueService {
    
    @Autowired
    JmsTemplate jmsTemplate;

    public Boolean sentToEmpresa(EmpresaIntegration empresaIntegration) {
        try {
            jmsTemplate.convertAndSend(JMSConfig.MESSAGE_TO_EMPRESA, empresaIntegration); 
        } catch (JmsException e) {
            log.error("FALHA AO ENVIAR EMPRESA: {}", e.getMessage());
            return false;
        }
        return true;
    }

    public Boolean sentToColaborador(ColaboradorIntegration colaboradorIntegration) {
        try {
            jmsTemplate.convertAndSend(JMSConfig.MESSAGE_TO_COLABORADOR, colaboradorIntegration); 
        } catch (JmsException e) {
            log.error("FALHA AO ENVIAR COLABORADOR: {}", e.getMessage());
            return false;
        }
        return true;
    }


}
