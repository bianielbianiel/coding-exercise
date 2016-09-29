package swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Timer;
import employee_payslip.EmployeePayslip;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.UIManager;
import java.awt.Font;
import java.awt.GridBagLayout;

public class EP_Swing extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	EmployeePayslip ep;
	private String openUrl;
	private String saveUrl;
	private String line;
	private String printTemp;
	private StringBuilder str;
	private String result;
	private JPanel northPanel;
	private JPanel cenerPanel;
	private JPanel southPanel;
	 
	private final JPanel validPanel = new JPanel(new GridBagLayout());
	private final JPanel inputPanel = new JPanel(new GridBagLayout());
	private final JPanel buttonsPanel = new JPanel();
	private JTextField filename = new JTextField(), dir = new JTextField();
	private JButton open = new JButton("Open"), save = new JButton("Save");
	private final JTextArea inputTA = new JTextArea(40, 45);
	private final JTextArea validTA = new JTextArea(40, 50);
	private final JScrollPane inputSP ;
	private final JScrollPane validSP ;
	private JPanel centerPanel;
	
	  public EP_Swing()  {
	    Container cp = getContentPane();	    
	    northPanel = new JPanel();
	    	northPanel.setLayout(new GridLayout(2, 1));
	    	dir.setFont(new Font("Tahoma", Font.BOLD, 15));
	    	northPanel.add(dir);
	    	dir.setEditable(false);
	    	filename.setFont(new Font("Tahoma", Font.BOLD, 15));
	    	northPanel.add(filename);
	    		filename.setEditable(false);
	    cp.add(northPanel, BorderLayout.NORTH);	
		
	    centerPanel = new JPanel(new GridBagLayout());
		    centerPanel.setBackground(Color.BLACK);
		    centerPanel.setLayout(new GridBagLayout());
		    	   	     
				     inputPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "..::: Input Data :::..", TitledBorder.CENTER, TitledBorder.TOP, null, Color.GRAY));
				     	inputTA.setMargin(new Insets(0,10,0,10));
					     inputTA.setFont(new Font("Arial Narrow", Font.PLAIN, 15));
					     inputTA.setForeground(new Color(255, 255, 0));
						 inputTA.setBackground(Color.BLACK);
						 inputTA.setEditable(false);
						 inputSP = new JScrollPane(inputTA,
						            JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
						            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
						 inputSP.setEnabled(false);
					inputPanel.add(inputSP);
					
					validPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "..::: Output Data :::..", TitledBorder.CENTER, TitledBorder.TOP, null, Color.GRAY));
						validTA.setMargin(new Insets(0,10,0,10));
					    validTA.setFont(new Font("Arial Narrow", Font.PLAIN, 15));
					    validTA.setForeground(new Color(173, 255, 47));
					    validTA.setBackground(Color.BLACK);
					    validTA.setEditable(false);
					    validSP = new JScrollPane(validTA,
					            JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
					            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
					    validSP.setEnabled(false);
				    validPanel.add(validSP);
				    	
			centerPanel.add(inputPanel);
		    centerPanel.add(validPanel);
		cp.add(centerPanel, BorderLayout.CENTER);
		    
	  
	    
	    southPanel = new JPanel(new GridLayout(0,1));
	   		buttonsPanel.add(open);
	    		buttonsPanel.add(save);
	    			save.addActionListener(new SaveL());
	    			open.addActionListener(new OpenL());
	    			save.setEnabled(false);
	    			southPanel.add(buttonsPanel);
	    cp.add(southPanel, BorderLayout.SOUTH);

	    //call displays welcome message
	    displayText(inputTA,"  Employee Payslip "+"\n"+
	    "only .csv or .doc or txt files in  \n"+
	    "Sinle line Input Format "+"\n\n"+
	    "first name, last name, annual salary, pension rate (%), payment start date"+"\n\n"+
	    "ex    David,Rudd,60050,9%,3"+"\n\n\n"+
		"please click Open button below");	   
	 }

	  
	  class OpenL implements ActionListener {
	    public void actionPerformed(ActionEvent e) {
	    	validTA.setText("");
	   	displayText(validTA,"Welcome "+"\n\n"+
	    "Please select the file you want to open and  press Open button");
	    	inputTA.setText("");
	    	
	    	 
	    	
	    	//file chooser filters to specify with files are valid entry
	      JFileChooser c = new JFileChooser(); 	      
	     	c.removeChoosableFileFilter(c.getFileFilter());//remove default  All Files from Files of type list
	      FileNameExtensionFilter filterTxt = new FileNameExtensionFilter("Notepad  Document .txt", "txt");
	        c.setFileFilter(filterTxt);
		  FileNameExtensionFilter filterDoc = new FileNameExtensionFilter("Microsoft Word  Document  .doc", "doc");
		    c.setFileFilter(filterDoc);		        
		  FileNameExtensionFilter filterCsv = new FileNameExtensionFilter("Microsoft Excel  Document  .csv", "csv");
		    c.setFileFilter(filterCsv);	   
	      // Demonstrate "Open" dialog:
	      int rVal = c.showOpenDialog(EP_Swing.this);
	      if (rVal == JFileChooser.APPROVE_OPTION) {
	        filename.setText(c.getSelectedFile().getName());
	        dir.setText(c.getCurrentDirectory().toString());
	        validTA.setText("");
	        setOpenUrl(c.getCurrentDirectory().toString()+"\\"+c.getSelectedFile().getName());
	        start();
	      }
	      //cancel button open open dialog
	      if (rVal == JFileChooser.CANCEL_OPTION) {
	    	  	save.setEnabled(false);
	    	  	validTA.setText("");
		    	inputTA.setText("");
	        filename.setText("You pressed cancel");
	        dir.setText("");
	      }
	    }
	  }

	  class SaveL implements ActionListener {
	    public void actionPerformed(ActionEvent e) {
	    	
	      JFileChooser c = new JFileChooser();
	      
	      c.removeChoosableFileFilter(c.getFileFilter());//remove default  All Files from Files of type list
	      
	      FileNameExtensionFilter filterTxt = new FileNameExtensionFilter(".txt", "txt");
	        c.setFileFilter(filterTxt);

		  FileNameExtensionFilter filterDoc = new FileNameExtensionFilter(".doc", "doc");
		    c.setFileFilter(filterDoc);
		    c.setFileFilter(filterDoc);
		        
		  FileNameExtensionFilter filterCsv = new FileNameExtensionFilter(".csv", "csv");
		    c.setFileFilter(filterCsv);
	      // Demonstrate "Save" dialog:
	      int rVal = c.showSaveDialog(EP_Swing.this);
	      if (rVal == JFileChooser.APPROVE_OPTION) {
	        filename.setText(c.getSelectedFile().getName());
	        dir.setText(c.getCurrentDirectory().toString());
	    	 
	        setSaveUrl(c.getSelectedFile()+""+c.getFileFilter().getDescription());
	        
	        saveFile(getSaveUrl());
	        open.setEnabled(true);
	      }
	    //cancel button save open dialog
	      if (rVal == JFileChooser.CANCEL_OPTION) {
	        filename.setText("You pressed cancel");
	        dir.setText("");
	        
	      }
	    }
	  }
	   private int charIndex;
	  public void displayText(final JTextArea with, final String inText) {
		  charIndex = 0;
			 Timer timer1 = new Timer(1, new ActionListener() {
	                @Override
	                public void actionPerformed(ActionEvent e) {
	                    printTemp = with.getText();
	                    printTemp += inText.charAt(charIndex);
	                    with.setText(printTemp);
	                    charIndex++;
	                    if (charIndex ==inText.length()) {
	                        ((Timer)e.getSource()).stop();
	                    }
	                }
	            });

					 timer1.setRepeats(true);
				    timer1.setCoalesce(true);
				    timer1.setInitialDelay(100);
					timer1.start();
	charIndex = 0;
		}//end displayText()
	  
	  
	 //Open Save url's getters and setters
	  public String getOpenUrl() {
			return openUrl;
		}

		public void setOpenUrl(String openUrl) {
			this.openUrl = openUrl;
		}
		
		public String getSaveUrl() {
				return saveUrl;
		}

		public void setSaveUrl(String saveUrl) {
				this.saveUrl = saveUrl;
		}	 
	 
	  
	  public  void start() {
		
		
		  
		 
			BufferedReader br = null;
			
			try {	
				File file = new File(openUrl);
				//File file = new File("C:\\Users\\budus\\Desktop\\test.txt");
				
				line = new String();
				br = new BufferedReader(new FileReader(file));
			
					//  read file  line by line?
					while ((line = br.readLine()) != null) {
													
							//open.setEnabled(false);//open button set FALSE
							save.setEnabled(true);//save button set TRUE
							
						//	System.out.println(line);
							ep = new  EmployeePayslip(lineCSVtoArray(line));
							
							if(ep.isValid(lineCSVtoArray(line))){
								System.out.println(("TRUE "+ep.output()));
								inputTA.append(ep.inputToString()+"\n");
								validTA.append(ep.outputToString()+"\n");
							}else{
								inputTA.append("! ERROR !! "
										+ ""+"..::: "+line+" :::.."+"\n");
								validTA.append("\n");
								System.out.println(("\n"+"FALSE  ")+line.toString()+"\n\n");
							}
							
							
								
						
					}
				
			} catch (IOException e) {
				System.out.println("IOException first catch");
				e.printStackTrace();
			} finally {
				try {
				
					if (br != null) br.close();
				} catch (IOException e) {
					System.out.println("IOException second catch");
					e.printStackTrace();
				}
			}
			
		}
	  
	  
	//count how many lines in CSV File
		private int countLines(String url) throws FileNotFoundException{
			File f = new File(url);
			String[] fileLines=null;
            try {
                byte[] bytes = Files.readAllBytes(f.toPath());
                String asString =  new String(bytes,"UTF-8");
                 fileLines = asString.split("\r\n|\r|\n", -1);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            
     	   return fileLines.length-1;
		}
	  
	  

		// Utility which converts CSV to String[] using Split operation
		public   String[] lineCSVtoArray(String lineCSV) {
			
				String[] splitData = lineCSV.split("\\s*,\\s*");
				String[] result = new String[splitData.length];
				System.arraycopy(splitData, 0, result, 0, result.length);
			
			
				return result;
		}
		
		
		
	
	//method saving file in chosen directory  
public void saveFile(String saveURL){
	
	
	String[] toSave = validTA.getText().toString().split("\n");//get all 
		
	File file = new File(saveURL);
	try {
		FileWriter fw;
		fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(validTA.getText().toString());
		validTA.setText("..::: SAVING :::..\n"+
		"File URL "+saveUrl.toString()+"\n\n");
		
		inputTA.setText("");//clear input Data JTextArea 
		for (int i=0; i<=toSave.length-1;i++){
			validTA.append(toSave[i]+"\n");
			
			
		System.out.println("Saving >>> "+toSave[i].toString());
		}
		System.out.println("Saving Done ");
		validTA.append("..::: SAVING successfull :::..");
		save.setEnabled(false);
		bw.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally{
		
		
	}
}
		
	  public static void main(String[] args) {
	    run(new EP_Swing(), 1200, 1000);
	    
	  }

	  public static void run(JFrame frame, int width, int height) {
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setSize(width, height);
	    frame.setTitle("<< , , . . : : : CODING EXERCISE : : : . ., ,   EmployeePayslip designed and coded by  Daniel Bialek    bianielbianiel@gmail.com   0879870339 >>            ");
	    frame.setVisible(true);
	  
	    
	  }

}
