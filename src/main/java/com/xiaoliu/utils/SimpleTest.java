package com.xiaoliu.utils;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * 你正在使用一堆木板建造跳水板。有两种类型的木板，其中长度较短的木板长度为shorter，长度较长的木板长度为longer。你必须正好使用k块木板。编写一个方法，生成跳水板所有可能的长度。
 *
 * 返回的长度需要从小到大排列。
 *
 * 示例：
 *
 * 输入：
 * shorter = 1
 * longer = 2
 * k = 3
 * 输出： {3,4,5,6}
 * 提示：
 *
 * 0 < shorter <= longer
 * 0 <= k <= 100000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/diving-board-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SimpleTest {
    static int shorter = 1;
    static int longer = 3;
    static Set<Integer> set = new HashSet<>();
    public static  int froot(int i,int j,int k){
        if(k == 0) {
            set.add(i);
            set.add(j);
            return 0;
        };
         k--;
        froot(i+shorter,j+longer,k);
        froot(i+longer,j+shorter,k);
        froot(i+longer,j+longer,k);
        froot(i+shorter,j+shorter,k);

        return 0;
    }
    public static void main(String[] args) {
         int k = 3 ;
         froot(0,0,k) ;
         set.forEach((y)-> System.out.println(y));
     }
}
