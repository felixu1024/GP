package top.felixu;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @Author felixu
 * @Date 2018/6/20
 */
public class ConnectionDemo {
//    public static void main(String[] args) {
//        try {
//            ZooKeeper zooKeeper = new ZooKeeper("10.211.55.8,10.211.55.9,10.211.55.10", 4000, null);
//            // status is CONNECTING
//            System.out.println(zooKeeper.getState());
//            Thread.sleep(1000);
//            // status is CONNECTED
//            System.out.println(zooKeeper.getState());
//            zooKeeper.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }

    public static void main(String[] args) {
        try {
            final CountDownLatch countDownLatch = new CountDownLatch(1);
            ZooKeeper zooKeeper = new ZooKeeper("10.211.55.8,10.211.55.9,10.211.55.10", 4000, new Watcher() {
                @Override
                public void process(WatchedEvent event) {
                    if (Event.KeeperState.SyncConnected == event.getState()) {
                        countDownLatch.countDown();
                    }
                }
            });
            countDownLatch.await();
            // status is CONNECTED
            System.out.println(zooKeeper.getState());

            final String path = "/zk-persis-felixu";
            zooKeeper.create(path, "0".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            Thread.sleep(1000);
            Stat stat = new Stat();
            byte[] bytes = zooKeeper.getData(path, null, stat);
            System.out.println(new String(bytes));
            zooKeeper.setData(path, "1".getBytes(), stat.getVersion());
            byte[] bytes1 = zooKeeper.getData(path, null, stat);
            System.out.println(new String(bytes1));
            zooKeeper.delete(path, stat.getVersion());
            zooKeeper.close();
            System.in.read();
        } catch (IOException | InterruptedException | KeeperException e) {
            e.printStackTrace();
        }
    }
}
