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
import java.util.Vector;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;


public class Main {
    public static int process_input(String[] args){
        if (args.length != 3){
            System.out.println("Error input.\nArguments should be <dictFile> <word1> <word2>");
            return -1;
        }
        String file_name = args[0];
        args[1] = args[1].toLowerCase();
        args[2] = args[2].toLowerCase();
        char[] word1 = args[1].toCharArray();
        char[] word2 = args[2].toCharArray();
        if (args[1].equals(args[2]))
        {
            System.out.println("The two words must be different.");
            return -1;
        }
        if (word1.length != word2.length){
            System.out.println("The two words must be of the same length.");
            return -1;
        }
        for (int i = 0; i < word1.length; i++)
        {
            if (!(word1[i] >= 'a' && word1[i] <= 'z')){
                System.out.println("Please input two English words.");
                return -1;
            }

        }
        for (int i = 0; i < word2.length; i++)
        {
            if (!(word2[i] >= 'a' && word2[i] <= 'z')){
                System.out.println("Please input two English words.");
                return -1;
            }
        }
        return 0;
    }

    public static int read_dict(String file_name, Set word_set){
        File file = new File(file_name);
        BufferedReader reader = null;
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

    public static String get_letter(int id){
        id = id + 96;
        char[] chs = new char[1];
        chs[0] = ((char)id);
        String s = new String(chs);
        return s;
    }


    public static Vector find_ladder(String from, String to, Set word_set){
        Vector path = new Vector();
        Set used_set = new HashSet();
        Queue<Vector<String>> word_queue = new ArrayBlockingQueue<Vector<String>>(10000000);
        Vector<String> init = new Vector();
        init.add(from);
        word_queue.add(init);
        used_set.add(from);//to store words that have already been visited in the used_set.
        if (word_set.contains(from) == false) {
            word_set.add(from);
        }

        Vector<String> poped, to_be_inserted;


        while (true)
        {
            poped = word_queue.element();
            word_queue.remove();
            String now_word = poped.lastElement();
            used_set.add(from);
            for (int i = 0; i < now_word.length(); i++)
            {
                for (int j = 1; j <= 26; j++)
                {
                    StringBuffer temp = new StringBuffer();
                    temp.append(now_word);
                    temp = temp.replace(i, i+1, get_letter(j));
                    if (temp.toString().compareTo(to) == 0)
                    {
                        path = (Vector) poped.clone();
                        path.add(to);
                        return path;
                    }
                    if (used_set.contains(temp.toString())) continue;
                    if (word_set.contains(temp.toString()))
                    {
                        used_set.add(temp.toString());
                        to_be_inserted = (Vector<String>) poped.clone();
                        to_be_inserted.add(temp.toString());
                        word_queue.add(to_be_inserted);
                    }
                }
            }
            if (word_queue.peek()==null) return null;
        }
    }

    public static void main(String[] args)  {
        int ret;
        ret = process_input(args);
        if (ret != 0){
            // if input is invalid, quit.
            return;
        }
        System.out.println("Searching for word ladder from "+args[1]+" to "+args[2]+".");
        Set<String> word_set = new HashSet();
        Vector<String> path = new Vector();
        ret = read_dict(args[0], word_set);
        if(ret != 0){
            System.out.println("Some error occurred. See the info above.");
            return;
        }
        path = find_ladder(args[1], args[2], word_set);
        if(path == null){
            System.out.println("No word ladder is found.");
        }
        else {
            System.out.println("Word ladder is found:");
            System.out.println(path);
        }
    }



}

