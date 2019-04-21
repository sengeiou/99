package pub.types;

import java.io.Serializable;
import java.util.List;
 
public class TreeNode implements Serializable {
 
	private static final long serialVersionUID = 8456843451921058144L;
	private Object id;
	private String text;
	private Boolean leaf;
	private List<TreeNode> children;

	public TreeNode() {

	}

	public TreeNode(Object id, String text) {
		this(id, text, true);
	}

	public TreeNode(Object id, String text, boolean leaf) {
		this.id = id;
		this.text = text;
		this.leaf = leaf;
	}

	public Object getId() {
		return id;
	}
	public void setId(Object id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Boolean getLeaf() {
		return leaf;
	}
	public void setLeaf(Boolean leaf) {
		this.leaf = leaf;
	}
	public List<TreeNode> getChildren() {
		return children;
	}
	public void setChildren(List<TreeNode> children) {
		this.children = children;
		this.leaf = (children != null && children.size() > 0)? null: true;
	}
}
