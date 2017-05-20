/**
 * Created by Administrator on 2017/3/28.
 */

 import us.codecraft.webmagic.Page;

 import java.io.*;
 import java.util.Map;

public class FileOperation {

 /**
 * 创建文件并写入该条简历信息
 * @return
 * author：阿朕
 */
    public static void createFile(Page page) {
        FileWriter fw = null;
        try {
            //如果文件存在，则追加内容；如果文件不存在，则创建文件
            File f = new File("E:\\temporary\\intellij idea\\resumes\\temp0\\" + page.getResultItems().get("uuid") + ".txt");
            fw = new FileWriter(f, false);
        } catch (IOException e) {
            e.printStackTrace();
        }
        PrintWriter pw = new PrintWriter(fw);
        pw.println("get page: " + page.getResultItems().getRequest().getUrl());
        //pw.println("uuid: " + page.getUrl().toString().substring(page.getUrl().toString().lastIndexOf('/')+1, page.getUrl().toString().lastIndexOf('x')).toString());
        for (Map.Entry<String, Object> entry : page.getResultItems().getAll().entrySet()) {
            pw.println(entry.getKey() + ":\t" + entry.getValue());
        }

        try {
            fw.flush();
            pw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 }

