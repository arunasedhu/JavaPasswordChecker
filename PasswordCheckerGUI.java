import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
class PasswordCheckerGUI{
    public static void main(String args[]){
	PassGUI frame=new PassGUI();
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setLayout(new FlowLayout());
	frame.setVisible(true);
	frame.setSize(600,400);
    }
}
class PassGUI extends JFrame{
    JTextField pass;
    JTextArea res;
    JButton check;
    String val;
    PassGUI(){
	JLabel passtext=new JLabel("Enter your password:");
	check=new JButton("Check");
	pass=new JTextField(30);
	res=new JTextArea("");
	add(passtext);
	add(pass);
	add(check);
	add(res);
	
	check.addActionListener(new ActionListener(){
	    public void actionPerformed(ActionEvent e){
		String ps=pass.getText();
		val="";
		if(ps.length()<8){
	    	    val+="Password length should be atleast 8.";
		    res.setText(val);
		    return;
		}
		int p=0,sp=0,uc=0,lc=0,d=0,se=0,rp=0;
		for(int i=0;i<ps.length()-1;i++){
		    char c1=ps.charAt(i),c2=ps.charAt(i+1);
		    if(Character.isLetter(c1) || Character.isDigit(c1)){
	        	if(c1+1==c2 || c1-1==c2 || c1==c2){
		            if(c1==c2){
		                rp++;
		            }
		            else{
		                se++;
		            }
		        }
		        else{
		            p++;
		        }
		        if(Character.isDigit(c1)){
		            d++;
		        }
		        else if(Character.isUpperCase(c1)){
		            uc++;
		        }
		        else{
		            lc++;
		        }
		    }
		    else{
		        sp=1;
		        if(c1!=c2){
		            p++;
		        }
		        else{
		            rp++;
		        }
		    }
		}
		val+="Password Status:";
		if(p<=ps.length()/4 || (d==0 || (lc==0 && uc==0)) || rp>2){
		    val+="Weak\n";
		    val+=enhance(uc,lc,sp,d,se,rp);
		}
		else if(p<=ps.length()/2){
		    val+="Moderate\n";
		    val+=enhance(uc,lc,sp,d,se,rp);
		}
		else{
		    val+="Strong\n";
		}
		res.setText(val);
	    }	
	});

    }
    String enhance(int uc,int lc,int sp,int d,int se,int rp){
        String val="To enhance your password add:\n";
        if(se>2){
	    val+="    Avoid Sequence\n";
	}    
	if(rp>2){
	    val+="    Avoid Repetition\n";
	    }
        if(uc==0){
	    val+="    Uppercase Characters\n";
	}
	if(lc==0){
	    val+="    Lowercase Characters\n";
	}
	if(sp==0){
	    val+="    Special Characters\n";
	}
	if(d==0){
	    val+="    Digits\n";
	}
	return val;
    }

}	

	
	