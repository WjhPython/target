package crawlerDownload;

import crawler.main.Crawler;
import crawler.main.ParseFile;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Spider;

import java.util.*;

/**
 * Text crawlerDownload page
 * Created by Administrator on 2017/3/25.
 */
public class CrawlerDownload extends Thread{
    private Page page;
    private ParseFile PF = new ParseFile();
    private List<String> list = new LinkedList<String>();
    public CrawlerDownload(List<String> list) {
        this.list = list;
    }
    //????run????
    public void run(){
        Crawler crawler = new Crawler();
        Spider spider = Spider.create(crawler);//
        //spider.addPipeline(new FilePipeline());
        ListIterator<String> iter = list.listIterator();
        spider.thread(15);
        //spider.addUrl("http://anshan.ganji.com/zpdianhuaxiaoshou/").thread(5).run();
        while(iter.hasNext()) {//
            spider.addRequest(new Request(iter.next()));//http://anshan.ganji.com/zpdianhuaxiaoshou/
            spider.run();
        }


    }
        public static void main(String[] args){
            ParseFile PF = new ParseFile();
            List<String> list = PF.reFile();
            //Ïß³Ì1
            int nu = list.size()/1;
            List<String> ls = list.subList(1,nu);

            CrawlerDownload CD_01 = new CrawlerDownload(ls);
            CD_01.start();
//            int nu_02 = nu*2;
//            CrawlerDownload CD_02 = new CrawlerDownload(list.subList(nu+1,nu*2));
//            CD_02.start();
        }

}
