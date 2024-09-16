package com.se.seleniumJavaAccelerator;

import org.testng.annotations.Test;
import io.qameta.allure.Description;

import com.se.seleniumJavaAccelerator.pages.HomePage;

public class HomePageTest extends HomePage {
	
	
	@Test
	@Description("This test attempts to log into the website using a lo test 2-Factor Authentication.")
	public void InputName () {
		
		enterFirstName("Edfier");
		waitFor(4);
		
	}

}
