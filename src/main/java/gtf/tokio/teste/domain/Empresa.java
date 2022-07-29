package gtf.tokio.teste.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
 
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Empresa {
    
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String nome;
    
    @OneToMany(mappedBy = "empresa", cascade = { CascadeType.ALL })
    List<Colaborador> colaboradores;

}
