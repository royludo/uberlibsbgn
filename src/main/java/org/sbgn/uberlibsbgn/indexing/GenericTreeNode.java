package org.sbgn.uberlibsbgn.indexing;

import java.util.ArrayList;
import java.util.List;

public class GenericTreeNode<T> {

    private T userObject;
    private GenericTreeNode<T> parent;
    private List<GenericTreeNode<T>> children;

    public GenericTreeNode(T userObject) {
        this.userObject = userObject;
        this.children = new ArrayList<>();
    }

    public void setParent(GenericTreeNode<T> node) {
        this.parent = node;
    }

    public void addChild(GenericTreeNode<T> node) {
        this.children.add(node);
    }


}
