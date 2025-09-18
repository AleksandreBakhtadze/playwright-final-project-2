package ge.tbc.testautomation.data;

import java.util.HashMap;

public class TBCContants {
    public String mainPageLink = "https://tbcbank.ge";

    public String homaPageMainTitle = "თიბისი არის ტექნოლოგიური კომპანია, რომელიც ზრუნავს მომხმარებლებზე და ამარტივებს მათ ცხოვრებას.";

    public String[] currencies = {"USD", "EUR", "GBP","GEL"};

    public String productTitle = "სწრაფი ფულადი გზავნილები";

    public String[] productSecondaryTitles = {"სამომხმარებლო სესხი","იპოთეკური სესხი","განვადება"};

    public String[] transferSystems = {"FastTransfer\n" + "currency - EUR/GEL/USD",
            "IntelExpress\n" + "currency - EUR/GBP/GEL/USD",
            "MoneyGram\n" + "currency - EUR/GEL/USD",
            "Ria\n" + "currency - EUR/GEL/USD",
            "RicoGram\n" + "currency - EUR/USD",
            "WesternUnion\n" + "currency - EUR/GEL/USD",
            "ZolotayaKorona\n" + "currency - EUR/GEL/RUB/USD"};

    public HashMap<String, String[]> countriesCommissions = new HashMap<String, String[]>() {{
        put("საქართველო", new String[] {
                "WesternUnion\n"+"საკომისიო 1.8 $",
                "FastTransfer\n"+"საკომისიო 5 $",
                "MoneyGram\n"+"საკომისიო 2 $",
                "IntelExpress\n"+"საკომისიო 1 $"
        });

        put("აშშ", new String[] {
                "Ria\n"+"საკომისიო 4 $",
                "WesternUnion\n"+"საკომისიო 4 $",
                "MoneyGram\n"+"საკომისიო 2.99 $",
                "IntelExpress\n"+"საკომისიო 3 $"
        });

        put("კანადა", new String[] {
                "Ria\n"+"საკომისიო 4 $",
                "WesternUnion\n"+"საკომისიო 4 $",
                "MoneyGram\n"+"საკომისიო 2.99 $"
        });
    }};



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

    public HashMap<String, String[]> sectionCardsTitles = new HashMap<String, String[]>() {{
        put("მობაილბანკის უპირატესობები", new String[]{
                "ავტო კომფორტი",
                "მომენტალური გადარიცხვა",
                "ტრანზაქციის განაწილება",
                "კომუნალური გადახდები",
                "ინვესტიციები",
                "ამანათები და ფოსტა"
        });

        put("სხვადასხვა", new String[]{
                "გაიგე ვალუტის მიმდინარე კურსი",
                "გამოთვალე სესხი",
                "იპოვე ფილიალები და ბანკომატები"
        });

        put("ბლოგები", new String[]{
                "ვის მოუსმენთ შავი ზღვის მე-17 ჯაზფესტივალზე",
                "DEVOTED ბლიცი, DEVOTED ადამიანებთან",
                "Etsy საქართველოს ბაზარზე დაბრუნდა — რა უნდა იცოდეთ?",
                "8 კომპანიამ ინდოეთში საერთაშორისო გამოფენაში მიიღო მონაწილეობა"
        });
    }};

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


    public HashMap<String, Integer> loadedCardsCounts = new HashMap<String, Integer>() {{
        put("საქართველო", 11);

        put("აშშ", 11);

        put("კანადა", 10);
    }};

    public String externalPageLink = "https://tbconline.ge/tbcrd/login";
    public String commissionTabText = "გზავნილის გაგზავნის საკომისიო";
    public String convertTabText = "გზავნილის კონვერტაცია";

    public String[] BtnTexts = {"ვრცლად","დეტალურად","გაიგე მეტი"};
}
