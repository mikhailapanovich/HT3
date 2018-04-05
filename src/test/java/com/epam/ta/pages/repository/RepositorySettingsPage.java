package com.epam.ta.pages.repository;

import com.epam.ta.pages.AbstractPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

public class RepositorySettingsPage extends AbstractPage
{
	private String baseUrl;
	private final Logger logger = LogManager.getRootLogger();

    @FindBy(xpath = "//button[contains(text(), 'Delete')]")
	private WebElement buttonDelete;

	@FindBy(xpath = "//input[@aria-label='Type in the name of the repository to confirm that you want to delete this repository.']")
	private WebElement inputVerify;

	@FindBy(xpath = "//button[text()='I understand the consequences, delete this repository']")
	private WebElement buttonVerify;

	public RepositorySettingsPage(WebDriver driver, String username, String repoName)
	{
		super(driver);
		PageFactory.initElements(this.driver, this);
		baseUrl = "https://github.com/" + username + "/" + repoName + "/settings";
	}

	public void deleteRepository(String repoName) {
		buttonDelete.click();
		inputVerify.click();
		inputVerify.sendKeys(repoName);
		//TODO: make button enabled
        // there is some condition that trigger javascript to make button enabled
        // and when debugging it doesn't work, but when run is all right
        buttonVerify.click();
	}

	@Override
	public void openPage()
	{
		driver.navigate().to(baseUrl);
	}
}
