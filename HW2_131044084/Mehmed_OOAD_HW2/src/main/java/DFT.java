/**
 * Created by Mehmed Mustafa on 22-Nov-17.
 */

import java.util.List;

public class DFT extends Transformer {

    public void transformMethod(List<ComplexNumber> input, List<ComplexNumber> output){
        // Current complex number
        ComplexNumber c;
        // rSum for real part sumation
        // iSum for imaginary part sumation
        double rSum, iSum, angle;

        int N = input.size();

        // Create the DFT transform
        for(int k = 0; k < N; ++k){
            rSum = 0.0f;
            iSum = 0.0f;

            // Iterate over every input number and
            // calculate the transform of the current output number
            for(int n = 0; n < N; ++n){
                c = input.get(n);
                angle = (2.0 * Math.PI * k * n) / N;

                rSum += c.getReal() * Math.cos(angle) + c.getImaginary() * Math.sin(angle);
                iSum += -c.getReal() * Math.sin(angle) + c.getImaginary() * Math.cos(angle);
            }

            // Create and add the transformed number to the output list
            output.add(new ComplexNumber(rSum, iSum));
        }
    }
}
