package net.xdclass.xdclasskafka;

import org.apache.kafka.clients.admin.*;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.ExecutionException;

/**
 * 小滴课堂,愿景：让技术不再难学
 *
 * @Description
 * @Author 二当家小D
 * @Remark 有问题直接联系我，源码-笔记-技术交流群
 * @Version 1.0
 **/

public class KafkaAdminTest {

    private static final String TOPIC_NAME = "xdclass-sp-topic-test";

    /**
     * 设置admin 客户端
     * @return
     */
    public static AdminClient initAdminClient(){
        Properties properties = new Properties();
        properties.setProperty(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG,"8.140.98.27:9092");

        AdminClient adminClient = AdminClient.create(properties);
        return adminClient;
    }


    /**
     * 创建topic
     */
    @Test
    public void createTopicTest(){
        AdminClient adminClient = initAdminClient();

        //指定分区数量，副本数量
        NewTopic newTopic = new NewTopic(TOPIC_NAME,2,(short) 1);

        CreateTopicsResult createTopicsResult = adminClient.createTopics(Arrays.asList(newTopic));
        try {
            //future等待创建，成功则不会有任何报错
            createTopicsResult.all().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }


    /**
     * 列举topic列表
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void listTopicTest() throws ExecutionException, InterruptedException {
        AdminClient adminClient = initAdminClient();

        //是否查看内部的topic，可以不用
        ListTopicsOptions options = new ListTopicsOptions();
        options.listInternal(true);

        ListTopicsResult listTopicsResult = adminClient.listTopics(options);

        Set<String> topics = listTopicsResult.names().get();
        for(String name : topics){
            System.err.println(name);
        }

    }


    /**
     * 删除topic
     */
    @Test
    public void delTopicTest() throws ExecutionException, InterruptedException {
        AdminClient adminClient = initAdminClient();

        DeleteTopicsResult deleteTopicsResult = adminClient.deleteTopics(Arrays.asList("xdclass-sp11-topic","version1-topic","my-topic"));

        deleteTopicsResult.all().get();
    }


    /**
     * 查看某个topic详情
     */
    @Test
    public void detailTopicTest() throws ExecutionException, InterruptedException {

        AdminClient adminClient = initAdminClient();
        DescribeTopicsResult describeTopicsResult = adminClient.describeTopics(Arrays.asList(TOPIC_NAME));

        Map<String, TopicDescription> stringTopicDescriptionMap = describeTopicsResult.all().get();

        Set<Map.Entry<String, TopicDescription>> entries = stringTopicDescriptionMap.entrySet();

        entries.stream().forEach((entry)-> System.out.println("name ："+entry.getKey()+" , desc: "+ entry.getValue()));
    }


    /**
     * 增加topic分区数量
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void incrPartitionTopicTest() throws ExecutionException, InterruptedException {
        Map<String,NewPartitions> infoMap = new HashMap<>(1);


        AdminClient adminClient = initAdminClient();
        NewPartitions newPartitions = NewPartitions.increaseTo(5);

        infoMap.put(TOPIC_NAME,newPartitions);

        CreatePartitionsResult createPartitionsResult = adminClient.createPartitions(infoMap);

        createPartitionsResult.all().get();

    }



}
