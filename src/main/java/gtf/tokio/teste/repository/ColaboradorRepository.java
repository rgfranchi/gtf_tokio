package gtf.tokio.teste.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import gtf.tokio.teste.domain.Colaborador;

public interface ColaboradorRepository extends PagingAndSortingRepository<Colaborador, Long> {
}