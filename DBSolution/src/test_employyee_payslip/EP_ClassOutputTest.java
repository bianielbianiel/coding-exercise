package test_employyee_payslip;
import static org.junit.Assert.*;

import org.junit.Test;

import employee_payslip.EmployeePayslip;

public class EP_ClassOutputTest {
	
	
	String actualString = "David,Rudd,60050,9%,3";
	String[] actualArray = actualString.split("\\,");
	
	String expectedString = "David Rudd,01 March–31 March,5004,922,4082,450";				
	String[] expectedArray = new String[]{"David Rudd","01 March–31 March","5004","922","4082","450"};
	

	@Test
	public void test() {
		
		//Output Data
		assertEquals("David Rudd", new EmployeePayslip(actualArray).getEmployeeName());
		assertEquals("01 March-31 March", new EmployeePayslip(actualArray).getPayPeriod());
		assertEquals(5004, (new EmployeePayslip(actualArray).getGrossIncome()));
		assertEquals(922, (new EmployeePayslip(actualArray).getIncomeTax()));
		assertEquals(4082, (new EmployeePayslip(actualArray).getNetIncome()));
		assertEquals(450, (new EmployeePayslip(actualArray).getPensionContr()));				
		assertEquals("David Rudd", new EmployeePayslip(actualArray).getEmployeeName());
		assertEquals("Rudd", new EmployeePayslip(actualArray).getLastName());
	}

}

