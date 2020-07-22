package br.com.controledespesa.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.controledespesa.data.model.MelhorDataCompra;

@Repository
public interface MelhorDataCompraRepository extends JpaRepository<MelhorDataCompra, Long> {

	@Query(value = "SELECT * FROM MELHOR_DATA_COMPRA M INNER JOIN USUARIO U ON U.ID = M.USUARIO_ID INNER JOIN FORMA_PAGAMENTO F ON F.ID = M.FORMA_PAGAMENTO_ID WHERE U.ID = :idUsuario AND F.ID = :idFormaPagamento AND MES_REFERENCIA >= :dataInicio AND MES_REFERENCIA <= :dataFim",
		   nativeQuery = true)
	MelhorDataCompra getMelhorDataCompra(@Param("idUsuario") Long idUsuario, @Param("idFormaPagamento") Long idFormaPagamento, @Param("dataInicio") LocalDateTime dataInicio,
							@Param("dataFim") LocalDateTime dataFim);

}
