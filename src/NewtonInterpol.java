import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class NewtonInterpol {

    private String fileName;
    private float z;
    private ArrayList<Float> xs = new ArrayList<Float>();
    private ArrayList<Float> ys = new ArrayList<Float>();
    private ArrayList<Float> cs = new ArrayList<Float>();

    public static void main(String[] args) throws IOException {
        NewtonInterpol newtonInterpol = new NewtonInterpol();
        newtonInterpol.commandHandler(args);
    }

    /*
    Command handler method for taking in command line arguments.
     */
    public void commandHandler(String[] args) throws IOException {
        try{
            if (args[0].contains(".test")){
                fileName = args[0];
                try{
                    z = Float.parseFloat(args[1]);
                } catch (NumberFormatException | NullPointerException e){
                    System.out.println("You did not enter the format of the number you want to calculate for x correctly.");
                }
            } else {
                System.out.println("Your file name needs to be in the format [File Name].test");
            }
        } catch(ArrayIndexOutOfBoundsException e){
            System.out.println("You did not enter the correct format of params for the program to run.");
        }

        File toProcess = new File(fileName);
        processFile(toProcess);
    }
    /*
    Processing the input .pol file and then sending found vectors into correct methods for finding root.
     */
    public void processFile(File toProcess) throws IOException {


        FileReader fileReader = new FileReader(toProcess);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        ArrayList<String> lines = new ArrayList<String>();
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            lines.add(line);
        }
        bufferedReader.close();
        System.out.println(lines.toString());

        for (int i = 0; i < 2; i++){
            if (i == 0){
                String[] parts = lines.get(0).split(" ");
                System.out.println(Arrays.toString(parts));
                float[] numbers = new float[parts.length];
                for (int j = 0; j < parts.length; j++) {
                    float number = Float.parseFloat(parts[j]);
                    numbers[j] = number;
                }
                for (int k = 0; k < numbers.length; k++){
                    xs.add(k, numbers[k]);
                }
            } else {
                String[] parts = lines.get(1).split(" ");
                float[] numbers = new float[parts.length];
                for (int j = 0; j < parts.length; j++) {
                    float number = Float.parseFloat(parts[j]);
                    numbers[j] = number;
                }
                for (int k = 0; k < numbers.length; k++){
                    ys.add(k, numbers[k]);
                }
            }
        }


        System.out.println("XS CREATED: " + Arrays.toString(xs.toArray()));
        System.out.println("YS CREATED: " + Arrays.toString(ys.toArray()));
        coeff(xs, ys, cs);
        System.out.println("f(x) = " + evalNewton(xs, cs, z));


    }

    public void coeff(ArrayList<Float> xs, ArrayList<Float> ys, ArrayList<Float> cs){
        for(int i = 0; i < xs.size(); i++){
            cs.add(i, ys.get(i));
        }

        System.out.println();
        for (int j = 1; j < xs.size(); j++){
            for (int i = xs.size() - 1; i >= j; i-- ){
                cs.set(i, (cs.get(i) - cs.get(i-1))/(xs.get(i) - xs.get(i-j)));
            }
        }

    }

    public float evalNewton(ArrayList<Float> xs, ArrayList<Float> cs, float z){
        float result = cs.get(cs.size() - 1);

        for (int i = cs.size() - 1; i >= 0; i--){
            result = result * (z - xs.get(i)) + cs.get(i);
        }

        return result;
    }
}
