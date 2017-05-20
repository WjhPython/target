import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/23.
 * 读取抓包获取的cookie信息， 暂存在静态变量cookies中。
 * author：阿朕
 */
public class Cookie {
    public static List<String[]> cookies = new LinkedList<String[]>();

    public Cookie(String filePath){
        try {
            String encoding="utf8";
            File file=new File(filePath);
            if(file.isFile() && file.exists()) { //判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file), encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while((lineTxt = bufferedReader.readLine()) != null){
                    cookies.add(lineTxt.split(" "));
                    //text.add(lineTxt.substring(lineTxt.indexOf('@')+1));
                    //System.out.println(text.get(text.size()-1));
                }
                read.close();
            }else{
                System.out.println("找不到指定的文件");
            }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
    }
/*
    public static void main(String[] args){
        Cookie.readCookie("E:\\temporary\\intellij idea\\test\\cookies\\cookie00.txt");
    }*/
}
