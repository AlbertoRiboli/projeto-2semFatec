package br.edu.fateccotia.projectsmartrow.view.cliente.screens;

import br.edu.fateccotia.projectsmartrow.model.Cliente;
import br.edu.fateccotia.projectsmartrow.model.Estabelecimento;
import br.edu.fateccotia.projectsmartrow.model.Pedidos;
import br.edu.fateccotia.projectsmartrow.util.JsonInObject;
import br.edu.fateccotia.projectsmartrow.util.Requests;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MenuInicialClienteScreen extends Application {

	private static Stage stage;

	private static Cliente cliente;

	private static Estabelecimento estabelecimento;
	
	private static Pedidos pedido;

	@Override
	public void start(Stage stage) throws Exception {
		try {
			Parent root = FXMLLoader.load(getClass()
					.getResource("/br/edu/fateccotia/projectsmartrow/view/cliente/fxml/MenuInicialCliente.fxml"));
			Scene scene = new Scene(root);
			stage.setTitle("Project SmartRow");
			stage.setScene(scene);
			stage.show();
			setStage(stage);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static Stage getStage() {
		return stage;
	}

	public static void setStage(Stage stage) {
		MenuInicialClienteScreen.stage = stage;
	}

	public static Cliente getCliente() {
		return cliente;
	}

	public static void setCliente(Cliente cliente) {
		MenuInicialClienteScreen.cliente = cliente;
	}

	public static Estabelecimento getEstabelecimento() {
		return estabelecimento;
	}

	public static void setEstabelecimento(Estabelecimento estabelecimento) {
		MenuInicialClienteScreen.estabelecimento = estabelecimento;
	}

	public static void main(String[] args) {
		launch(args);
	}

	public static Pedidos getPedido() {
		return pedido;
	}

	public static void setPedido(Pedidos pedido) {
		MenuInicialClienteScreen.pedido = pedido;
	}
	
	public static void atualizarPedido() {
		String request = "pedidos/" + MenuInicialClienteScreen.getPedido().getIDPedido();
		Pedidos pedido = JsonInObject.stringToPedidos(Requests.GETPedido(request), cliente, estabelecimento) ;
		MenuInicialClienteScreen.setPedido(pedido);
		System.out.println("###################################PEDIDO ATUALIZADO#########################");
	}

}
