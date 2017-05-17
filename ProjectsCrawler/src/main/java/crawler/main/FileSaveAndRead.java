package crawler.main;

import java.io.*;

/**
 * Created by Administrator on 2017/3/31.
 */
public class FileSaveAndRead {
    private OutputStreamWriter outputstreamwriter;
    private FileOutputStream fileoutputstream;
    private File file,file1;
    private FileInputStream fileInputStream;
    public void saveFile(String ss,String path){
        file = new File(path);
        if (file.exists()){//判断文件存不存在
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            fileoutputstream = new FileOutputStream(file,true);
            outputstreamwriter = new OutputStreamWriter(fileoutputstream,"UTF-8");
            outputstreamwriter.append(ss+'\n');
            outputstreamwriter.flush();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String readFile(String path)
    {
        file1 = new File(path);
        Long filelength = file1.length();//获取文件的长度，用来建立字节数组
        byte[] filecontent = new byte[filelength.intValue()];

        try {
            fileInputStream = new FileInputStream(file1);
            fileInputStream.read(filecontent);
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String infomation = new String(filecontent);
        return infomation;
    }
    public void close(){
        try {
            fileoutputstream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
