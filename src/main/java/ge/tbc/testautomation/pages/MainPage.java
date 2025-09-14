
package ge.tbc.testautomation.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class MainPage {

    public Locator forMe;
    public Locator cookiesAcceptButton;
    public Locator locationsLink;
    public Locator productLink;
    public Locator slider;
    public Locator sliderNextButton;
    public Locator h1s;
    public Locator mainTitle;
    public Locator sectionNextButtons;
    public Locator sectionBackButtons;
    public Locator sections;

    // Mobile elements
    public Locator hamburgerMenuButton;
    public Locator megaMenuMainNavigationItems;
    public Locator subNavigationItems;
    public Locator navigationDropDownItems;
    public Locator header;
    public Locator keyCTAButtons;

    public MainPage(Page page) {
        this.forMe = page.locator(".tbcx-pw-navigation-item:has-text('ჩემთვის')");
        this.cookiesAcceptButton = page.locator(".tbcx-pw-cookie-consent .primary.size-m.state-initial");
        this.locationsLink = page.locator("span:has-text('მისამართები')");
        this.productLink = page.locator("span:has-text('ფულადი გზავნილები')");
        this.slider = page.locator("tbcx-pw-hero-slider");
        this.sliderNextButton = page.locator("button[class*='tbcx-pw-hero-slider-section'][class*='button_next']");
        this.h1s = page.locator("h1");
        this.mainTitle = page.locator("div[class*='tbcx-pw-featured-text-section__content']");
        this.sectionNextButtons = page.locator("button[tbcxbutton][class*='carousel-triggers--next']");
        this.sectionBackButtons = page.locator("button[tbcxbutton][class*='carousel-triggers--prev']");
        this.sections = page.locator("div.tbcx-pw-section-with-title");

        // Mobile elements
        this.hamburgerMenuButton = page.locator("div[class*='tbcx-pw-hamburger-menu'] button");
        this.megaMenuMainNavigationItems = page.locator("div[class*='tbcx-pw-mega-menu-navigation__items'] button");
        this.subNavigationItems = page.locator("tbcx-pw-mega-menu-sub-navigation[class*='ng-star-inserted'] tbcx-pw-mega-menu-sub-group");
        this.navigationDropDownItems = page.locator("tbcx-pw-mega-menu-sub-navigation[class*='ng-star-inserted'] tbcx-pw-mega-menu-sub-group tbcx-pw-mega-menu-sub-item[class*='ng-tns-c2478649229-44']");
        this.header = page.locator("div[class*='header-wrapper']");
        this.keyCTAButtons = page.locator("tbcx-pw-button");
    }

    public Locator getCurrentSlide() {
        return slider.locator(".tbcx-pw-hero-slider-section__slide.ng-trigger-slideAnimation");
    }
}