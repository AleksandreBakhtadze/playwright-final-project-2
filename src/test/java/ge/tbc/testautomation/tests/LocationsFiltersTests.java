package ge.tbc.testautomation.tests;

import org.testng.annotations.Test;
import ge.tbc.testautomation.runners.BaseTest;
import ge.tbc.testautomation.steps.LocationsPageSteps;
import ge.tbc.testautomation.steps.MainPageSteps;

public class LocationsFiltersTests extends BaseTest {

    private MainPageSteps mainPageSteps;
    private LocationsPageSteps locationsPageSteps;

    @Test(priority = 1, description = "FP1-T2")
    public void navigateToLocationsPageAndValidateDefaultTabsAndFilters() {
        mainPageSteps = new MainPageSteps(page);
        locationsPageSteps = new LocationsPageSteps(page);

        mainPageSteps.openMainPage().acceptCookies()
                .goToLocationsPage();
        locationsPageSteps.validateMap()
                .validateFilters();
    }

    @Test(priority = 2, dependsOnMethods = "navigateToLocationsPageAndValidateDefaultTabsAndFilters")
    public void validateTabSwitchingBetweenATMAndBranches() {
        locationsPageSteps.clickAtmFilter()
                .clickBranchFilter();
    }

    @Test(priority = 3, dependsOnMethods = "navigateToLocationsPageAndValidateDefaultTabsAndFilters")
    public void validateSubFilterFunctionalityFor24_7Locations() {
        locationsPageSteps.clickSubFilter24_7();
    }

    @Test(priority = 4, dependsOnMethods = "navigateToLocationsPageAndValidateDefaultTabsAndFilters")
    public void validateSubFilterFunctionalityForOpenNowLocations() {
        locationsPageSteps.clickSubFilterOpenNow();
    }
}