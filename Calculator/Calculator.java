import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
class Calculator extends JFrame implements ActionListener{
    static Panel p1,p2; //declare 2 panels
    static Button ze,on,tw,th,fo,fi,si,se,ei,ni,ad,su,mu,di,eq,de,cl; //declare all your buttons
    static JTextField button; //declare your button;
    static JLabel l1; //declare your label;
    static Operatorr o = new Operatorr();
    static String fnum = "", snum = "";
    
    public void createGUI(){
       p1 = new Panel();
       
           //create first panel
   
           //create new textfield and add it to the panel
       button = new JTextField(10);
       p1.add(button);
       
   //create clear button and add to panel
       
       p1.add(cl);
           //create label and add to panel
       l1 = new JLabel("                                      ");
       p1.add(l1);
           //create a new panel with gridlayout
           //add all buttons to the panel
           //add both panels to frame
       p2 = new Panel();
       p2.setLayout(new GridLayout(4,4));
   
       
       p2.add(se);p2.add(ei);p2.add(ni);p2.add(ad);p2.add(fo);p2.add(fi);p2.add(si);p2.add(su);p2.add(on);p2.add(tw);p2.add(th);p2.add(mu);p2.add(ze);p2.add(de);p2.add(eq);p2.add(di);
       
       add(p2);
       add(p1, BorderLayout.SOUTH);
       }
    public void addListeners(){
        //add actionlistener to all number buttons
//add a different actionlistener to the operators and clear button
       ze = new Button("0");
       on = new Button("1");
       tw = new Button("2");
       th = new Button("3");
       fo = new Button("4");
       fi = new Button("5");
       si = new Button("6");
       se = new Button("7");
       ei = new Button("8");
       ni = new Button("9");
       ad = new Button("+");
       su = new Button("-");
       mu = new Button("*");
       eq = new Button("=");
       di = new Button("/");
       de = new Button(".");
     
      ze.addActionListener(this);
      on.addActionListener(this);  
      tw.addActionListener(this);  
      th.addActionListener(this);
      fo.addActionListener(this);
      fi.addActionListener(this);  
      si.addActionListener(this);  
      se.addActionListener(this); 
      ei.addActionListener(this);
      ni.addActionListener(this);  
      ad.addActionListener(o);  
      su.addActionListener(o);     
      mu.addActionListener(o);
      eq.addActionListener(o);  
      di.addActionListener(o);  
      de.addActionListener(this);  
      
      cl = new Button("Clear");
      cl.addActionListener(o);  
    }
    public Calculator(){
      addListeners();
      createGUI();
//call the above methods to setup your calculator
    }
    public void actionPerformed(ActionEvent e){
//You enter this method if a number was chosen
//clear the label
//Check a boolean flag to see if a number or an operator was chosen before
                //if operator then clear text otherwise append old number to new number
//update the textbox and set the boolean flag for operator to false
    String s = e.getActionCommand(); 
      if ((s.charAt(0) >= '0' && s.charAt(0) <= '9') || s.charAt(0) == '.') { 
            l1.setText("");
            if(snum != null && o.chosen == true){
               snum = snum + s.charAt(0);
               button.setText(snum); 
            }
            else{
               fnum = fnum + s.charAt(0);
               button.setText(fnum);
            }
            // set the value of text 
             
        } 
    
    }
    public static void main(String[] args){
      Calculator obj = new Calculator();
      obj.setDefaultCloseOperation(EXIT_ON_CLOSE);
      obj.setSize(350,350);
      obj.setVisible(true);
        //create calculator object
    }
}
class Operatorr extends Frame implements ActionListener{
    static String error;
    static double total, first, second;
    static boolean chosen=false;
    static String next, s , previous = null;  //point to an operator. Process previous first
    void init(){
        next=previous=null;
        total=0;
        chosen=false;
        Calculator.l1.setText("Cleared");
        Calculator.button.setText("");
        Calculator.snum = "";
        Calculator.fnum = "";
        
        //set label to clear
    }
    public void actionPerformed(ActionEvent e){
//if clear was chosen then reset the calculator, the labels, textfield and flags (init function)
           //if operator is chosen, make sure it wasn't chosen before (check boolean flag)
//based on the operator that was chosen before, do the proper calculations 
//and update total
//set the flag for operator having been chosen to true
//point to operator to be processed next time to the operator chosen
           //otherwise
                //two operators were chosen back to back and it is illegal
//come up with an error message in the label section
   
   if(chosen == false){
      s = e.getActionCommand();
      previous = e.getActionCommand();
   }
   else
   {
      next = e.getActionCommand();
      s = e.getActionCommand();
   }
   
   if (previous == next){
      chosen = true;
      }
   
   if (s.charAt(0) == 'C') {
      init();

   }
   if (s.charAt(0) == '+' && (chosen == false)) {
      Calculator.l1.setText("Adding");
      chosen = true;
      Calculator.button.setText("");
   }
   else if(s.charAt(0) == '-' && (chosen == false)){
      Calculator.l1.setText("Subtracting");
      chosen = true;
      Calculator.button.setText("");
   }
   else if(s.charAt(0) == '*' && (chosen == false)){
      Calculator.l1.setText("Multiplying");
      chosen = true;
      Calculator.button.setText("");
   }
   else if(s.charAt(0) == '/' && (chosen == false)){
      Calculator.l1.setText("Dividing");
      chosen = true;
      Calculator.button.setText("");
   }
   if(previous == next)
   {
      Calculator.l1.setText("Can't use the same operator!");
      Calculator.button.setText("");
   }
   
   if(s.charAt(0) == '='){
   
     switch(previous.charAt(0)){
      case '+':
         total = Double.valueOf(Calculator.fnum) + Double.valueOf(Calculator.snum);
         break;
      case '-':
         total = Double.valueOf(Calculator.fnum) - Double.valueOf(Calculator.snum);
         break;
      case '*':
         total = Double.valueOf(Calculator.fnum) * Double.valueOf(Calculator.snum);
         break;
      case '/':
         total = Double.valueOf(Calculator.fnum) / Double.valueOf(Calculator.snum);
         break;
     }
     
     
     String stotal = String.valueOf(total);
     Calculator.button.setText(stotal);
     Calculator.snum = "";
     Calculator.fnum = stotal;
     chosen = false;
   }
}

}