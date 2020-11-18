package com.zz.clever_idea.Linked_List;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode-cn.com/problems/linked-list-components/
 * 给定链表头结点 head，该链表上的每个结点都有一个 唯一的整型值 。
 * 同时给定列表 G，该列表是上述链表中整型值的一个子集。
 * 返回列表 G 中组件的个数，这里对组件的定义为：
 * 链表中一段最长连续结点的值（该值必须在列表 G 中）构成的集合。
 *
 * 示例 1：
 * 输入:
 * head: 0->1->2->3
 * G = [0, 1, 3]
 * 输出: 2
 * 解释:
 * 链表中,0 和 1 是相连接的，且 G 中不包含 2，所以 [0, 1] 是 G 的一个组件，
 * 同理 [3] 也是一个组件，故返回 2。
 *
 * 提示：
 * 如果 N 是给定链表 head 的长度，1 <= N <= 10000。
 * 链表中每个结点的值所在范围为 [0, N - 1]。
 * 1 <= G.length <= 10000
 * G 是链表中所有结点的值的一个子集.
 *
 * 并查集也可做
 *
 */
public class P_817 {
    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }
    public int numComponents(ListNode head, int[] G) {
        int[] tmp = new int[10001];
        for(int i = 0;i<G.length;i++){
            tmp[G[i]]++;
        }
        int ans = 0;
        int flag = 0;
        while(head!=null){
            flag = 0;
            while(head != null && tmp[head.val]>0){
                flag = 1;
                head = head.next;
            }
            if (flag == 1)
                ans++;
            head = head == null?null:head.next;
        }
        return ans;
    }

    // 较为优雅代码
    public int numComponents2(ListNode head, int[] G) {
        Set<Integer> Gset = new HashSet<>();
        for(int i:G)Gset.add(i);
        int ans = 0;
        while(head != null){
            if(Gset.contains(head.val) &&(head.next == null||!Gset.contains(head.next.val))){
                ans++;
            }
            head = head.next;
        }
        return ans;
    }
}