package com.revature.project0.models;
import java.util.Date;

//@Entity
//@Table(name = "transaction")
public class UserTransactionHistory {
    //@Id
    //@Column(name = "user_id")
    private int userId;
    //@Column(name = "transaction_id", nullable = false)
    private int transactionId;
    //@Column(name = "transactionType", nullable = false)
    private String transactionType;
    //@Column(name = "transactionAmount", nullable = false)
    private double transactionAmount;
    //@Column(name = "transactionDate", nullable = false)
    private String transactionDate;
    //@Column(name = "accountType, nullable = false)
    private String accountType;
    //@Column(name = "accountId")
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


