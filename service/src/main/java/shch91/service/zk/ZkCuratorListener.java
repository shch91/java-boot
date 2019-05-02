package shch91.service.zk;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.TreeCache;
import org.apache.zookeeper.CreateMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ZkCuratorListener {

    private static Logger logger = LoggerFactory.getLogger(ZkCuratorListener.class);

    @Autowired
    protected CuratorFramework curator;

    public void  nodeCache(String path) throws Exception {

        NodeCache cache = new NodeCache(curator, path, false);
        cache.start(true);
        //只会监听节点的创建和修改，删除不会监听
        cache.getListenable().addListener(() -> {
            logger.info("路径：" + cache.getCurrentData().getPath());
            logger.info("数据：" + new String(cache.getCurrentData().getData()));
            logger.info("状态：" + cache.getCurrentData().getStat());
        });


        curator.create().forPath(path, "1234".getBytes());
        Thread.sleep(1000);
        curator.setData().forPath(path, "5678".getBytes());
        Thread.sleep(1000);

    }

    public void  pathChildrenCache(String path) throws Exception {
        //第三个参数表示是否接收节点数据内容
        PathChildrenCache childrenCache = new PathChildrenCache(curator, path, true);
        /**
         * 如果不填写这个参数，则无法监听到子节点的数据更新
         如果参数为PathChildrenCache.StartMode.BUILD_INITIAL_CACHE，则会预先创建之前指定的/super节点
         如果参数为PathChildrenCache.StartMode.POST_INITIALIZED_EVENT，效果与BUILD_INITIAL_CACHE相同，只是不会预先创建/super节点
         参数为PathChildrenCache.StartMode.NORMAL时，与不填写参数是同样的效果，不会监听子节点的数据更新操作
         */
        childrenCache.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);
        childrenCache.getListenable().addListener((framework, event) -> {
            switch (event.getType()) {
                case CHILD_ADDED:
                    logger.info("CHILD_ADDED，类型：" + event.getType() + "，路径：" + event.getData().getPath() + "，数据：" +
                            new String(event.getData().getData()) + "，状态：" + event.getData().getStat());
                    break;
                case CHILD_UPDATED:
                    logger.info("CHILD_UPDATED，类型：" + event.getType() + "，路径：" + event.getData().getPath() + "，数据：" +
                            new String(event.getData().getData()) + "，状态：" + event.getData().getStat());
                    break;
                case CHILD_REMOVED:
                    logger.info("CHILD_REMOVED，类型：" + event.getType() + "，路径：" + event.getData().getPath() + "，数据：" +
                            new String(event.getData().getData()) + "，状态：" + event.getData().getStat());
                    break;
                default:
                    break;
            }
        });

        curator.create().forPath(path, "123".getBytes());
        curator.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath(path+"c1", "c1内容".getBytes());
        //经测试，不会监听到本节点的数据变更，只会监听到指定节点下子节点数据的变更
        curator.setData().forPath(path, "456".getBytes());
        curator.setData().forPath(path+"/sc1", "c1新内容".getBytes());
        curator.delete().guaranteed().deletingChildrenIfNeeded().forPath(path);
        Thread.sleep(5000);

    }

    public void treeCache(String path) throws Exception {
        TreeCache treeCache = new TreeCache(curator, "/treeCache");
        treeCache.start();
        treeCache.getListenable().addListener((curatorFramework, treeCacheEvent) -> {
            switch (treeCacheEvent.getType()) {
                case NODE_ADDED:
                    logger.info("NODE_ADDED：路径：" + treeCacheEvent.getData().getPath() + "，数据：" + new String(treeCacheEvent.getData().getData())
                            + "，状态：" + treeCacheEvent.getData().getStat());
                    break;
                case NODE_UPDATED:
                    logger.info("NODE_UPDATED：路径：" + treeCacheEvent.getData().getPath() + "，数据：" + new String(treeCacheEvent.getData().getData())
                            + "，状态：" + treeCacheEvent.getData().getStat());
                    break;
                case NODE_REMOVED:
                    logger.info("NODE_REMOVED：路径：" + treeCacheEvent.getData().getPath() + "，数据：" + new String(treeCacheEvent.getData().getData())
                            + "，状态：" + treeCacheEvent.getData().getStat());
                    break;
                default:
                    break;
            }
        });

        curator.create().forPath("/treeCache", "123".getBytes());
        curator.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath("/treeCache/c1", "456".getBytes());
        curator.setData().forPath("/treeCache", "789".getBytes());
        curator.setData().forPath("/treeCache/c1", "910".getBytes());
        curator.delete().forPath("/treeCache/c1");
        curator.delete().forPath("/treeCache");
        Thread.sleep(5000);
    }
}
