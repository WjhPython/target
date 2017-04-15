package crawler.main;

/**
 * 将赶集网用户简历信息封装实体类
 * Created by Administrator on 2017/3/21.
 */
public class CrawlerPrivateResume {
    public String job;//招聘工作
    public String company;//公司名字
    public String exp;//工作经验
    public String salary;//工资
    public String workArea;//工作地点
    public String education;//学历
    public String age;//公司规模
    public String recruitPeople;//招聘人数

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getWorkArea() {
        return workArea;
    }

    public void setWorkArea(String workArea) {
        this.workArea = workArea;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getRecruitPeople() {
        return recruitPeople;
    }

    public void setRecruitPeople(String recruitPeople) {
        this.recruitPeople = recruitPeople;
    }

    @Override
    public String toString() {
        return "CrawlerPrivateResume{" +
                "job='" + job + '\'' +
                ", company='" + company + '\'' +
                ", exp='" + exp + '\'' +
                ", salary='" + salary + '\'' +
                ", workArea='" + workArea + '\'' +
                ", education='" + education + '\'' +
                ", age='" + age + '\'' +
                ", recruitPeople='" + recruitPeople + '\'' +
                '}';
    }
}
