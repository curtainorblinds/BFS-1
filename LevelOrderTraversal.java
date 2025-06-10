import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Leetcode 102. Binary Tree Level Order Traversal
 * Link: https://leetcode.com/problems/binary-tree-level-order-traversal/description/
 */
//------------------------------------ Solution 1 -----------------------------------
public class LevelOrderTraversal {
    /**
     * Using BFS - Take a queue to maintain order of processing in FIFO starting with root.
     * Process queue level by level and keep adding to the children once its processed
     *
     * TC: O(n) SC: O(n) as maximum numbers of nodes in the worst case will be for a complete binary
     * tree where leaf nodes are n/2
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        Queue<TreeNode> nodeQueue = new LinkedList<>();

        if (root == null) {
            return result;
        }

        nodeQueue.add(root);

        while (!nodeQueue.isEmpty()) {
            int size = nodeQueue.size();
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode curr = nodeQueue.poll();
                level.add(curr.val);

                if (curr.left != null) {
                    nodeQueue.add(curr.left);
                }
                if (curr.right != null) {
                    nodeQueue.add(curr.right);
                }
            }
            result .add(level);
        }
        return result;
    }
}

//------------------------------------ Solution 2 -----------------------------------
class LevelOrderTraversal2 {
    /**
     * Using DFS - void based recursion with result passed as reference inside recursive call function
     * along with current level of the node. As levels are 0,1 and onwards we can add value of a node
     * directly inside the result list. Key idea is to add the inner level list in the result list of list
     * at pre-order level. actually adding the value to it can be done at either pre, in or post order traversal.
     *
     * TC: O(n) Auxiliary SC: O(1)
     * Recursive stack space: O(h) h -> height of tree, worst case O(n), complete BST O(log n)
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        dfs(root, 0, result);
        return result;
    }

    private void dfs(TreeNode curr, int level, List<List<Integer>> result) {
        //base
        if (curr == null) {
            return;
        }

        //logic
        if (level == result.size()) {
            result.add(new ArrayList<>());
        }
        result.get(level).add(curr.val);

        dfs(curr.left, level + 1, result);
        dfs(curr.right, level + 1, result);
    }
}
