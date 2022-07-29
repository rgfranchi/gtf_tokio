
package gtf.tokio.teste.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import gtf.tokio.teste.domain.Empresa;
import gtf.tokio.teste.jms.integration.EmpresaIntegration;
import gtf.tokio.teste.mapper.EmpresaMapper;
import gtf.tokio.teste.repository.EmpresaRepository;
import gtf.tokio.teste.services.exceptions.DeleteNotFoundException;
import gtf.tokio.teste.services.exceptions.SaveDataException;
import gtf.tokio.teste.services.exceptions.UpdateNotFoundException;
import gtf.tokio.teste.web.model.EmpresaDto;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service 
public class EmpresaServiceImpl implements EmpresaService {

    private final EmpresaRepository empresaRepository;
    private final EmpresaMapper empresaMapper;

    private String entityName = "EMPRESA";

    @Override
    public Boolean create(EmpresaIntegration integration) {
		try {
			empresaRepository.save(empresaMapper.empresaIntegrationToEmpresa(integration));
		} catch (Exception e) {
			throw new SaveDataException(e.getMessage(), entityName);
		}        
        return true;
    }

    @Override
    public Boolean update(EmpresaDto dto) {
		if (!empresaRepository.existsById(dto.getId())) {
			throw new UpdateNotFoundException(dto.getId(), entityName);
		} 
		try {
			empresaRepository.save(empresaMapper.empresaDtoToEmpresa(dto));
		} catch (Exception e) {
			throw new SaveDataException(e.getMessage(), entityName);
		} 
        return true;
    }

    @Override
    public List<EmpresaDto> listAll() {
		List<EmpresaDto> listDto = new ArrayList<>();
        empresaRepository.findAll().forEach(entity -> { 
            listDto.add(empresaMapper.empresaToEmpresaDto(entity));
        });
        return listDto;
    }

    @Override
    public EmpresaDto findById(Long id) {
        Optional<Empresa> entity = empresaRepository.findById(id);
        return empresaMapper.empresaToEmpresaDto(entity.orElse(null));
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
    public List<EmpresaDto> listPage(PageRequest pageRequest) {
        List<EmpresaDto> listDto = new ArrayList<>();
        empresaRepository.findAll(pageRequest).forEach(entity -> { 
            listDto.add(empresaMapper.empresaToEmpresaDto(entity));
        });
        return listDto;
    } 
}
