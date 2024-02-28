package org.example.util;

import org.example.domain.LoanPlanCalcInfo;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EqualityPrinAndIntUtil {

    public static List<LoanPlanCalcInfo> calcEqualityCorpusAndInterest(BigDecimal loanAmt, BigDecimal yearRate, Integer term, Date dateStart) {

        List<LoanPlanCalcInfo> planList = new ArrayList<>();
        //日利率
        BigDecimal dayIntFee = calculateDailyInterest(yearRate);
        //月利率
        BigDecimal monthRate = dayIntFee.multiply(new BigDecimal(30)).setScale(6, RoundingMode.HALF_UP);
        //每期总额
        BigDecimal termTotalAmt = calculateTermTotalAmt(loanAmt, monthRate, term);
        BigDecimal loanBalAmt = loanAmt;
        //下期还款日
        Date next = getNext(dateStart);
        Date prev = dateStart;
        for (int i = 0; i < term; i++) {
            //每月利息
            BigDecimal intAmt = calculateMonthlyInterest(loanBalAmt, dayIntFee, prev, next);
            //每月本金
            BigDecimal termPrint = (i == term - 1) ? loanBalAmt : termTotalAmt.subtract(intAmt);

            planList.add(new LoanPlanCalcInfo(i + 1, termPrint.add(intAmt), termPrint, intAmt));
            loanBalAmt = loanBalAmt.subtract(termPrint).setScale(2, RoundingMode.HALF_UP);

            prev = next;
            next = DateUtil.addMonths(next, 1);
        }
        return planList;
    }

    private static BigDecimal calculateDailyInterest(BigDecimal yearRate) {
        return yearRate.divide(new BigDecimal(365), 6, RoundingMode.HALF_UP);
    }

    private static BigDecimal calculateTermTotalAmt(BigDecimal loanAmt, BigDecimal monthRate, Integer term) {
        return loanAmt.multiply(monthRate).multiply((BigDecimal.ONE.add(monthRate)).pow(term))
                .divide((BigDecimal.ONE.add(monthRate)).pow(term).subtract(BigDecimal.ONE), 2, RoundingMode.HALF_UP);
    }

    private static BigDecimal calculateMonthlyInterest(BigDecimal loanBalAmt, BigDecimal dayIntFee, Date prev, Date next) {
        return loanBalAmt.multiply(dayIntFee.multiply(new BigDecimal(DateUtil.getIntervalDays(next, prev)))).setScale(2, RoundingMode.HALF_UP);
    }

    private static Date getNext(Date dateStart) {
        int day = DateUtil.getDay(dateStart);
        if (day > 28) {
            day = 28;
        }
        return DateUtil.addMonths(DateUtil.getDateOfCurrcentMonth(dateStart, day), 1);
    }
}
