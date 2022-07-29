package gtf.tokio.teste.domain;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

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
public class Colaborador {
    
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String nome;

    Integer idade;

    @ManyToOne
	@JoinColumn(nullable = false, foreignKey = @ForeignKey(name = "fk_empresa_colaborador")) 
    @JsonBackReference
    Empresa empresa;

}
