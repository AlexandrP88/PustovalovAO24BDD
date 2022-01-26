package ru.netology.web.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.MonyTransferPage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    private SelenideElement heading = $("[data-test-id=dashboard]");
    private SelenideElement topUpCard1Button = $("[data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0'] button");
    private SelenideElement topUpCard2Button = $("[data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d'] button");
    private ElementsCollection cards = $$(".list__item");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";



    public DashboardPage() {
        heading.shouldBe(visible);
    }

    public MonyTransferPage transferMonyFromCard1() {
        topUpCard1Button.click();
        return new MonyTransferPage();
    }

    public MonyTransferPage transferMonyFromCard2() {
        topUpCard2Button.click();
        return new MonyTransferPage();
    }

    public int getFirstCardBalance() {
        val text = cards.first().text();
        return extractBalance(text);
    }

    public int getSecondCardBalance() {
        val text = cards.last().text();
        return extractBalance(text);
    }

    private int extractBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }
}