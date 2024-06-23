import java.util.*;

abstract class bankaccount {
    double accountno;
    String accountholdername;

    bankaccount(double accountno, String accountholdername) {
        this.accountno = accountno;
        this.accountholdername = accountholdername;
    }

    protected double balance;

    void setbalance(double a) {
        balance = a;
    }

    double getbalance() {
        return balance;
    }

    abstract void deposit(double amount);

    abstract void withdraw(double amount);
}

class savingaccount extends bankaccount {
    double interestrate = 0.05;

    // private double balance;
    savingaccount(double accountno, String accountholdername) {
        super(accountno, accountholdername);
    }

    protected void setbalance(double a) {
        balance = a;
    }

    void withdraw(double amount) {
        if (balance > 5000) {
            balance -= amount;
            System.out.println("your amount" + amount + "is withdrwan");
            System.out.println("YOU HAVE" + balance + "REMAONING");
        } else
            System.out.println("NOT ENOUGH BALANCE");

    }

    void deposit(double amount) {
        balance += amount;
        System.out.println("your new balance is " + balance);
    }

    double getBalance() {
        return balance;
    }

}

class checkingaccount extends bankaccount {
    double overdraftlimit = 5000;
    private double balance;

    checkingaccount(double accountno, String accountholdername) {
        super(accountno, accountholdername);

    }

    protected void setbalance(double a) {
        balance = a;
    }

    void withdraw(double amount) {
        if (balance + overdraftlimit >= amount) {
            balance -= amount;
            System.out.println("your amount " + amount + " is withdrwan");
            System.out.println("YOU HAVE" + balance + "REMAiNING");
        } else
            System.out.println("NOT ENOUGH BALANCE");

    }

    void deposit(double amount) {
        balance += amount;
        System.out.println("your new balance is " + balance);
    }

    double getbalance() {
        return balance;
    }
}

interface atmtransaction {
    void withdraw(double accountno, double balance);

    void deposit(double accountno, double balance);

    void checkbalance(double accountno);
}

class atm implements atmtransaction {
    ArrayList<bankaccount> BA = new ArrayList<>();

    Random random = new Random();
    int rand = random.nextInt(9000) + 1000;

    int flag = 0;

    void addAccount(bankaccount b) {

        BA.add(b);

    }

    public void withdraw(double accountno, double amount) {
        for (bankaccount account : BA) {
            if (account.accountno == accountno) {
                account.withdraw(amount);
                flag = 1;
                break;
            }
        }
        if (flag == 0) {
            System.out.println("Account not found.");
        }
    }

    public void deposit(double accountno, double amount) {
        for (bankaccount account : BA) {

            if (account.accountno == accountno) {
                account.deposit(amount);
                flag = 1;
                break;
            }

        }

        if (flag == 0) {
            System.out.println("Account not found.");

        }
    }

    public void checkbalance(double accountno) {
        for (bankaccount account : BA) {
            if (account.accountno == accountno) {
                System.out.println(account.getbalance());
                flag = 1;
                break;
            }
        }
        if (flag == 0) {
            System.out.println("Account not found.");
        }
    }

    int flagfa = 0;

    void findaccount(double accountno) {

        for (bankaccount bankaccount : BA) {
            if (bankaccount.accountno == accountno) {
                System.out.println("WELCOME BACK..");
                flag = 1;
                flagfa = 1;
                break;
            }
        }
        if (flag == 0) {
            System.out.println("INVALID CREDENTIALS");
        }

    }

