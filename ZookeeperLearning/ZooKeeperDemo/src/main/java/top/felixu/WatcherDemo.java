package top.felixu;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @Author felixu
 * @Date 2018/6/25
 */
public class WatcherDemo {
    public static void main(String[] args) throws InterruptedException, IOException, KeeperException {

        final CountDownLatch countDownLatch = new CountDownLatch(1);
        ZooKeeper zooKeeper = new ZooKeeper("10.211.55.8,10.211.55.9,10.211.55.10", 4000, event -> {
            System.out.println("default event" + event.getType());
            if (Watcher.Event.KeeperState.SyncConnected == event.getState()) {
                countDownLatch.countDown();
            }
        });
        countDownLatch.await();
        final String path = "/zk-persis-felixu";
        zooKeeper.create(path, "1".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        // exists getData getChildren

        Stat stat = zooKeeper.exists(path, event -> {
            System.out.println(event.getType() + " -> " + event.getPath());
            try {
                zooKeeper.exists(event.getPath(), true);
            } catch (KeeperException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // 通过修改事件触发监听事件
        stat = zooKeeper.setData(path, "2".getBytes(), stat.getVersion());

        Thread.sleep(1000);

        zooKeeper.delete(path, stat.getVersion());

        System.in.read();
    }
}
