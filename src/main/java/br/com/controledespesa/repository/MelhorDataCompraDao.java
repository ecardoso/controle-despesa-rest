package br.com.controledespesa.repository;

import br.com.controledespesa.entity.FormaPagamento;
import br.com.controledespesa.entity.MelhorDataCompra;
import br.com.controledespesa.entity.Usuario;

public interface MelhorDataCompraDao {

	MelhorDataCompra getMelhorDataCompra(Usuario usuario, FormaPagamento formaPagamento);

}
