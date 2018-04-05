package com.epam.ta.steps;

import com.epam.ta.driver.DriverSingleton;
import com.epam.ta.pages.*;
import com.epam.ta.pages.profile.ProfilePage;
import com.epam.ta.pages.profile.SettingsProfilePage;
import com.epam.ta.pages.repository.RepositoryPage;
import com.epam.ta.pages.repository.RepositorySettingsPage;
import com.sun.org.apache.regexp.internal.RE;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Steps
{
	private WebDriver driver;
	private String userName;
	private String expectedName;
	private String lastRepoName;

	private final Logger logger = LogManager.getRootLogger();

	public void initBrowser()
	{
		driver = DriverSingleton.getDriver();
	}

	public void closeDriver()
	{
		DriverSingleton.closeDriver();
	}

	public void loginGithub(String username, String password)
	{
		LoginPage loginPage = new LoginPage(driver);
		loginPage.openPage();
		loginPage.login(username, password);
		userName = username;
	}

	public boolean isLoggedIn(String username)
	{
		LoginPage loginPage = new LoginPage(driver);
		String actualUsername = loginPage.getLoggedInUserName().trim().toLowerCase();
		logger.info("Actual username: " + actualUsername);
		return actualUsername.equals(username);
	}

	public boolean createNewRepository(String repositoryName, String repositoryDescription)
	{
		MainPage mainPage = new MainPage(driver);
		mainPage.clickOnCreateNewRepositoryButton();
		CreateNewRepositoryPage createNewRepositoryPage = new CreateNewRepositoryPage(driver);
		String expectedRepoName = createNewRepositoryPage.createNewRepository(repositoryName, repositoryDescription);
		lastRepoName = expectedRepoName;
		return expectedRepoName.equals(createNewRepositoryPage.getCurrentRepositoryName());
	}

	// broken
	public boolean currentRepositoryIsEmpty()
	{
		CreateNewRepositoryPage createNewRepositoryPage = new CreateNewRepositoryPage(driver);
		return createNewRepositoryPage.isCurrentRepositoryEmpty();
	}

	public void changeName(String name) {
		MainPage mainPage = new MainPage(driver);
		mainPage.clickOnSettings();
		SettingsProfilePage settingsProfilePage = new SettingsProfilePage(driver);
		expectedName = settingsProfilePage.changeName(name);
	}

	public boolean profileNameChangedSuccessfully() {
		ProfilePage profilePage = new ProfilePage(driver, userName);
		profilePage.openPage();
		return expectedName.equals(profilePage.getName());
	}

	public void deleteLastRepository() {
		RepositoryPage repositoryPage = new RepositoryPage(driver, userName, lastRepoName);
		repositoryPage.openPage();
		repositoryPage.clickOnSettings();
		RepositorySettingsPage repositorySettingsPage = new RepositorySettingsPage(driver, userName, lastRepoName);
		repositorySettingsPage.deleteRepository(lastRepoName);
		logger.info("Repo [" + lastRepoName + "] has been deleted");
	}

	public boolean isLastRepositoryDeleted() {
		RepositoryPage repositoryPage = new RepositoryPage(driver, userName, lastRepoName);
		return repositoryPage.isDeleted();
	}

	public void addReadme() {
		RepositoryPage repositoryPage = new RepositoryPage(driver, userName, lastRepoName);
		repositoryPage.clickOnREADME();
		repositoryPage.clickCommitNewFile();
	}

	public boolean isReadmeExist() {
		RepositoryPage repositoryPage = new RepositoryPage(driver, userName, lastRepoName);
		return repositoryPage.isFileCommited("README.md");
	}
}
