package gtf.tokio.teste.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gtf.tokio.teste.jms.integration.ColaboradorIntegration;
import gtf.tokio.teste.jms.integration.EmpresaIntegration;
import gtf.tokio.teste.jms.service.SendQueueService;


@RequestMapping("/api/v1/endpoint")
@RestController
public class EndpointController {

    @Autowired
    private SendQueueService sendQueueService;

    @PostMapping(value = "empresa")
    public ResponseEntity<Boolean> createEmpresa(@RequestBody EmpresaIntegration empresa) {
        return new ResponseEntity<>(sendQueueService.sentToEmpresa(empresa), HttpStatus.CREATED);
    }

    @PostMapping(value = "colaborador")
    public ResponseEntity<Boolean> createColaborador(@RequestBody ColaboradorIntegration colaborador) {
        return new ResponseEntity<>(sendQueueService.sentToColaborador(colaborador), HttpStatus.CREATED);
    }
}
