import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/21.
 */
public class ParseFile {
    private String cityUrl;//城市url解析字段
    private String recruitUrl;//招聘分类字段
    List<String> cityList = new ArrayList<String>();
    List<String> jobList = new ArrayList<String>();
    List<String> cityAndJobList = new LinkedList<String>();
    public List reFile() {
        //从文件中解析得到
        FileSaveAndRead fileReadCitys = new FileSaveAndRead();
        String citystr = null;
        citystr = fileReadCitys.readFile("urlFile/Citys.txt");
        for (String str : citystr.split("/\n")
                ) {
            str = str.substring(str.indexOf("@") + 1);
            cityList.add(str);
        }
        FileSaveAndRead fileReadjob = new FileSaveAndRead();
        String jobstr = null;
        jobstr = fileReadjob.readFile("urlFile/job.txt");
        for (String str : jobstr.split("\n")
                ) {
            str = str.substring(str.indexOf("@") + 1);
            jobList.add(str);
        }
        //组合url
        for (String list : cityList
                ) {
            if (list != null) {
                for (String ls : jobList
                        ) {
                    if (ls != null) {
                        String s = list + ls;
                        cityAndJobList.add(s);
                    }
                }
            }
        }
        //测试代码 -- 没问题
//        FileSaveAndRead fileSaveAndRead = new FileSaveAndRead();
//        fileSaveAndRead.saveFile(cityAndJobList.toString(),"F:\\2.txt");
        return cityAndJobList;
    }
    //测试代码 -- 没问题
//    public static void main(String args[]){
//        ParseFile parseFile = new ParseFile();
//        String s = parseFile.reFile().toString();
//    }
}
