package gtf.tokio.teste.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import gtf.tokio.teste.domain.Colaborador;
import gtf.tokio.teste.repository.EmpresaRepository;
import gtf.tokio.teste.web.model.ColaboradorDto;

@Component
public class ColaboradorHelperMapper {

    @Autowired
    EmpresaRepository empresaRepository;

    public List<ColaboradorDto> asColaboradorListDto(List<Colaborador> listEntity) {
        List<ColaboradorDto> list = new ArrayList<>();
        listEntity.iterator().forEachRemaining(action -> {
            list.add(ColaboradorDto.builder()
                .id(action.getId())
                .nome(action.getNome())
                .idade(action.getIdade())
                .empresa_id(action.getEmpresa().getId())
                .build());
        }); 
        return list;
    }

    public List<Colaborador> asColaboradorList(List<ColaboradorDto> listDto) {
        List<Colaborador> list = new ArrayList<>();
        listDto.iterator().forEachRemaining(action -> {
            list.add(Colaborador.builder()
                .id(action.getId())
                .nome(action.getNome())
                .idade(action.getIdade())
                .empresa(empresaRepository.findById(action.getEmpresa_id()).get())
                .build());
        });         
        return list;
    }

}
