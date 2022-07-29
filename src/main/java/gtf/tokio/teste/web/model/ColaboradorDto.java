package gtf.tokio.teste.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ColaboradorDto {
    Long id;
    String nome;
    Integer idade;
    Long empresa_id;
}
