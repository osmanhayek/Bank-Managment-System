-- Create the users table
CREATE TABLE users (
    UsersTcId VARCHAR(12) PRIMARY KEY NOT NULL,
    UserName VARCHAR(45),
    UserSurName VARCHAR(45),
    UserFatherName VARCHAR(255),
    UserGender TINYINT,
    UserDateOfBirth DATE,
    UserMailAddress VARCHAR(255),
    UserAddress VARCHAR(255),
    UserCity VARCHAR(255),
    UserState VARCHAR(255),
    UserAccountNumber VARCHAR(36)
);

-- Create the accounts_info table
CREATE TABLE accounts_info (
    AccountId VARCHAR(36) PRIMARY KEY NOT NULL,
    AccountTcId VARCHAR(11),
    AccountAmount DOUBLE,
    AccountDebitCardNo VARCHAR(16),
    AccountCreditCardNo VARCHAR(16),
    AccountPin VARCHAR(4),
    AccountCreditCardCon TINYINT,
    AccountIsExpired TINYINT,
    AccountSecurityQuestion VARCHAR(255),
    AccountQuestionAnswer VARCHAR(255)
);

CREATE TABLE admin (
    adminId VARCHAR(255) PRIMARY KEY NOT NULL,
    adminPassword VARCHAR(45)
);

CREATE TABLE employee_info (
    EmployeeTcId VARCHAR(11) PRIMARY KEY NOT NULL,
    EmployeeMail VARCHAR(255),
    EmployeePassword VARCHAR(25),
    EmployeeSecurityQ VARCHAR(255),
    EmployeeSecurityA VARCHAR(255)
);

CREATE TABLE employee_rapors (
    RaporsId INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    RaporsContext LONGTEXT,
    RaporsPassedTime VARCHAR(45),
    EmployeeTcId VARCHAR(11)
);

CREATE TABLE user_bill (
    UserTcId VARCHAR(11) PRIMARY KEY NOT NULL,
    ElectricBill TINYINT,
    WaterBill TINYINT,
    NaturalGasBill TINYINT
);