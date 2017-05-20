import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/22.
 * 读取city.txt和resume.txt， 并生成最终的url， 暂存在静态变量urls中。
 * author：阿朕
 */
public class CreateURL {
    private List<String> urls_citys = new ArrayList<String>();
    private List<String> urls_kind = new ArrayList<String>();
    private List<String> urls = new LinkedList<String>();

    public static void readFile(String filePath, List<String> text){
        try {
            String encoding="utf8";
            File file=new File(filePath);
            if(file.isFile() && file.exists()) { //判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file), encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while((lineTxt = bufferedReader.readLine()) != null){
                    text.add(lineTxt.substring(lineTxt.indexOf('@')+1));
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

    public List<String> getUrls(String urlKind){
        readFile("src/url/Citys.txt", urls_citys);
        readFile("src/url/"+urlKind+".txt", urls_kind);

        for(String city : urls_citys){
            for (String resume : urls_kind){
                urls.add(city+resume.substring(1));
                //System.out.println(urls.get(urls.size()-1));
            }
        }

        //System.out.println(urls.size());
        return urls;
    }

    /*
    //测试
    public static void main(String[] args){
        CreateURL.getUrls();
    }
    */
}