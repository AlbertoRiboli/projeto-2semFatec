package br.edu.fateccotia.projectsmartrow.services;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import br.edu.fateccotia.projectsmartrow.model.Cliente;
import br.edu.fateccotia.projectsmartrow.model.Estabelecimento;
import br.edu.fateccotia.projectsmartrow.model.Usuario;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.AddressException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EmailService {
	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(EmailService.class);
	
	@Autowired
	ServidorService servidorService;
	
	private final String requestValidarEmail = "email/validaremail?id=";
	private final String requestValidarEmailUsuario = "email/validaremailusuario?id=";
	private final String requestValidarEmailEstabelecimento = "email/validaremailestabelecimento?id=";
	
	@Bean
	public JavaMailSender getJavaMailSender() {
	    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
	    mailSender.setHost("smtp.gmail.com");
	    mailSender.setPort(587);
	    
	    mailSender.setUsername(servidorService.getEmail());
	    mailSender.setPassword(servidorService.getPass());
	    
	    Properties props = mailSender.getJavaMailProperties();
	    props.put("mail.transport.protocol", "smtp");
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.debug", "true");
	    
	    return mailSender;
	}
	
	public void enviarEmailConfirmacao(String para, String titulo, String conteudo) throws AddressException, MessagingException {
		 SimpleMailMessage message = new SimpleMailMessage();
		
		message.setFrom("richard.silva46fatec@gmail.com");
		message.setTo(para);
		message.setSubject(titulo);
		message.setText(conteudo);
		
		
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				long ini = System.currentTimeMillis();
				log.info("Enviando email para validação de cadastro");
				getJavaMailSender().send(message);
				log.info("Email de validação enviado! Tempo decorrido: " + (System.currentTimeMillis() - ini)/1000 + "s");
			}
		});
		thread.start();
	}
	
	
	
	public String gerarLinkValidacao(Cliente cliente) {
		String enderecoServidor = servidorService.getEnderecoServidorWebService();
		String enderecoRequisicao = requestValidarEmail;
		Integer id = cliente.getIDCliente();
		String link = enderecoServidor + enderecoRequisicao + id;
		return link;
	}
	
	public String gerarLinkValidacaoUsuario(Usuario usuario) {
		String enderecoServidor = servidorService.getEnderecoServidorWebService();
		String enderecoRequisicao = requestValidarEmailUsuario;
		Integer id = usuario.getIDUsuario();
		String link = enderecoServidor + enderecoRequisicao + id;
		return link;
	}
	
	public String gerarLinkValidacaoEstabelecimento(Estabelecimento usuario) {
		String enderecoServidor = servidorService.getEnderecoServidorWebService();
		String enderecoRequisicao = requestValidarEmailEstabelecimento;
		Integer id = usuario.getIDEstabelecimento();
		String link = enderecoServidor + enderecoRequisicao + id;
		return link;
	}
	
	public String titulo(String nomeUsuario) {
		return "Olá " + nomeUsuario + "! Seja Bem Vindo a familia SmartRow";
	}
	
	public String conteudoEmailValidacao(Cliente cliente) {
		return "Olá " +  cliente.getNome()
		+ "! É muito bom ter você conosco! \n"
		+ "Recebemos a sua solicitação de cadastro!\n"
		+ "Confirme seus dados abaixo: Se tiver alguma divergência nos avise viu?\n "
		+ "**********************************************************************"
		+ "\nNome: " + cliente.getNome()
		+ "\nEmail: " + cliente.getEmail()
		+ "\nRG: " + cliente.getRg()
		+ "\nCPF: " + cliente.getCpf()
		+ "\nEndereço: " + cliente.getEndereco()
		+ "\nTelefone: " + cliente.getTelefone()
		+ "\n**********************************************************************\n"
		+ "Tudo certo? Então falta só validar o seu cadastro através do link abaixo\n"
		+ gerarLinkValidacao(cliente);
	}
	
	public String conteudoEmailValidacaoUsuario(Usuario usuario) {
		return "Olá " +  usuario.getNome()
		+ "! É muito bom ter você conosco! \n"
		+ "Recebemos a sua solicitação de cadastro!\n"
		+ "Confirme seus dados abaixo: Se tiver alguma divergência nos avise viu?\n "
		+ "**********************************************************************"
		+ "\nNome: " + usuario.getNome()
		+ "\nEmail: " + usuario.getEmail()
		+ "\nCPF: " + usuario.getCpf()
		+ "\nEstabelecimento: " + usuario.getNomeEstabelecimento()
		+ "\n**********************************************************************\n"
		+ "Tudo certo? Então falta só validar o seu cadastro através do link abaixo\n"
		+ gerarLinkValidacaoUsuario(usuario);
	}
	
	public String conteudoEmailValidacaoEstabelecimento(Estabelecimento usuario) {
		return "Olá " +  usuario.getNome()
		+ "! É muito bom ter você conosco! \n"
		+ "Recebemos a sua solicitação de cadastro!\n"
		+ "Confirme seus dados abaixo: Se tiver alguma divergência nos avise viu?\n "
		+ "**********************************************************************"
		+ "\nNome: " + usuario.getNome()
		+ "\nEmail: " + usuario.getEmail()
		+ "\nCNPJ: " + usuario.getCnpj()
		+ "\n**********************************************************************\n"
		+ "Tudo certo? Então falta só validar o seu cadastro através do link abaixo\n"
		+ gerarLinkValidacaoEstabelecimento(usuario);
	}
}
