package gtf.tokio.teste.services;

import java.util.List;

import org.springframework.data.domain.PageRequest;

import gtf.tokio.teste.jms.integration.ColaboradorIntegration;
import gtf.tokio.teste.web.model.ColaboradorDto;

public interface ColaboradorService {
    Boolean create(ColaboradorIntegration integration);

    Boolean update(ColaboradorDto integration);

    List<ColaboradorDto> listAll();

    List<ColaboradorDto> listPage(PageRequest pageRequest);    

    ColaboradorDto findById(Long id);

    Boolean delete(Long id);
}
