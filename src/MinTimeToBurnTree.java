import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
        left = null;
        right = null;
    }
}

public class MinTimeToBurnTree {
    Map<TreeNode, TreeNode> parentMap = new HashMap();

    public int solve(TreeNode A, int B) {
        TreeNode source = getSourceNode(A, B);
        int totalTime = 0;
        Queue<TreeNode> q = new LinkedList();
        HashMap<TreeNode, Integer> visited = new HashMap();
        q.add(source);
        while (!q.isEmpty()) {
            int size = q.size();
            boolean isBurnt = false;
            while (size-- > 0) {
                TreeNode curr = q.poll();
                if (curr.left != null && visited.get(curr.left) == null) {
                    isBurnt = true;
                    q.add(curr.left);
                    visited.put(curr.left, 1);

                }
                if (curr.right != null && visited.get(curr.right) == null) {
                    isBurnt = true;
                    q.add(curr.right);
                    visited.put(curr.right, 1);
                }
                if (parentMap.containsKey(curr) && visited.get(parentMap.get(curr)) == null) {
                    isBurnt = true;
                    q.add(parentMap.get(curr));
                    visited.put(parentMap.get(curr), 1);
                    parentMap.remove(curr);
                }
            }
            if (isBurnt) totalTime++;
        }
        return totalTime;
    }

    private TreeNode getSourceNode(TreeNode root, int target) {
        if (root == null)
            return null;

        Queue<TreeNode> q = new LinkedList();
        q.add(root);
        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                TreeNode curr = q.poll();
                if (curr.val == target)
                    return curr;
                if (curr.left != null) {
                    q.add(curr.left);
                    parentMap.put(curr.left, curr);
                }
                if (curr.right != null) {
                    q.add(curr.right);
                    parentMap.put(curr.right, curr);
                }
            }
        }
        return null;
    }
}
