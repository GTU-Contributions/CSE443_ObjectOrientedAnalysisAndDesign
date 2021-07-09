/**
 * Created by Mehmed Mustafa on 22-Nov-17.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.function.BooleanSupplier;

public class main {

    public static void main(String [] args){

        /*************************** PART 1 TESTING ***************************/
        // Transformation Testing
        List<ComplexNumber> input = new ArrayList<ComplexNumber>();
        List<ComplexNumber> DFToutput = new ArrayList<ComplexNumber>();
        List<ComplexNumber> DCToutput = new ArrayList<ComplexNumber>();

        // Set the input array
        input.add(new ComplexNumber(1.7, 0.0));
        input.add(new ComplexNumber(3.6, 0.0));
        input.add(new ComplexNumber(5.2, 0.0));
        input.add(new ComplexNumber(9.8, 0.0));

        // DFT Transform
        Transformer DFT_T = new DFT();
        DFT_T.transformMethod(input, DFToutput);

        // DCT Transform
        Transformer DCT_T = new DCT();
        DCT_T.transformMethod(input, DCToutput);


        // Console output
        System.out.println("-------------------- PART 1 Testing --------------------");
        System.out.println("Input for Transformation:");
        for(ComplexNumber iterator : input)
            System.out.println(iterator.toString());
        System.out.println();

        System.out.println("DFT Transform Result:");
        for(ComplexNumber iterator : DFToutput)
            System.out.println(iterator.toString());
        System.out.println();

        System.out.println("DCT II Transform Result:");
        for(ComplexNumber iterator : DCToutput)
            System.out.println(iterator.toString());
        System.out.println();
        System.out.println("--------------------------------------------------------");
        /**********************************************************************/


        /*************************** PART 2 TESTING ***************************/
        TheClass scenario = new TheClass();

        try {
            scenario.runScenario();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        /**********************************************************************/
    }
}
