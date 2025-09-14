package ge.tbc.testautomation.tests;

import ge.tbc.testautomation.data.TBCContants;
import org.testng.annotations.Test;
import ge.tbc.testautomation.runners.BaseTest;
import ge.tbc.testautomation.steps.MainPageSteps;

public class MobileSpecificTests extends BaseTest {

    private MainPageSteps mainPageSteps;
    private TBCContants tbcContants = new TBCContants();

    @Test(priority = 1, description = "FP1-T5")
    public void setupMobileResolutionAndNavigateToPage() {
        mainPageSteps = new MainPageSteps(page);
        mainPageSteps.openMainPage().acceptCookies().setMobileResolution();
    }

    @Test(priority = 2, dependsOnMethods = "setupMobileResolutionAndNavigateToPage")
    public void validateHamburgerMenuNavigationAndSubItems() {
        if (page.isClosed()) {
            page = context.newPage();
            mainPageSteps = new MainPageSteps(page);
            page.navigate(tbcContants.mainPageLink);
            mainPageSteps.setMobileResolution();
        }
        mainPageSteps.selectAndOpenHamburgerMenu()
                .getMegaMenuMainItems()
                .validateSubNavigationItems()
                .closeHamburgerMenu();
    }

    @Test(priority = 3, dependsOnMethods = "setupMobileResolutionAndNavigateToPage")
    public void validateStickyHeaderAndHamburgerMenuFunctionality() {
        mainPageSteps.selectHeader()
                .scrollIntoView()
                .selectHeader()
                .selectAndOpenHamburgerMenu()
                .closeHamburgerMenu();
    }

    @Test(priority = 4, dependsOnMethods = "setupMobileResolutionAndNavigateToPage")
    public void validateKeyCTAButtonsVisibilityInMobileViewport() {
        mainPageSteps.getAllKeyCTAButtons();
    }
}