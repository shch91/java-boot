package shch91.service.zk;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.CuratorListener;
import org.apache.curator.framework.recipes.queue.DistributedQueue;
import org.apache.curator.framework.recipes.queue.QueueBuilder;
import org.apache.curator.framework.recipes.queue.QueueConsumer;
import org.apache.curator.framework.recipes.queue.QueueSerializer;
import org.apache.curator.framework.state.ConnectionState;

import org.apache.curator.utils.CloseableUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ZkDistributedQueue {

    private  static final Logger logger= LoggerFactory.getLogger(ZkDistributedQueue.class);

    @Autowired
    private  CuratorFramework client;

    private String path="/dis";

    public  void gfds(String[] args) throws Exception {

        DistributedQueue<String> queue = null;
        try {
            client.getCuratorListenable().addListener(new CuratorListener() {
                @Override
                public void eventReceived(CuratorFramework client, CuratorEvent event) throws Exception {
                    logger.info("CuratorEvent: " + event.getType().name());
                }
            });

            QueueConsumer<String> consumer = createQueueConsumer();
            QueueBuilder<String> builder = QueueBuilder.builder(client, consumer, createQueueSerializer(), path);
            queue = builder.buildQueue();
            queue.start();

            for (int i = 0; i < 10; i++) {
                queue.put(" test-" + i);
                Thread.sleep((long)(3 * Math.random()));
            }

            Thread.sleep(20000);

        } catch (Exception ex) {

        } finally {
            CloseableUtils.closeQuietly(queue);
            CloseableUtils.closeQuietly(client);

        }
    }

    private static QueueSerializer<String> createQueueSerializer() {
        return new QueueSerializer<String>(){

            @Override
            public byte[] serialize(String item) {
                return item.getBytes();
            }

            @Override
            public String deserialize(byte[] bytes) {
                return new String(bytes);
            }

        };
    }

    private static QueueConsumer<String> createQueueConsumer() {

        return new QueueConsumer<String>(){

            @Override
            public void stateChanged(CuratorFramework client, ConnectionState newState) {
                logger.info("connection new state: " + newState.name());
            }

            @Override
            public void consumeMessage(String message) throws Exception {
                logger.info("consume one message: " + message);
            }

        };
    }
}
