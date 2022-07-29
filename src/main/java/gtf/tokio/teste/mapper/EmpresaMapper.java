package gtf.tokio.teste.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import gtf.tokio.teste.domain.Empresa;
import gtf.tokio.teste.jms.integration.EmpresaIntegration;
// import gtf.tokio.teste.web.model.EmpresaDto;
import gtf.tokio.teste.web.model.EmpresaDto;

@Mapper(uses = { ColaboradorHelperMapper.class })
public interface EmpresaMapper {
    Empresa empresaIntegrationToEmpresa(EmpresaIntegration empresaIntegration);
	EmpresaIntegration empresaToEmpresaIntegration(Empresa company);

    @Mapping(source = "dto.colaboradores", target = "colaboradores")
    Empresa empresaDtoToEmpresa(EmpresaDto dto);
    @Mapping(source = "entity.colaboradores", target = "colaboradores")
    EmpresaDto empresaToEmpresaDto(Empresa entity);
}