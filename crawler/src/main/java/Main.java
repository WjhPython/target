import java.util.List;

/**
 * Created by Administrator on 2017/4/22.
 * 函数入口
 */
public class Main{
    static List<String> urls0;
    static List<String> urls1;

    public static void main(String[] args) {

        urls0 = new CreateURL().getUrls("job");
        urls1 = new CreateURL().getUrls("resume");
        new Thread_createSpider("00", urls1).start();
        new CrawlerDownload(urls0).start();
    }
}
