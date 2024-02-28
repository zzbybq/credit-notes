package org.example.domain;

import java.math.BigDecimal;

public class LoanPlanCalcInfo {

    /**
     * 期数
     */
    private Integer term;

    /**
     * 月供金额
     */
    private BigDecimal monthPayment;

    /**
     * 月供本金
     */
    private BigDecimal prinAmt;

    /**
     * 月供利息
     */
    private BigDecimal intAmt;

    public LoanPlanCalcInfo() {
    }

    public LoanPlanCalcInfo(Integer term, BigDecimal monthPayment, BigDecimal prinAmt, BigDecimal intAmt) {
        this.term = term;
        this.monthPayment = monthPayment;
        this.prinAmt = prinAmt;
        this.intAmt = intAmt;
    }

    public Integer getTerm() {
        return term;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }

    public BigDecimal getMonthPayment() {
        return monthPayment;
    }

    public void setMonthPayment(BigDecimal monthPayment) {
        this.monthPayment = monthPayment;
    }

    public BigDecimal getPrinAmt() {
        return prinAmt;
    }

    public void setPrinAmt(BigDecimal prinAmt) {
        this.prinAmt = prinAmt;
    }

    public BigDecimal getIntAmt() {
        return intAmt;
    }

    public void setIntAmt(BigDecimal intAmt) {
        this.intAmt = intAmt;
    }
}
