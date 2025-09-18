package ge.tbc.testautomation.tests;

import ge.tbc.testautomation.data.DataSupplier;
import ge.tbc.testautomation.data.TBCContants;
import ge.tbc.testautomation.steps.MainPageSteps;
import org.testng.annotations.Test;
import ge.tbc.testautomation.runners.BaseTest;

public class ContentIntegrityTests extends BaseTest {

    private MainPageSteps mainPageSteps;
    private TBCContants tbcContants = new TBCContants();

    @Test(priority = 1, description = "FP1-T4")
    public void navigateToMainPageAndValidatePageTitles() {
        mainPageSteps = new MainPageSteps(page);
        mainPageSteps.openMainPage()
                .acceptCookies()
                .checkMainTitle();
        for(int i = 0 ; i < tbcContants.h1Titles.length ; i++) {
            mainPageSteps.checkH1Title(i)
                    .checkCTABtns(i);
        }
    }

    @Test(priority = 2, dependsOnMethods = "navigateToMainPageAndValidatePageTitles")
    public void validateSliderActionButtonsPresence() {
        for(int i = 0 ; i <tbcContants.mainPageSlides.length ; i++ ) {
            mainPageSteps.hoveOnSlider()
                    .checkSliderCTABtn(mainPageSteps.getCurrentSlide(i))
                    .moveOnNextSlide();
        }
    }

    @Test(priority = 3, dataProvider = "sections", dataProviderClass = DataSupplier.class, dependsOnMethods = "navigateToMainPageAndValidatePageTitles")
    public void validateSectionContentForDuplicateHeadingsAndTextBlocks(String sectionName, int index) {
        mainPageSteps.checkSectionName(sectionName,index);
        for(int i = 0 ;i < mainPageSteps.getCardsCount(index);i++){
            mainPageSteps.checkCardText(sectionName,i,index)
                    .clickSectionNextBtn(i,index);
        }
        mainPageSteps.clickSectionBackBtn(index);
        for(int i = 0 ; i < mainPageSteps.getCardsCount(index);i++){
            mainPageSteps.checkCardLink(sectionName,i,index);
            String atr = mainPageSteps.getCardTargetAttribute(i,index);
            mainPageSteps.validateCardLink(atr,index,i,sectionName)
                    .reloadCards(index);
        }
    }
}