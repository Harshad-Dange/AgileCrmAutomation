package com.agilecrmautomation;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestCase1 {
	/*
	 * @BeforeTest public void beforeTest() {
	 * System.out.println("This is beforeTest method"); }
	 * 
	 * @BeforeClass public void beforeClass() {
	 * System.out.println("This is beforeClass method");
	 * 
	 * }
	 * 
	 * @BeforeMethod public void beforeMethod() {
	 * System.out.println("This is before method"); }
	 */
	
	@Test(priority=1)
	public void login() throws Exception {
		System.out.println("This is login method");
		String actual="Test123";
		String expected="Test";
//		Assert.assertEquals(actual, expected, "Fail to validate actual with expected : ");
//		Assert.assertNotEquals(actual, expected, "Message");
//		Assert.assertNotNull(actual, expected);
		if(actual.equals(expected)) {
			//pass test case
		}else {
			Assert.fail("Actual value is not equals with Expected..");
		}
		
		
	}	
	
	@Test(priority=2, dependsOnMethods = {"login"})
	public void addCompany() throws Exception {
		System.out.println("This is addCompany method");
//		throw new Exception("fail to login in application");
	}
	
	@Test(priority=3,dependsOnMethods = {"login", "addCompany"})
	public void addContact() {
		System.out.println("This is addContact method");
	}
	
	@Test
	public void arraysImplementation() {
		List<Integer> a1=new ArrayList<>();
		a1.add(1);
		a1.add(2);
		a1.add(3);
		
		
		List<Integer> a2= new ArrayList<>();
		a2.add(11);
		a2.add(22);
		a2.add(33);
		
		List<List<Integer>> twoD=new ArrayList<>();
		twoD.add(a1);
		twoD.add(a2);
		
		System.out.println(twoD);
		
		for(List<Integer> list1: twoD) {			
			for(Integer val:list1) {
				System.out.println(val);
			}		
		}
		
		twoD.forEach(val->{
			val.forEach(v->{
				System.out.println(v);
			});
			
		});
		
	}
	
	
	
	@Test
	public void lambda() {
		
		LambdaImplementation obj=new LambdaImplementation() {
			@Override
			public void add(int a, int b) {
				// TODO Auto-generated method stub
				System.out.println(a+b);
			}
			
		};
		
		LambdaImplementation obj1=(a,b)->{
				// TODO Auto-generated method stub
				System.out.println(a+b);
		};
		
		obj.add(10,200);
		obj1.add(10,89);
		
	}
	
	
	
	
	
	/*
	 * @AfterMethod public void afterMethod() {
	 * System.out.println("This is after method"); }
	 * 
	 * @AfterClass public void afterClass() {
	 * System.out.println("This is afterClass method"); }
	 * 
	 * @AfterTest public void aftertest(){
	 * System.out.println("This is afterTest method");
	 * 
	 * }
	 * 
	 * 
	 */
	
	
	
	
	
	
	
	

}
