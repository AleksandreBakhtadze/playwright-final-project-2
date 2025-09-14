package ge.tbc.testautomation.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class ProductPage {

    public final Locator titles;
    public final Locator CTAButtons;
    public final Locator popUpDetails;
    public final Locator closePopUpDetails;
    public final Locator additionalButtons;
    public final Locator currentExchange;
    public final Locator convertFields;

    public final Locator leftCurrencyDropdown;
    public final Locator rightCurrencyDropdown;
    public final Locator currencyOptions;
    public final Locator swapButton;
    public final Locator transactionField;
    public final Locator transactionDropDowns;
    public final Locator transactionCountries;

    // loaded cards
    public final Locator loadedTransferCards;

    // loading placeholders
    public final Locator loadingPlaceholders;

    public final Locator secondaryLinks;
    public final Locator externalLink;

    public ProductPage(Page page) {
        this.titles = page.locator("//h1");
        this.CTAButtons = page.locator("//button[contains(@class, 'primary') and contains(@class, 'size-l')]");
        this.popUpDetails = page.locator("//div[contains(@class, 'tbcx-pw-dialog__content-wrapper')]");
        this.closePopUpDetails = page.locator("//div[contains(@class, 'tbcx-pw-dialog__header-close')]//button");
        this.additionalButtons = page.locator("//div[contains(@class, 'tbcx-pw-tab-menu')]//button");
        this.currentExchange = page.locator("//div[contains(@class, 'tbcx-pw-exchange-rates-calculator') and contains(@class, 'description')]");
        this.convertFields = page.locator("//div[contains(@class, 'input-with-label')]//input[@type='number']");

        this.leftCurrencyDropdown = page.locator("//tbcx-dropdown-selector[@formcontrolname='iso1Currency']");
        this.rightCurrencyDropdown = page.locator("//tbcx-dropdown-selector[@formcontrolname='iso2Currency']");
        this.currencyOptions = page.locator("//div[contains(@class, 'tbcx-dropdown-popover-item__title')]");
        this.swapButton = page.locator("//div[contains(@class, 'tbcx-pw-exchange-rates-calculator__swap')]//button");
        this.transactionField = page.locator("//div[contains(@class, 'input-with-label')]//input[@id='tbcx-text-input-1']");
        this.transactionDropDowns = page.locator("xpath=//*[name()='tbcx-dropdown-selector']//div[contains(@class, 'tbcx-field')]");
        this.transactionCountries = page.locator("//div[@class='tbcx-item-list']//tbcx-dropdown-popover-item");

        this.loadedTransferCards = page.locator("tbcx-pw-money-transfer-system-card");
        this.loadingPlaceholders = page.locator("tbcx-pw-money-transfer-system-card-loading");

        this.secondaryLinks = page.locator("//div[contains(@class, 'pane-container')]//div[contains(@class,'tbcx-pw-carousel__card')]");
        this.externalLink = page.locator("//tbcx-pw-button[contains(@class, 'tbcx-pw-cta-section__info__button')]//a");
    }
}
