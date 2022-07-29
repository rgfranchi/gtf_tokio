package gtf.tokio.teste.jms.integration;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ColaboradorIntegration implements Serializable {
    static final long serialVersionUID = -6703829570277352446L;
    String nome;
    Integer idade;
    Long empresa_id;
}