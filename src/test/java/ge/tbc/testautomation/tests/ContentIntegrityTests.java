package ge.tbc.testautomation.tests;

import ge.tbc.testautomation.data.DataSupplier;
import ge.tbc.testautomation.steps.MainPageSteps;
import org.testng.annotations.Test;
import ge.tbc.testautomation.runners.BaseTest;

public class ContentIntegrityTests extends BaseTest {

    private MainPageSteps mainPageSteps;

    @Test(priority = 1, description = "FP1-T4")
    public void navigateToMainPageAndValidatePageTitles() {
        mainPageSteps = new MainPageSteps(page);
        mainPageSteps.openMainPage().acceptCookies().checkTitles();
    }

    @Test(priority = 2, dependsOnMethods = "navigateToMainPageAndValidatePageTitles")
    public void validateSliderActionButtonsPresence() {
        mainPageSteps.validateSlider();
    }

    @Test(priority = 3, dataProvider = "sections", dataProviderClass = DataSupplier.class, dependsOnMethods = "navigateToMainPageAndValidatePageTitles")
    public void validateSectionContentForDuplicateHeadingsAndTextBlocks(String sectionName, int index) {
        mainPageSteps.checkSectionTitles(sectionName, index)
                .checkSectionUrls(sectionName, index);
    }
}