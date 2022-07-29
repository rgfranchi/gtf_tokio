package gtf.tokio.teste.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import gtf.tokio.teste.domain.Colaborador;
import gtf.tokio.teste.jms.integration.ColaboradorIntegration;
import gtf.tokio.teste.web.model.ColaboradorDto;

@Mapper(uses = { EmpresaHelperMapper.class })
public interface ColaboradorMapper {
    Colaborador colaboradorIntegrationToColaborador(ColaboradorIntegration colaboradorIntegration);
	ColaboradorIntegration colaboradorToColaboradorIntegration(Colaborador colaborador);

    @Mapping(source = "entity.empresa", target = "empresa_id")
    ColaboradorDto colaboradorToColaboradorDto(Colaborador entity);
    @Mapping(source = "dto.empresa_id", target = "empresa")
    Colaborador colaboradorDtoToColaborador(ColaboradorDto dto);
}
