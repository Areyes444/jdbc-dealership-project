package com.pluralsight.models;

public class Lease extends Contract
{
    private int leaseId;
    private final double expectedEndingValue;
    private final double LEASE_FEE;


    public Lease(int leaseId,String date, String customerName, String customerEmail, Vehicle vehicleSold)
    {
        super(date, customerName, customerEmail, vehicleSold);
        this.leaseId = leaseId;
        this.expectedEndingValue = vehicleSold.getPrice() / 2;
        this.LEASE_FEE = vehicleSold.getPrice() * .07;
    }

    public int getLeaseId()
    {
        return leaseId;
    }

    public void setLeaseId(int leaseId)
    {
        this.leaseId = leaseId;
    }

    public double getExpectedEndingValue()
    {
        return expectedEndingValue;
    }

    public double getLEASE_FEE()
    {
        return LEASE_FEE;
    }

    @Override
    public double getTotalPrice()
    {
        return  getExpectedEndingValue() + getLEASE_FEE();
    }

    @Override
    public double getMonthlyPayment()
    {
        double principal = getTotalPrice();
        double rate = 0.04;
        double time = 3;

        return calculateMonthlyPayment(principal,rate,time);

    }

    public double calculateMonthlyPayment(double principal, double rate, double time)
    {
        double r = rate /(12*100);
        double t = time *12;

        return (principal * r * (double)Math.pow(1 + r,t)) / (double)(Math.pow(1+r,t) -1);

    }


}
