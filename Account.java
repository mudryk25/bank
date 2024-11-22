import java.io.IOException;

public class Account {
    String name;
    double balance;
    String accNo;
    String password;

    static DBParser parser = new DBParser("G:\\java\\accounts.txt");

    Account(String name, double balance, String accNo, String password) {
        this.name = name;
        this.balance = balance;
        this.accNo = accNo;
        this.password = password;
    }

    Account(String name, String accNo, String password) {
        this.name = name;
        this.balance = 0.0;
        this.accNo = accNo;
        this.password = password;
    }

    String asDbString() {
        return String.format("%s # %s # %s # %s", this.name, this.password, this.balance, this.accNo);
    }

    static Account getAccount(String accNo) throws Exception {
        for (Account acc : parser.parse()) {
            if (acc.accNo.equals(accNo))
                return acc;
        }

        throw new RuntimeException("Account not Found");
    }

    void deposit(double amount) throws IOException {
        this.balance += amount;
        this.balance = Math.round(balance * 100.0) / 100.0;
        parser.updateAccount(this);
        
    }

    void withdraw(double amount) throws IOException {
        if (this.balance < amount) {
            return;
        }
        this.balance -= amount;
        this.balance = Math.round(balance * 100.0) / 100.0;
        parser.updateAccount(this);
    }

    void transfer(Account to, double amount) throws IOException {
        if (this.balance < amount) {
            System.out.println("Insufficient funds!");
            return;
        }
        this.balance -= amount;
        this.balance = Math.round(balance * 100.0) / 100.0;
        to.balance += amount;
        to.balance = Math.round(balance * 100.0) / 100.0;
        parser.updateAccount(this);
        parser.updateAccount(to);
    }
    public Account(String accNo,String name) {
        this.accNo = accNo;
        this.name=name;
    }
}
