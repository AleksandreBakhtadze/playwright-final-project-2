package ge.tbc.testautomation.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class LocationsPage {

    public Locator map ;
    public Locator mapContainer ;
    public Locator mapPins ;
    public Locator allFilter ;
    public Locator atmFilter ;
    public Locator branchesFilter ;
    public Locator subFilter27_4 ;
    public Locator subFilterOpenNow ;
    public Locator optionsList ;

    public LocationsPage(Page page) {
        this.map = page.locator("div[role='region'][aria-label='Map']");
        this.mapContainer = page.locator("div.map-container");
        this.mapPins = page.locator("gmp-advanced-marker");
        this.allFilter = page.locator("button:has-text('ბანკომატები'):has([class*='tbcx-pw-tab-menu__item'])");
        this.atmFilter = page.locator("button:has-text('ყველა'):has([class*='tbcx-pw-tab-menu__item'])");
        this.branchesFilter = page.locator("button:has-text('ფილიალები'):has([class*='tbcx-pw-tab-menu__item'])");
        this.subFilter27_4 = page.locator("label.tbcx-pw-chip:has-text('24/7')");
        this.subFilterOpenNow = page.locator("label.tbcx-pw-chip:has-text(' ღიაა ')");
        this.optionsList = page.locator("app-atm-branches-section-list-item");
    }
}
