package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class MonyTransferPage {
    private SelenideElement sum = $("[data-test-id='amount'] input");
    private SelenideElement from = $("[data-test-id='from'] input");
    private SelenideElement transferButton = $("[data-test-id='action-transfer']");
    private SelenideElement errorOverLimit = $(".notification__content");

    public MonyTransferPage() {

    }

    public DashboardPage transferMony(int transfersum, DataHelper.Card cardInfo) {
        sum.setValue(String.valueOf(transfersum));
        from.setValue(cardInfo.getNamber());
        transferButton.click();
        return new DashboardPage();
    }
    public DashboardPage transferError(int transfersum, DataHelper.Card cardInfo) {
        sum.setValue(String.valueOf(transfersum));
        from.setValue(cardInfo.getNamber());
        transferButton.click();
        errorOverLimit.shouldBe(visible)
                .shouldHave(exactText("Превышен остаток по карте"));
        return new DashboardPage();
    }



}
