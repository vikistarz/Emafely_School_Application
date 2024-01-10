package com.example.emafelyapp.utility;

public class PaymentModel {

    private boolean checkbox;
    private String fees;
    private String amount;

    public PaymentModel(boolean checkbox, String fees, String amount) {
        this.checkbox = checkbox;
        this.fees = fees;
        this.amount = amount;
    }

    public boolean isCheckbox() {
        return checkbox;
    }

    public void setCheckbox(boolean checkbox) {
        this.checkbox = checkbox;
    }

    public String getFees() {
        return fees;
    }

    public void setFees(String fees) {
        this.fees = fees;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}