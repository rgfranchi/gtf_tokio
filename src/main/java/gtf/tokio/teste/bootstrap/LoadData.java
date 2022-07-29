package gtf.tokio.teste.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import gtf.tokio.teste.config.JMSConfig;
import gtf.tokio.teste.domain.Colaborador;
import gtf.tokio.teste.domain.Empresa;
import gtf.tokio.teste.jms.integration.ColaboradorIntegration;
import gtf.tokio.teste.jms.integration.EmpresaIntegration;
import gtf.tokio.teste.repository.ColaboradorRepository;
import gtf.tokio.teste.repository.EmpresaRepository;

@Component
public class LoadData implements CommandLineRunner {

    @Autowired
    EmpresaRepository empresaRepository;
    
    @Autowired
    ColaboradorRepository colaboradorRepository;

    @Autowired
    JmsTemplate jmsTemplate;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("LOAD DATA");
        Empresa empresa = Empresa.builder().nome("TOKIO").build();
        empresaRepository.save(empresa);
        Colaborador colaborador = Colaborador.builder().nome("Rafael GUERRA Franchi").idade(41).empresa(empresa).build();
        colaboradorRepository.save(colaborador);
        System.out.println(colaborador.getEmpresa().getNome());

        
        EmpresaIntegration empresaIntegration = EmpresaIntegration.builder().nome("GTF").build();
        jmsTemplate.convertAndSend(JMSConfig.MESSAGE_TO_EMPRESA, empresaIntegration); 


        ColaboradorIntegration colaboradorIntegration = ColaboradorIntegration.builder().nome("Rafael").idade(22).empresa_id(empresa.getId()).build();
        jmsTemplate.convertAndSend(JMSConfig.MESSAGE_TO_COLABORADOR, colaboradorIntegration); 
    }
    
}
