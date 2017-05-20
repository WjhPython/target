import com.mongodb.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.Page;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2017/5/9.
 * author：阿朕
 */


public class TransferToDB {
    private MongoClient mongoClient;
    private DB db = null;
    private DBCollection dbCollection = null;
    private DBObject dbObject = null;

    private static TransferToDB instance;
    private static final String HOSTNAME = "120.24.97.160";
    private static final int PORT  = 27017;

    private static final String USERNAME = "fanzezhen";
    private static final String PASSWORD = "FZz19951106";
    private static final String DATABASE = "admin";

    private String collectionName;
    private HashMap<String,String> hashMap = new HashMap<String, String>();
    private Logger logger = LoggerFactory.getLogger(getClass());

    public TransferToDB(){
        collectionName  = "resumes";
        try {       /*数据库登录验证*/
            ServerAddress serverAddress = new ServerAddress(HOSTNAME,PORT);

            MongoCredential mongoCredential = MongoCredential.createMongoCRCredential(USERNAME,DATABASE,PASSWORD.toCharArray());
            List<MongoCredential> mongoCredentialList = new ArrayList<MongoCredential>();
            mongoCredentialList.add(mongoCredential);

            mongoClient = new MongoClient(serverAddress,mongoCredentialList);
            db = mongoClient.getDB(DATABASE);
            dbCollection = db.getCollection(collectionName);
        }catch(Exception e){
            logger.error("数据库连接失败：\n"+e.getMessage());
            System.out.println("数据库连接失败： ");
            e.printStackTrace();
        }
    }

    public static TransferToDB getInstance(){
        if (instance == null){
            instance = new TransferToDB();
        }
        return instance;
    }

    public void getMongo(){}

    public void transferToDB(Page page){
        hashMap.put(page.getResultItems().get("uuid").toString(),
                page.getResultItems().getAll().toString());
        dbObject = new BasicDBObject(hashMap);
        dbCollection.insert(dbObject);
    }


    //测试
    /*
    //因存在内存泄漏问题已改用其他方法。（java heap space）
    public static void main(String[] args) {

        try {
            // 实例化Mongo对象，连接27017端口
            Mongo mongo = new Mongo("localhost", 27017);
            // 连接名为test的数据库，假如数据库不存在的话，mongodb会自动建立
            DB db = mongo.getDB("test");
            // Get collection from MongoDB, database named "test"
            // 从Mongodb中获得名为yourColleection的数据集合，如果该数据集合不存在，Mongodb会为其新建立
            DBCollection collection = db.getCollection("yourCollection");
            // 使用BasicDBObject对象创建一个mongodb的document,并给予赋值。
            BasicDBObject document = new BasicDBObject();
            document.put("id", 1001);
            document.put("msg", "hello world mongoDB in Java");
            // 将新建立的document保存到collection中去
            collection.insert(document);
            // 创建要查询的document
            BasicDBObject searchQuery = new BasicDBObject();
            searchQuery.put("id", 1001);
            // 使用collection的find方法查找document
            DBCursor cursor = collection.find(searchQuery);
            // 循环输出结果
            while (cursor.hasNext()) {
                System.out.println(cursor.next());
            }
            System.out.println("Done");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (MongoException e) {
            e.printStackTrace();
        }
    }
    */
}
