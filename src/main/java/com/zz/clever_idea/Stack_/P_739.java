package com.zz.clever_idea.Stack_;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/daily-temperatures/
 * 请根据每日 气温 列表，重新生成一个列表。对应位置的输出为：
 * 要想观测到更高的气温，至少需要等待的天数。如果气温在这之后都不会升高，请在该位置用 0 来代替。
 * 例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，
 * 你的输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]。
 * 提示：气温 列表长度的范围是 [1, 30000]。每个气温的值的均为华氏度，都是在 [30, 100] 范围内的整数。
 *
 *
 */
public class P_739 {
    /**
     * 保存每个元素的小标位置，通过i计算大于当前元素的最近距离
     * 单调栈
     * @param T
     * @return
     */
    public int[] dailyTemperatures(int[] T) {
        Deque<Integer> dq = new ArrayDeque<>();
        Stack<Integer> idx = new Stack<>();
        int[] ans = new int[T.length];
        for (int i = 0;i<T.length;i++){
            while (!dq.isEmpty() && dq.peekLast()<T[i]){
                int t = idx.pop();
                ans[t] = i-t;
                dq.pollLast();
            }
            dq.add(T[i]);
            idx.add(i);
        }
        while (!idx.isEmpty()){
            ans[idx.pop()] = 0;
        }
        return ans;
    }
}
