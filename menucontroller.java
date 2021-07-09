package ce1004_B.finalproject.s107201020;
import java.io.IOException;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;


public class menucontroller {	
	@FXML
	TextField blackuser;
	@FXML
	TextField whiteuser;
	@FXML
	public void startpressed() throws IOException{//¶}©l«ö¶s
		FXMLLoader loader = new FXMLLoader(getClass().getResource("checkboard.fxml"));
		Scene checkboardScene = new Scene(loader.load());
		checkboardcontroller controller = loader.<checkboardcontroller>getController();
		controller.passdata(blackuser,whiteuser);
		finalproject_107201020.currentStage.setScene(checkboardScene);
	}
	@FXML
	public void exitpressed() {//Â÷¶}«ö¶s
		finalproject_107201020.currentStage.close();
	}	
}