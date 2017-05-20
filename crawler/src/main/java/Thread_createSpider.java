import us.codecraft.webmagic.Spider;

import java.util.List;
import java.util.ListIterator;

/**
 * Created by Administrator on 2017/4/23.
 * author：阿朕
 */
public class Thread_createSpider extends Thread {
    private String cookieNumber;
    private ListIterator<String> urls;

    public Thread_createSpider(String cookieNumber, List<String> urls){
        this.cookieNumber = cookieNumber;
        this.urls = urls.listIterator();
    }

    public void run(){
        //System.out.println("E:\\temporary\\intellij idea\\test\\cookies\\cookie"+cookieNumber+".txt");
        Spider spider = Spider.create(new RepoPageProcessor(new Cookie("src/cookies/cookie"+cookieNumber+".txt")));
        spider.thread(10);
        while(urls.hasNext()){
            spider.addUrl(urls.next());
            spider.run();
        }
    }
}
