
import java.util.ArrayList;
import java.util.List;

//  Base class should be substituatable for derived class without affecting the correctness of the program.
public class LSP{

    public static void main(String[] args) {
        List<WithdrawableAccount> withdrawableAccounts = new ArrayList<>();
        withdrawableAccounts.add(new savingAccount());
        withdrawableAccounts.add(new currentAccount());
        
        List<NonWithdrawableAccount> nonWithdrawableAccounts = new ArrayList<>();
        nonWithdrawableAccounts.add(new FixedDepositAccount());

        BankClient bankClient = new BankClient(withdrawableAccounts, nonWithdrawableAccounts);
        bankClient.performTransactions();

    }

    public interface NonWithdrawableAccount{
        void deposit(double amount);
        double getBalance();
    }

    public interface WithdrawableAccount extends NonWithdrawableAccount {
        void withdraw(double amount);
    }

    public static class savingAccount implements WithdrawableAccount{
        private double balance;
        public savingAccount(){
            this.balance = 0.0;
        }
        public void deposit(double amount){
            balance += amount;
            System.out.println("Deposited: " + amount + " in saving account, New Balance: " + balance);
        }
        public double getBalance(){
            System.out.println("Current Balance in saving account: " + balance);
            return balance;
        }
        public void withdraw(double amount){
            balance -= amount;
            System.out.println("Withdrawing: " + amount + " from saving account, New Balance: " + balance);
        }
    }

    public static class currentAccount implements WithdrawableAccount {
        
        private double balance;

        public currentAccount(){
            this.balance = 0.0;
        }
        public void deposit(double amount){
            balance += amount;
            System.out.println("Deposited: " + amount + " in current account, New Balance: " + balance);
        }
        public double getBalance(){
            System.out.println("Current Balance in current account: " + balance);
            return balance;
        }
        public void withdraw(double amount){
            balance -= amount;
            System.out.println("Withdrawing: " + amount + " from current account, New Balance: " + balance);
        }
    }

    public static class BankClient{
        private List<WithdrawableAccount> withdrawableAccounts;
        private List<NonWithdrawableAccount> nonWithdrawableAccounts;
        public BankClient(List<WithdrawableAccount> withdrawableAccounts, List<NonWithdrawableAccount> nonWithdrawableAccounts) {
            this.withdrawableAccounts = withdrawableAccounts;
            this.nonWithdrawableAccounts = nonWithdrawableAccounts;
        }

        public void performTransactions() {
            for (WithdrawableAccount account : withdrawableAccounts) {
                account.deposit(1000);
                account.withdraw(500);
                account.getBalance();
            }
            for (NonWithdrawableAccount account : nonWithdrawableAccounts) {
                account.deposit(1000);
                account.getBalance();
            }
        }
    }
        public static class FixedDepositAccount implements NonWithdrawableAccount {
        
        private double balance;

        public FixedDepositAccount(){
            this.balance = 0.0;
        }
        public void deposit(double amount){
            balance += amount;
            System.out.println("Deposited: " + amount + " in fixed deposit account, New Balance: " + balance);
        }
        public double getBalance(){
            System.out.println("Current Balance in fixed deposit account: " + balance);
            return balance;
        }
               
    }

}