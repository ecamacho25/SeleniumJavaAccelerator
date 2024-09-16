
package com.se.seleniumJavaAccelerator.pages;

import com.se.seleniumJavaAccelerator.BaseTest;
import org.openqa.selenium.By;

public class HomePage extends BaseTest {

    // Interactive elements selectors
    By adminButton = By.className("admin");
    By logoImage = By.className("logo");
    By aboutLink = By.linkText("About Us");
    By servicesLink = By.linkText("Services");
    By productsLink = By.partialLinkText("Products");
    By locationsLink = By.partialLinkText("Locations");
    By adminPageLink = By.partialLinkText("Admin Page");
    By homeLink = By.partialLinkText("home");
    By aboutPageLink = By.partialLinkText("about");
    By contactLink = By.partialLinkText("contact");
    By usernameInput = By.name("username");
    By passwordInput = By.name("password");
    By loginButton = By.className("button");
    By forgotLoginInfoLink = By.partialLinkText("Forgot login info?");
    By registerLink = By.partialLinkText("Register");
    By firstNameInput = By.id("customer.firstName");
    By lastNameInput = By.id("customer.lastName");
    By addressInput = By.id("customer.address.street");
    By cityInput = By.id("customer.address.city");
    By stateInput = By.id("customer.address.state");
    By zipCodeInput = By.id("customer.address.zipCode");
    By phoneNumberInput = By.id("customer.phoneNumber");
    By ssnInput = By.id("customer.ssn");
    By usernameInputForm = By.id("customer.username");
    By passwordInputForm = By.id("customer.password");
    By repeatedPasswordInput = By.id("repeatedPassword");
    By registerButton = By.className("button");
    By homeLink2 = By.partialLinkText("Home");
    By aboutUsLink = By.partialLinkText("About Us");
    By servicesLink2 = By.partialLinkText("Services");
    By productsLink2 = By.partialLinkText("Products");
    By locationsLink2 = By.partialLinkText("Locations");
    By forumLink = By.partialLinkText("Forum");
    By siteMapLink = By.partialLinkText("Site Map");
    By contactUsLink = By.partialLinkText("Contact Us");
    By parasoftLink = By.partialLinkText("www.parasoft.com");

    // Methods to interact with elements
    public void clickAdminButton() {
        driver.findElement(adminButton).click();
    }

    public void clickLogoImage() {
        driver.findElement(logoImage).click();
    }

    public void clickAboutLink() {
        driver.findElement(aboutLink).click();
    }

    public void clickServicesLink() {
        driver.findElement(servicesLink).click();
    }

    public void clickProductsLink() {
        driver.findElement(productsLink).click();
    }

    public void clickLocationsLink() {
        driver.findElement(locationsLink).click();
    }

    public void clickAdminPageLink() {
        driver.findElement(adminPageLink).click();
    }

    public void clickHomeLink() {
        driver.findElement(homeLink).click();
    }

    public void clickAboutPageLink() {
        driver.findElement(aboutPageLink).click();
    }

    public void clickContactLink() {
        driver.findElement(contactLink).click();
    }

    public void enterUsername(String username) {
        clearAndEnterText(usernameInput, username);
    }

    public void enterPassword(String password) {
        clearAndEnterText(passwordInput, password);
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    public void clickForgotLoginInfoLink() {
        driver.findElement(forgotLoginInfoLink).click();
    }

    public void clickRegisterLink() {
        driver.findElement(registerLink).click();
    }

    public void enterFirstName(String firstName) {
        clearAndEnterText(firstNameInput, firstName);
    }

    public void enterLastName(String lastName) {
        clearAndEnterText(lastNameInput, lastName);
    }

    public void enterAddress(String address) {
        clearAndEnterText(addressInput, address);
    }

    public void enterCity(String city) {
        clearAndEnterText(cityInput, city);
    }

    public void enterState(String state) {
        clearAndEnterText(stateInput, state);
    }

    public void enterZipCode(String zipCode) {
        clearAndEnterText(zipCodeInput, zipCode);
    }

    public void enterPhoneNumber(String phoneNumber) {
        clearAndEnterText(phoneNumberInput, phoneNumber);
    }

    public void enterSSN(String ssn) {
        clearAndEnterText(ssnInput, ssn);
    }

    public void enterUsernameForm(String username) {
        clearAndEnterText(usernameInputForm, username);
    }

    public void enterPasswordForm(String password) {
        clearAndEnterText(passwordInputForm, password);
    }

    public void enterRepeatedPassword(String repeatedPassword) {
        clearAndEnterText(repeatedPasswordInput, repeatedPassword);
    }

    public void clickRegisterButton() {
        driver.findElement(registerButton).click();
    }

    public void clickHomeLink2() {
        driver.findElement(homeLink2).click();
    }

    public void clickAboutUsLink() {
        driver.findElement(aboutUsLink).click();
    }

    public void clickServicesLink2() {
        driver.findElement(servicesLink2).click();
    }

    public void clickProductsLink2() {
        driver.findElement(productsLink2).click();
    }

    public void clickLocationsLink2() {
        driver.findElement(locationsLink2).click();
    }

    public void clickForumLink() {
        driver.findElement(forumLink).click();
    }

    public void clickSiteMapLink() {
        driver.findElement(siteMapLink).click();
    }

    public void clickContactUsLink() {
        driver.findElement(contactUsLink).click();
    }

    public void clickParasoftLink() {
        driver.findElement(parasoftLink).click();
    }
}