package com.epam.ta.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProfilePage extends AbstractPage
{
	private String baseUrl = "https://github.com/";
	private final Logger logger = LogManager.getRootLogger();

    @FindBy(xpath = "//h1[@class='vcard-names']/span[@itemprop='name']")
	private WebElement nameElement;

	public ProfilePage(WebDriver driver, String username)
	{
		super(driver);
		PageFactory.initElements(this.driver, this);
		baseUrl = "https://github.com/" + username;
	}

    public String getName() {
	    String currentName = nameElement.getText();
	    logger.info("Current name is [" + currentName + "]");
        return currentName;
    }

	@Override
	public void openPage()
	{
		driver.navigate().to(baseUrl);
	}
}
