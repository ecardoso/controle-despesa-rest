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

import br.com.controledespesa.data.vo.MelhorDataCompraVO;
import br.com.controledespesa.service.MelhorDataCompraService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(value = "Melhor Data Compra Endpoint", tags = { "MelhorDataCompraEndpoint" })
public class MelhorDataCompraController {

	@Autowired
	private MelhorDataCompraService melhorDataCompraService;

	@GetMapping(value = "/findAllMelhorDataCompra")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna a lista de melhor data de compra"), @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
							@ApiResponse(code = 500, message = "Foi gerada uma exceção"), })
	public List<MelhorDataCompraVO> findAll() {
		List<MelhorDataCompraVO> melhoresDataCompraVO = melhorDataCompraService.findAll();
		melhoresDataCompraVO.stream().forEach(dtCompra -> addLinkByMelhorDataCompra(dtCompra));

		return melhoresDataCompraVO;
	}

	@GetMapping(value = "/getMelhorDataCompra")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna uma melhor data de compra"), @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
							@ApiResponse(code = 500, message = "Foi gerada uma exceção"), })
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "id da melhor data de compra", defaultValue = "1", required = true, dataTypeClass = Long.class) })
	public MelhorDataCompraVO getMelhorDataCompra(@RequestParam(value = "id", defaultValue = "1") Long id) {
		MelhorDataCompraVO melhorDataCompraVO = melhorDataCompraService.getById(id);
		addLinkByMelhorDataCompra(melhorDataCompraVO);

		return melhorDataCompraVO;
	}

	@PostMapping(value = "/saveMelhorDataCompra", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "saçlvar melhor data de compra"), @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
							@ApiResponse(code = 500, message = "Foi gerada uma exceção"), })
	@ApiImplicitParams({ @ApiImplicitParam(name = "melhorDataCompra", value = "melhor data de compra", required = true, dataTypeClass = MelhorDataCompraVO.class) })
	public MelhorDataCompraVO save(@RequestBody MelhorDataCompraVO melhorDataCompraVO) {
		if (melhorDataCompraVO.getKey() == null) {
			melhorDataCompraService.save(melhorDataCompraVO);
		} else {
			melhorDataCompraService.update(melhorDataCompraVO);
		}

		addLinkByMelhorDataCompra(melhorDataCompraVO);

		return melhorDataCompraVO;
	}

	@DeleteMapping(value = "/deleteMelhorDataCompra", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "deletar melhor data de compra"), @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
							@ApiResponse(code = 500, message = "Foi gerada uma exceção"), })
	@ApiImplicitParams({ @ApiImplicitParam(name = "melhorDataCompra", value = "melhor data de compra", required = true, dataTypeClass = MelhorDataCompraVO.class) })
	public ResponseEntity<MelhorDataCompraVO> delete(@RequestBody MelhorDataCompraVO melhorDataCompraVO) {
		melhorDataCompraService.delete(melhorDataCompraVO);

		return ResponseEntity.ok().build();
	}

	private void addLinkByMelhorDataCompra(MelhorDataCompraVO melhorDataCompraVO) {
		if (melhorDataCompraVO == null) {
			return;
		}

		melhorDataCompraVO.add(linkTo(methodOn(MelhorDataCompraController.class).getMelhorDataCompra(melhorDataCompraVO.getKey())).withRel("get-MelhorDataCompra"));
		melhorDataCompraVO.add(linkTo(methodOn(FormaPagamentoController.class).getFormaPagamento(melhorDataCompraVO.getFormaPagamento().getKey())).withRel("get-FormaPagamento"));
		melhorDataCompraVO.add(linkTo(methodOn(UsuarioController.class).getUsuario(melhorDataCompraVO.getUsuario().getKey())).withRel("get-Usuario"));
	}

}
