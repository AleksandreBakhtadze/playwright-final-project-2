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

        mainPageSteps.openMainPage()
                .acceptCookies()
                .hoverOnNavigation()
                .goToLocationsPage();
    }

    @Test(priority = 2, dependsOnMethods = "navigateToLocationsPageFromMainNavigation")
    public void validateLocationsMapLoadingAndDefaultState() {
        locationsPageSteps.verifyMapVisible()
                .verifyAllFilterDefault()
                .verifyMapPinsAreVisible();
    }

    @Test(priority = 3, dependsOnMethods = "navigateToLocationsPageFromMainNavigation")
    public void validateMapPanningFunctionalityInAllDirections() {
        locationsPageSteps.clickOnMap();
        int initialCount = locationsPageSteps.getPinsCount();
        locationsPageSteps.moveLeft()
                .moveRight()
                .moveDown()
                .moveUp()
                .checkPins(initialCount);
    }

    @Test(priority = 4, dependsOnMethods = "navigateToLocationsPageFromMainNavigation")
    public void validateMapZoomInAndZoomOutControls() {
        locationsPageSteps.clickOnMap();
        int initialCount = locationsPageSteps.getPinsCount();
        locationsPageSteps.zoomIn()
                .zoomOut()
                .checkPins(initialCount);
    }
}