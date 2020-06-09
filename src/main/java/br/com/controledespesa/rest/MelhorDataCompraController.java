package br.com.controledespesa.rest;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.controledespesa.entity.MelhorDataCompra;
import br.com.controledespesa.repository.MelhorDataCompraDao;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class MelhorDataCompraController {

	@Autowired
	private MelhorDataCompraDao melhorDataCompraDao;

	@GetMapping(value = "/findAllMelhorDataCompra")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna a lista de melhor data de compra"), @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
							@ApiResponse(code = 500, message = "Foi gerada uma exceção"), })
	public List<MelhorDataCompra> findAll() {
		List<MelhorDataCompra> melhoresDataCompra = melhorDataCompraDao.findAll();
		melhoresDataCompra.stream().forEach(dtCompra -> addLinkByMelhorDataCompra(dtCompra));

		return melhoresDataCompra;
	}

	@GetMapping(value = "/getMelhorDataCompra")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna uma melhor data de compra"), @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
							@ApiResponse(code = 500, message = "Foi gerada uma exceção"), })
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "id da melhor data de compra", defaultValue = "1", required = true, dataTypeClass = Long.class) })
	public MelhorDataCompra getMelhorDataCompra(@RequestParam(value = "id", defaultValue = "1") Long id) {
		MelhorDataCompra melhorDataCompra = melhorDataCompraDao.getById(id);
		addLinkByMelhorDataCompra(melhorDataCompra);

		return melhorDataCompra;
	}

	@PostMapping(value = "/saveMelhorDataCompra", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "saçlvar melhor data de compra"), @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
							@ApiResponse(code = 500, message = "Foi gerada uma exceção"), })
	@ApiImplicitParams({ @ApiImplicitParam(name = "melhorDataCompra", value = "melhor data de compra", required = true, dataTypeClass = MelhorDataCompra.class) })
	public MelhorDataCompra save(@RequestBody MelhorDataCompra melhorDataCompra) {
		if (melhorDataCompra.getId() == null) {
			melhorDataCompraDao.save(melhorDataCompra);
		} else {
			melhorDataCompraDao.update(melhorDataCompra);
		}

		addLinkByMelhorDataCompra(melhorDataCompra);

		return melhorDataCompra;
	}

	@DeleteMapping(value = "/deleteMelhorDataCompra", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "deletar melhor data de compra"), @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
							@ApiResponse(code = 500, message = "Foi gerada uma exceção"), })
	@ApiImplicitParams({ @ApiImplicitParam(name = "melhorDataCompra", value = "melhor data de compra", required = true, dataTypeClass = MelhorDataCompra.class) })
	public ResponseEntity<MelhorDataCompra> delete(@RequestBody MelhorDataCompra melhorDataCompra) {
		melhorDataCompraDao.delete(melhorDataCompra);

		return ResponseEntity.ok().build();
	}

	private void addLinkByMelhorDataCompra(MelhorDataCompra melhorDataCompra) {
		if (melhorDataCompra == null) {
			return;
		}

		melhorDataCompra.add(linkTo(methodOn(MelhorDataCompraController.class).getMelhorDataCompra(melhorDataCompra.getKey())).withRel("get-MelhorDataCompra"));
		melhorDataCompra.add(linkTo(methodOn(FormaPagamentoController.class).getFormaPagamento(melhorDataCompra.getFormaPagamento().getKey())).withRel("get-FormaPagamento"));
		melhorDataCompra.add(linkTo(methodOn(UsuarioController.class).getUsuario(melhorDataCompra.getUsuario().getKey())).withRel("get-Usuario"));
	}

}
