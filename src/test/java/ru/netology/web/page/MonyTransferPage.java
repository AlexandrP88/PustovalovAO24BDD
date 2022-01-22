package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class MonyTransferPage {
    private SelenideElement sum = $("[data-test-id='amount'] input");
    private SelenideElement from = $("[data-test-id='from'] input");
    private SelenideElement transferButton = $("[data-test-id='action-transfer']");

    public MonyTransferPage() {

    }

    public DashboardPage transferMony (int transfersum, DataHelper.Card cardInfo) {
        sum.setValue(String.valueOf(transfersum));
        from.setValue(cardInfo.getNamber());
        transferButton.click();
        return new DashboardPage();
    }

}
