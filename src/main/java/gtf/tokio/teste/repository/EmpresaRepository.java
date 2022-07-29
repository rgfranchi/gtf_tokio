package gtf.tokio.teste.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import gtf.tokio.teste.domain.Empresa;


public interface EmpresaRepository extends PagingAndSortingRepository<Empresa, Long> {
}
