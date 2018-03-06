import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;


public class Main {
    public static int process_input(String[] args){
        if (args.length != 3){
            System.out.println("Error input.\nArguments should be <dictFile> <word1> <word2>");
            return -1;
        }
        String file_name = args[0];
        args[1] = args[1].toLowerCase();
        args[2] = args[2].toLowerCase();
        //System.out.println("Searching for word ladder from "+word1+" to "+word2+".");
        return 0;
    }

    public static int read_dict(String file_name){
        File file = new File(file_name);
        BufferedReader reader = null;
        Set<String> word_set = new HashSet();
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
                word_set.add(tempString);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return 0;
    }

    public static void main(String[] args)  {
        process_input(args);
        read_dict(args[0]);
        System.out.println("Searching for word ladder from "+args[1]+" to "+args[2]+".");
    }



}

