package gtf.tokio.teste.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import gtf.tokio.teste.domain.Empresa;
import gtf.tokio.teste.repository.EmpresaRepository;

@Component
public class EmpresaHelperMapper {
    
    @Autowired
    EmpresaRepository empresaRepository;

    public Empresa asEmpresa(Long empresa_id) {
        return empresaRepository.findById(empresa_id).get();
    }

    public Long asEmpresa(Empresa entity) {
        return entity.getId();
    }

}
