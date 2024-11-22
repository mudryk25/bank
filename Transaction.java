import java.time.LocalDateTime;

public class Transaction {
    public enum TransactionType {
        CREDIT, DEBIT, TRANSFER
    }

    String transactionId;
    Account from;
    Account to;
    double amount;
    TransactionType type;
    LocalDateTime createdAt;
    String fromName;
    String toName;

    public Transaction(String id,Account from, Account to, double amount, TransactionType type,String createdAt,String fromName,String toName) {
        this.transactionId = type.toString().charAt(0) + String.valueOf((int)(Math.random() * 1000000));
        this.from = from;
        this.fromName=fromName;
        this.toName=toName;
        this.to = to;
        this.amount = amount;
        this.type = type;
        this.createdAt = LocalDateTime.parse(createdAt);
    }

   
    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s,%.2f,%s,%s,%s", transactionId, createdAt, from.accNo, to.accNo, amount, type,fromName,toName);
    }
}
