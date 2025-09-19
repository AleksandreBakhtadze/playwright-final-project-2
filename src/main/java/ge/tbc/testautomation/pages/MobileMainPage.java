package ge.tbc.testautomation.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.util.regex.PatternSyntaxException;

public class MobileMainPage {

    public Locator hamburgerMenuButton;
    public Locator megaMenuMainNavigationItems;
    public Locator subNavigationItems;
    public Locator navigationDropDownItems;
    public Locator header;
    public Locator keyCTAButtons;
    public Locator cookiesAcceptButton;

    public MobileMainPage(Page page) {
        this.hamburgerMenuButton = page.locator("div[class*='tbcx-pw-hamburger-menu'] button");
        this.megaMenuMainNavigationItems = page.locator("div[class*='tbcx-pw-mega-menu-navigation__items'] button");
        this.subNavigationItems = page.locator("tbcx-pw-mega-menu-sub-navigation[class*='ng-star-inserted'] tbcx-pw-mega-menu-sub-group");
        this.navigationDropDownItems = page.locator("tbcx-pw-mega-menu-sub-navigation[class*='ng-star-inserted'] tbcx-pw-mega-menu-sub-group tbcx-pw-mega-menu-sub-item[class*='ng-tns-c2478649229-44']");
        this.header = page.locator("div[class*='header-wrapper']");
        this.keyCTAButtons = page.locator("tbcx-pw-button");
        this.cookiesAcceptButton = page.locator(".tbcx-pw-cookie-consent .primary.size-m.state-initial");

    }

}
