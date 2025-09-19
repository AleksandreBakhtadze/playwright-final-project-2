package ge.tbc.testautomation.data;

import java.util.HashMap;

public class TBCContants {
    public String mainPageLink = "https://tbcbank.ge";

    public String homaPageMainTitle = "თიბისი არის ტექნოლოგიური კომპანია, რომელიც ზრუნავს მომხმარებლებზე და ამარტივებს მათ ცხოვრებას.";

    public String[] currencies = {"USD", "EUR", "GBP","GEL"};

    public String productTitle = "სწრაფი ფულადი გზავნილები";

    public String[] productSecondaryTitles = {"სამომხმარებლო სესხი","იპოთეკური სესხი","განვადება"};

    public String[] mainPageSlides = {"თიბისი კონცეპტის სამოგზაურო ბარათი",
            "აიღე ახალი თიბისი ბარათი",
            "გაანაღდე გზავნილი მობაილბანკში და დასაჩუქრდი",
            "გახსენი მობაილბანკი",
            "მოითხოვე 80 000 ლარამდე სესხი ონლაინ",
            "გაანაღდე ფულადი გზავნილი მობაილბანკში",
            "გახსენი დეპოზიტი მობაილბანკში",
            "დაიბრუნე და გადაანაწილე",
            "გადარიცხე მომენტალურად"};

    public String[] h1Titles = {"გახდი თიბისის მომხმარებელი 30 წამში","აიღე თიბისი ბარათი"};

    public String[] mainNavigationItems = {"ჩემთვის","ჩემი ბიზნესისთვის","თიბისი"};

    public String[] mainPageKeyCTAButtonTexts = {"ციფრული ბანკი","ვრცლად","ვრცლად","ვრცლად","ვრცლად","ვრცლად","ყველა"};

    public String[][] subNavigationItems = {
            {"სესხები","ბარათები","ანაბრები","ციფრული სერვისები","სხვა პროდუქტები","არარეზიდენტებისთვის"},
            {"ფინანსირება","ციფრული სერვისები","ყოველდღიური ბანკინგი","ვაჭრობის ფინანსირება","გადახდები","საგადახდო სისტემები","ბიზნეს განათლება"},
            {"განათლება","ფინანსური ანგარიშგება"}
    };

    public HashMap<String, String[]> sectionCardsUrls = new HashMap<String, String[]>() {{
        put("მობაილბანკის უპირატესობები", new String[]{
                "/ka/articles/auto-comfort",
                "/ka/articles/instant-transfers",
                "/ka/other-products/ganatsileba/transaction",
                "/ka/articles/utility-payments",
                "https://tbccapital.ge/ge/investment/main-page",
                "/ka/articles/shipping-and-post"
        });

        put("სხვადასხვა", new String[]{
                "/ka/treasury-products",
                "/ka/loans",
                "/ka/atms&branches"
        });

        put("ბლოგები", new String[]{
                "/ka/blogs/QAlFGUWGM2EBXQv1wsWbG/17th-black-sea-jazz",
                "/ka/blogs/4xWa7YStgYOYtS2SLnZ2GK/devoted-blitz-with-devoted-people",
                "/ka/blogs/23NiNN571IcSRPSB3z8rFR/etsy-is-back",
                "/ka/blogs/66se2Mm6PD8VM33W7UhqR/mewa-india-2025"
        });
    }};

    public String externalPageLink = "https://tbconline.ge/tbcrd/login";
    public String commissionTabText = "გზავნილის გაგზავნის საკომისიო";
    public String convertTabText = "გზავნილის კონვერტაცია";
    public String[] BtnTexts = {"ვრცლად","დეტალურად","გაიგე მეტი"};
}
