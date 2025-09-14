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

    public LocationsPageSteps validateMap() {
        page.waitForTimeout(3000);
        // validate map should be visible
        assertThat(locationsPage.mapContainer).isVisible(new com.microsoft.playwright.assertions.LocatorAssertions.IsVisibleOptions().setTimeout(10000));
        // all filter is selected by default
        assertThat(locationsPage.allFilter).isVisible();
        assertThat(locationsPage.allFilter).hasClass(java.util.regex.Pattern.compile(".*active.*"));
        // locations are pined
        assertThat(locationsPage.mapPins.first()).isVisible();
        assertTrue(locationsPage.mapPins.count() > 0);
        return this;
    }

    public LocationsPageSteps validateFilters() {
        assertThat(locationsPage.atmFilter).isVisible();
        assertThat(locationsPage.atmFilter).not().hasClass(java.util.regex.Pattern.compile(".*active.*"));

        assertThat(locationsPage.branchesFilter).isVisible();
        assertThat(locationsPage.branchesFilter).not().hasClass(java.util.regex.Pattern.compile(".*active.*"));

        assertThat(locationsPage.subFilter27_4).isVisible();
        assertThat(locationsPage.subFilterOpenNow).isVisible();

        return this;
    }

    public LocationsPageSteps clickAtmFilter() {
        // pins change
        int originalPinsCount = locationsPage.mapPins.count();
        locationsPage.atmFilter.click();
        assertThat(locationsPage.atmFilter).hasClass(java.util.regex.Pattern.compile(".*active.*"));

        page.waitForFunction("() => document.querySelectorAll('" + locationsPage.mapPins.first().getAttribute("class") + "').length !== " + originalPinsCount);
        assertNotEquals(locationsPage.mapPins.count(), originalPinsCount);

        // option list changes
        int optionsCount = locationsPage.optionsList.count();
        for (int i = 0; i < optionsCount; i++) {
            assertThat(locationsPage.optionsList.nth(i)).containsText(" ბანკომატი ");
        }
        return this;
    }

    public LocationsPageSteps clickBranchFilter() {
        // pins change
        int originalPinsCount = locationsPage.mapPins.count();
        locationsPage.branchesFilter.click();
        assertThat(locationsPage.branchesFilter).hasClass(java.util.regex.Pattern.compile(".*active.*"));

        page.waitForFunction("() => document.querySelectorAll('" + locationsPage.mapPins.first().getAttribute("class") + "').length !== " + originalPinsCount);
        assertNotEquals(locationsPage.mapPins.count(), originalPinsCount);

        // option list changes
        int optionsCount = locationsPage.optionsList.count();
        for (int i = 0; i < optionsCount; i++) {
            assertThat(locationsPage.optionsList.nth(i)).not().containsText(" ბანკომატი ");
        }
        return this;
    }

    public LocationsPageSteps clickSubFilter24_7() {
        locationsPage.subFilter27_4.click();
        page.waitForTimeout(3000);

        // check if it is highlighted
        Locator span = locationsPage.subFilter27_4.locator("span");
        assertThat(span).hasCSS("background-color", java.util.regex.Pattern.compile("rgba?\\(0,\\s*173,\\s*238.*\\)|rgb\\(0,\\s*173,\\s*238\\)"));

        int optionsCount = locationsPage.optionsList.count();
        for (int i = 0; i < optionsCount; i++) {
            assertThat(locationsPage.optionsList.nth(i)).containsText("24/7");
        }

        // uncheck filter
        locationsPage.subFilter27_4.click();
        return this;
    }

    public LocationsPageSteps clickSubFilterOpenNow() {
        locationsPage.subFilterOpenNow.click();
        page.waitForTimeout(3000);

        // check if it is highlighted
        Locator span = locationsPage.subFilterOpenNow.locator("span");
        assertThat(span).hasCSS("background-color", java.util.regex.Pattern.compile("rgba?\\(0,\\s*173,\\s*238.*\\)|rgb\\(0,\\s*173,\\s*238\\)"));

        // check filtered list
        int optionsCount = locationsPage.optionsList.count();
        for (int i = 0; i < optionsCount; i++) {
            String optionText = locationsPage.optionsList.nth(i).textContent();
            assertTrue(helperFunctions.validateTime(optionText));
        }

        // uncheck filter
        locationsPage.subFilterOpenNow.click();
        return this;
    }

    public LocationsPageSteps tapZoomInAndZoomOut() {
        page.waitForTimeout(1500);
        locationsPage.map.click();
        assertThat(locationsPage.mapPins.last()).isVisible();
        int originalPinsCount = locationsPage.mapPins.count();

        locationsPage.map.press("NumpadAdd");
        page.waitForFunction("() => document.querySelectorAll('" + locationsPage.mapPins.first().getAttribute("class") + "').length !== " + originalPinsCount);
        assertNotEquals(locationsPage.mapPins.count(), originalPinsCount);

        locationsPage.map.press("NumpadSubtract");
        page.waitForTimeout(1500);

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

    public LocationsPageSteps mapMoveSides() {
        locationsPage.map.click();
        page.waitForTimeout(1500);
        assertThat(locationsPage.mapPins.last()).isVisible();
        int originalPinsCount = locationsPage.mapPins.count();

        for (int i = 0; i < 100; i++) locationsPage.map.press("ArrowLeft");
        for (int i = 0; i < 100; i++) locationsPage.map.press("ArrowRight");
        for (int i = 0; i < 100; i++) locationsPage.map.press("ArrowDown");
        for (int i = 0; i < 100; i++) locationsPage.map.press("ArrowUp");

        page.waitForTimeout(1500);

        try {
            assertEquals(locationsPage.mapPins.count(), originalPinsCount);
        } catch (AssertionError e) {
            int currentCount = locationsPage.mapPins.count();
            System.out.println("Map movement test failed: Expected " + originalPinsCount +
                    " pins, but found " + currentCount + " pins, map did not load correctly");
            throw e;
        }
        return this;
    }
}