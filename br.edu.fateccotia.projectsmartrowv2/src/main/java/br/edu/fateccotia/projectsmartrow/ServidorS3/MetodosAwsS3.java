package br.edu.fateccotia.projectsmartrow.ServidorS3;

import java.io.File;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.joda.time.Instant;
import org.springframework.stereotype.Service;

import com.amazonaws.HttpMethod;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.GetObjectRequest;

import br.edu.fateccotia.projectsmartrow.exceptions.ExcessaoArquivoNaoEncontrado;
import br.edu.fateccotia.projectsmartrow.model.Mesas;

@Service
public class MetodosAwsS3 {
	
	private final AmazonS3 s3;
	
	
	public String bucket = Credenciais.getBucket();
	public String destino = Credenciais.getDestino();
	
	public MetodosAwsS3() {
		var credenciais = new BasicAWSCredentials(Credenciais.getAcessKey(), Credenciais.getSecretKey());
		s3 = AmazonS3ClientBuilder
				.standard()
				.withCredentials(new AWSStaticCredentialsProvider(credenciais))
				.withRegion(Regions.SA_EAST_1)
				.build();
	}
	
	public void criarBucket(final String nomeBucket) {
		if(s3.doesBucketExistV2(nomeBucket)) {
			System.out.println("Nome Existente");
		}
		s3.createBucket(nomeBucket);
	}
	
	public List<String> listarBuckets() {
		return s3.listBuckets()
				.stream()
				.map(bucket -> bucket.getName())
				.collect(Collectors.toList());
	}
	
	public void deletarBucket(final String nomeBucket) {
		if(!s3.doesBucketExistV2(nomeBucket)) {
			System.out.println("O Bucket não existe");
		}
		s3.deleteBucket(nomeBucket);
	}
	
	public void enviarArquivo (String arquivoOrigem, String nomeArquivo) {
		if(!s3.doesBucketExistV2(this.bucket)) {
			System.out.println("O Bucket não existe");
		}
		s3.putObject(this.bucket, (destino + nomeArquivo ), new File(arquivoOrigem));
		File file = new File(arquivoOrigem);
		file.delete();
	}
	
	public void receberArquivo (Mesas mesa) {
		if(!s3.doesBucketExistV2(this.bucket)) {
			System.out.println("O Bucket não existe");
		}
		GetObjectRequest request = new GetObjectRequest(this.bucket, destino + mesa.getNomeArquivoQr());
		s3.getObject(request, new File(mesa.getEnderecoQr()));
	}
	
	public List<String> listarArquivos(){
	if(!s3.doesBucketExistV2(this.bucket)) {
		System.out.println("O Bucket não existe");
	}
	var listaObjetos = s3.listObjects(this.bucket);
	return listaObjetos.getObjectSummaries()
			.stream()
			.map(sumario -> sumario.getKey())
			.collect(Collectors.toList());
	}
	
	public void deletarArquivo(String arquivo){
		if(!s3.doesBucketExistV2(this.bucket)) {
			System.out.println("O Bucket não existe");
		}
		s3.deleteObject(this.bucket, arquivo);
		
		}
	
	public void downloadArquivo(String arquivo) {
		try {
			Date expiracao = new Date();
			long exp = Instant.now().getMillis();
			exp += 1000*60*60;
			expiracao.setTime(exp);
			
			GeneratePresignedUrlRequest generatePresignedUrlRequest = 
					new GeneratePresignedUrlRequest(this.bucket, arquivo)
					.withMethod(HttpMethod.GET)
					.withExpiration(expiracao);
			URL url = s3.generatePresignedUrl(generatePresignedUrlRequest);
					
		System.out.println(url);
		}
		catch (RuntimeException e) {
			throw new ExcessaoArquivoNaoEncontrado(e.getMessage());
		}
	}
	
	
	
	

}
