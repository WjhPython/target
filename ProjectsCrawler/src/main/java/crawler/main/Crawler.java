package crawler.main;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * Created by Administrator on 2017/3/21.
 */
public class Crawler implements PageProcessor {
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);
    public String job;//招聘工作
    public String company;//公司名字
    public String exp;//工作经验
    public String salary;//工资
    public String workArea;//工作地点
    public String education;//学历
    public String age;//年龄限制
    public String recruitPeople;//招聘人数
    public String information;//招聘分类
    public String value;
    public String pageBoxUrl;//分页信息
    public CrawlerPrivateResume crawlerPrivateResume = new CrawlerPrivateResume();
    public FileSaveAndRead filesave = new FileSaveAndRead();
    public void process(Page page) {
        //简历太多，检测是否有分页
        pageBoxUrl = page.getHtml().xpath("div[@class='pageBox']/ul/li/a/@href").all().toString();
        pageBoxUrl = pageBoxUrl.substring(1, pageBoxUrl.lastIndexOf(']'));
        //将每一个公司的详细招聘页面下载下来
        page.addTargetRequests(page.getHtml().xpath("div[@data-widget='app/ms_v2/wanted/list.js#companyAjaxBid']/dl/dt/a/@href").all());
            /*招聘信息解析*/
        company = page.getHtml().xpath("//*[@id=\"companyName\"]/span/a/text()").toString();
        recruitPeople = page.getHtml().xpath("//*[@id=\"wrapper\"]/div[4]/div[1]/div[4]/ul/li[6]/em/text()").toString();//招聘规模
        job = page.getHtml().xpath("div[@class='d-c-left-hear']/h1/text()").toString();//招聘职位
        age = page.getHtml().xpath("//*[@id=\"wrapper\"]/div[4]/div[1]/div[4]/ul/li[5]/em/text()").toString();//年龄限制
        education = page.getHtml().xpath("//*[@id=\"wrapper\"]/div[4]/div[1]/div[4]/ul/li[3]/em/text()").toString();
        exp = page.getHtml().xpath("//*[@id=\"wrapper\"]/div[4]/div[1]/div[4]/ul/li[4]/em/text()").toString();
        salary = page.getHtml().xpath("//*[@id=\"wrapper\"]/div[4]/div[1]/div[4]/ul/li[2]/em/text()").toString();
        workArea = page.getHtml().xpath("//*[@id=\"wrapper\"]/div[4]/div[1]/div[4]/ul/li[@class='fl w-auto']/em/text()").toString();
            /*输出个人简历信息测试*/
        if (company != null)
        {
                /*将结果赋值给招聘实体类*/
            crawlerPrivateResume.setCompany(company);
            crawlerPrivateResume.setAge(age);
            crawlerPrivateResume.setEducation(education);
            crawlerPrivateResume.setExp(exp);
            crawlerPrivateResume.setJob(job);
            crawlerPrivateResume.setSalary(salary);
            crawlerPrivateResume.setWorkArea(workArea);
            crawlerPrivateResume.setRecruitPeople(recruitPeople);
            crawlerPrivateResume.setInformation(information);
                /*将信息写到本地*/
            filesave.saveFile(crawlerPrivateResume.toString(),"F://resume/"+company+".txt");
            if (page.getRequest() == null){
                filesave.close();
            }
        }//if
//            }//for
        //简历太多，检测是否有分页 -- o2 o3 o4....next page
        String pageBoxUrlExp = pageBoxUrl.split(", ")[1];
        //System.out.println("这是有分页"+pageBoxUrlExp);
        if (pageBoxUrlExp != null)
        {
            for (String pbe : pageBoxUrl.split(", ")
                    ) {
                if (pbe == null) {
                    System.out.println(pbe + " is null");
                }                //System.out.println("新加入"+pbe);
                else {
                    page.addTargetRequest(pbe);
                }

            }//for
        }//if
    }//process

    public Site getSite() {
        return site;
    }
}
