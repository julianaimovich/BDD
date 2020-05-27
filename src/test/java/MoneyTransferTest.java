import data.DataHelper;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import page.LoginPage;
import static com.codeborne.selenide.Selenide.open;

public class MoneyTransferTest {

    @Test
    @DisplayName("Should transfer money between own cards")
    void shouldTransferMoneyBetweenOwnCards () {
        open("http://localhost:9999");
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        val dashboardPage = verificationPage.validVerify(verificationCode);
        val cardNumber = DataHelper.getCardNumber();
        val moneyTransferPage = dashboardPage.chooseCard(cardNumber);
        moneyTransferPage.transferHasBeenMade(cardNumber);
        dashboardPage.transferWasSuccessful(cardNumber);
    }
}