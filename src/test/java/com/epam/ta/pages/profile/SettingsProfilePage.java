package com.epam.ta.pages.profile;

import com.epam.ta.pages.AbstractPage;
import com.epam.ta.utils.Utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SettingsProfilePage extends AbstractPage
{
	private final String BASE_URL = "https://github.com/settings/profile";
	private final Logger logger = LogManager.getRootLogger();

	@FindBy(xpath = "//input[@id='user_profile_name']")
    private WebElement inputName;

    @FindBy(xpath = "//button[text()='Update profile']")
    private WebElement buttonUpdate;

	public SettingsProfilePage(WebDriver driver)
	{
		super(driver);
		PageFactory.initElements(this.driver, this);
	}

	public String changeName(String name)
	{
		String profileName = name + Utils.getRandomString(3);
        inputName.clear();
        inputName.sendKeys(profileName);
        buttonUpdate.click();
		logger.info("[" + profileName + "] was set as profile name");
		return profileName;
	}

	@Override
	public void openPage()
	{
		driver.navigate().to(BASE_URL);
	}

}
