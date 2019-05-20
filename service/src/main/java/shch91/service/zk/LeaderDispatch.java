package shch91.service.zk;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListenerAdapter;


@Slf4j
public class LeaderDispatch  {


    private boolean leader = false;

    public boolean isLeader() {
        return leader;
    }

    private String path;

    private CuratorFramework curatorFramework;

    public LeaderDispatch(CuratorFramework curatorFramework, String path) {
        this.curatorFramework=curatorFramework;
        this.path = path;
    }

    /**
     * 异步监控，存在初始化过快，无法确定Leader，如必须在初始化时Leader执行，就在方法中直接调用
     */
    public void leaderSelector() {
        LeaderSelector selector = new LeaderSelector(curatorFramework, path,
                new LeaderSelectorListenerAdapter() {
                    @Override
                    public void takeLeadership(CuratorFramework curatorFramework)
                            throws Exception {
                        log.info("成为Master: leader 选择成功！！！");
                        leader = true;
                        while (true) {
                            Thread.sleep(Integer.MAX_VALUE);
                    }
                }
        });
        selector.autoRequeue();
        selector.start();
    }


    public void call() {
        leader = false;
        leaderSelector();
    }

}