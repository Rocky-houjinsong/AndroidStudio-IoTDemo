import org.eclipse.paho.client.mqttv3.MqttException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {

    @Test
    void connect() throws MqttException {
        Client client  = new Client();
        client.connect();
    }
}