package gtf.tokio.teste.services;

import java.util.List;

import org.springframework.data.domain.PageRequest;

import gtf.tokio.teste.jms.integration.EmpresaIntegration;
import gtf.tokio.teste.web.model.EmpresaDto;

public interface EmpresaService {
    Boolean create(EmpresaIntegration integration);

    Boolean update(EmpresaDto integration);

    List<EmpresaDto> listAll();

    List<EmpresaDto> listPage(PageRequest pageRequest);

    EmpresaDto findById(Long id);

    Boolean delete(Long id);
}
