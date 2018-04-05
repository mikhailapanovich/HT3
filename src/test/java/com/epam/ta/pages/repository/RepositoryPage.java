package com.epam.ta.pages.repository;

import com.epam.ta.pages.AbstractPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class RepositoryPage extends AbstractPage
{
	private String baseUrl;
	private final Logger logger = LogManager.getRootLogger();

    @FindBy(xpath = "//a[contains(@data-selected-links, 'repo_settings')]")
	private WebElement linkSettings;

    @FindBy(xpath = "//*[@id='js-flash-container']/div/div")
    private WebElement resultContainer;


	public RepositoryPage(WebDriver driver, String username, String repoName)
	{
		super(driver);
		PageFactory.initElements(this.driver, this);
		baseUrl = "https://github.com/" + username + "/" + repoName;
	}

	public void clickOnSettings() {
        linkSettings.click();
	}

    public boolean isDeleted() {
	    try {
            resultContainer.getText().contains("was successfully deleted");
        } catch (Exception e) {
	        return false;
        }
        return true;
    }

	@Override
	public void openPage()
	{
		driver.get(baseUrl);
	}
}
