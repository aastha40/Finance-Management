package com.example.financemanagement.util;

public class FinanceCalculator {

    /**
     * SIP Calculation: M = P × ({[1 + i]^n – 1} / i) × (1 + i)
     * @param monthlyInvestment P
     * @param annualRate r (percentage)
     * @param years n_years
     * @return Future Value
     */
    public static double calculateSIP(double monthlyInvestment, double annualRate, int years) {
        double i = (annualRate / 100) / 12;
        int n = years * 12;
        return monthlyInvestment * ((Math.pow(1 + i, n) - 1) / i) * (1 + i);
    }

    /**
     * SWP Calculation: Calculates the remaining balance or required corpus.
     * Often used to see how long a corpus lasts.
     */
    public static double calculateSWP(double initialCorpus, double monthlyWithdrawal, double annualRate, int years) {
        double i = (annualRate / 100) / 12;
        int n = years * 12;
        double futureValue = initialCorpus * Math.pow(1 + i, n) - monthlyWithdrawal * ((Math.pow(1 + i, n) - 1) / i);
        return Math.max(0, futureValue);
    }

    /**
     * EMI Calculation: E = P × r × (1 + r)^n / ((1 + r)^n – 1)
     */
    public static double calculateEMI(double principal, double annualRate, int years) {
        double r = (annualRate / 100) / 12;
        int n = years * 12;
        return (principal * r * Math.pow(1 + r, n)) / (Math.pow(1 + r, n) - 1);
    }

    /**
     * FD Calculation: A = P(1 + r/n)^(nt)
     * For FD in India, n is usually 4 (quarterly compounding).
     */
    public static double calculateFD(double principal, double annualRate, double years) {
        double r = annualRate / 100;
        int n = 4; // Quarterly compounding
        return principal * Math.pow(1 + r / n, n * years);
    }

    /**
     * RD Calculation: M = R[(1+i)^n - 1] / (1-(1+i)^(-1/3))
     * Approximate formula for quarterly compounding in India.
     */
    public static double calculateRD(double monthlyDeposit, double annualRate, int quarters) {
        double r = annualRate / 100;
        double n = quarters; // total quarters
        double i = r / 4;
        return monthlyDeposit * 3 * ((Math.pow(1 + i, n) - 1) / (1 - Math.pow(1 + i, -1.0/3)));
    }
}
