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
	boolean blackchess = true;//黑子起手
	boolean empty = true;//棋盤位置沒棋子
	boolean end = false;//遊戲結束

	int chess [][] = new int[23][23];//0~3 4~18(棋盤) 19~22
	{
		for(int i=0;i<chess.length;i++) {
			for(int j=0;j<chess.length;j++) {
				chess[i][j] = 0;
			}
		}
	}//chess二維陣列判斷遊戲是否結束
	@FXML
	Pane pane;//最外層的pane
	@FXML 
	Button backbutton;//返回
	@FXML
	Button newbutton;//再來一局
	@FXML
	Text white;//顯示黑子名稱 由TextField存入
	@FXML
	Text black;//顯示白子名稱 由TextField存入
	@FXML
	Pane blackturn;//輪到黑子
	@FXML
	Pane whiteturn;//輪到白子
	@FXML
	Text blackwinner;//黑子獲勝時顯示
	@FXML
	Text whitewinner;//白子獲勝時顯示
	
	
	

	
	
	public void backpressed() {//返回按鈕
		finalproject_107201020.currentStage.setScene(finalproject_107201020.menuScene);
	}

	public void handle(MouseEvent e) {
		
		Circle newblack = new Circle();
        newblack.setRadius(10);
        newblack.setStroke(Color.BLACK);
        newblack.setFill(Color.BLACK);
        //黑色棋子:newblack
        Circle newwhite = new Circle();
        newwhite.setRadius(10);
        newwhite.setStroke(Color.BLACK);
        newwhite.setFill(Color.WHITE);
        //白色棋子:newwhite
        
		
		if(e.getEventType() == MouseEvent.MOUSE_CLICKED) {//滑鼠點擊視窗之後			
			if(e.getSceneX()>=140 && e.getSceneX()<=440 && e.getSceneY() >=40 && e.getSceneY() <=340 && end == false) {
				//左上:(150,50),右上:(430,50),左下:(150,330),右下:(430,330)
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
				int xposition = (x-150)/20 + 4;//xposition為二維陣列的x位置
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
				int yposition = (y-50)/20 + 4;//yposition為二維陣列的y位置
				if(blackchess == true && chess[xposition][yposition] == 0 && end == false) {//輪到黑子下棋  blackchess == true
					newblack.setLayoutX(x);
					newblack.setLayoutY(y);
					pane.getChildren().add(newblack);
					chess[xposition][yposition] = 1;//沒有棋子放置:chess=0 黑子放置:chess=1 白子放置:chess=2
					blackchess = false;

				}
				else if(blackchess == false && chess[xposition][yposition] == 0 && end == false) {//輪到白子下棋 白子 blackchess == false
					newwhite.setLayoutX(x);
					newwhite.setLayoutY(y);
					pane.getChildren().add(newwhite);
					chess[xposition][yposition] = 2;//沒有棋子放置:chess=0 黑子放置:chess=1 白子放置:chess=2
					blackchess = true;
				}
				//判斷橫線是否連線
				for(int i=xposition-4;i<=xposition;i++) {
					if(chess[i][yposition] == chess[xposition][yposition] 
						&& chess[i+1][yposition] == chess[xposition][yposition]
						&& chess[i+2][yposition] == chess[xposition][yposition]
						&& chess[i+3][yposition] == chess[xposition][yposition]
						&& chess[i+4][yposition] == chess[xposition][yposition]) {
						backbutton.requestFocus();
						if(chess[xposition][yposition]==1) {
							blackwinner.setText("玩家 " + black.getText()+" 獲勝!");
							blackwinner.setFill(Color.BLUE);
							blackwinner.setVisible(true);
							end = true;
						}
						else {
							whitewinner.setText("玩家 " + white.getText()+" 獲勝!");
							whitewinner.setFill(Color.BLUE);
							whitewinner.setVisible(true);
							end = true;
						}
						break;//遊戲結束停止迴圈
					}
				}
				//判斷直線是否連線
				for(int j=yposition-4;j<=yposition;j++) {
					if(chess[xposition][j] == chess[xposition][yposition]
					&& chess[xposition][j+1] == chess[xposition][yposition]
					&& chess[xposition][j+2] == chess[xposition][yposition]
					&& chess[xposition][j+3] == chess[xposition][yposition]
					&& chess[xposition][j+4] == chess[xposition][yposition]) {
						backbutton.requestFocus();
						if(chess[xposition][yposition]==1) {
							blackwinner.setText("玩家 " + black.getText()+" 獲勝!");
							blackwinner.setFill(Color.BLUE);
							blackwinner.setVisible(true);
							end = true;
						}
						else {
							whitewinner.setText("玩家 " + white.getText()+" 獲勝!");
							whitewinner.setFill(Color.BLUE);
							whitewinner.setVisible(true);
							end = true;
						}
						break;//遊戲結束停止迴圈
					}
				}
				//判斷斜線(\)使否連線
				for(int i=0;i<=4;i++) {
					if(chess[xposition-4+i][yposition-4+i] == chess[xposition][yposition]
					&& chess[xposition-3+i][yposition-3+i] == chess[xposition][yposition]
					&& chess[xposition-2+i][yposition-2+i] == chess[xposition][yposition]
					&& chess[xposition-1+i][yposition-1+i] == chess[xposition][yposition]
					&& chess[xposition-0+i][yposition-0+i] == chess[xposition][yposition]) {
						backbutton.requestFocus();
						if(chess[xposition][yposition]==1) {
							blackwinner.setText("玩家 " + black.getText()+" 獲勝!");
							blackwinner.setFill(Color.BLUE);
							blackwinner.setVisible(true);
							end = true;
						}
						else {
							whitewinner.setText("玩家 " + white.getText()+" 獲勝!");
							whitewinner.setFill(Color.BLUE);
							whitewinner.setVisible(true);
							end = true;
						}
						break;//遊戲結束停止迴圈
					}					
				}
				//判斷反斜線(/)使否連線
				for(int i=0;i<=4;i++) {					
					if(chess[xposition-0+i][yposition+0-i] == chess[xposition][yposition]
					&& chess[xposition-1+i][yposition+1-i] == chess[xposition][yposition]
					&& chess[xposition-2+i][yposition+2-i] == chess[xposition][yposition]
					&& chess[xposition-3+i][yposition+3-i] == chess[xposition][yposition]
					&& chess[xposition-4+i][yposition+4-i] == chess[xposition][yposition]) {
						backbutton.requestFocus();
						if(chess[xposition][yposition]==1) {
							blackwinner.setText("玩家 " + black.getText()+" 獲勝!");
							blackwinner.setFill(Color.BLUE);
							blackwinner.setVisible(true);
							end = true;
						}
						else {
							whitewinner.setText("玩家 " + white.getText()+" 獲勝!");
							whitewinner.setFill(Color.BLUE);
							whitewinner.setVisible(true);
							end = true;
						}
						break ;//遊戲結束停止迴圈
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
	public void newpressed() throws IOException{//再來一局按鈕
		FXMLLoader loader = new FXMLLoader(getClass().getResource("checkboard.fxml"));
		Scene checkboardScene = new Scene(loader.load());
		checkboardcontroller controller = loader.<checkboardcontroller>getController();
		finalproject_107201020.currentStage.setScene(checkboardScene);
		blackname = black.getText();//紀錄black black紀錄先前TextField輸入的黑子使用者名稱
		whitename = white.getText();//紀錄white white紀錄先前TextField輸入的白子使用者名稱
		controller.black.setText(blackname);//設定black為 blackname
		controller.white.setText(whitename);//設定white為 whitename
		
	}

	public void passdata(TextField blackuser,TextField whiteuser) {	//將前一個頁面輸入的資料傳入	
		black.setText(blackuser.getText());
		white.setText(whiteuser.getText());

	}

	
	
	
	
}	

