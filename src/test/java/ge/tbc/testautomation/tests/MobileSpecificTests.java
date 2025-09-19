package ge.tbc.testautomation.tests;

import ge.tbc.testautomation.data.TBCContants;
import ge.tbc.testautomation.runners.CrossBrowser;
import ge.tbc.testautomation.steps.MobileMainSteps;
import org.testng.annotations.Test;
import ge.tbc.testautomation.runners.BaseTest;
import ge.tbc.testautomation.steps.MainPageSteps;

public class MobileSpecificTests extends CrossBrowser {

    private MobileMainSteps mobileMainSteps;
    private TBCContants tbcContants = new TBCContants();

    @Test(priority = 1, description = "FP1-T5")
    public void setupMobileResolutionAndNavigateToPage() {
        mobileMainSteps = new MobileMainSteps(page);
        mobileMainSteps.openMainPage().acceptCookies().setMobileResolution();
    }

    @Test(priority = 2, dependsOnMethods = "setupMobileResolutionAndNavigateToPage")
    public void validateHamburgerMenuNavigationAndSubItems() {
        if (page.isClosed()) {
            page = context.newPage();
            mobileMainSteps = new MobileMainSteps(page);
            page.navigate(tbcContants.mainPageLink);
            mobileMainSteps.setMobileResolution();
        }
        mobileMainSteps.openHamburgerMenu()
                .checkMegaMenuMainItems();
        for(int i =0 ; i < mobileMainSteps.getSubNavItemsCount();i++){
            mobileMainSteps.validateSubNavItems(i)
                    .clickSubNavItem(i)
                    .checkSubNavItemColor(i);
            for(int j =0 ; j < mobileMainSteps.getSubItemsCount();j++){
                mobileMainSteps.checkSubItemText(i,j)
                        .clickSubItem(i,j);
            }
        }
        mobileMainSteps.closeHamburgerMenu();
    }

    @Test(priority = 3, dependsOnMethods = "setupMobileResolutionAndNavigateToPage")
    public void validateStickyHeaderAndHamburgerMenuFunctionality() {
        mobileMainSteps.selectHeader()
                .scrollIntoView()
                .selectHeader()
                .openHamburgerMenu()
                .closeHamburgerMenu();
    }

    @Test(priority = 4, dependsOnMethods = "setupMobileResolutionAndNavigateToPage")
    public void validateKeyCTAButtonsVisibilityInMobileViewport() {
        for (int i =0 ; i < mobileMainSteps.getCTAButtonCount();i++){
            mobileMainSteps.checkCTABtnIsVisible(i)
                    .checkCTABtnText(i)
                    .checkCTABtnColor(i);
        }
    }
}