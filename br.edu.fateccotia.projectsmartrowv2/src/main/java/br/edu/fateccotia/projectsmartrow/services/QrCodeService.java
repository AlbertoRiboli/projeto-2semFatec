package br.edu.fateccotia.projectsmartrow.services;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeWriter;

import br.edu.fateccotia.projectsmartrow.model.Estabelecimento;
import br.edu.fateccotia.projectsmartrow.model.Mesas;

@Service
public class QrCodeService {

	@Autowired
	ServidorService servidorService;

	public File geradorQrEmArquivo(String info, String nomeImagem) throws IOException, WriterException {
		String formatCharset = "UTF-8";
		File file = new File("data/temp/" + nomeImagem);
		int tamanho = 500;
		QRCodeWriter qrCodeWriter = new QRCodeWriter();
		String newDataCharset = new String(info.getBytes(formatCharset), formatCharset);
		BitMatrix bitMatrix = qrCodeWriter.encode(newDataCharset, BarcodeFormat.QR_CODE, tamanho, tamanho);
		MatrixToImageWriter.writeToFile(bitMatrix, "PNG", file);
		return file;
	}

	public String geradorTextoDoQr(String path) throws IOException, WriterException, NotFoundException {
		BinaryBitmap binaryBitmap = new BinaryBitmap(
				new HybridBinarizer(new BufferedImageLuminanceSource(ImageIO.read(new FileInputStream(path)))));
		Result result = new MultiFormatReader().decode(binaryBitmap);
		return result.getText();
	}

	public byte[] geradorQrStream(String info, int altura, int largura) throws IOException, WriterException {
		String formatCharset = "UTF-8";

		QRCodeWriter qrCodeWriter = new QRCodeWriter();
		String newDataCharset = new String(info.getBytes(formatCharset), formatCharset);
		BitMatrix bitMatrix = qrCodeWriter.encode(newDataCharset, BarcodeFormat.QR_CODE, altura, largura);

		ByteArrayOutputStream converterParaStream = new ByteArrayOutputStream();
		MatrixToImageWriter.writeToStream(bitMatrix, "PNG", converterParaStream);
		byte[] stream = converterParaStream.toByteArray();

		return stream;
	}

	public byte[] gerarQrStream(String info) throws IOException, WriterException {
		return geradorQrStream(info, 500, 500);
	}

	// Metodo para implementar em um PDF através de um svg futuramente
	public String generateQrBase64(String info) throws IOException, WriterException {
		byte[] qrCodeStream = gerarQrStream(info);
		String qrCodeBase64 = Base64Utils.encodeToString(qrCodeStream);
		return "data:image/png:base64," + qrCodeBase64;
	}

	public String geradorInfoQr(Mesas mesa, Estabelecimento estabelecimento) {
		String infoQr = servidorService.getEnderecoServidorWebService() + "pedidos/criarpedidoqr&idestab="
				+ estabelecimento.getIDEstabelecimento() + "&idmesa=" + mesa.getIDMesa();
		return infoQr;
	}

	public String geradorNomeArquivo(Estabelecimento estabelecimento, Mesas mesa) {
		return estabelecimento.getIDEstabelecimento() + "-" + mesa.getIDMesa() + ".png";
	}
	
	public String geradorEnderecoQr(String nomeArquivo) {
		return "./data/temp/" + nomeArquivo;
	}
	
	

}
