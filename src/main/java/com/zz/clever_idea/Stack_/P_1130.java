package com.zz.clever_idea.Stack_;

import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/minimum-cost-tree-from-leaf-values/
 *
 * 给你一个正整数数组 arr，考虑所有满足以下条件的二叉树：
 *
 * 每个节点都有 0 个或是 2 个子节点。
 * 数组 arr 中的值与树的中序遍历中每个叶节点的值一一对应。
 * （知识回顾：如果一个节点有 0 个子节点，那么该节点为叶节点。）
 * 每个非叶节点的值等于其左子树和右子树中叶节点的最大值的乘积。
 * 在所有这样的二叉树中，返回每个非叶节点的值的最小可能总和。这个和的值是一个 32 位整数。
 *
 * 示例：
 * 输入：arr = [6,2,4]
 * 输出：32
 * 解释：
 * 有两种可能的树，第一种的非叶节点的总和为 36，第二种非叶节点的总和为 32。
 *
 *     24            24
 *    /  \          /  \
 *   12   4        6    8
 *  /  \               / \
 * 6    2             2   4
 *  
 *
 * 提示：
 *
 * 2 <= arr.length <= 40
 * 1 <= arr[i] <= 15
 * 答案保证是一个 32 位带符号整数，即小于 2^31
 *
 *
 */
public class P_1130 {

    /**
     * 1.因为要满足题意数组对应中序遍历叶子节点的值
     * 2.有要让最终非叶子节点和最大
     * 3.非叶子节点的的计算规则是 左右子树叶子节点最大相乘
     *
     * 那必须要让值最小的叶子节点在最下面
     * 6 2 3 4 1 2
     * 【MAX 6 2】 3  -> 2,3 合并后把较大的值放进数组
     *    6
     *  /   \
     * 2     3
     *
     * 【MAX 6 3】 4 -> 3,4 合并后把较大的值放进数组
     * 2,3构建好后，【要选下一个左右两边最小的来构建，或者选择其他更小的一对来构建但必须要符合中序遍历】
     *      12
     *     /  \
     *    6    4
     *   / \
     *  2  3
     *  【MAX 6 4】 1
     *  【MAX 6 4 1】 2 -> 1,2 合并后把较大的值放进数组
     * 3,4构建好后,选 1,2
     *      12       2
     *     /  \     / \
     *    6    4   1  2
     *   / \
     *  2  3
     *  【MAX 6 4 2】
     *  【MAX 6 】 -> 4,2 合并后把较大的值放进数组
     *  4,2构建
     *          8
     *       /    \
     *      12     2
     *     /  \   / \
     *    6    4 1  2
     *   / \
     *  2  3
     *  【MAX 6 4】 遍历完元素后，把数组里的元素弹完
     *  【MAX】 -> 6,4
     *  6,4构建
     *       24
     *      /  \
     *     6   8
     *       /   \
     *      12    2
     *     /  \  / \
     *    6   4 1  2
     *   / \
     *  2  3
     *
     * 【MAX】 在遍历过程，刚好满足单调栈
     *
     * @param arr
     * @return
     */
    public int mctFromLeafValues(int[] arr) {
        Stack<Integer> st = new Stack<>();
        st.add(Integer.MAX_VALUE);
        int ans = 0;
        for(int i = 0;i<arr.length;i++){
            while(arr[i] >= st.peek()){
                                // 判断是乘左边还是又边
                ans += st.pop()*Math.min(st.peek(),arr[i]);
            }
            st.add(arr[i]);
        }
        while (st.size()>2){
            ans += st.pop()*st.peek();
        }
        return ans;
    }

    public int mctFromLeafValues2(int[] arr) {
        int ans = 0;
        for (int i = 0,j = arr.length-1;i!=j;){
            if (arr[i]>=arr[j]){
                ans += arr[i]*getMax(arr,i+1,j);
                i++;
            }else{
                ans += arr[j]*getMax(arr,i,j-1);
                j--;
            }
        }
        return ans;
    }

    private int getMax(int[] arr, int i, int j) {
        int maxx = Integer.MIN_VALUE;
        for(int l = i;l<=j;l++){
            maxx = Math.max(arr[l],maxx);
        }
        return maxx;
    }
}
