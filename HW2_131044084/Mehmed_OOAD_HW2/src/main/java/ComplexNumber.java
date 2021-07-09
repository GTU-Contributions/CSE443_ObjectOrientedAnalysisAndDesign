/**
 * Created by Mehmed Mustafa on 22-Nov-17.
 */
public class ComplexNumber {
    private double real, imaginary;

    public ComplexNumber(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    public double getReal(){
        return real;
    }

    public double getImaginary(){
        return imaginary;
    }

    public String toString(){
        StringBuilder result = new StringBuilder();
        int compareResult = Double.compare(imaginary, 0.0);

        result.append(String.format("%.1f", real));

        // The imaginary part is positive
        if(compareResult > 0){
            result.append(" + " + String.format("%.1f", imaginary) + "j");
            return result.toString();
        }

        // The imaginary part is negative
        if(compareResult < 0){
            result.append(" - " + String.format("%.1f", -1*imaginary) + "j");
            return result.toString();
        }

        return result.toString();
    }

}
