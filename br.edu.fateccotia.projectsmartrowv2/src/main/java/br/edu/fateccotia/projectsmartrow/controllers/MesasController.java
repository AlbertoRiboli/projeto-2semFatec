package br.edu.fateccotia.projectsmartrow.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.zxing.WriterException;

import br.edu.fateccotia.projectsmartrow.ServidorS3.MetodosAwsS3;
import br.edu.fateccotia.projectsmartrow.model.Estabelecimento;
import br.edu.fateccotia.projectsmartrow.model.Mesas;
import br.edu.fateccotia.projectsmartrow.services.EstabelecimentoService;
import br.edu.fateccotia.projectsmartrow.services.MesasService;
import br.edu.fateccotia.projectsmartrow.services.QrCodeService;

@RestController
@RequestMapping(value = "/mesas")
public class MesasController {

	@Autowired
	MesasService mesasService;
	@Autowired
	EstabelecimentoService estcont;
	@Autowired
	QrCodeService qrCodeService;
	@Autowired
	MetodosAwsS3 awsS3;

	@GetMapping
	public ResponseEntity<List<Mesas>> findAll() {
		List<Mesas> list = mesasService.findAll();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<?> findById(@PathVariable Integer id) {
		Mesas mesas = mesasService.findById(id);
		if (mesas == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Mesa Não Encontrada! \nId: " + id + " não corresponde a nenhuma mesa cadastrada!");
		}
		return ResponseEntity.ok().body(mesas);
	}

	@PostMapping
	public ResponseEntity<Mesas> criarMesas(@RequestBody Mesas mesas) {
		Mesas newMesa = mesasService.criarMesas(mesas);
		return ResponseEntity.ok().body(newMesa);

	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<?> atualizarMesas(@PathVariable Integer id, @RequestBody Mesas mesas) {
		mesas.setIDMesa(id);
		if (mesas.getIDMesa() == -1) {
			return new ResponseEntity<>("Mesa não Encontrada", HttpStatus.NOT_FOUND);
		}
		Mesas newMesa = mesasService.atualizarMesas(id, mesas);
		return ResponseEntity.ok().body(newMesa);

	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Integer id) {
		Mesas mesasVerif = mesasService.findById(id);
		if (mesasVerif == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Mesa Não Encontrada! \nId: " + id + " não corresponde a nenhuma mesa cadastrada!");
		}
		mesasService.deleteById(id);
		return new ResponseEntity<>("Estabelecimento Deletado", HttpStatus.ACCEPTED);
	}

	@PostMapping(value = "/cadastrarmesa/{id}")
	public ResponseEntity<?> cadastrarListaDeMesas(@PathVariable Integer id, @RequestBody Mesas mesa)
			throws IOException, WriterException {
		Estabelecimento estabelecimento = estcont.findById(id);
		if (estabelecimento == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Estabelecimento Não Encontrado!");
		}

		criarMesas(mesa);
		String nomeArquivo = qrCodeService.geradorNomeArquivo(estabelecimento, mesa);
		mesa.setNomeArquivoQr(nomeArquivo);
		qrCodeService.geradorQrEmArquivo(qrCodeService.geradorInfoQr(mesa, estabelecimento), nomeArquivo);
		mesa.setEnderecoQr(qrCodeService.geradorEnderecoQr(nomeArquivo));
		awsS3.enviarArquivo(mesa.getEnderecoQr(), nomeArquivo);
		atualizarMesas(mesa.getIDMesa(), mesa);
		estabelecimento.setAddMesas(mesa);
		estcont.atualizarEstabelecimento(id, estabelecimento);
		return ResponseEntity.ok().body(mesa);
	}

	@GetMapping(value = "/listarmesas/{id}")
	public ResponseEntity<?> listarMesasPorEstabelecimento(@PathVariable Integer id) {
		List<Mesas> mesas = new ArrayList<>();
		Estabelecimento est1 = estcont.findById(id);
		if (est1 == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Estabelecimento Não Encontrado!");
		}
		mesas = est1.getMesas();
		return ResponseEntity.ok().body(mesas);
	}
	
	@GetMapping(value = "/download/{id}")
	public ResponseEntity<?> downloadQrCode(@PathVariable Integer id){
		Mesas mesa = mesasService.findById(id);
		if(mesa == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi localizada nenhuma mesa com o id: [" + id + "]");
		}
		awsS3.receberArquivo(mesa);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body("QRCODE disponível na pasta " + mesa.getEnderecoQr());
			}
}
