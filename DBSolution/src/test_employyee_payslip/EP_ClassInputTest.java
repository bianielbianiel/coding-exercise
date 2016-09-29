package test_employyee_payslip;


import static org.junit.Assert.*;

import java.util.Arrays;


import org.junit.Test;

import employee_payslip.EmployeePayslip;
import swing.EP_Swing;

public class EP_ClassInputTest {
	
		EmployeePayslip testEP = new EmployeePayslip();
		EP_Swing testEPS = new EP_Swing();		
		
		String actualString = "David,Rudd,60050,9%,3";
		String[] actualArray = actualString.split("\\,");
		
		String expectedString = "David Rudd,01 March–31 March,5004,922,4082,450";				
		String[] expectedArray = new String[]{"David Rudd","01 March–31 March","5004","922","4082","450"};
				
		
	@Test
	public void test() {		
		
		System.out.println(Arrays.toString(expectedArray));
		System.out.println(expectedString);
		//input Data
		
		System.out.print("First Name "+" " +"David");
		assertEquals("David", new EmployeePayslip(actualArray).getFirstName());		
		assertEquals("Rudd", new EmployeePayslip(actualArray).getLastName());
		assertEquals(60050, (new EmployeePayslip(actualArray).getAnnualSalary()));
		assertEquals(9, (new EmployeePayslip(actualArray).getPensionRate()));
		assertEquals("01 March-31 March", (new EmployeePayslip(actualArray).getPayPeriod()));		
		assertEquals(actualArray, testEPS.lineCSVtoArray(actualString));
		assertEquals("David,Rudd,60050,9%,3", (new EmployeePayslip(actualArray).inputToString()));			
		assertEquals(false, (new EmployeePayslip().isValid(new String[]{","+","})));
		assertEquals(true, (new EmployeePayslip().isValid(actualArray)));
		
	
		
	}
	
	
	
}
