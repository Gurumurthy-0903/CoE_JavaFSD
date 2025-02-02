//Task_8
class TreeNode
{
    int val;
    TreeNode left;
    TreeNode right;
    
    TreeNode(int val)
    {
        this.val = val;
        this.left = null;
        this.right = null;
    }
}

class BinaryTreeSerialization
{
    public String serialize(TreeNode root)
    {
        StringBuilder sb = new StringBuilder();
        serializeHelper(root, sb);
        return sb.toString();
    }

    private void serializeHelper(TreeNode root, StringBuilder sb)
    {
        if (root == null)
        {
            sb.append("null,");
        }
        else
        {
            sb.append(root.val).append(",");
            serializeHelper(root.left, sb);
            serializeHelper(root.right, sb);
        }
    }

    public TreeNode deserialize(String data)
    {
        String[] nodes = data.split(",");
        int[] index = {0};
        return deserializeHelper(nodes, index);
    }

    private TreeNode deserializeHelper(String[] nodes, int[] index)
    {
        if (index[0] >= nodes.length || nodes[index[0]].equals("null"))
        {
            index[0]++;
            return null;
        }
        TreeNode node = new TreeNode(Integer.parseInt(nodes[index[0]++]));
        node.left = deserializeHelper(nodes, index);
        node.right = deserializeHelper(nodes, index);
        return node;
    }
}

public class Task_8
{
    public static void main(String[] args)
    {
        BinaryTreeSerialization serializer = new BinaryTreeSerialization();

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        String serializedData = serializer.serialize(root);
        System.out.println("Serialized tree: " + serializedData);

        TreeNode deserializedRoot = serializer.deserialize(serializedData);
        System.out.println("Deserialized tree root value: " + deserializedRoot.val);
    }
}
