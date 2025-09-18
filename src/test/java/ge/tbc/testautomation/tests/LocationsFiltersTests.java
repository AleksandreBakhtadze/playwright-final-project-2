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

        mainPageSteps.openMainPage()
                .acceptCookies()
                .hoverOnNavigation()
                .goToLocationsPage();
        locationsPageSteps.verifyMapVisible()
                .verifyAllFilterDefault()
                .verifyMapPinsAreVisible()
                .verifyAtmFilter()
                .verifyBranchesFilter()
                .verifySubFilterAreVisible();
    }

    @Test(priority = 2, dependsOnMethods = "navigateToLocationsPageAndValidateDefaultTabsAndFilters")
    public void validateTabSwitchingBetweenATMAndBranches() {
        locationsPageSteps.clickAtmFilter()
                .checkAppliedFilter("ბანკომატი")
                .clickBranchFilter()
                .checkAppliedFilter("ფილიალი");
    }

    @Test(priority = 3, dependsOnMethods = "navigateToLocationsPageAndValidateDefaultTabsAndFilters")
    public void validateSubFilterFunctionalityFor24_7Locations() {
        locationsPageSteps.clickSubFilter24_7()
                .checkIfHighlighted27_4()
                .checkAppliedFilter("24/7")
                .uncheck27_4filter();
    }

    @Test(priority = 4, dependsOnMethods = "navigateToLocationsPageAndValidateDefaultTabsAndFilters")
    public void validateSubFilterFunctionalityForOpenNowLocations() {
        locationsPageSteps.clickSubFilterOpenNow()
                .checkIfHighlightedOpenNow()
                .checkOpenNowFilter()
                .uncheckOpenNowFilter();
    }
}