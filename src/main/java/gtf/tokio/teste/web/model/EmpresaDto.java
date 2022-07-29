package gtf.tokio.teste.web.model;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmpresaDto implements Serializable {
    private static final long serialVersionUID = -8795095555686101656L;

    Long id;
    String nome;
    List<ColaboradorDto> colaboradores;
}
