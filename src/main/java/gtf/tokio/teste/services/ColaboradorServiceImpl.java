package gtf.tokio.teste.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import gtf.tokio.teste.domain.Colaborador;
import gtf.tokio.teste.jms.integration.ColaboradorIntegration;
import gtf.tokio.teste.mapper.ColaboradorMapper;
import gtf.tokio.teste.repository.ColaboradorRepository;
import gtf.tokio.teste.repository.EmpresaRepository;
import gtf.tokio.teste.services.exceptions.DeleteNotFoundException;
import gtf.tokio.teste.services.exceptions.SaveDataException;
import gtf.tokio.teste.services.exceptions.UpdateNotFoundException;
import gtf.tokio.teste.web.model.ColaboradorDto;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service 
public class ColaboradorServiceImpl implements ColaboradorService {

    private final ColaboradorRepository colaboradorRepository;
    private final EmpresaRepository empresaRepository;
    private final ColaboradorMapper colaboradorMapper;

    private String entityName = "COLABORADOR";

    @Override
    public Boolean create(ColaboradorIntegration integration) {
		try {
            Colaborador colaborador = colaboradorMapper.colaboradorIntegrationToColaborador(integration);
            colaborador.setEmpresa(empresaRepository.findById(integration.getEmpresa_id()).get());
			colaboradorRepository.save(colaborador);
		} catch (Exception e) {
			throw new SaveDataException(e.getMessage(), entityName);
		}        
        return true;
    }

    @Override
    public Boolean update(ColaboradorDto dto) {
		if (!colaboradorRepository.existsById(dto.getId())) {
			throw new UpdateNotFoundException(dto.getId(), entityName);
		} 
		try {
			colaboradorRepository.save(colaboradorMapper.colaboradorDtoToColaborador(dto));
		} catch (Exception e) {
			throw new SaveDataException(e.getMessage(), entityName);
		} 
        return true;
    }

    @Override
    public List<ColaboradorDto> listAll() {
		List<ColaboradorDto> listDto = new ArrayList<>();
        colaboradorRepository.findAll().forEach(entity -> { 
            listDto.add(colaboradorMapper.colaboradorToColaboradorDto(entity));
        });
        return listDto;
    }

    @Override
    public ColaboradorDto findById(Long id) {
        Optional<Colaborador> entity = colaboradorRepository.findById(id);
        return colaboradorMapper.colaboradorToColaboradorDto(entity.orElse(null));
    }

    @Override
    public Boolean delete(Long id) {
		if (empresaRepository.existsById(id)) {
			empresaRepository.deleteById(id);
		} else {
			throw new DeleteNotFoundException(id, entityName);
		}
        return true;
    }

    @Override
    public List<ColaboradorDto> listPage(PageRequest pageRequest) {
        List<ColaboradorDto> listDto = new ArrayList<>();
        colaboradorRepository.findAll(pageRequest).forEach(entity -> { 
            listDto.add(colaboradorMapper.colaboradorToColaboradorDto(entity));
        });        
        return listDto;
    }
    
}
