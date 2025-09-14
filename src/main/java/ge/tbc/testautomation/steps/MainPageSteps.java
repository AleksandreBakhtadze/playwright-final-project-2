package ge.tbc.testautomation.steps;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Locator;
import ge.tbc.testautomation.pages.MainPage;
import ge.tbc.testautomation.data.TBCContants;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class MainPageSteps {
    private final Page page;
    private final MainPage mainPage;
    private final TBCContants tbcContants = new TBCContants();

    public MainPageSteps(Page page) {
        this.page = page;
        this.mainPage = new MainPage(page);
    }

    public MainPageSteps openMainPage() {
        page.navigate(tbcContants.mainPageLink);
        return this;
    }

    public void goToLocationsPage() {
        assertThat(mainPage.forMe).isVisible();
        mainPage.forMe.hover();
        page.waitForTimeout(1000);
        assertThat(mainPage.locationsLink).isVisible();
        mainPage.locationsLink.click();
        page.waitForFunction("() => window.location.href.includes('atm') || window.location.href.includes('branches')");
    }

    public void goToProductsPage() {
        assertThat(mainPage.forMe).isVisible();
        mainPage.forMe.hover();
        page.waitForTimeout(2000);
        assertThat(mainPage.productLink).isVisible();
        mainPage.productLink.click();
        page.waitForFunction("() => window.location.href.includes('money') || window.location.href.includes('transfers')");
    }

    public MainPageSteps acceptCookies() {
        try {
            assertThat(mainPage.cookiesAcceptButton).isVisible(new com.microsoft.playwright.assertions.LocatorAssertions.IsVisibleOptions().setTimeout(5000));
            if (mainPage.cookiesAcceptButton.isVisible()) {
                mainPage.cookiesAcceptButton.click();
            }
        } catch (Exception e) {
            // Cookie button not visible, continue
        }
        return this;
    }

    public MainPageSteps validateSlider() {
        mainPage.slider.hover();
        page.waitForTimeout(1000);
        for (int i = 0; i < 9; i++) {
            try {
                Locator currentSlide = mainPage.getCurrentSlide();
                assertThat(currentSlide).isVisible(new com.microsoft.playwright.assertions.LocatorAssertions.IsVisibleOptions().setTimeout(10000));

                assertThat(currentSlide.locator("h2.tbcx-pw-hero-slider-section__slide-title"))
                        .hasText(tbcContants.mainPageSlides[i]);

                Locator button = currentSlide.locator("tbcx-pw-button a");
                assertThat(button).isVisible();
                assertThat(button).hasAttribute("href", java.util.regex.Pattern.compile(".*"));
                assertThat(button).hasText(
                        new String[]{"ვრცლად", "დეტალურად", "გაიგე მეტი"}
                );

            } catch (Exception e) {
                System.out.println("Error on slide " + (i + 1) + ": " + e.getMessage());
            }
            assertThat(mainPage.sliderNextButton).isEnabled();
            mainPage.sliderNextButton.click();
            page.waitForTimeout(2000);
        }
        return this;
    }

    public MainPageSteps checkTitles() {
        assertThat(mainPage.mainTitle).hasText(tbcContants.homaPageMainTitle);

        for (int i = 0; i < tbcContants.h1Titles.length; i++) {
            assertThat(mainPage.h1s.nth(i)).hasText(tbcContants.h1Titles[i]);

            Locator infoSection = mainPage.h1s.nth(i).locator("xpath=./ancestor::div[contains(@class, 'tbcx-pw-cta-section__info')]");
            Locator buttonsWrapper = infoSection.locator(".tbcx-pw-cta-section__info__buttons-wrapper");
            Locator button = buttonsWrapper.locator("button");

            assertThat(button).isVisible();
            assertThat(button).hasText("ვრცლად");
            assertThat(button).hasCSS("background-color", java.util.regex.Pattern.compile("rgba?\\(0,\\s*173,\\s*238.*\\)|rgb\\(0,\\s*173,\\s*238\\)"));
        }
        return this;
    }

    public MainPageSteps checkSectionTitles(String sectionName, int index) {
        assertThat(mainPage.sections.nth(index)).hasText(sectionName);
        Locator cards = mainPage.sections.nth(index)
                .locator(".tbcx-pw-carousel__slides__slide__card, .tbcx-pw-carousel__card");

        String[] expectedTitles = tbcContants.sectionCardsTitles.get(sectionName);
        int cardCount = cards.count();

        for (int i = 0; i < cardCount; i++) {
            if (expectedTitles != null && i < expectedTitles.length) {
                assertThat(cards.nth(i)).hasText(expectedTitles[i]);
            }
            if (i == 2 && index != 1) {
                mainPage.sectionNextButtons.nth(Math.max(index - 1, 0)).click();
            }
        }
        mainPage.sectionBackButtons.nth(Math.max(index - 1, 0)).click();
        return this;
    }

    public MainPageSteps checkSectionUrls(String sectionName, int index) {
        assertThat(mainPage.sections.nth(index)).hasText(sectionName);
        Locator cards = mainPage.sections.nth(index)
                .locator(".tbcx-pw-carousel__slides__slide__card, .tbcx-pw-carousel__card");

        String[] expectedUrls = tbcContants.sectionCardsUrls.get(sectionName);
        int cardCount = cards.count();

        for (int i = 0; i < cardCount; i++) {
            Locator link = cards.nth(i).locator("a");

            // Validate card URL
            if (expectedUrls != null && i < expectedUrls.length) {
                assertThat(link).hasAttribute("href", expectedUrls[i]);
            }

            // Click card, validate URL navigation
            link.click();
            page.waitForTimeout(1000);

            if (link.getAttribute("target") != null && link.getAttribute("target").equals("_blank")) {
                Page newPage = page.context().waitForPage(() -> {});
                assertThat(newPage).hasURL(java.util.regex.Pattern.compile(".*" + java.util.regex.Pattern.quote(expectedUrls[i]) + ".*"));
                newPage.close();
            } else {
                page.waitForFunction("() => window.location.href.includes('" + expectedUrls[i] + "')");
                page.goBack();
            }

            page.waitForTimeout(1000);
            assertThat(mainPage.sections.nth(index)).isVisible();

            // Reload cards
            cards = mainPage.sections.nth(index)
                    .locator(".tbcx-pw-carousel__slides__slide__card, .tbcx-pw-carousel__card");

            if (i >= 2 && index != 1 && mainPage.sectionNextButtons.nth(Math.max(index - 1, 0)).isEnabled()) {
                mainPage.sectionNextButtons.nth(Math.max(index - 1, 0)).click();
            }
        }
        return this;
    }

    public MainPageSteps setMobileResolution() {
        page.setViewportSize(375, 667);
        page.waitForTimeout(1000);
        return this;
    }

    public MainPageSteps selectAndOpenHamburgerMenu() {
        assertThat(mainPage.hamburgerMenuButton).isVisible();
        mainPage.hamburgerMenuButton.click();
        page.waitForTimeout(1000);
        return this;
    }

    public MainPageSteps getMegaMenuMainItems() {
        for (int i = 0; i < tbcContants.mainNavigationItems.length; i++) {
            assertThat(mainPage.megaMenuMainNavigationItems.nth(i)).isVisible();
            assertThat(mainPage.megaMenuMainNavigationItems.nth(i)).hasText(tbcContants.mainNavigationItems[i]);
        }
        return this;
    }

    public MainPageSteps validateSubNavigationItems() {
        int menuCount = mainPage.megaMenuMainNavigationItems.count();
        for (int i = 0; i < menuCount; i++) {
            assertThat(mainPage.megaMenuMainNavigationItems.nth(i)).isVisible();
            assertThat(mainPage.megaMenuMainNavigationItems.nth(i)).hasText(tbcContants.mainNavigationItems[i]);
            mainPage.megaMenuMainNavigationItems.nth(i).click();
            assertThat(mainPage.megaMenuMainNavigationItems.nth(i))
                    .hasCSS("color", java.util.regex.Pattern.compile("rgba?\\(0,\\s*173,\\s*238.*\\)|rgb\\(0,\\s*173,\\s*238\\)"));
            page.waitForTimeout(700);

            int subItemCount = mainPage.subNavigationItems.count();
            for (int j = 0; j < subItemCount; j++) {
                assertThat(mainPage.subNavigationItems.nth(j))
                        .containsText(tbcContants.subNavigationItems[i][j]);
                if (mainPage.subNavigationItems.nth(j).textContent().contains("chevron-down-outlined")) {
                    mainPage.subNavigationItems.nth(j).click();
                }
            }
        }
        return this;
    }

    public void closeHamburgerMenu() {
        assertThat(mainPage.hamburgerMenuButton).isVisible();
        mainPage.hamburgerMenuButton.click();
        page.waitForTimeout(1000);
    }

    public MainPageSteps selectHeader() {
        assertThat(mainPage.header).isVisible();
        assertThat(mainPage.header).hasCSS("position", "sticky");
        return this;
    }

    public MainPageSteps scrollIntoView() {
        page.evaluate("window.scrollBy(0, 1500)");
        page.waitForTimeout(3000);
        page.evaluate("window.scrollBy(0, -500)");
        page.waitForTimeout(3000);
        return this;
    }

    public void getAllKeyCTAButtons() {
        int buttonCount = mainPage.keyCTAButtons.count();
        for (int i = 0; i < buttonCount; i++) {
            assertThat(mainPage.keyCTAButtons.nth(i)).isVisible();
            assertThat(mainPage.keyCTAButtons.nth(i)).hasText(tbcContants.mainPageKeyCTAButtonTexts[i]);
            assertThat(mainPage.keyCTAButtons.nth(i))
                    .hasCSS("color", java.util.regex.Pattern.compile("rgba?\\(0,\\s*0,\\s*0.*\\)|rgb\\(0,\\s*0,\\s*0\\)"));
        }
    }
}