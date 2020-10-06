import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.awt.Color;
import java.awt.Button;
import java.awt.GridLayout;

class Collection extends JFrame{
	Collection(){
		setSize(650,170);
		setTitle("Collection Graphics");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridLayout(2,1));
		
		JPanel panel = new JPanel();
		JLabel message = new JLabel();
		
		ActionButton gen = new ActionButton("Generate", message, Color.RED);
		ActionButton que = new ActionButton("Queue", message, new Color(0,176,80));
		ActionButton lst = new ActionButton("List", message, new Color (0,112,192));
		ActionButton vec = new ActionButton("Vector", message, Color.BLACK);
		ActionButton map = new ActionButton("Map", message, new Color(227,108,10));
		
		panel.setLayout(new GridLayout(1,5));
		panel.add(gen);panel.add(que);panel.add(lst);panel.add(vec);panel.add(map);
		
		message.setHorizontalAlignment(JLabel.CENTER);
		
		add(panel);
		add(message);
		setVisible(true);
   }     
   public static void main(String[] args){
      new Collection();
   }

 }
class ActionButton extends Button implements ActionListener{
	static Stack <Integer> stack = new Stack <Integer>();
	static Queue <Integer> queue = new LinkedList <Integer>();
	static Vector <Integer> vector = new Vector <Integer>();
	static Map <Integer, Integer> map = new HashMap <Integer, Integer>();
	static List <Integer> quel = new ArrayList <Integer>();
	static List <Integer> genl = new ArrayList <Integer>();
	static List <Integer> lisl = new ArrayList <Integer>();
	static Random rand = new Random(System.currentTimeMillis());
	
	int numClick;
	JLabel message;
	
	ActionButton(String str, JLabel label, Color color){
		super(str);
		message = label;
		setForeground(color);
		addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e){
		String click = ((Button)e.getSource()).getLabel();
		
		if(click == "Generate"){
			genAct();
		
		}else if(click == "Queue"){
			queAct();
		}else if(click == "List"){
			lisAct();
		}else if(click == "Vector"){
			vecAct();
		}else if(click == "Map"){
			mapAct();
		}else{
			System.out.println("Unknown button: " + click);
		}
    }
	void genAct(){
		String dis = "";
		
		if(stack.isEmpty()){
			genl.clear();
			numClick = 0;
		}
		
		if(numClick < 3){
			for(int i = 0; i < 5;i++){
				stack.push(rand.nextInt(100) + 1);
				genl.add(stack.peek());
			}
			numClick++;
		}else
		{ dis = "Stack is Full ";}
	
		for(Integer element : genl){
			dis += element.toString() + " ";
		}
		
		message.setText(dis);
	}
	void queAct(){
		String qDis = "";
		
		if(queue.isEmpty()){
			quel.clear();
		}
      
      if(stack.isEmpty() && queue.isEmpty()){
         qDis = "Queue is empty ";
      }
      else{
         if(!stack.isEmpty()){
               quel.add(stack.pop());
              }
          else
            qDis = "Stack is empty ";  
            
            for(Integer ele : quel){
               queue.add(ele);
               qDis += ele.toString() + " ";
            }  
        }
        
        message.setText(qDis);  
               
	}
   
   void lisAct(){
      String lDis = "";
      
      if(lisl.isEmpty()){
			lisl.clear();
		}
     
     if(queue.isEmpty() && lisl.isEmpty()){
     lDis = "List is empty ";
      }
      else{
         if(!quel.isEmpty()){
            lisl.add(quel.get(0));
            quel.remove(0); 
            queue.remove();  
              }
          else
            lDis = "Queue is empty ";  
            
            for(Integer ele : lisl){
               lDis += ele.toString() + " ";
            }  
        }   
   
      message.setText(lDis);
   }
   
   void vecAct(){
      String vDis = "";
      
      if(vector.isEmpty()){
			vector.clear();
		}
     
     if(vector.isEmpty() && lisl.isEmpty()){
     vDis = "Vector is empty ";
      }
      else{
         if(!lisl.isEmpty()){
            Integer temp = lisl.get(0);         
               vector.add(temp);
               lisl.remove(0);
              }
          else
            vDis = "List is Empty ";  
            
            for(Integer ele : vector){
               vDis += ele.toString() + " ";
            }  
        }   
   
      
      message.setText(vDis);
   }
   
   void mapAct(){
      String mDis = "";
      
      Iterator <Integer> values;
      
      
      if(map.isEmpty()){
			map.clear();
		}
     
     if(map.isEmpty() && vector.isEmpty()){
     mDis = "Map is empty";
      }
      else{
         if(!vector.isEmpty()){     
            map.put(rand.nextInt(100) + 1,vector.firstElement());
            vector.remove(0);
              }
          else{
            mDis = "Vector is Empty "; 
          }
            
            Set entrySet = map.entrySet(); 
            Iterator keys = entrySet.iterator();
            
            
            while(keys.hasNext()){
               Map.Entry m = (Map.Entry)keys.next();
               mDis += m.getKey() + "=" + m.getValue() + " ";
            }  
        }   
      
      message.setText(mDis);
   }
}
