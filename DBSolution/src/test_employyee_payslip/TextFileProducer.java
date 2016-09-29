package test_employyee_payslip;


//this class produced a samples of 
//	.csv  
//	.doc  
//	.txt 
//	files on custom directory



import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;

import javax.swing.JOptionPane;

public class TextFileProducer {
	String[] row;
	public static void main(String[] args) {
		
		try {
			
			String[] ext = new String[]{".doc",".txt",".csv"};
			
			for(int x=0; x<=ext.length-1;x++){
					StringBuilder str = new StringBuilder("");
					String content = "FirstName,LastName,AnnualSalary,pensionRate,PaymentStartDay";
					String[] row = content.split(",");
					//enter url change for your own (budus\\Desktop\\test) path
					File file = new File("C:\\Users\\abc\\Desktop\\test"+ext[x]);
		
					// if file doesnt exists, then create it
					if (!file.exists()) {
						file.createNewFile();
					}
					//str.append("FIRSTNAME,LASTNAME,ANNUALSALARY,PENSIONRATE,PAYMENTSTARTDAY\n");
					for(int i = 1; i<=50; i++){
					int annualSalary =(int) Math.abs((int)1+Math.random()*180000);
					int pensionRate =(int) Math.abs((int)1+Math.random()*50);
					int month =(int) Math.abs((int)1+Math.random()*12);
						
					for( int j = 0; j<=row.length-1; j++){
							switch(j){
								case 0:str.append(row[j]+i+",");break;
								case 1:str.append(row[j]+i+",");break;
								case 2:str.append(annualSalary+",");break;
								case 3:str.append(( pensionRate)+"%,");break;
								case 4:str.append(month+"");break;
							}
													
							if(j==row.length-1){
								str.append("\n");
							}
							row = content.split(",");
						}
						
					}
					FileWriter fw = new FileWriter(file.getAbsoluteFile());
					BufferedWriter bw = new BufferedWriter(fw);
					bw.write(str.toString());
					bw.close();
		
					System.out.println(ext[x]+" Done ");
					}
					
		
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

	static public String customFormat(String pattern, double value ) {
	      DecimalFormat myFormatter = new DecimalFormat(pattern);
	      String output = myFormatter.format(value);
	      return output;
	   }
}

