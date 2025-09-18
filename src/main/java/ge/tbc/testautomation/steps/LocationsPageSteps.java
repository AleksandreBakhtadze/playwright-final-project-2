package ge.tbc.testautomation.steps;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Locator;
import ge.tbc.testautomation.pages.LocationsPage;
import ge.tbc.testautomation.utils.HelperFunctions;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.testng.Assert.*;

public class LocationsPageSteps {
    private final Page page;
    private final LocationsPage locationsPage;
    private final HelperFunctions helperFunctions = new HelperFunctions();

    public LocationsPageSteps(Page page) {
        this.page = page;
        this.locationsPage = new LocationsPage(page);
    }

    public LocationsPageSteps verifyMapVisible() {
        page.waitForTimeout(5000);
        assertThat(locationsPage.mapContainer).isVisible(new com.microsoft.playwright.assertions.LocatorAssertions.IsVisibleOptions().setTimeout(10000));
        return this;
    }

    public LocationsPageSteps verifyAllFilterDefault() {
        assertThat(locationsPage.allFilter).isVisible();
        assertThat(locationsPage.allFilter).hasClass(java.util.regex.Pattern.compile(".*active.*"));
        return this;
    }

    public LocationsPageSteps verifyMapPinsAreVisible() {
        assertThat(locationsPage.mapPins.first()).isVisible();
        assertTrue(locationsPage.mapPins.count() > 0);
        return this;
    }

    public LocationsPageSteps verifyAtmFilter() {
        assertThat(locationsPage.atmFilter).isVisible();
        assertThat(locationsPage.atmFilter).not().hasClass(java.util.regex.Pattern.compile(".*active.*"));
        return this;
    }

    public LocationsPageSteps verifyBranchesFilter() {
        assertThat(locationsPage.branchesFilter).isVisible();
        assertThat(locationsPage.branchesFilter).not().hasClass(java.util.regex.Pattern.compile(".*active.*"));
        return this;
    }

    public LocationsPageSteps verifySubFilterAreVisible() {
        assertThat(locationsPage.subFilter27_4).isVisible();
        assertThat(locationsPage.subFilterOpenNow).isVisible();
        return this;
    }

    public LocationsPageSteps clickAtmFilter() {
        locationsPage.atmFilter.click();
        page.waitForTimeout(3000);
        assertThat(locationsPage.atmFilter).hasClass(java.util.regex.Pattern.compile(".*active.*"));
        return this;
    }

    public LocationsPageSteps checkAppliedFilter(String filter) {
        for (int i = 0; i < locationsPage.optionsList.count(); i++) {
            assertThat(locationsPage.optionsList.nth(i)).containsText(filter);
        }
        return this;
    }

    public LocationsPageSteps clickBranchFilter() {
        locationsPage.branchesFilter.click();
        assertThat(locationsPage.branchesFilter).hasClass(java.util.regex.Pattern.compile(".*active.*"));
        return this;
    }

    public LocationsPageSteps clickSubFilter24_7() {
        locationsPage.subFilter27_4.click();
        page.waitForTimeout(3000);
        return this;
    }

    public LocationsPageSteps checkIfHighlighted27_4(){
        assertThat(locationsPage.getSpan(locationsPage.subFilter27_4)).hasCSS("background-color", java.util.regex.Pattern.compile("rgba?\\(0,\\s*173,\\s*238.*\\)|rgb\\(0,\\s*173,\\s*238\\)"));
        return this;
    }

    public LocationsPageSteps uncheck27_4filter(){
        locationsPage.subFilter27_4.click();
        return this;
    }

    public LocationsPageSteps clickSubFilterOpenNow() {
        locationsPage.subFilterOpenNow.click();
        page.waitForTimeout(3000);
        return this;
    }

    public LocationsPageSteps checkIfHighlightedOpenNow(){
        assertThat(locationsPage.getSpan(locationsPage.subFilterOpenNow)).hasCSS("background-color", java.util.regex.Pattern.compile("rgba?\\(0,\\s*173,\\s*238.*\\)|rgb\\(0,\\s*173,\\s*238\\)"));
        return this;
    }

    public LocationsPageSteps checkOpenNowFilter() {
        for (int i = 0; i < locationsPage.optionsList.count(); i++) {
            String optionText = locationsPage.optionsList.nth(i).textContent();
            assertTrue(helperFunctions.validateTime(optionText));
        }
        return this;
    }

    public LocationsPageSteps uncheckOpenNowFilter(){
        locationsPage.subFilter27_4.click();
        return this;
    }

    public LocationsPageSteps clickOnMap(){
        page.waitForTimeout(1500);
        locationsPage.map.click(new Locator.ClickOptions().setForce(true));
        assertThat(locationsPage.mapPins.last()).isVisible();
        return this;
    }

    public int getPinsCount() {
        return locationsPage.mapPins.count();
    }

    public LocationsPageSteps zoomIn() {
        locationsPage.map.press("NumpadAdd");
        return this;
    }

    public LocationsPageSteps zoomOut() {
        locationsPage.map.press("NumpadSubtract");
        page.waitForTimeout(1500);
        return this;
    }

    public LocationsPageSteps checkPins(int originalPinsCount) {
        try {
            assertEquals(locationsPage.mapPins.count(), originalPinsCount);
        } catch (AssertionError e) {
            int currentCount = locationsPage.mapPins.count();
            System.out.println("Zoom test failed: Expected " + originalPinsCount +
                    " pins, but found " + currentCount + " pins, map did not load correctly");
            throw e;
        }
        return this;
    }

    public LocationsPageSteps moveLeft(){
        for (int i = 0; i < 100; i++) locationsPage.map.press("ArrowLeft");
        return this;
    }

    public LocationsPageSteps moveRight(){
        for (int i = 0; i < 100; i++) locationsPage.map.press("ArrowRight");
        return this;
    }

    public LocationsPageSteps moveDown(){
        for (int i = 0; i < 100; i++) locationsPage.map.press("ArrowDown");
        return this;
    }

    public LocationsPageSteps moveUp(){
        for (int i = 0; i < 100; i++) locationsPage.map.press("ArrowUp");
        assertThat(locationsPage.mapPins.last()).isVisible();
        page.waitForTimeout(3000);
        return this;
    }

}