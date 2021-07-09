/**
 * Created by Mehmed Mustafa on 22-Nov-17.
 */

import java.util.List;

public class DCT extends Transformer {

    public void transformMethod(List<ComplexNumber> input, List<ComplexNumber> output){
        // Current double number, Real numbers are Complex numbers with imaginary part 0
        Double currentNum;
        double rSum, angle;

        int N = input.size();

        // Create the DCT transform
        for(int k = 0; k < N; ++k){
            rSum = 0.0;

            // Iterate over every input number and
            // calculate the transform of the current output number
            for(int n = 0; n < N; ++n){
                currentNum = input.get(n).getReal();
                angle = (Math.PI * (n + 0.5) * k) / N;

                rSum += currentNum * Math.cos(angle);
            }

            // Create and add the transformed number to the output list
            output.add(new ComplexNumber(rSum, 0.0));
        }

    }

}