package com.revature.project1.models;

//@Table
//@Entity(name = "account")
public class UserAccount {
    //@Id
    //@Column(name = "account_id")
    private int accountId;

    //@Column(name = "user_id", nullable = false)
    private int userId;

    //@Column(name = "balance_id", nullable = false)
    private double balance;

    //@Column(name = "account_type", nullable = false)
    private String accountType;

    public UserAccount(){
        super();
    }
    public UserAccount(int userId, double balance,String accountType){
        System.out.println("UserAccount invoked!");
        this.accountId = accountId;
        this.userId = userId;
        this.balance = balance;
        this.accountType = accountType;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UserAccount{");
        sb.append(" accountId='").append(accountId).append('\'');
        sb.append(", userId='").append(userId).append('\'');
        sb.append(", balance= $'").append(balance).append('\'');
        sb.append(", accountType='").append(accountType).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
