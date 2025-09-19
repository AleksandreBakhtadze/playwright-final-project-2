
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
    }

    public Locator getCurrentSlide() {
        return slider.locator(".tbcx-pw-hero-slider-section__slide.ng-trigger-slideAnimation");
    }

    public Locator getInfoSectionForH1(int index) {
        return h1s.nth(index).locator("xpath=./ancestor::div[contains(@class, 'tbcx-pw-cta-section__info')]");
    }

    public Locator getButtonsWrapperForH1(int index) {
        return getInfoSectionForH1(index).locator(".tbcx-pw-cta-section__info__buttons-wrapper");
    }

    public Locator getButtonForH1(int index) {
        return getButtonsWrapperForH1(index).locator("button");
    }

    public Locator getSectionCards(int index) {
        return sections.nth(index).locator(".tbcx-pw-carousel__slides__slide__card, .tbcx-pw-carousel__card");
    }

    public Locator getCardLink(int index, int i){
        return getSectionCards(index).nth(i).locator("a");
    }

    public Locator getSliderBtn(Locator slider) {
        return slider.locator("tbcx-pw-button a");
    }

}