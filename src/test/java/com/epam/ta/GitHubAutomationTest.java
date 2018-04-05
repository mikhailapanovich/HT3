package com.epam.ta;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.epam.ta.steps.Steps;

public class GitHubAutomationTest
{
	private Steps steps;
	private final String USERNAME = "testautomationuser";
	private final String PASSWORD = "Time4Death!";

	@BeforeMethod(description = "Init browser")
	public void setUp()
	{
		steps = new Steps();
		steps.initBrowser();
	}

	@Test(enabled = false)
	public void oneCanCreateProject()
	{
		steps.loginGithub(USERNAME, PASSWORD);
		Assert.assertTrue(steps.createNewRepository("testRepo", "auto-generated test repo"));
		Assert.assertTrue(steps.currentRepositoryIsEmpty());
		// do not use lots of asserts
	}

	@Test(description = "Login to Github", enabled = false)
	public void oneCanLoginGithub()
	{
		steps.loginGithub(USERNAME, PASSWORD);
		Assert.assertTrue(steps.isLoggedIn(USERNAME));
	}

    // First
    @Test(description = "Change name in profile")
    public void oneCanChangeName() {
	    steps.loginGithub(USERNAME, PASSWORD);
        steps.changeName("New Name");
        Assert.assertTrue(steps.profileNameChangedSuccessfully());
    }

    // Second
	@Test(description = "Delete repo")
	public void oneCanDeleteRepo() {
		steps.loginGithub(USERNAME, PASSWORD);
		steps.createNewRepository("RepoForDeletion", "this repository must be deleted");
		steps.deleteLastRepository();
		Assert.assertTrue(steps.isLastRepositoryDeleted());
	}

	// Third
	@Test(description = "Add README to repository from quick setup")
	public void third() {
        steps.loginGithub(USERNAME, PASSWORD);
        steps.createNewRepository("RepoForReadme", "add readme, after creating new repo");
        steps.addReadme();
        Assert.assertTrue(steps.isReadmeExist());
	}

	@AfterMethod(description = "Stop Browser")
	public void stopBrowser()
	{
		steps.closeDriver();
	}

}
