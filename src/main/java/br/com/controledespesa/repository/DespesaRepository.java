package br.com.controledespesa.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.controledespesa.data.model.Despesa;

@Repository
public interface DespesaRepository extends JpaRepository<Despesa, Long> {

	@Query(value = "SELECT * FROM DESPESA D INNER JOIN USUARIO U ON U.ID = D.USUARIO_ID INNER JOIN CATEGORIA C ON C.ID = D.CATEGORIA_ID WHERE U.ID = :idUsuario AND DATA_PAGAMENTO BETWEEN :dataInicial AND :dataFinal ORDER BY C.ID, DATA_PAGAMENTO ASC",
		   nativeQuery = true)
	List<Despesa> findByMes(@Param("idUsuario") Long idUsuario, @Param("dataInicial") LocalDateTime dataInicial, @Param("dataFinal") LocalDateTime dataFinal);

	@Query(value = "SELECT * FROM DESPESA D INNER JOIN USUARIO U ON U.ID = D.USUARIO_ID INNER JOIN CATEGORIA C ON C.ID = D.CATEGORIA_ID INNER JOIN FORMA_PAGAMENTO F ON F.ID = D.FORMA_PAGAMENTO_ID WHERE U.ID = :idUsuario AND F.ID = :idFormaPagamento AND DATA_PAGAMENTO >= cast(:dataInicial as date) AND DATA_PAGAMENTO <= cast(:dataFinal as date) ORDER BY C.ID, DATA_PAGAMENTO ASC",
		   nativeQuery = true)
	public List<Despesa> findByMesFormaPagamento(@Param("idUsuario") Long idUsuario, @Param("idFormaPagamento") Long idFormaPagamento, @Param("dataInicial") LocalDateTime dataInicial,
							@Param("dataFinal") LocalDateTime dataFinal);

}
