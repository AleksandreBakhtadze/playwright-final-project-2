package ge.tbc.testautomation.tests;

import org.testng.annotations.Test;
import ge.tbc.testautomation.runners.BaseTest;
import ge.tbc.testautomation.steps.LocationsPageSteps;
import ge.tbc.testautomation.steps.MainPageSteps;

public class LocationsMapTests extends BaseTest {

    private MainPageSteps mainPageSteps;
    private LocationsPageSteps locationsPageSteps;

    @Test(priority = 1, description = "FP1-T1")
    public void navigateToLocationsPageFromMainNavigation() {
        mainPageSteps = new MainPageSteps(page);
        locationsPageSteps = new LocationsPageSteps(page);

        mainPageSteps.openMainPage().acceptCookies()
                .goToLocationsPage();
    }

    @Test(priority = 2, dependsOnMethods = "navigateToLocationsPageFromMainNavigation")
    public void validateLocationsMapLoadingAndDefaultState() {
        locationsPageSteps.validateMap();
    }

    @Test(priority = 3, dependsOnMethods = "navigateToLocationsPageFromMainNavigation")
    public void validateMapPanningFunctionalityInAllDirections() {
        locationsPageSteps.mapMoveSides();
    }

    @Test(priority = 4, dependsOnMethods = "navigateToLocationsPageFromMainNavigation")
    public void validateMapZoomInAndZoomOutControls() {
        locationsPageSteps.tapZoomInAndZoomOut();
    }
}