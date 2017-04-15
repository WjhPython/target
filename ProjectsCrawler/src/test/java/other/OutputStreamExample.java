package other;

import java.io.*;

/**
 * Created by Administrator on 2017/3/25.
 */
public class OutputStreamExample {
    public void os(String ss){
        File file = new File("D:/Resume.txt");
            FileOutputStream fileoutputstream = null;
            try {
                fileoutputstream = new FileOutputStream(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        OutputStreamWriter writer = null;
        try {
            writer = new OutputStreamWriter(fileoutputstream,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            //fileoutputstream.write(ss.getBytes());
            writer.append(ss);
            writer.flush();
            fileoutputstream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static void main(String[] args){
        OutputStreamExample example = new OutputStreamExample();
        example.os("");
    }
}
