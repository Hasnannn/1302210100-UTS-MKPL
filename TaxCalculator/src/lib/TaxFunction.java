package lib;

public class TaxFunction {
    
    private static final int MONTHS_IN_YEAR = 12;
    private static final int MAX_CHILDREN_FOR_TAX = 3;
    private static final int MARRIED_DEDUCTIBLE = 54000000 + 1500000;
    private static final int SINGLE_DEDUCTIBLE = 54000000;
    private static final int CHILD_DEDUCTION_PER_CHILD = 1500000;
    private static final double TAX_RATE = 0.05;

    public static int calculateTax(int monthlySalary, int otherMonthlyIncome, int numberOfMonthWorking, int deductible, boolean isMarried, int numberOfChildren) {
        validateMonthsWorked(numberOfMonthWorking);
        numberOfChildren = capNumberOfChildren(numberOfChildren);

        int totalIncome = (monthlySalary + otherMonthlyIncome) * numberOfMonthWorking;
        int totalDeductible = calculateDeductible(isMarried, deductible, numberOfChildren);
        int taxableIncome = totalIncome - totalDeductible;

        int tax = (int) Math.round(TAX_RATE * taxableIncome);

        return Math.max(tax, 0);
    }

    private static void validateMonthsWorked(int numberOfMonthWorking) {
        if (numberOfMonthWorking > MONTHS_IN_YEAR) {
            throw new IllegalArgumentException("Number of months worked cannot exceed 12");
        }
    }

    private static int capNumberOfChildren(int numberOfChildren) {
        return Math.min(numberOfChildren, MAX_CHILDREN_FOR_TAX);
    }

    private static int calculateDeductible(boolean isMarried, int deductible, int numberOfChildren) {
        int baseDeductible = isMarried ? MARRIED_DEDUCTIBLE : SINGLE_DEDUCTIBLE;
        int childDeduction = numberOfChildren * CHILD_DEDUCTION_PER_CHILD;
        return baseDeductible + deductible + childDeduction;
    }
}
