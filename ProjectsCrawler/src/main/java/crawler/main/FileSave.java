package crawler.main;

import java.io.*;

/**
 * Created by Administrator on 2017/3/31.
 */
public class FileSave {
    private OutputStreamWriter outputstreamwriter;
    private FileOutputStream fileoutputstream;
    private File file;
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
    public void close(){
        try {
            fileoutputstream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
