import javax.swing.*;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.time.format.DateTimeFormatter;
public class home implements ActionListener {
    public enum TransactionType {
        CREDIT, DEBIT, TRANSFER
    }
    static DBParser parser = new DBParser("G:\\java\\accounts.txt");
    static LogBook log = new LogBook("passbook.txt");
    static JLabel userlabel;
    static JLabel passlabel;
    static JTextField userText;
    static JPasswordField passText;
    static JButton button, back;
    static JFrame frame;
    static JButton button2;
    static Color bg = new Color(37, 37, 38);
    static Color txt = new Color(84, 199, 125);
    static Color byec = new Color(219, 46, 98);

    // greeting
    public static String getGreeting() {
        LocalTime now = LocalTime.now();
        int hour = now.getHour();

        if (hour >= 5 && hour < 12) {
            return "Good Morning ,";
        } else if (hour >= 12 && hour < 18) {
            return "Good Afternoon ,";
        } else {
            return "Good Evening ,";
        }
    }

    // LOGIN PAGE (DONE)

    public static void login() {
        JPanel panel = new JPanel();
        frame = new JFrame();
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setLocation(500, 200);
        panel.setBackground(bg);
        panel.setLayout(null);

        back = new JButton("←");
        back.setBounds(10, 10, 45, 20);
        back.setFont(new Font("SF Pro", 0, 30));
        back.setForeground(Color.white);
        back.setBorder(null);
        back.setFocusPainted(false);
        back.setBackground(bg);
        back.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        frame.setVisible(false);
                        main(null);
                    }
                });
        panel.add(back);

        JLabel s = new JLabel("Enter your credentials");
        s.setBounds(100, 100, 500, 30);
        s.setFont(new Font("SF Pro", Font.BOLD, 25));
        s.setForeground(txt);
        panel.add(s);

        userlabel = new JLabel("Username");
        userlabel.setBounds(100, 180, 80, 25);
        userlabel.setForeground(Color.white);
        panel.add(userlabel);

        userText = new JTextField(20);
        userText.setBounds(180, 180, 175, 25);

        panel.add(userText);

        passlabel = new JLabel("Password");
        passlabel.setBounds(100, 220, 80, 25);
        passlabel.setForeground(Color.white);
        panel.add(passlabel);

        passText = new JPasswordField(20);
        passText.setBounds(180, 220, 175, 25);

        panel.add(passText);

        button = new JButton("Sign in");
        button.setBounds(200, 300, 80, 25);
        button.setBackground(bg);
        button.setForeground(txt);
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
               button.setBackground(txt);
               button.setForeground(bg);
               button.setBorder(null);
            }
        
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(bg);
                button.setForeground(txt);
                button.setBorder(BorderFactory.createLineBorder(Color.gray));
            }
        });
        button.addActionListener(new home());
        panel.add(button);

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String user = userText.getText();
        String pass = new String(passText.getPassword());
        try {
            Account[] accs = parser.parse();
            for (Account acc : accs) {
                if (acc.name.equals(user) && acc.password.equals(pass)) {
                   
                    login_success(acc,log);

                    return;
                } else if (acc.name.equals(user) && !acc.password.equals(pass)) {
                    JOptionPane.showMessageDialog(frame, "Invalid password !");
                    return;
                }
            }
        } catch (Exception ee) {
            System.out.println(ee);
        }
    }

    // REGISTER (DONE)

    static JTextField nameText;
    static JPasswordField passText2, repassText;

    public static void register() {
        JPanel panel = new JPanel();
        JFrame frame = new JFrame();
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(panel);
        panel.setBackground(bg);
        frame.setLocation(500, 200);
        panel.setLayout(null);

        back = new JButton("←");
        back.setBounds(10, 10, 45, 20);
        back.setFont(new Font("SF Pro", 0, 30));
        back.setForeground(Color.white);
        back.setBorder(null);
        back.setFocusPainted(false);
        back.setBackground(bg);
        back.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        frame.setVisible(false);
                        main(null);
                    }
                });
        panel.add(back);

        JLabel s = new JLabel("Enter your credentials");
        s.setBounds(120, 100, 500, 30);
        s.setFont(new Font("SF Pro", Font.BOLD, 25));
        s.setForeground(txt);
        panel.add(s);

        JLabel name = new JLabel("Name");
        name.setBounds(70, 170, 80, 25);
        name.setForeground(Color.white);
        panel.add(name);

        nameText = new JTextField(20);
        nameText.setBounds(200, 170, 185, 25);
        panel.add(nameText);

        passlabel = new JLabel("Password");
        passlabel.setBounds(70, 220, 80, 25);
        passlabel.setForeground(Color.white);
        panel.add(passlabel);

        passText2 = new JPasswordField(20);
        passText2.setBounds(200, 220, 185, 25);
        panel.add(passText2);

        JLabel repasslabel = new JLabel("Retype Password");
        repasslabel.setBounds(70, 270, 120, 25);
        repasslabel.setForeground(Color.white);
        panel.add(repasslabel);

        repassText = new JPasswordField(20);
        repassText.setBounds(200, 270, 185, 25);
        panel.add(repassText);

        button = new JButton("Sign up");
        button.setBounds(200, 350, 80, 25);
        button.setBackground(bg);
        button.setForeground(txt);
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
               button.setBackground(txt);
               button.setForeground(bg);
               button.setBorder(null);
            }
        
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(bg);
                button.setForeground(txt);
                button.setBorder(BorderFactory.createLineBorder(Color.gray));
            }
        });
        button.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (!passText2.getText().equals( repassText.getText())) {
                            JOptionPane.showMessageDialog(frame, "Passwords do not match!");
                        } else {
                            try {
                                String accNo = String.valueOf(Math.round(Math.random() * 10000000))
                                        + String.valueOf(Math.round(Math.random() * 10000000));
                                Account acc = new Account(nameText.getText(), accNo,
                                        new String(passText2.getText()));
                                parser.addAccount(acc);
                            } catch (Exception e1) {
                                System.out.println(e1);
                            }
                            frame.setVisible(false);
                            JOptionPane.showMessageDialog(frame, "Signed up successfully!");
                            main(null);
                        }
                    }
                });
        panel.add(button);

        frame.setVisible(true);
    }

    // LOGIN SUCCESS (DONE)

    public static void login_success(Account acc, LogBook logBook) {
        frame.setVisible(false);
        JPanel panel = new JPanel();
        JFrame suc = new JFrame();
        suc.setSize(500, 500);
        suc.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        suc.add(panel);
        panel.setBackground(bg);
        suc.setLocation(500, 200);
        suc.add(panel);
        panel.setLayout(null);

        back = new JButton("←");
        back.setBounds(10, 10, 45, 20);
        back.setFont(new Font("SF Pro", 0, 30));
        back.setForeground(Color.white);
        back.setBorder(null);
        back.setFocusPainted(false);
        back.setBackground(bg);
        back.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        suc.setVisible(false);
                        login();
                    }
                });
        panel.add(back);

        JLabel wel = new JLabel("Hi, " + Character.toUpperCase(acc.name.charAt(0)) + acc.name.substring(1));
        wel.setBounds(30, 100, 500, 30);
        wel.setFont(new Font("SF Pro", Font.BOLD, 30));
        wel.setForeground(Color.white);
        panel.add(wel);

        JLabel wel2 = new JLabel("Welcome Back!");
        wel2.setBounds(30, 130, 500, 30);
        wel2.setFont(new Font("SF Pro", Font.PLAIN, 12));
        wel2.setForeground(Color.gray);
        panel.add(wel2);

        JLabel bal = new JLabel("₹ " + acc.balance);
        bal.setBounds(30, 200, 300, 100);
        bal.setFont(new Font("SF Pro", Font.BOLD, 55));
        bal.setForeground(txt);
        panel.add(bal);

        JLabel bal2 = new JLabel("Wallet Balance");
        bal2.setBounds(35, 245, 200, 100);
        bal2.setFont(new Font("SF Pro", Font.PLAIN, 12));
        bal2.setForeground(Color.gray);
        panel.add(bal2);

        JButton dash = new JButton("Dashboard");
        dash.setBounds(360, 100, 100, 25);
        dash.setBorder(null);
        dash.setBackground(bg);
        dash.setForeground(Color.white);

        panel.add(dash);

        JButton dep = new JButton("Deposit");
        dep.setBounds(360, 140, 100, 25);
        dep.setBorder(null);
        dep.setBackground(bg);
        dep.setForeground(Color.gray);
        dep.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        suc.setVisible(false);
                        deposit(acc,logBook);
                    }
                });
        panel.add(dep);

        JButton with = new JButton("Withdraw");
        with.setBounds(360, 180, 100, 25);
        with.setBackground(bg);
        with.setBorder(null);
        with.setForeground(Color.gray);
        with.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        suc.setVisible(false);
                        withdraw(acc,logBook);
                    }
                });
        panel.add(with);

        JButton transfer = new JButton("Transfer");
        transfer.setBounds(360, 220, 100, 25);
        transfer.setBackground(bg);
        transfer.setBorder(null);
        transfer.setForeground(Color.gray);
        transfer.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        suc.setVisible(false);
                        transfer(acc,logBook);
                    }
                });
        panel.add(transfer);

        JButton history = new JButton("Activity");
        history.setBounds(360, 260, 100, 25);
        history.setBorder(null);
        history.setBackground(bg);
        history.setForeground(Color.gray);
        history.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        suc.setVisible(false);
                        activity(acc,logBook);
                    }
                });
        panel.add(history);

        JButton bye = new JButton("EXIT");
        bye.setBorder(null);
        bye.setBounds(360, 400, 105, 25);
        bye.setForeground(byec);
        bye.setBackground(bg);
        bye.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        suc.setVisible(false);
                        JOptionPane.showMessageDialog(frame, "Thank you for using our services");
                    }
                });
        panel.add(bye);

        suc.setVisible(true);
    }

    // MAIN (DONE)

    public static void main(String[] args) {
        JPanel panel = new JPanel();
        JFrame frame = new JFrame();
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setTitle("VMS_bank");
        panel.setLayout(null);
        panel.setBackground(bg);
        frame.setLocation(500, 200);

        JLabel wel = new JLabel(getGreeting());
        wel.setBounds(50, 80, 500, 60);
        wel.setFont(new Font("SF Pro", Font.BOLD, 40));
        wel.setForeground(txt);
        panel.add(wel);

        JLabel text = new JLabel(
                "<html>Welcome to VMS_bank,<br> your secure, convenient, and reliable banking partner.<br> Manage your finances with ease—deposit, withdraw, transfer,<br> and view your transaction history anytime.<br> Empowering you with seamless banking<br> at your fingertips!");
        text.setBounds(60, 120, 700, 200);
        text.setFont(new Font("SF Pro", Font.PLAIN, 14));
        text.setForeground(Color.gray);
        panel.add(text);

        JButton loginButton = new JButton("SIGN IN");
        loginButton.setBounds(90, 320, 130, 35);
        loginButton.setBackground(bg);
        loginButton.setFocusPainted(false);
        loginButton.setForeground(Color.white);
        loginButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
               loginButton.setBackground(txt);
               loginButton.setForeground(bg);
               loginButton.setBorder(null);
            }
        
            public void mouseExited(java.awt.event.MouseEvent evt) {
                loginButton.setBackground(bg);
                loginButton.setForeground(Color.white);
                loginButton.setBorder(BorderFactory.createLineBorder(Color.gray));
            }
        });
        loginButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        frame.setVisible(false);
                        login();
                    }
                });
        panel.add(loginButton);

        JButton accButton = new JButton("SIGN UP");
        accButton.setBounds(265, 320, 130, 35);
        accButton.setBackground(bg);
        accButton.setForeground(Color.white);
        accButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
               accButton.setBackground(txt);
               accButton.setForeground(bg);
               accButton.setBorder(null);
            }
        
            public void mouseExited(java.awt.event.MouseEvent evt) {
                accButton.setBackground(bg);
                accButton.setForeground(Color.white);
                accButton.setBorder(BorderFactory.createLineBorder(Color.gray));
            }
        });
        accButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        frame.setVisible(false);
                        register();
                    }
                });
        panel.add(accButton);

        JButton bye = new JButton("EXIT");
        bye.setBounds(215, 400, 50, 25);
        bye.setBorder(null);
        bye.setForeground(byec);
        bye.setBackground(bg);
        bye.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
               bye.setBackground(byec);
               bye.setForeground(Color.white);
             
            }
        
            public void mouseExited(java.awt.event.MouseEvent evt) {
                bye.setBackground(bg);
                bye.setForeground(byec);
                
            }
        });
        bye.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        frame.setVisible(false);
                        JOptionPane.showMessageDialog(frame, "Thank you for using our services");
                    }
                });
        panel.add(bye);

        frame.setVisible(true);
    }

    // DEPOSIT (DONE)

    public static void deposit(Account acc,LogBook logBook) {
        JPanel panel = new JPanel();
        JFrame frame = new JFrame();
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        panel.setLayout(null);
        panel.setBackground(bg);
        frame.setLocation(500, 200);
        back = new JButton("←");
        back.setBounds(10, 10, 45, 20);
        back.setFont(new Font("SF Pro", 0, 30));
        back.setForeground(Color.white);
        back.setBorder(null);
        back.setFocusPainted(false);
        back.setBackground(bg);
        back.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        frame.setVisible(false);
                        login_success(acc,logBook);
                    }
                });
        panel.add(back);

        JButton dash = new JButton("Dashboard");
        dash.setBounds(360, 100, 100, 25);
        dash.setBorder(null);
        dash.setBackground(bg);
        dash.setForeground(Color.gray);
        dash.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        frame.setVisible(false);
                        login_success(acc,logBook);
                    }
                });
        panel.add(dash);

        JButton dep = new JButton("Deposit");
        dep.setBounds(360, 140, 100, 25);
        dep.setBorder(null);
        dep.setBackground(bg);
        dep.setForeground(Color.white);
        dep.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        frame.setVisible(false);
                        deposit(acc,logBook);
                    }
                });
        panel.add(dep);

        JButton with = new JButton("Withdraw");
        with.setBounds(360, 180, 100, 25);
        with.setBackground(bg);
        with.setBorder(null);
        with.setForeground(Color.gray);
        with.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        frame.setVisible(false);
                        withdraw(acc,logBook);
                    }
                });
        panel.add(with);

        JButton transfer = new JButton("Transfer");
        transfer.setBounds(360, 220, 100, 25);
        transfer.setBackground(bg);
        transfer.setBorder(null);
        transfer.setForeground(Color.gray);
        transfer.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        frame.setVisible(false);
                        transfer(acc,logBook);
                    }
                });
        panel.add(transfer);

        JButton history = new JButton("Activity");
        history.setBounds(360, 260, 100, 25);
        history.setBorder(null);
        history.setBackground(bg);
        history.setForeground(Color.gray);
        history.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        frame.setVisible(false);
                        activity(acc,logBook);
                    }
                });
        panel.add(history);

        JButton bye = new JButton("EXIT");
        bye.setBorder(null);
        bye.setBounds(360, 400, 105, 25);
        bye.setForeground(byec);
        bye.setBackground(bg);
        bye.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        frame.setVisible(false);
                        JOptionPane.showMessageDialog(frame, "Thank you for using our services");
                    }
                });
        panel.add(bye);

        JLabel s = new JLabel("Deposit");
        s.setBounds(30, 80, 500, 30);
        s.setFont(new Font("SF Pro", Font.BOLD, 30));
        s.setForeground(txt);
        panel.add(s);
        userlabel = new JLabel("Enter amount to be deposited");
        userlabel.setBounds(50, 150, 200, 25);
        userlabel.setForeground(Color.white);
        panel.add(userlabel);

        userText = new JTextField(20);
        userText.setBounds(50, 185, 175, 25);
        panel.add(userText);

        userlabel = new JLabel("Password");
        userlabel.setBounds(50, 240, 200, 25);
        userlabel.setForeground(Color.white);
        panel.add(userlabel);

        JPasswordField passText = new JPasswordField(20);
        passText.setBounds(50, 275, 175, 25);
        panel.add(passText);

        button = new JButton("Deposit");
        button.setBounds(50, 350, 175, 25);
        button.setBackground(bg);
        button.setForeground(txt);
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
               button.setBackground(txt);
               button.setForeground(bg);
               button.setBorder(null);
            }
        
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(bg);
                button.setForeground(txt);
                button.setBorder(BorderFactory.createLineBorder(Color.gray));
            }
        });
        button.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (acc.password.equals(new String(passText.getPassword()))) {
                            try {
                                acc.deposit(Double.parseDouble(userText.getText()));
                                logBook.logTransaction(new Transaction("",acc, acc,Double.parseDouble(userText.getText()) , Transaction.TransactionType.CREDIT,LocalDateTime.now().toString(),acc.name,acc.name));
                                frame.setVisible(false);
                                JOptionPane.showMessageDialog(frame,
                                        "Successfully deposited \nCurrent balance : " + acc.balance);
                                login_success(acc,logBook);
                            } catch (NumberFormatException e1) {
                                JOptionPane.showMessageDialog(frame,
                                        "Amount cannot be empty!");
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        } else {
                            JOptionPane.showMessageDialog(frame, "Invalid Password !");
                        }
                    }
                });
        panel.add(button);

        frame.setVisible(true);
    }

    // WITHDRAW

    public static void withdraw(Account acc, LogBook logBook) {
        JPanel panel = new JPanel();
        JFrame frame = new JFrame();
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        panel.setLayout(null);
        panel.setBackground(bg);
        frame.setLocation(500, 200);
        back = new JButton("←");
        back.setBounds(10, 10, 45, 20);
        back.setFont(new Font("SF Pro", 0, 30));
        back.setForeground(Color.white);
        back.setBorder(null);
        back.setFocusPainted(false);
        back.setBackground(bg);
        back.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        frame.setVisible(false);
                        login_success(acc,logBook);
                    }
                });
        panel.add(back);

        JButton dash = new JButton("Dashboard");
        dash.setBounds(360, 100, 100, 25);
        dash.setBorder(null);
        dash.setBackground(bg);
        dash.setForeground(Color.gray);
        dash.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        frame.setVisible(false);
                        login_success(acc,logBook);
                    }
                });
        panel.add(dash);

        JButton dep = new JButton("Deposit");
        dep.setBounds(360, 140, 100, 25);
        dep.setBorder(null);
        dep.setBackground(bg);
        dep.setForeground(Color.gray);
        dep.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        frame.setVisible(false);
                        deposit(acc,logBook);
                    }
                });
        panel.add(dep);

        JButton with = new JButton("Withdraw");
        with.setBounds(360, 180, 100, 25);
        with.setBackground(bg);
        with.setBorder(null);
        with.setForeground(Color.white);
        with.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        frame.setVisible(false);
                        withdraw(acc,logBook);
                    }
                });
        panel.add(with);

        JButton transfer = new JButton("Transfer");
        transfer.setBounds(360, 220, 100, 25);
        transfer.setBackground(bg);
        transfer.setBorder(null);
        transfer.setForeground(Color.gray);
        transfer.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        frame.setVisible(false);
                        transfer(acc,logBook);
                    }
                });
        panel.add(transfer);

        JButton history = new JButton("Activity");
        history.setBounds(360, 260, 100, 25);
        history.setBorder(null);
        history.setBackground(bg);
        history.setForeground(Color.gray);
        history.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        frame.setVisible(false);
                        activity(acc,logBook);
                    }
                });
        panel.add(history);

        JButton bye = new JButton("EXIT");
        bye.setBorder(null);
        bye.setBounds(360, 400, 105, 25);
        bye.setForeground(byec);
        bye.setBackground(bg);
        bye.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        frame.setVisible(false);
                        JOptionPane.showMessageDialog(frame, "Thank you for using our services");
                    }
                });
        panel.add(bye);

        JLabel s = new JLabel("Withdraw");
        s.setBounds(30, 80, 500, 30);
        s.setFont(new Font("SF Pro", Font.BOLD, 30));
        s.setForeground(txt);
        panel.add(s);
        userlabel = new JLabel("Enter amount to be withdrawn");
        userlabel.setBounds(50, 150, 200, 25);
        userlabel.setForeground(Color.white);
        panel.add(userlabel);

        userText = new JTextField(20);
        userText.setBounds(50, 185, 175, 25);
        panel.add(userText);

        userlabel = new JLabel("Password");
        userlabel.setBounds(50, 240, 200, 25);
        userlabel.setForeground(Color.white);
        panel.add(userlabel);

        JPasswordField passText = new JPasswordField(20);
        passText.setBounds(50, 275, 175, 25);
        panel.add(passText);

        button = new JButton("Withdraw");
        button.setBounds(50, 350, 175, 25);
        button.setBackground(bg);
        button.setForeground(txt);
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
               button.setBackground(txt);
               button.setForeground(bg);
               button.setBorder(null);
            }
        
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(bg);
                button.setForeground(txt);
                button.setBorder(BorderFactory.createLineBorder(Color.gray));
            }
        });
        button.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (acc.password.equals(new String(passText.getPassword()))) {
                            try {

                                if (Double.parseDouble(userText.getText()) < acc.balance) {
                                    acc.withdraw(Double.parseDouble(userText.getText()));
                                    logBook.logTransaction(new Transaction("",acc, acc,Double.parseDouble(userText.getText()) , Transaction.TransactionType.DEBIT,LocalDateTime.now().toString(),acc.name,acc.name));
                                    frame.setVisible(false);
                                    JOptionPane.showMessageDialog(frame,
                                            "Successfully withdrawn \nCurrent balance : " + acc.balance);
                                    login_success(acc,logBook);
                                }

                else
                                    JOptionPane.showMessageDialog(frame, "Insufficient balance !");
                            } catch (NumberFormatException e2) {
                                JOptionPane.showMessageDialog(frame,
                                        "Amount cannot be empty!");
                            } catch (Exception e1) {
                                System.out.println(e1);
                            }
                        } else {
                            JOptionPane.showMessageDialog(frame, "Invalid Password !");
                        }
                    }
                });
        panel.add(button);

        frame.setVisible(true);
    }

    // TRANSFER

    public static void transfer(Account acc ,LogBook logBook) {
        JPanel panel = new JPanel();
        JFrame frame = new JFrame();
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        panel.setLayout(null);
        panel.setBackground(bg);
        frame.setLocation(500, 200);
        back = new JButton("←");
        back.setBounds(10, 10, 45, 20);
        back.setFont(new Font("SF Pro", 0, 30));
        back.setForeground(Color.white);
        back.setBorder(null);
        back.setFocusPainted(false);
        back.setBackground(bg);
        back.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        frame.setVisible(false);
                        login_success(acc,logBook);
                    }
                });
        panel.add(back);

        JButton dash = new JButton("Dashboard");
        dash.setBounds(360, 100, 100, 25);
        dash.setBorder(null);
        dash.setBackground(bg);
        dash.setForeground(Color.gray);
        dash.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        frame.setVisible(false);
                        login_success(acc,logBook);
                    }
                });
        panel.add(dash);

        JButton dep = new JButton("Deposit");
        dep.setBounds(360, 140, 100, 25);
        dep.setBorder(null);
        dep.setBackground(bg);
        dep.setForeground(Color.gray);
        dep.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        frame.setVisible(false);
                        deposit(acc,logBook);
                    }
                });
        panel.add(dep);

        JButton with = new JButton("Withdraw");
        with.setBounds(360, 180, 100, 25);
        with.setBackground(bg);
        with.setBorder(null);
        with.setForeground(Color.gray);
        with.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        frame.setVisible(false);
                        withdraw(acc,logBook);
                    }
                });
        panel.add(with);

        JButton transfer = new JButton("Transfer");
        transfer.setBounds(360, 220, 100, 25);
        transfer.setBackground(bg);
        transfer.setBorder(null);
        transfer.setForeground(Color.white);
        transfer.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        frame.setVisible(false);
                        transfer(acc,logBook);
                    }
                });
        panel.add(transfer);

        JButton history = new JButton("Activity");
        history.setBounds(360, 260, 100, 25);
        history.setBorder(null);
        history.setBackground(bg);
        history.setForeground(Color.gray);
        history.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        frame.setVisible(false);
                        activity(acc,logBook);
                    }
                });
        panel.add(history);

        JButton bye = new JButton("EXIT");
        bye.setBorder(null);
        bye.setBounds(360, 400, 105, 25);
        bye.setForeground(byec);
        bye.setBackground(bg);
        bye.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        frame.setVisible(false);
                        JOptionPane.showMessageDialog(frame, "Thank you for using our services");
                    }
                });
        panel.add(bye);

        JLabel s = new JLabel("Transfer");
        s.setBounds(30, 80, 500, 30);
        s.setFont(new Font("SF Pro", Font.BOLD, 30));
        s.setForeground(txt);
        panel.add(s);

        userlabel = new JLabel("Enter account no. to transfer to");
        userlabel.setBounds(50, 150, 200, 25);
        userlabel.setForeground(Color.white);
        panel.add(userlabel);

        userText = new JTextField(20);
        userText.setBounds(50, 180, 175, 25);
        panel.add(userText);

        JLabel amtlabel = new JLabel("Enter amount");
        amtlabel.setBounds(50, 210, 200, 25);
        amtlabel.setForeground(Color.white);
        panel.add(amtlabel);

        JTextField amtText = new JTextField(20);
        amtText.setBounds(50, 240, 175, 25);
        panel.add(amtText);

        userlabel = new JLabel("Password");
        userlabel.setBounds(50, 270, 200, 25);
        userlabel.setForeground(Color.white);
        panel.add(userlabel);

        JPasswordField passText = new JPasswordField(20);
        passText.setBounds(50, 300, 175, 25);
        panel.add(passText);

        button = new JButton("Transfer");
        button.setBounds(50, 370, 175, 25);
        button.setBackground(bg);
        button.setForeground(txt);
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
               button.setBackground(txt);
               button.setForeground(bg);
               button.setBorder(null);
            }
        
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(bg);
                button.setForeground(txt);
                button.setBorder(BorderFactory.createLineBorder(Color.gray));
            }
        });
        button.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (acc.password.equals(new String(passText.getPassword()))) {
                            try {
                                if (Double.parseDouble(amtText.getText()) < acc.balance) {
                                    int flag = 0;
                                    for (Account accTo : parser.parse()) {
                                        if (accTo.accNo.equals(userText.getText())) {
                                            acc.transfer(accTo, Double.parseDouble(amtText.getText()));
                                           
                                            
                                           logBook.logTransaction(new Transaction("",acc, accTo, Double.parseDouble(amtText.getText()), Transaction.TransactionType.TRANSFER,LocalDateTime.now().toString(),acc.name,accTo.name));
                                          // logBook.logTransaction(new Transaction(accTo, acc,  Double.parseDouble(amtText.getText()), Transaction.TransactionType.CREDIT));
                                         
                                            flag = 1;
                                            break;
                                        }
                                    }
                                    if (flag == 1) {
                                        frame.setVisible(false);
                                        JOptionPane.showMessageDialog(frame,
                                                "Successfully transferred \nCurrent balance : " + acc.balance);
                                        login_success(acc,logBook);
                                    } else
                                        JOptionPane.showMessageDialog(frame, "Invalid account number !");

                                }

                else
                                    JOptionPane.showMessageDialog(frame, "Insufficient balance !");
                            } catch (NumberFormatException e2) {
                                JOptionPane.showMessageDialog(frame,
                                        "Amount cannot be empty!");
                            } catch (Exception e1) {
                                System.out.println(e1);
                            }

                        } else {
                            JOptionPane.showMessageDialog(frame, "Invalid Password !");
                        }
                    }
                });
        panel.add(button);

        frame.setVisible(true);
    }

    public static void activity(Account acc,  LogBook logBook) {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(bg);

        JFrame frame = new JFrame();
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setLocation(500, 200);

        back = new JButton("←");
        back.setBounds(10, 10, 45, 20);
        back.setFont(new Font("SF Pro", 0, 30));
        back.setForeground(Color.white);
        back.setBorder(null);
        back.setFocusPainted(false);
        back.setBackground(bg);
        back.addActionListener(e -> {
            frame.setVisible(false);
            login_success(acc,logBook);
        });
        panel.add(back);

        // Dashboard and other navigation buttons
        setupNavigationButtons(panel, acc, frame,logBook);

        JLabel titleLabel = new JLabel("Activity");
        titleLabel.setBounds(30, 60, 500, 50);
        titleLabel.setFont(new Font("SF Pro", Font.BOLD, 35));
        titleLabel.setForeground(Color.white);
        panel.add(titleLabel);

        JLabel subtitleLabel = new JLabel("Transaction history");
        subtitleLabel.setBounds(30, 100, 500, 30);
        subtitleLabel.setFont(new Font("SF Pro", Font.PLAIN, 13));
        subtitleLabel.setForeground(Color.gray);
        panel.add(subtitleLabel);

        // Scrollable area for transaction logs
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(bg);

        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setBounds(30, 140, 320, 300);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(null);
        panel.add(scrollPane);

         try {
            List<Transaction> transactions = logBook.parse();

            for (Transaction txn : transactions) {
                // Only display transactions relevant to the specified account
                if (txn.from.accNo.equals(acc.accNo) || txn.to.accNo.equals(acc.accNo)) {
                    JPanel txnPanel = new JPanel();
                    txnPanel.setLayout(null);  // Use absolute positioning for exact layout
                    txnPanel.setPreferredSize(new Dimension(300, 110)); // Fixed height for each entry
                    txnPanel.setBackground(bg);
                    txnPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.gray));

                    String displayName="";
                    String printType="";
                    // Account holder's name or ID
                   if (txn.from.accNo.equals(acc.accNo) && txn.to.accNo.equals(acc.accNo))
                    {try{
                        displayName =(Character.toUpperCase(acc.name.charAt(0)) + acc.name.substring(1));
                        printType = "self";
                    }catch(Exception ee){System.out.println("1st error");}
                   }
                
                   else {
                    // Transfer transaction
                    if (txn.from.accNo.equals(acc.accNo)) {
                        // Display the name of `txn.to` account holder
                        try{
                        displayName = Character.toUpperCase(txn.to.name.charAt(0)) + txn.to.name.substring(1);
                        printType="from";
                        }catch(Exception ee){System.out.println("2nd error");}
                    } else {
                        // Display the name of `txn.from` account holder
                        try{
                        displayName = Character.toUpperCase(txn.from.name.charAt(0)) + txn.from.name.substring(1);
                        printType="to";
                        }catch(Exception ee){System.out.println("3rd error");}
                    }}
                  
                    
                    JLabel nameLabel = new JLabel(displayName);
                    nameLabel.setBounds(10, 40, 140, 30);
                    nameLabel.setFont(new Font("SF Pro", Font.BOLD, 25));
                    nameLabel.setForeground(txt);
                    txnPanel.add(nameLabel);
                    
                    DateTimeFormatter displayFormatter = DateTimeFormatter.ofPattern("MMM dd, yyyy 'at' HH:mm");
                    String formattedDate = txn.createdAt.format(displayFormatter);
                    // Date and time
                    JLabel dateLabel = new JLabel(formattedDate);
                    dateLabel.setBounds(10, 70, 200, 20);
                    dateLabel.setFont(new Font("SF Pro", Font.PLAIN, 10));
                    dateLabel.setForeground(Color.gray);
                    txnPanel.add(dateLabel);

                    // Type of transaction (colored based on type)
                    JLabel typeLabel = new JLabel("Type: ");
                    typeLabel.setBounds(175, 10, 50, 20);
                    typeLabel.setFont(new Font("SF Pro", Font.PLAIN, 12));
                    typeLabel.setForeground(Color.gray);
                    txnPanel.add(typeLabel);

                    JLabel txnTypeLabel = new JLabel((printType.equals("self"))?txn.type.toString():(printType.equals("from")?"DEBIT":"CREDIT"));
                    txnTypeLabel.setBounds(205, 10, 100, 20);
                    txnTypeLabel.setFont(new Font("SF Pro", Font.BOLD, 12));
                    txnTypeLabel.setForeground(txn.type == Transaction.TransactionType.CREDIT ? txt :txn.type==Transaction.TransactionType.DEBIT? byec:(printType.equals("from")?byec:txt));
                    txnPanel.add(txnTypeLabel);

                    // Transaction ID (or masked account number)
                    JLabel idLabel = new JLabel(txn.from.accNo.equals(acc.accNo) ? txn.transactionId : txn.transactionId);
                    idLabel.setBounds(170, 70, 200, 20);
                    idLabel.setFont(new Font("SF Pro", Font.PLAIN, 10));
                    idLabel.setForeground(Color.gray);
                    txnPanel.add(idLabel);

                    // Amount (aligned to the right)
                    JLabel amountLabel = new JLabel("₹ " + String.format("%.2f", txn.amount));
                    amountLabel.setBounds(50, 40, 200, 30);
                    amountLabel.setFont(new Font("SF Pro", Font.BOLD, 25));
                    amountLabel.setForeground(Color.white);
                    amountLabel.setHorizontalAlignment(SwingConstants.RIGHT);
                    txnPanel.add(amountLabel);

                    mainPanel.add(txnPanel);
                }
            }

        } catch (Exception e) {
            JLabel errorLabel = new JLabel("Failed to load transaction history.");
            errorLabel.setForeground(Color.red);
            errorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            mainPanel.add(errorLabel);
        }
      
        frame.setVisible(true);
    }

    // Helper method to set up navigation buttons
    private static void setupNavigationButtons(JPanel panel, Account acc, JFrame frame,LogBook logBook) {
        JButton[] buttons = {
                createNavButton("Dashboard", 100, e -> {
                    frame.setVisible(false);
                    login_success(acc,logBook);
                }),
                createNavButton("Deposit", 140, e -> {
                    frame.setVisible(false);
                    deposit(acc,logBook);
                }),
                createNavButton("Withdraw", 180, e -> {
                    frame.setVisible(false);
                    withdraw(acc,logBook);
                }),
                createNavButton("Transfer", 220, e -> {
                    frame.setVisible(false);
                    transfer(acc,logBook);
                }),
                createNavButton("Activity", 260, null),
                createNavButton("EXIT", 400, e -> {
                    frame.setVisible(false);
                    JOptionPane.showMessageDialog(frame, "Thank you for using our services");
                })
        };

        for (JButton button : buttons) {
            panel.add(button);
        }
    }

    private static JButton createNavButton(String text, int yPosition, ActionListener action) {
        JButton button = new JButton(text);
        button.setBounds(360, yPosition, 100, 25);
        button.setBorder(null);
        button.setBackground(bg);
        button.setForeground(text.equals("Activity") ? Color.white : (text.equals("EXIT")) ? byec : Color.gray);
        if (action != null)
            button.addActionListener(action);
        return button;
    }

}
