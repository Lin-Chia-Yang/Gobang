package ce1004_B.finalproject.s107201020;

import java.io.IOException;
import java.util.LinkedList;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public class checkboardcontroller implements EventHandler <MouseEvent>{
	
	String blackname;
	String whitename;
	int x=0;
	int y=0;
	boolean blackchess = true;//�¤l�_��
	boolean empty = true;//�ѽL��m�S�Ѥl
	boolean end = false;//�C������

	int chess [][] = new int[23][23];//0~3 4~18(�ѽL) 19~22
	{
		for(int i=0;i<chess.length;i++) {
			for(int j=0;j<chess.length;j++) {
				chess[i][j] = 0;
			}
		}
	}//chess�G���}�C�P�_�C���O�_����
	@FXML
	Pane pane;//�̥~�h��pane
	@FXML 
	Button backbutton;//��^
	@FXML
	Button newbutton;//�A�Ӥ@��
	@FXML
	Text white;//��ܶ¤l�W�� ��TextField�s�J
	@FXML
	Text black;//��ܥդl�W�� ��TextField�s�J
	@FXML
	Pane blackturn;//����¤l
	@FXML
	Pane whiteturn;//����դl
	@FXML
	Text blackwinner;//�¤l��Ӯ����
	@FXML
	Text whitewinner;//�դl��Ӯ����
	
	
	

	
	
	public void backpressed() {//��^���s
		finalproject_107201020.currentStage.setScene(finalproject_107201020.menuScene);
	}

	public void handle(MouseEvent e) {
		
		Circle newblack = new Circle();
        newblack.setRadius(10);
        newblack.setStroke(Color.BLACK);
        newblack.setFill(Color.BLACK);
        //�¦�Ѥl:newblack
        Circle newwhite = new Circle();
        newwhite.setRadius(10);
        newwhite.setStroke(Color.BLACK);
        newwhite.setFill(Color.WHITE);
        //�զ�Ѥl:newwhite
        
		
		if(e.getEventType() == MouseEvent.MOUSE_CLICKED) {//�ƹ��I����������			
			if(e.getSceneX()>=140 && e.getSceneX()<=440 && e.getSceneY() >=40 && e.getSceneY() <=340 && end == false) {
				//���W:(150,50),�k�W:(430,50),���U:(150,330),�k�U:(430,330)
				for(int i=140;i<=440;i=i+20) {
					if(e.getSceneX() <= i) {
						if(e.getSceneX()==140) {
							x = 150;
						}
						else{
							x = i - 10;
							break;
						}
					}
					else {
						continue;
					}	
				}
				int xposition = (x-150)/20 + 4;//xposition���G���}�C��x��m
				for(int j=40;j<=340;j=j+20) {
					if(e.getSceneY() <= j) {
						if(e.getSceneY()==40) {
							y = 50;
						}
						else{
							y = j - 10;
							break;
						}
					}	
					else {
						continue;
					}	
				}
				int yposition = (y-50)/20 + 4;//yposition���G���}�C��y��m
				if(blackchess == true && chess[xposition][yposition] == 0 && end == false) {//����¤l�U��  blackchess == true
					newblack.setLayoutX(x);
					newblack.setLayoutY(y);
					pane.getChildren().add(newblack);
					chess[xposition][yposition] = 1;//�S���Ѥl��m:chess=0 �¤l��m:chess=1 �դl��m:chess=2
					blackchess = false;

				}
				else if(blackchess == false && chess[xposition][yposition] == 0 && end == false) {//����դl�U�� �դl blackchess == false
					newwhite.setLayoutX(x);
					newwhite.setLayoutY(y);
					pane.getChildren().add(newwhite);
					chess[xposition][yposition] = 2;//�S���Ѥl��m:chess=0 �¤l��m:chess=1 �դl��m:chess=2
					blackchess = true;
				}
				//�P�_��u�O�_�s�u
				for(int i=xposition-4;i<=xposition;i++) {
					if(chess[i][yposition] == chess[xposition][yposition] 
						&& chess[i+1][yposition] == chess[xposition][yposition]
						&& chess[i+2][yposition] == chess[xposition][yposition]
						&& chess[i+3][yposition] == chess[xposition][yposition]
						&& chess[i+4][yposition] == chess[xposition][yposition]) {
						backbutton.requestFocus();
						if(chess[xposition][yposition]==1) {
							blackwinner.setText("���a " + black.getText()+" ���!");
							blackwinner.setFill(Color.BLUE);
							blackwinner.setVisible(true);
							end = true;
						}
						else {
							whitewinner.setText("���a " + white.getText()+" ���!");
							whitewinner.setFill(Color.BLUE);
							whitewinner.setVisible(true);
							end = true;
						}
						break;//�C����������j��
					}
				}
				//�P�_���u�O�_�s�u
				for(int j=yposition-4;j<=yposition;j++) {
					if(chess[xposition][j] == chess[xposition][yposition]
					&& chess[xposition][j+1] == chess[xposition][yposition]
					&& chess[xposition][j+2] == chess[xposition][yposition]
					&& chess[xposition][j+3] == chess[xposition][yposition]
					&& chess[xposition][j+4] == chess[xposition][yposition]) {
						backbutton.requestFocus();
						if(chess[xposition][yposition]==1) {
							blackwinner.setText("���a " + black.getText()+" ���!");
							blackwinner.setFill(Color.BLUE);
							blackwinner.setVisible(true);
							end = true;
						}
						else {
							whitewinner.setText("���a " + white.getText()+" ���!");
							whitewinner.setFill(Color.BLUE);
							whitewinner.setVisible(true);
							end = true;
						}
						break;//�C����������j��
					}
				}
				//�P�_�׽u(\)�ϧ_�s�u
				for(int i=0;i<=4;i++) {
					if(chess[xposition-4+i][yposition-4+i] == chess[xposition][yposition]
					&& chess[xposition-3+i][yposition-3+i] == chess[xposition][yposition]
					&& chess[xposition-2+i][yposition-2+i] == chess[xposition][yposition]
					&& chess[xposition-1+i][yposition-1+i] == chess[xposition][yposition]
					&& chess[xposition-0+i][yposition-0+i] == chess[xposition][yposition]) {
						backbutton.requestFocus();
						if(chess[xposition][yposition]==1) {
							blackwinner.setText("���a " + black.getText()+" ���!");
							blackwinner.setFill(Color.BLUE);
							blackwinner.setVisible(true);
							end = true;
						}
						else {
							whitewinner.setText("���a " + white.getText()+" ���!");
							whitewinner.setFill(Color.BLUE);
							whitewinner.setVisible(true);
							end = true;
						}
						break;//�C����������j��
					}					
				}
				//�P�_�ϱ׽u(/)�ϧ_�s�u
				for(int i=0;i<=4;i++) {					
					if(chess[xposition-0+i][yposition+0-i] == chess[xposition][yposition]
					&& chess[xposition-1+i][yposition+1-i] == chess[xposition][yposition]
					&& chess[xposition-2+i][yposition+2-i] == chess[xposition][yposition]
					&& chess[xposition-3+i][yposition+3-i] == chess[xposition][yposition]
					&& chess[xposition-4+i][yposition+4-i] == chess[xposition][yposition]) {
						backbutton.requestFocus();
						if(chess[xposition][yposition]==1) {
							blackwinner.setText("���a " + black.getText()+" ���!");
							blackwinner.setFill(Color.BLUE);
							blackwinner.setVisible(true);
							end = true;
						}
						else {
							whitewinner.setText("���a " + white.getText()+" ���!");
							whitewinner.setFill(Color.BLUE);
							whitewinner.setVisible(true);
							end = true;
						}
						break ;//�C����������j��
					}					
				}
				if(blackchess==true && end == false) {
					blackturn.setStyle("-fx-border-color: red ; -fx-border-width: 5px ;");
					whiteturn.setStyle("-fx-border-color: transparent ; -fx-border-width: 5px ;");
				}
				else if(blackchess==false && end == false) {
					blackturn.setStyle("-fx-border-color: transparent ; -fx-border-width: 5px ;");
					whiteturn.setStyle("-fx-border-color: red ; -fx-border-width: 5px ;");
				}																
			}		
		}			
	}
	public void newpressed() throws IOException{//�A�Ӥ@�����s
		FXMLLoader loader = new FXMLLoader(getClass().getResource("checkboard.fxml"));
		Scene checkboardScene = new Scene(loader.load());
		checkboardcontroller controller = loader.<checkboardcontroller>getController();
		finalproject_107201020.currentStage.setScene(checkboardScene);
		blackname = black.getText();//����black black�������eTextField��J���¤l�ϥΪ̦W��
		whitename = white.getText();//����white white�������eTextField��J���դl�ϥΪ̦W��
		controller.black.setText(blackname);//�]�wblack�� blackname
		controller.white.setText(whitename);//�]�wwhite�� whitename
		
	}

	public void passdata(TextField blackuser,TextField whiteuser) {	//�N�e�@�ӭ�����J����ƶǤJ	
		black.setText(blackuser.getText());
		white.setText(whiteuser.getText());

	}

	
	
	
	
}	

