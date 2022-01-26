package ru.netology.web.test;

import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.*;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MoneyTransferTest {

    @Test
    void shouldTransferMoneyOnFirstCard() {
        open("http://localhost:9999");
        var loginPage = new LoginPageV2();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        var dashboardPage = new DashboardPage();
        var balanceFirstCardBeforeTransfer = dashboardPage.getFirstCardBalance();
        var balanceSecondCardBeforeTransfer = dashboardPage.getSecondCardBalance();
        dashboardPage.transferMonyFromCard1();
        int transfersum = 1000;
        var monyTransferPage = new MonyTransferPage();
        monyTransferPage.transferMony(transfersum, DataHelper.getCardSecond());
        var dashboardPage2 = new DashboardPage();
        var balanceFirstCardAfterTransfer = dashboardPage2.getFirstCardBalance();
        var balanceSecondCardAfterTransfer = dashboardPage2.getSecondCardBalance();
        assertEquals((balanceFirstCardBeforeTransfer + transfersum), balanceFirstCardAfterTransfer);
        assertEquals((balanceSecondCardBeforeTransfer - transfersum), balanceSecondCardAfterTransfer);
    }

    @Test
    void shouldNotTransferMoneyOnFirstCard() {
        open("http://localhost:9999");
        var loginPage = new LoginPageV2();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        var dashboardPage = new DashboardPage();
        var balanceFirstCardBeforeTransfer = dashboardPage.getFirstCardBalance();
        var balanceSecondCardBeforeTransfer = dashboardPage.getSecondCardBalance();
        dashboardPage.transferMonyFromCard1();
        int transfersum = 50000;
        var monyTransferPage = new MonyTransferPage();
        monyTransferPage.transferError(transfersum, DataHelper.getCardSecond());
        var dashboardPage2 = new DashboardPage();
        var balanceFirstCardAfterTransfer = dashboardPage2.getFirstCardBalance();
        var balanceSecondCardAfterTransfer = dashboardPage2.getSecondCardBalance();
        assertEquals((balanceFirstCardBeforeTransfer), balanceFirstCardAfterTransfer);
        assertEquals((balanceSecondCardBeforeTransfer), balanceSecondCardAfterTransfer);
    }

}