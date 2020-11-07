package shch91.common.code;

import java.util.Scanner;

/*
 *录入数据
 *
 */
public class Trains {

    //case 6 路径数
    private int case6Cnt;

    //case 10 路径数
    private int case10Cnt;

    //节点数量
    private static final int MAX_NODE = 5;

    //节点的有向图邻接矩阵
    public int[][] map = new int[MAX_NODE][MAX_NODE];

    //保存最短路径
    public int[][] edge = new int[MAX_NODE][MAX_NODE];

    //初始化地图  -1 代表节点之间距离不可达
    public void initMap() {
        for (int i = 'A'; i < 'F'; i++) {
            for (int j = 'A'; j < 'F'; j++) {
                map[i - 'A'][j - 'A'] = -1;
            }
        }
    }

    //输出地图
    public void printMap() {
        for (int i = 'A'; i < 'F'; i++) {
            for (int j = 'A'; j < 'F'; j++) {
                System.out.print(map[i - 'A'][j - 'A'] + " ");
            }
            System.out.println();
        }
    }

    //录入地图
    public void createMap() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        input=input.substring(7);
        //Graph: AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7
        String[] strs = input.split(", ");
        for (String str : strs) {
            map[str.charAt(0) - 'A'][str.charAt(1) - 'A'] = str.charAt(2) - '0';
        }
    }

    //floyd计算最短路径
    public void floyd89() {

        for (int i = 'A'; i < 'F'; i++) {
            for (int j = 'A'; j < 'F'; j++) {
                edge[i - 'A'][j - 'A'] = map[i - 'A'][j - 'A'];
            }
        }

        for (char k = 'A'; k < 'F'; k++) {
            for (char i = 'A'; i < 'F'; i++) {
                for (char j = 'A'; j < 'F'; j++) {

                    //中间节点路径i-k,k-j可达
                    if (edge[i - 'A'][k - 'A'] != -1 && edge[k - 'A'][j - 'A'] != -1) {

                        //当i-j初始节点之间不可达
                        if (edge[i - 'A'][j - 'A'] == -1)
                            edge[i - 'A'][j - 'A'] = edge[i - 'A'][k - 'A'] + edge[k - 'A'][j - 'A'];

                        if (edge[i - 'A'][j - 'A'] > edge[i - 'A'][k - 'A'] + edge[k - 'A'][j - 'A'])
                            edge[i - 'A'][j - 'A'] = edge[i - 'A'][k - 'A'] + edge[k - 'A'][j - 'A'];
                    }

                }
            }
        }
        return;
    }

    /**
     * 计算路径的距离
     *
     * @param path
     * @return 路径长度   -1表示不可达
     */
    public int calcPathDis1_5(String path) {
        int dis = 0;

        //start node
        char star = path.charAt(0);
        for (int i = 1; i < path.length(); i++) {
            //iterate node
            char newChar = path.charAt(i);
            if (newChar == '-') {
                continue;
            }
            if (map[star - 'A'][newChar - 'A'] == -1) {
                return -1;
            } else {
                dis += map[star - 'A'][newChar - 'A'];
                star = newChar;
            }
        }
        return dis;
    }

    /**
     * 沿路径path寻找到end节点的路径数不超过最大跳数
     *
     * @param end       目标节点
     * @param path      路径
     * @param maxLength 最大跳数
     */
    public void dfs6(String end, String path, int maxLength) {
        //超出最大跳数
        if (path.length() - 1 > maxLength) {
            return;
        }

        //找到了一条路径到达end 节点
        if (path.length() > 1 && path.endsWith(end)) {
            //System.out.println(path + ", " + path.length());
            case6Cnt++;
        }

        //路径的最后一个节点
        char lastChar = path.charAt(path.length() - 1);

        int lastNodeIndex = lastChar - 'A';

        for (int i = 0; i < map[lastNodeIndex].length; i++) {
            char newChar = (char) (i + 'A');

            //路径可达
            if (map[lastNodeIndex][i] > 0) {
                //递归从下一个节点查找
                dfs6(end, path + newChar, maxLength);
            }
        }
    }

    /**
     * 寻找从start开始到end结束的路径，精确长度为 exactLength
     *
     * @param start       开始节点
     * @param end         目标节点
     * @param exactLength 路径长度
     * @return cnt 满足条件的路径数量
     */
    public int bfs7(String start, String end, int exactLength) {

        int cnt = 0;
        //当前队列元素
        String queue = start;

        //循环路径长度
        for (int step = 0; step < exactLength; step++) {
            //从当前层出发可达的节点
            String curReach = "";

            //循环队列中的节点
            for (int i = 0; i < queue.length(); i++) {

                int index = queue.charAt(i) - 'A';
                for (int j = 0; j < map[index].length; j++) {
                    //目标节点j可达
                    if (map[index][j] > 0)
                        curReach += (char) (j + 'A');
                }
            }
            //System.out.println(queue);
            //替换队列中的元素
            queue = curReach;
        }
        //System.out.println(queue);

        //统计目标节点可达数量
        for (int i = 0; i < queue.length(); i++) {
            if (end.charAt(0) == queue.charAt(i)) {
                cnt++;
            }
        }
        return cnt;
    }

    /*
     *计算从节点start到节点end的距离小于cost的路径数
     *@param end 目标节点
     *@param path 路径
     *@param cost 最大代价
     */
    public void dfs10(String end, String path, int cost) {
        //代价大于30
        if (cost >= 30) {
            return;
        }
        //找到路径且代价小于30
        if (cost > 0 && path.endsWith(end)) {
            //System.out.println(path + ", " + cost);
            case10Cnt++;
        }
        char lastChar = path.charAt(path.length() - 1);
        int lastNodeIndex = lastChar - 'A';

        for (int i = 0; i < map[lastNodeIndex].length; i++) {
            char newChar = (char) (i + 'A');

            int newCost = map[lastNodeIndex][i];
            //几点i可达
            if (newCost > 0) {
                dfs10(end, path + newChar, cost + newCost);
            }
        }
    }


    //主函数
    public static void main(String[] args) {
        Trains g = new Trains();

        //创建地图
        g.initMap();
        g.createMap();

        //计算最短路径
        g.floyd89();


        //计算路径的距离 case 1
        int dis = g.calcPathDis1_5("A-B-C");
        if (dis > 0) {
            System.out.println("Output #1:" + dis);
        } else {
            System.out.println("Output #1:NO SUCH ROUTE");
        }

        //计算路径的距离 case 2
        dis = g.calcPathDis1_5("A-D");
        if (dis > 0) {
            System.out.println("Output #2:" + dis);
        } else {
            System.out.println("Output #2:NO SUCH ROUTE");
        }


        //计算路径的距离 case 3
        dis = g.calcPathDis1_5("A-D-C");
        if (dis > 0) {
            System.out.println("Output #3:" + dis);
        } else {
            System.out.println("Output #3:NO SUCH ROUTE");
        }

        //计算路径的距离 case 4
        dis = g.calcPathDis1_5("A-E-B-C-D");
        if (dis > 0) {
            System.out.println("Output #4:" + dis);
        } else {
            System.out.println("Output #4:NO SUCH ROUTE");
        }

        //计算路径的距离 case 5
        dis = g.calcPathDis1_5("A-E-D");
        if (dis > 0) {
            System.out.println("Output #5:" + dis);
        } else {
            System.out.println("Output #5:NO SUCH ROUTE");
        }


        //计算从C到C 不超过三跳的路径数  case 6
        g.case6Cnt = 0;
        g.dfs6("C", "C", 3);
        System.out.println("Output #6:" + g.case6Cnt);


        //计算从A到C经过4跳的路径数  case 7
        System.out.println("Output #7:" + g.bfs7("A", "C", 4));


        //计算从A到C的最短路径 case 8
        System.out.println("Output #8:" + g.edge['A' - 'A']['C' - 'A']);

        //计算从B到B的最短路径 case 9
        System.out.println("Output #9:" + g.edge['B' - 'A']['B' - 'A']);

        //计算从C到C的代价不超过30的路径数 case 10
        g.case10Cnt = 0;
        g.dfs10("C", "C", 0);
        System.out.println("Output #10:" + g.case10Cnt);

    }
}
