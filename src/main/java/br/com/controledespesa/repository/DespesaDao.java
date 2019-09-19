package br.com.controledespesa.repository;

import java.util.Date;
import java.util.List;

import br.com.controledespesa.entity.Despesa;

public interface DespesaDao {

	List<Despesa> findByMes(String idUsuario, Date dataInicial, Date dataFinal);

}
