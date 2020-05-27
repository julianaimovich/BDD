package page;

import com.codeborne.selenide.SelenideElement;
import data.DataHelper;
import page.DashboardPage;

import java.util.Random;
import static com.codeborne.selenide.Selenide.$;

public class MoneyTransferPage {
    private SelenideElement amountOfTransferField = $("[maxlength='14']");
    private SelenideElement sendingCardNumber = $("[maxlength='19']");
    private SelenideElement sendMoneyButton = $("[data-test-id=action-transfer]");
    static String transferAmount = amountOfMoneyToBeSent();

    public DashboardPage transferHasBeenMade(DataHelper.CardNumber cardNumber) {
        amountOfTransferField.setValue(transferAmount);
        sendingCardNumber.setValue(cardNumber.getNumber());
        sendMoneyButton.click();
        return new DashboardPage();
    }

    public static String amountOfMoneyToBeSent() {
        int currentCardBalance = DashboardPage.balance;
        Random rn = new Random();
        int transferAmountInt = rn.nextInt(currentCardBalance);
        transferAmount = String.valueOf(transferAmountInt);
        return transferAmount;
    }
}