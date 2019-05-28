import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.Random;

public class FileGenerator {

    private int n;


    public static void main(String[] args) throws IOException {
        FileGenerator fileGenerator = new FileGenerator();
        fileGenerator.commandHandler(args);
    }

    /*
    Command handler method for taking in command line arguments.
     */
    public void commandHandler(String[] args) throws IOException {
        try{
            try{
                n = Integer.parseInt(args[0]);
            } catch (NumberFormatException | NullPointerException e){
                System.out.println("You did not enter the number of data points you want in your file correctly.");
            }
        } catch(ArrayIndexOutOfBoundsException e){
            System.out.println("You did not enter the correct format of params for the program to run.");
        }

        outputToFile(n);
    }

    public void outputToFile(int n) throws IOException {

        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();
        File file = new File(s + "//dataset.test");

        FileWriter writer = new FileWriter(file);
        Random r = new Random();
        DecimalFormat df = new DecimalFormat("#.##");
        for (int i = 0; i < 2; i++){
            for (int j = 0; j < n; j++){
                writer.write(Double.toString(Double.parseDouble(df.format(-5.0 + (5.0 - -5.0) * r.nextDouble()))) + " ");
            }
            writer.write(System.getProperty( "line.separator" ));
        }

        System.out.println("Dataset file has been created/updated.");
        writer.close();
    }

}

