package com.revature.project1.models;

//@Table
//@Entity(name = "transaction")
public class UserTransactionHistory {
    //@Id
    //@Column(name = "user_id")
    private int userId;

    //@Column(name = "transaction_id", nullable = false)
    private int transactionId;

    //@Column(name = "transaction_type", nullable = false)
    private String transactionType;

    //@Column(name = "transaction_amount", nullable = false)
    private double transactionAmount;

    //@Column(name = "transaction_date", nullable = false)
    private String transactionDate;

    //@Column(name = "account_type, nullable = false)
    private String accountType;

    //@Column(name = "account_id,, nullable = false")
    private int accountId;

    public UserTransactionHistory() {
        super();
    }

    public UserTransactionHistory(int userId, String transactionType, double transactionAmount, String transactionDate, String accountType) {
        this.userId = userId;
        this.transactionId = transactionId;
        this.transactionType = transactionType;
        this.transactionAmount = transactionAmount;
        this.transactionDate = transactionDate;
        this.accountType = accountType;
        this.accountId = accountId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public Double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(Double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UserTransactionHistory{");
        sb.append("userId='").append(userId).append('\'');
        sb.append(", transactionID='").append(transactionId).append('\'');
        sb.append(", transactionType='").append(transactionType).append('\'');
        sb.append(", transactionAmount= $'").append(transactionAmount).append('\'');
        sb.append(", transactionDate='").append(transactionDate).append('\'');
        sb.append(", accountType=").append(accountType);
        sb.append(", accountId=").append(accountId);
        sb.append('}');
        return sb.toString();
    }
}


