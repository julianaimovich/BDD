package page;

import com.codeborne.selenide.SelenideElement;
import data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DashboardPage {
    private SelenideElement heading = $("[data-test-id=dashboard");
    private SelenideElement transferButton = $$("[data-test-id=action-deposit]").last();
    static int balance;
    static int balanceAfterTransfer;

    public DashboardPage() {
        heading.shouldBe(visible);
    }

    public MoneyTransferPage chooseCard(DataHelper.CardNumber cardNumber) {
        balance = getCardBalance(cardNumber);
        transferButton.click();
        return new MoneyTransferPage();
    }

    public static int getCardBalance(DataHelper.CardNumber cardNumber) {
        String exactCardNumber = cardNumber.getNumber();
        String lastFourDigits = exactCardNumber.substring(exactCardNumber.length() - 4);
        String cardInfo = $(withText(lastFourDigits)).getText();
        String cardBalanceStr = cardInfo.substring(cardInfo.lastIndexOf(":") + 2, cardInfo.length() - 13);
        int cardBalance = Integer.parseInt(cardBalanceStr);
        return cardBalance;
    }

    public void transferWasSuccessful(DataHelper.CardNumber cardNumber) {
        balanceAfterTransfer = getCardBalance(cardNumber);
        int transferAmountInt = Integer.parseInt(MoneyTransferPage.transferAmount);
        int calculatedBalance = balance - transferAmountInt;
        assertEquals(balanceAfterTransfer, calculatedBalance);
    }
}