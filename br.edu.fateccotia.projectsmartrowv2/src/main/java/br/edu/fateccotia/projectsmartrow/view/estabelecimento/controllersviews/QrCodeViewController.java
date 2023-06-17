package br.edu.fateccotia.projectsmartrow.view.estabelecimento.controllersviews;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import br.edu.fateccotia.projectsmartrow.services.ServidorService;
import br.edu.fateccotia.projectsmartrow.util.ErroJavafx;
import br.edu.fateccotia.projectsmartrow.util.Requests;
import br.edu.fateccotia.projectsmartrow.view.estabelecimento.screens.MenuInicialEstabelecimentoScreen;
import br.edu.fateccotia.projectsmartrow.view.estabelecimento.screens.MesasEstabelecimentoScreen;
import br.edu.fateccotia.projectsmartrow.view.estabelecimento.screens.QrCodeScreen;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

public class QrCodeViewController implements Initializable {

	@FXML
	private Button btnDownload;

	@FXML
	private Button btnRetornar;

	@FXML
	private ImageView imageView;

	@FXML
	private Label labelStatus;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
		String path = QrCodeScreen.getMesa().getNomeArquivoQr();
		Image image = Requests.GETImage(path);
		if (image != null) {

			imageView.setImage(image);
		} else {
			QrCodeScreen.getStage().close();
		}
		}
		catch(Exception e) {
			ErroJavafx.error("Erro QrCode", "Erro ao acessar QrCode ou a Imagem não existe");
		}

		btnDownload.setOnMouseClicked(event -> {
			FileChooser fileChooser = new FileChooser();
			fileChooser.getExtensionFilters()
					.add(new FileChooser.ExtensionFilter("Imagens", "*.png", "*.jpg", "*.jpeg"));
			File file = fileChooser.showSaveDialog(null);

			if (file != null) {
				try (OutputStream outputStream = new FileOutputStream(file)) {
					String stg = file.getName().substring(file.getName().lastIndexOf(".") + 1);
					ImageIO.write(SwingFXUtils.fromFXImage(imageView.getImage(), null), stg, outputStream);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		});

		btnRetornar.setOnMouseClicked(event -> {
			QrCodeScreen.getStage().close();
		});

	}

}
