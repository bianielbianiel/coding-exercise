package employee_payslip;
import java.text.DecimalFormat;




public class EmployeePayslip {
	
	//in employee data
	private String firstName;
	private String lastName;
	private int annualSalary;
	private int pensionRate;//pensionRate(%)
	private int month;//payment start month
	private int incomeTax;
	
	private final int IN_LEN =5;
	
	//constructors
	public EmployeePayslip(String firstName, String lastName, String annualSalary, String pensionRate,String month) {
		super();
		this.firstName = firstName.trim();
		this.lastName = lastName.trim();
		this.annualSalary = Integer.parseInt(annualSalary.trim());
		String pension = pensionRate.trim().substring(0, pensionRate.length()-1);
		this.pensionRate = Integer.parseInt(pension);
		
		
		this.month = Integer.parseInt(month.trim());
		setIncomeTax(this.annualSalary);
	}
	
	
	public EmployeePayslip(String[] cvsRow){
		super();
		isValid(cvsRow);			
	}	
	
	public EmployeePayslip() {
		// TODO Auto-generated constructor stub
	}//end constructors

	//getters and setters
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public int getAnnualSalary() {
		return annualSalary;
	}
	
	public void setAnnualSalary(int annualSalary) {
		
		this.annualSalary = annualSalary;
	}
	
	public int getPensionRate() {
		return (pensionRate);
	}
	
	public void setPensionRate(int pensionRate) {
		this.pensionRate = pensionRate;
	}
	
	public int getMonth() {
		return month;
	}
	
	public void setMonth(int month) {
		this.month = month;
	}
	
	public String getPayPeriod(){
		String month = "";
		try{
			switch(Integer.parseInt(String.valueOf(getMonth()))){
				case 1:{month = "01 January-31 January";	}break;
				case 2:{month = "01 February-28 February";	}break;
				case 3:{month = "01 March-31 March";		}break;
				case 4:{month = "01 April-30 April";		}break;
				case 5:{month = "01 May-31 May";			}break;
				case 6:{month = "01 June-30 June";			}break;
				case 7:{month = "01 July-31 July";			}break;
				case 8:{month = "01 August-31 August";		}break;
				case 9:{month = "01 September-30 September";}break;
				case 10:{month = "01 October-31 October";	}break;
				case 11:{month = "01 November-30 Novebber";	}break;
				case 12:{month = "01 December-31 December";	}break;
				default:{month = "month-ERROR!";			}break;
			}
			
		}catch(NumberFormatException e){System.out.println("wrong month format");}

		return month;
	}
	
	 public String customFormat(String pattern, int value ) {
	      DecimalFormat myFormatter = new DecimalFormat(pattern);
	      String output = myFormatter.format(value);
	      return output;
	   }
	
	public void setIncomeTax(int salary){
				
		if(salary>=0 && salary <=18200){
			this.incomeTax =0;			
		}else if(salary>= 18201 && salary <=37000){			
			double tax = ((salary-18200)*0.19)/12;
			this.incomeTax = roundValue(tax);			
		}else if (salary>= 37001 && salary <=80000){			
			double tax = (3572+(salary-37000)*0.325)/12;
			this.incomeTax = roundValue(tax);			
		} else if (salary>= 80001 && salary <=180000){			
			double tax = (17547+(salary-80000)*0.37)/12;
			this.incomeTax = roundValue(tax);			
		}else if (salary>= 180001 ){			
			double tax = (54547+(salary-180000)*0.45)/12;
			this.incomeTax = roundValue(tax);
		}
	}
	
	public int getIncomeTax(){
		return incomeTax;
	}
	
	public String getEmployeeName(){
		return firstName+" "+lastName;
	}	
	
	public int getGrossIncome(){
		return  roundValue(getAnnualSalary()/12f);
	}		
		
	public int getNetIncome(){
		return getGrossIncome()-getIncomeTax() ;
	}	
		
	public int getPensionContr(){
		return roundValue(getGrossIncome()*((double)getPensionRate()/100));
	}
	//end getters and setters
	
	
	
