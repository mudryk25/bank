import java.io.*;
import java.util.*;
import java.time.LocalDateTime;

public class LogBook {
    private String dbPath;

    public LogBook(String path) {
        this.dbPath = path;
    }

    // Parses transactions from the file
    public List<Transaction> parse() throws Exception {
        List<Transaction> transactions = new ArrayList<>();
        try (Scanner sc = new Scanner(new File(dbPath))) {
            while (sc.hasNextLine()) {
                String txnRaw = sc.nextLine();
                String[] txnTokenized = txnRaw.split(",");
                if (txnTokenized.length != 8) continue; // Skip invalid lines

                Account from = new Account(txnTokenized[2],txnTokenized[6]);
                Account to = new Account(txnTokenized[3],txnTokenized[7]);
                Transaction.TransactionType txnType = Transaction.TransactionType.valueOf(txnTokenized[5]);
                Transaction txn = new Transaction(txnTokenized[0], from, to, Double.parseDouble(txnTokenized[4]), txnType, txnTokenized[1],txnTokenized[6],txnTokenized[7]);
                transactions.add(txn);
            }
        } catch (IOException e) {
            throw new Exception("Failed to load transaction history.");
        }
        return transactions;
    }

    // Writes a new transaction to the file
    public void logTransaction(Transaction transaction) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(dbPath, true))) {
            writer.write(transaction.toString());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error logging transaction: " + e.getMessage());
        }
    }
}
