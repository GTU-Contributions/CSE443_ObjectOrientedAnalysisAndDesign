/**
 * Created by Mehmed Mustafa on 22-Nov-17.
 */

import java.util.List;
import java.util.ArrayList;

public abstract class Transformer {
    private List<ComplexNumber> inputNumbers = new ArrayList<>();
    private List<ComplexNumber> outputNumbers = new ArrayList<>();

    final void transform(String inputFile, String outputFile){
        readFromFile(inputFile);
        transformMethod(inputNumbers, outputNumbers);
        writeToFile(outputFile);
        hook();
    }

    abstract void transformMethod(List<ComplexNumber> input, List<ComplexNumber> output);

    void readFromFile(String fileName){
        System.out.println("Reading N numbers from a file " + fileName);
    }

    void writeToFile(String fileName){
        System.out.println("Writing the transformed N numbers to a file " + fileName);
    }

    void hook(){}
}
