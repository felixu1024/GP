package top.felixu;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @Author felixu
 * @Date 2018.07.02
 */
public class DistributedLock implements Lock, Watcher {

    private ZooKeeper zk = null;
    /**
     * 定义根节点
     */
    private String ROOT_LOCK = "/lock";
    /**
     * 等待前一个锁
     */
    private String WAIT_LOCK;
    /**
     * 表示当前锁
     */
    private String CURRENT_LOCK;
    private CountDownLatch countDownLatch;

    public DistributedLock() {
        try {
            zk = new ZooKeeper("10.211.55.8", 4000, this);
            Stat stat = zk.exists(ROOT_LOCK, false);
            if (null == stat) {
                zk.create(ROOT_LOCK, "0".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }
        } catch (IOException | InterruptedException | KeeperException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void lock() {
        if (tryLock()) {
            System.out.println(Thread.currentThread().getName() + " -> " + CURRENT_LOCK + " ,获得锁成功");
            return;
        }
        try {
            waitForLock(WAIT_LOCK);
        } catch (KeeperException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private boolean waitForLock(String prev) throws KeeperException, InterruptedException {
        Stat stat = zk.exists(prev, true);
        if (null != stat) {
            System.out.println(Thread.currentThread().getName() + " -> 等待锁" + ROOT_LOCK + "/" + prev + "释放");
            countDownLatch = new CountDownLatch(1);
            countDownLatch.await();
            System.out.println(Thread.currentThread().getName() + " -> 获得锁成功");
        }
        return true;
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        try {
            CURRENT_LOCK = zk.create(ROOT_LOCK + "/", "0".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
            System.out.println(Thread.currentThread().getName() + " -> " + CURRENT_LOCK + " ,尝试竞争锁");
            List<String> children = zk.getChildren(ROOT_LOCK, false);
            SortedSet<String> sortedSet = new TreeSet<>();
            children.forEach(child -> sortedSet.add(ROOT_LOCK + "/" + child));
            String firstNode = sortedSet.first();
            SortedSet<String> lessThenMe = sortedSet.headSet(CURRENT_LOCK);
            if (CURRENT_LOCK.equals(firstNode)) {
                return true;
            }
            if (!lessThenMe.isEmpty()) {
                WAIT_LOCK = lessThenMe.last();
            }
        } catch (KeeperException | InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        System.out.println(Thread.currentThread().getName() + " -> 释放锁" + CURRENT_LOCK);
        try {
            zk.delete(CURRENT_LOCK, -1);
            CURRENT_LOCK = null;
            zk.close();
        } catch (InterruptedException | KeeperException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Condition newCondition() {
        return null;
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        if (null != this.countDownLatch) {
            this.countDownLatch.countDown();
        }
    }
}
