package crawlertext;

import crawler.main.Crawler;
import crawler.main.FileSaveAndRead;
import crawler.main.ParseFile;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

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

    public void runCrawler() {
//        Crawler crawler = new Crawler();
//        Spider spider = Spider.create(new Crawler());
//        list = PF.reFile();
//        int nu = list.size()/3;//分部分爬取
//        for (String urls:list
//             ) {
//            spider.addUrl(urls);//http://anshan.ganji.com/zpdianhuaxiaoshou/
//        }
//        spider.thread(5).start();
    }
    public void run(){
        Crawler crawler = new Crawler();
        Spider spider = Spider.create(new Crawler());
        for (String urls:list
                ) {
            spider.addUrl(urls);//http://anshan.ganji.com/zpdianhuaxiaoshou/
        }
        spider.thread(10).start();


    }
    public static void main(String[] args){
        ParseFile PF = new ParseFile();
        List<String> list = PF.reFile();
        //线程thread-01爬取第一部分（三分之一）
        int nu = list.size()/3;//分部分爬取
        CrawlerDownload CD = new CrawlerDownload(list.subList(1,nu));
        CD.start();
        //线程thread-02爬取第2部分（三分之1）
        int nu_02 = nu*2;
        CrawlerDownload CD_02 = new CrawlerDownload(list.subList(nu+1,nu_02));
        CD_02.start();
        //线程thread-03爬取第3部分（三分之一）
        CrawlerDownload CD_03 = new CrawlerDownload(list.subList(nu_02+1,list.size()));
        CD_03.start();
    }
}
