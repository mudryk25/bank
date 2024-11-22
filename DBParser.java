import java.io.*;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class DBParser {
    String dbPath;

    DBParser(String path) {
        this.dbPath = path;
    }

    Account[] parse() throws Exception {
        if (dbPath.length() <= 0) {
            throw new Exception("DB Path is empty or invalid!");
        }

        try {
            FileReader fr = new FileReader(dbPath);
            Scanner sc = new Scanner(fr);
            sc.useDelimiter("\n");
            List<Account> accs = new ArrayList<Account>();
            while (sc.hasNext()) {
                String accRaw = sc.next();
                String[] accTokenized = accRaw.split(" # ");
                if (accTokenized.length != 4) {
                    sc.close();
                    throw new Exception("Invalid format!");
                }
                try {
                    Account acc = new Account(accTokenized[0], Double.parseDouble(accTokenized[2]), accTokenized[3],
                            accTokenized[1]);
                    accs.add(acc);
                } catch (Exception e) {
                    sc.close();
                    throw new Exception("Invalid format!");
                }
            }
            sc.close();
            return accs.toArray(new Account[0]);
        } catch (Exception e) {
            throw new Exception("DB Path is empty or invalid!");
        }
    }

    boolean addAccount(Account acc) throws Exception {
        try {
            FileWriter fw = new FileWriter(dbPath, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(acc.name + " # " + acc.password + " # " + acc.balance + " # " + acc.accNo + "\n");
            bw.close();
            return true;
        } catch (Exception e) {
            throw new Exception("Failed to add account!");
        }
    }

    void updateAccount(Account acc) throws IOException {
        String temp = "";

        try (Scanner reader = new Scanner(new File("G:\\java\\accounts.txt"))) {
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String[] parts = line.split(" # ");

                if (parts.length != 4)
                    throw new IOException("Invalid account format in database!");

                if (parts[3].equals(acc.accNo))
                    line = acc.asDbString();

                temp += line + "\n";
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            return;
        }

        BufferedWriter bw = new BufferedWriter(new FileWriter("G:\\java\\accounts.txt", false));
        bw.write(temp);
        bw.close();
    }
}