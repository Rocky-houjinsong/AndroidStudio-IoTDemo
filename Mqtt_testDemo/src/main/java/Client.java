import com.google.gson.Gson;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.awt.*;
import java.awt.desktop.SystemSleepEvent;

/**
 * @author hp
 * @program:
 * @description:
 * @date 2022-06-07 11:24:44
 */
public class Client {
    private  static  final  String HOST = "tcp://127.0.0.1:1883"; //服务器ip 端口
    private  static  final  String CLIENT_ID = "local_client"; //客户端唯一标识
    private  static  final  String USERNAME = "idea";
    private  static  final  String PASSWORD = "13579";
    private static  final  String WILL_TOPIC = "test";

    private MqttClient mqttClient;

    public void connect() throws MqttException{
        //新建客户端实例
        //MemoryPersisernce设置客户端实例的保存形式， 默认为内存保存，此处就是以内存进行保存
        mqttClient = new MqttClient(HOST,CLIENT_ID,new MemoryPersistence());
        //设置连接时的参数
        MqttConnectOptions options = new MqttConnectOptions();
        //是否情况session。如果设置为false 标识服务器会保存客户端的连接记录
        //该选项设置为true， 标识客户端每次连接的服务器都已新的身份进行连接；
        options.setCleanSession(true);
        options.setUserName(USERNAME);
        options.setPassword(PASSWORD.toCharArray());

        //连接超时时间
        options.setConnectionTimeout(100);
        //心跳间隔时间
        options.setKeepAliveInterval(180);

        // 掉线自动重连
        options.setAutomaticReconnect(true);
        // 遗嘱消息，当前连接断开时发送的死亡预告， 此客户端连接断开后
        //服务器会把 此消息 推送给订阅了此主题的客户机；
        options.setWill(WILL_TOPIC,"offline".getBytes(),0,true);
        mqttClient.setCallback(new MqttCallback() {
            @Override
            public void connectionLost(Throwable cause) {
                System.out.println("connection lost,reconnection");
            }
            // 收到 已经订阅的发布
            @Override
            public void messageArrived(String topic, MqttMessage message) throws Exception {
                System.out.println("topic:"+topic+",message:"+message);
                String jsonStr = new String(message.getPayload());
                // mqtt.fx中使用 gbk加码器， 若与其连用是想要避免钟文乱码就要使用以下的方法
                // string jsonstr = new string（message.getPayload（），charset.forname("gbk"));
                System.out.println("json string: "+jsonStr);
                try {
                    TemperatureAndHumidity jsonObj = new Gson().fromJson(jsonStr, TemperatureAndHumidity.class);
                    System.out.println("json object:"+ jsonStr);
                }catch (Exception e){
                    System.out.println(e.toString());
                }
            }
            //接收到已经发布的QoS 1 或 QoS2 消息的传递令牌时调用
            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {
                System.out.println("delivery complete");
            }
        });
        mqttClient.connect(options);
        System.out.println("connect success");
    }

}