	//round value up if >0.50 and down if value<0.50
	public int roundValue(double value){	
		String[] round = String.valueOf(value).split("\\.");
		int i = Integer.parseInt(round[0]);
		int ii = Integer.parseInt(round[1].substring(0,1));		
		if(ii>=5){i+=1;}
		else{	}		
		return (i);
	}
	
	//@Override
	public String outputToString() {		
		return getEmployeeName()+","+getPayPeriod()+","+getGrossIncome()+","+getIncomeTax()+","+getNetIncome()+","+getPensionContr();		
	}	
	
	public boolean isValid(String[] inRow){
		
		if((inRow.length!=IN_LEN)||inRow[1].equals("")||inRow[2].equals("")
				||(inRow[3].equals("")  ||( (inRow[3].charAt(inRow[3].length()-1)!='%') || ( inRow[3].length()-1 <1)))
				||inRow[4].equals(""))
				{
			
			return false;
		}else {
			
			for(int i=0; i<=inRow.length; i++){
				switch(i){
				case 0:{firstName=inRow[i];}break;				
				case 1:{lastName=inRow[i];}break;				
				case 2:{
					try{
						annualSalary=Integer.parseInt(inRow[i]);
						setIncomeTax(this.annualSalary);
					}catch(NumberFormatException e){
						inRow[i].equals("");
					}					
				}break;				
				case 3:{					
						try{
							pensionRate=Integer.parseInt(inRow[i].substring(0, inRow[i].length()-1));
							setIncomeTax(this.annualSalary);
						}catch(NumberFormatException e){
							inRow[i].equals("");
						}
				}break;				
				case 4:{
					try{
						month=Integer.parseInt(inRow[i]);						
					}catch(NumberFormatException e){
						inRow[i].equals("");
					}				
				}break;
					default:{}break;
				}
			}
		}
		//System.out.println("<<OUT_ROW "+getEmployeeName()+","+getPayPeriod()+","+getGrossIncome()+","+getIncomeTax()+","+getNetIncome()+","+getPensionContr());
		
		return true;
	}
	
	public String inputToString() {	
			return getFirstName()+","+getLastName()+","+getAnnualSalary()+","+getPensionRate()+"%,"+getMonth();
	}
	
	public String output(){
		return 
				""+"\n"+
				"+---------------------+--------------------------+"+"\n"+
				String.format("|%-20.30s | %-25.30s|%n", "<<Employee>> ", "<< INPUT DATA >>")+
				"+---------------------+--------------------------+"+"\n"+
				String.format("|%-20.30s | %-25.30s|%n", "First Name ", getFirstName())+
				String.format("|%-20.30s | %-25.30s|%n", "Last Name ", getLastName())+
				String.format("|%-20.30s | %-25.30s|%n", "Annual Salary ", customFormat("###,###.##",getAnnualSalary() ))+
				String.format("|%-20.30s | %-25.30s|%n", "Pension Rate(%) ", getPensionRate()+"%")+
				String.format("|%-20.30s | %-25.30s|%n", "Payment Start Date ", getPayPeriod())+
				"+---------------------+--------------------------+"+"\n\n"+
				
				
				""+"\n"+
				"+---------------------+--------------------------+"+"\n"+
				String.format("|%-20.30s | %-25.30s|%n", "<<Employee>> ", "<< OUTPUT DATA >>")+
				"+---------------------+--------------------------+"+"\n"+
				String.format("|%-20.30s | %-25.30s|%n", "Name ", getEmployeeName())+
				String.format("|%-20.30s | %-25.30s|%n", "Period ", getPayPeriod())+
				String.format("|%-20.30s | %-25.30s|%n", "Gross Income ", customFormat("###,###.##", getGrossIncome()))+
				String.format("|%-20.30s | %-25.30s|%n", "Income Tax ", customFormat("###,###.##", getIncomeTax()))+
				String.format("|%-20.30s | %-25.30s|%n", "Net Income ", customFormat("###,###.##", getNetIncome()))+
				String.format("|%-20.30s | %-25.30s|%n", "Pension contr ", customFormat("###,###.##", getPensionContr()))+
				"+---------------------+--------------------------+"+"\n\n";
	}
	
	

}

