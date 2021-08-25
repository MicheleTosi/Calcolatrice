package calculator;
	
import javafx.application.Application;
import javafx.scene.input.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Calculator extends Application {

	Button[] button;
	TextField textfield1, textfield2;
	static double num1=0;
	static double num2=0;
	static char op=0;
	static boolean control=true;
	static String result;
	
	@Override
	public void start(Stage stage) {
		button=new Button[19];
		textfield1=new TextField();
		textfield1.setEditable(false);
		textfield1.setAlignment(Pos.CENTER_RIGHT);
		textfield2=new TextField("0");
		textfield2.setAlignment(Pos.CENTER_RIGHT);
		textfield1.setMaxWidth(195);
		textfield2.setMaxWidth(195);
		result= new String();
		
		stage.setTitle("Calculator");

        GridPane root = new GridPane();
        root.setHgap(5);
        root.setVgap(5);
        root.setAlignment(Pos.CENTER);
               
        Scene scene=new Scene(root, 250, 250);
        root.add(textfield1, 0,0, 6, 1);
        root.add(textfield2, 0,1, 6, 1);
        
        for(int j =3; j>0;j--) {
        	for(int i=1;i<4;i++) {
        		int z=10-(3*(j-1)+i);
				button[z]= new Button(String.valueOf(z));
				root.add(button[z],3-i, j+1);
				button[z].setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent event) {
						if(!control) {
							textfield2.setText("0");
							control=true;
						}
						if(textfield2.getText().equals("-0")) {
							textfield2.setText('-'+button[z].getText());
						}else if(textfield2.getText().equals("0")){
							textfield2.setText(button[z].getText());
						}else{
							textfield2.setText(textfield2.getText()+z);
						}
					}
				});
        	}
        }
        button[0]= new Button("0");
        root.add(button[0], 0, 5);
        button[0].setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				if(!control) {
					textfield2.setText("0");
					control=true;
				}
				if(!textfield2.getText().equals("0")) {
					textfield2.setText(textfield2.getText()+"0");
				}
			}
		});
        
        button[18]= new Button("AC");
        root.add(button[18], 3, 2, 2,1);
        button[18].setPrefSize(75,35);
        button[18].setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				textfield1.setText("0");
				textfield2.setText("0");
				num1=0;
				num2=0;
				op=0;
			}
		});
        
        button[17]=new Button("±");
        root.add(button[17], 2,5);
        button[17].setPrefSize(35,35);
        button[17].setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				if((textfield2.getText()).charAt(0)!=('-')) {
					textfield2.setText('-'+(textfield2.getText()).substring(0, textfield2.getText().length()));
				}else{
					textfield2.setText((textfield2.getText()).substring(1, textfield2.getText().length()));
				}
			}
		});

        
        button[10]= new Button("C");
        root.add(button[10], 4, 3);
        button[10].setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				if(textfield2.getText().length()>1) {
					textfield2.setText((textfield2.getText()).substring(0, textfield2.getText().length() - 1)); 
				}else {
					textfield2.setText("0");
				}
			}
		});
        
        button[14]=new Button("+");
        root.add(button[14], 3, 3);
        button[14].setPrefSize(35, 75);
        button[14].setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				if(num1==0) {
					num1=Double.valueOf(textfield2.getText());
					textfield1.setText(""+num1);
					op='+';
				}else {
					button[16].fire();
					num1=Double.valueOf(textfield2.getText());
					op='+';
				}
				result=(textfield1.getText()+"+");
				textfield1.setText(result);
				textfield2.setText("0");
				textfield2.positionCaret(1);
			}
		});
        button[12]=new Button("-");
        root.add(button[12], 3, 4); 
        button[12].setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				if(num1==0) {
					num1=Double.valueOf(textfield2.getText());
					textfield1.setText(""+num1);
					op='-';
				}else {
					button[16].fire();
					num1=Double.valueOf(textfield2.getText());
					op='-';
				}
				result=(textfield1.getText()+"-");
				textfield1.setText(result);
				textfield2.setText("0");
				textfield2.positionCaret(1);
			}
        });
        button[13]=new Button("*");
        root.add(button[13], 4, 4);
        button[13].setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				if(num1==0) {
					num1=Double.valueOf(textfield2.getText());
					textfield1.setText(""+num1);
					op='*';
				}else {
					button[16].fire();
					num1=Double.valueOf(textfield2.getText());
					op='*';
				}
				result=("("+textfield1.getText()+")"+"*");
				textfield1.setText(result);
				textfield2.setText("0");
				textfield2.positionCaret(1);
			}
        });
        button[11]=new Button("/");
        root.add(button[11], 4, 5);
        button[11].setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				if(num1==0) {
					num1=Double.valueOf(textfield2.getText());
					textfield1.setText(""+num1);
					op='/';
				}else {
					button[16].fire();
					num1=Double.valueOf(textfield2.getText());
					op='/';
				}
				result=("("+textfield1.getText()+")"+"/");
				textfield1.setText(result);
				textfield2.setText("0");
				textfield2.positionCaret(1);
			}
        });
        
        button[16]=new Button("=");
        button[16].setPrefSize(35, 35);
        root.add(button[16], 1, 5);
        button[16].setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				num2 = Double.valueOf(textfield2.getText());
				switch(op){
					case '+':
						result=result+num2;
						textfield1.setText(result);
						textfield2.setText(String.valueOf(num1+num2));
						break;
					case '-':
						result=result+num2;
						textfield1.setText(result);
						textfield2.setText(String.valueOf(num1-num2));
						break;
					case '*':
						result=result+num2;
						textfield1.setText(result);
						textfield2.setText(String.valueOf(num1*num2));
						break;
					case '/':
						result=result+num2;
						textfield1.setText(result);
						textfield2.setText(String.valueOf(num1/num2));
						break;
					default : break;
				}
				num1=0;
				num2=0;
				op=0;
				control=false;
			}
        });
        
        button[15]=new Button(".");
        root.add(button[15], 3, 5);
        button[15].setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {		
				if(!control) {
					textfield2.setText("0");
					control=true;
				}
				textfield2.setText(textfield2.getText()+".");
			}
		});
        
        for(int c=0;c<16;c++) {
			button[c].setPrefSize(35, 35);
		}
      
        textfield2.textProperty().addListener((observable, oldValue, newValue)-> {
        	if(newValue.length()>1) {
	        	char c=newValue.charAt(1);
	        	switch(c) {
	        	case '+':
	            	textfield2.setText("0");
	        		break;
	        	case '-':
	        	    textfield2.setText("0");
	        		break;
	        	case '*':
	            	textfield2.setText("0");
	        		break;
	        	case '/':
	            	textfield2.setText("0");
	        		break;
	        	default:break;
	        	}
	        	if(newValue.charAt(0)=='0' && newValue.charAt(1)!='.' && newValue.charAt(1)!='0') {
	        		textfield2.setText(newValue.replace('0', ' '));
	        	}
        	}
        });
        
        textfield2.setOnKeyPressed(new EventHandler<KeyEvent>()
        		{
        			public void handle(KeyEvent ke) {
        				if(ke.getCode().equals(KeyCode.ENTER)) {
        					button[16].fire();
        				}
            			if(ke.getCode().equals(KeyCode.ADD)) {
            				button[14].fire();
            			}
        				if(ke.getCode().equals(KeyCode.SUBTRACT)) {
        						button[12].fire();
        				}
        				if(ke.getCode().equals(KeyCode.MULTIPLY)) {
        					button[13].fire();
        				}
        				if(ke.getCode().equals(KeyCode.DIVIDE)) {
        					button[11].fire();
        				}
        			}
        			
        		});
        
        stage.setScene(scene);
		stage.show();
		
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
