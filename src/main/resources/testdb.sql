
CREATE TABLE countries (
    id INT PRIMARY KEY IDENTITY(1,1),
    name VARCHAR(50) NOT NULL UNIQUE,
    expected_card_count INT
);

CREATE TABLE commissions (
    id INT PRIMARY KEY IDENTITY(1,1),
    country_id INT NOT NULL FOREIGN KEY REFERENCES countries(id),
    system_name VARCHAR(50) NOT NULL,
    commission_details VARCHAR(100) NOT NULL,
    order_index INT NOT NULL,
    CONSTRAINT UC_Country_System UNIQUE (country_id, system_name)
);

INSERT INTO countries (name, expected_card_count) VALUES
(N'საქართველო', 11),
(N'აშშ', 11),
(N'კანადა', 10);

INSERT INTO commissions (country_id, system_name, commission_details, order_index) VALUES
((SELECT id FROM countries WHERE name = N'საქართველო'), N'WesternUnion', N'საკომისიო 1.8 $', 0),
((SELECT id FROM countries WHERE name = N'საქართველო'), N'FastTransfer', N'საკომისიო 5 $', 1),
((SELECT id FROM countries WHERE name = N'საქართველო'), N'MoneyGram', N'საკომისიო 2 $', 2),
((SELECT id FROM countries WHERE name = N'საქართველო'), N'IntelExpress', N'საკომისიო 1 $', 3);

INSERT INTO commissions (country_id, system_name, commission_details, order_index) VALUES
((SELECT id FROM countries WHERE name = N'აშშ'), N'Ria', N'საკომისიო 4 $', 0),
((SELECT id FROM countries WHERE name = N'აშშ'), N'WesternUnion', N'საკომისიო 4 $', 1),
((SELECT id FROM countries WHERE name = N'აშშ'), N'MoneyGram', N'საკომისიო 2.99 $', 2),
((SELECT id FROM countries WHERE name = N'აშშ'), N'IntelExpress', N'საკომისიო 3 $', 3);

INSERT INTO commissions (country_id, system_name, commission_details, order_index) VALUES
((SELECT id FROM countries WHERE name = N'კანადა'), N'Ria', N'საკომისიო 4 $', 0),
((SELECT id FROM countries WHERE name = N'კანადა'), N'WesternUnion', N'საკომისიო 4 $', 1),
((SELECT id FROM countries WHERE name = N'კანადა'), N'MoneyGram', N'საკომისიო 2.99 $', 2);


select * from commissions;
select * from countries;


CREATE TABLE transfer_systems (
    id INT PRIMARY KEY IDENTITY(1,1),
    system_name NVARCHAR(50) NOT NULL,
    currencies NVARCHAR(100) NOT NULL,
    display_order INT NOT NULL,
    CONSTRAINT UC_System_Name UNIQUE (system_name)
);

INSERT INTO transfer_systems (system_name, currencies, display_order) VALUES
(N'FastTransfer', N'EUR/GEL/USD', 0),
(N'IntelExpress', N'EUR/GBP/GEL/USD', 1),
(N'MoneyGram', N'EUR/GEL/USD', 2),
(N'Ria', N'EUR/GEL/USD', 3),
(N'RicoGram', N'EUR/USD', 4),
(N'WesternUnion', N'EUR/GEL/USD', 5),
(N'ZolotayaKorona', N'EUR/GEL/RUB/USD', 6);

select * from transfer_systems;

CREATE TABLE section_card_titles (
    id INT PRIMARY KEY IDENTITY(1,1),
    section_name NVARCHAR(100) NOT NULL,
    card_title NVARCHAR(200) NOT NULL,
    display_order INT NOT NULL,
    CONSTRAINT UC_Section_Card UNIQUE (section_name, card_title, display_order)
);

INSERT INTO section_card_titles (section_name, card_title, display_order) VALUES
(N'მობაილბანკის უპირატესობები', N'ავტო კომფორტი', 0),
(N'მობაილბანკის უპირატესობები', N'მომენტალური გადარიცხვა', 1),
(N'მობაილბანკის უპირატესობები', N'ტრანზაქციის განაწილება', 2),
(N'მობაილბანკის უპირატესობები', N'კომუნალური გადახდები', 3),
(N'მობაილბანკის უპირატესობები', N'ინვესტიციები', 4),
(N'მობაილბანკის უპირატესობები', N'ამანათები და ფოსტა', 5);

INSERT INTO section_card_titles (section_name, card_title, display_order) VALUES
(N'სხვადასხვა', N'გაიგე ვალუტის მიმდინარე კურსი', 0),
(N'სხვადასხვა', N'გამოთვალე სესხი', 1),
(N'სხვადასხვა', N'იპოვე ფილიალები და ბანკომატები', 2);

INSERT INTO section_card_titles (section_name, card_title, display_order) VALUES
(N'ბლოგები', N'ვის მოუსმენთ შავი ზღვის მე-17 ჯაზფესტივალზე', 0),
(N'ბლოგები', N'DEVOTED ბლიცი, DEVOTED ადამიანებთან', 1),
(N'ბლოგები', N'Etsy საქართველოს ბაზარზე დაბრუნდა — რა უნდა იცოდეთ?', 2),
(N'ბლოგები', N'8 კომპანიამ ინდოეთში საერთაშორისო გამოფენაში მიიღო მონაწილეობა', 3);

select * from section_card_titles;