    void tt() {
        int i;
        Scanner in = new Scanner(System.in);
        System.out.println("YOU WANT TO\nPRESS 1 FOR-CREATE ACCOUNT\nPRESS 2 FOR-MANAGE ACCOUNT");
        int a = in.nextInt();
        if (a == 1) {
            System.out.println("PRESS 1 FOR SAVING ACCOUNT\nPRESS 2 FOR CHECKING ACCOUNT");
            int b = in.nextInt();
            if (b == 1) {
                System.out.println("\nENTER YOUR NAME ");
                in.nextLine();
                String nm = in.nextLine();
                addAccount(new savingaccount(rand, nm));
                System.out.println("YOUR ACCOUNT IS CREATED\n");
                System.out.println("YOUR NAME IS " + nm);
                System.out.println("your account no is " + rand);
                System.out.println();
            } else if (b == 2) {
                System.out.println("\nENTER YOUR NAME ");
                in.nextLine();
                String nm = in.nextLine();
                addAccount(new checkingaccount(rand, nm));
                System.out.println("YOUR ACCOUNT IS CREATED\n");
                System.out.println("YOUR NAME IS " + nm);
                System.out.println("your account no is " + rand);
                System.out.println();
            } else {
                System.out.println("invalid input");
            }
        } else if (a == 2) {
            System.out.println("CHOOSE THE FOLLOWWING SERVICES:\n");
            System.out.println("PRESS 1 FOR DEPOSIT");
            System.out.println("PRESS 2 FOR WITHDRAW");
            System.out.println("PRESS 3 FOR CHECKBALANCE");
            int c = in.nextInt();
            if (c == 1) {
                int yf = 3;
                do {
                    System.out.println("ENTER YOOUR ACCOUNT NUMBER\n");
                    double acc = in.nextDouble();
                    findaccount(acc);
                    if (flagfa == 1) {
                        System.out.println("ENTER THE AMOUNT TO DEPOSIT");
                        double dp = in.nextDouble();
                        deposit(acc, dp);
                        break;
                    } else {
                        System.out.println("WRONG ACCOUNT NUMBER PLEASE TRY AGAIN ");
                        yf--;
                        System.out.println("YOU HAVE " + yf + " chances remainning");
                    }
                } while (yf > 0);
            }

            else if (c == 2) {
                int yf = 3;
                do {
                    System.out.println("ENTER YOOUR ACCOUNT NUMBER\n");
                    double acc = in.nextDouble();
                    findaccount(acc);
                    if (flagfa == 1) {
                        System.out.println("ENTER THE AMOUNT TO WITHDRAW");
                        double dp = in.nextDouble();
                        withdraw(acc, dp);
                        break;
                    } else {
                        System.out.println("WRONG ACCOUNT NUMBER PLEASE TRY AGAIN ");
                        yf--;
                        System.out.println("YOU HAVE " + yf + " chances remainning");
                    }

                } while (yf > 0);
            } else if (c == 3) {
                int u = 3;
                while (u > 0) {
                    System.out.println("ENTER YOOUR ACCOUNT NUMBER");
                    double acc = in.nextDouble();
                    findaccount(acc);
                    if (flagfa == 1) {
                        checkbalance(acc);
                        break;
                    }
                    System.out.println("YOU HAVE " + u + " chances remainning");
                    System.out.println("YOUR ACCOUNT NO IS WRONG\n\n DO YOU WANT TO TRY AGAIN?");
                    System.out.println("PRESS 1 FOR EXIT      OR     PRESS ANY NUMBER TO CONTINUE..");
                    int ag = in.nextInt();
                    if (ag == 1) {
                        break;
                    }
                    u--;

                }
                // System.out.println("PLEASE TRY AGAIN LATER");
            } else
                System.out.println("INVALID INPUT\n");

        } else {
            System.out.println("INVALID INPUT\n");
        }

    }
}

public class ATMmain {
    public static void main(String[] args) {

        atm atm = new atm();
        savingaccount s = new savingaccount(232, "l");
        checkingaccount c = new checkingaccount(233, "fhh");

        atm.addAccount(c);
        atm.addAccount(s);
        //// c.deposit(2000);
        // s.deposit(324);
        Scanner in = new Scanner(System.in);
        int f = 0;

        do {
            try {
                int fd;
                do {

                    atm.tt();
                    System.out.println("PRESS 1 FOR EXITTING THE ATM..\n                      OR");
                    System.out.println("PRESS ANY NUMBER FOR RELOADING THE ATM...");
                    fd = in.nextInt();
                } while (fd != 1);
                break;
            }

            catch (Exception e) {
                System.out.println("\nTHE EXCEPTION " + e + " HAS OCCURED\n");
                System.out.println("                                                 PLEASE DONT USE STRING KEYS\n");
                // f++;
            }
        } while (f < 4);

    }
}