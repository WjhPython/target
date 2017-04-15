package crawlertext;

import crawler.main.Crawler;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;

import java.io.*;

/**
 * Text crawlerDownload page
 * Created by Administrator on 2017/3/25.
 */
public class CrawlerDownload {
    private Page page;
    public void runCrawler() {
        Crawler crawler = new Crawler();
        Spider.create(new Crawler()).addUrl("http://beihai.ganji.com/zpdianhuaxiaoshou/").thread(5).start();
    }
    public static void main(String[] args){
        CrawlerDownload CD = new CrawlerDownload();
        CD.runCrawler();
    }
}
