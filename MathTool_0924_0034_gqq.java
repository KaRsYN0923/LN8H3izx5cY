// 代码生成时间: 2025-09-24 00:34:41
 * @author [Your Name]
 * @version 1.0
 */
public class MathTool {

    /**
     * Adds two numbers.
     * 
     * @param a The first number.
     * @param b The second number.
     * @return The sum of the two numbers.
     */
    public double add(double a, double b) {
        return a + b;
    }

    /**
     * Subtracts two numbers.
     * 
     * @param a The first number.
     * @param b The second number to be subtracted from the first.
     * @return The result of the subtraction.
     */
    public double subtract(double a, double b) {
        return a - b;
    }

    /**
     * Multiplies two numbers.
     * 
     * @param a The first number.
     * @param b The second number.
     * @return The product of the two numbers.
     */
    public double multiply(double a, double b) {
        return a * b;
    }

    /**
     * Divides two numbers.
     * 
     * @param a The dividend.
     * @param b The divisor.
     * @return The quotient of the two numbers.
     * @throws ArithmeticException If the divisor is zero.
     */
    public double divide(double a, double b) {
        if (b == 0) {
            throw new ArithmeticException("Divisor cannot be zero.");
        }
        return a / b;
    }

    /**
     * Calculates the square root of a number.
     * 
     * @param value The number to calculate the square root for.
     * @return The square root of the number.
     * @throws IllegalArgumentException If the number is negative.
     */
    public double squareRoot(double value) {
        if (value < 0) {
            throw new IllegalArgumentException("Cannot calculate square root of a negative number.");
        }
        return Math.sqrt(value);
    }

    // Additional mathematical operations can be added here...
}