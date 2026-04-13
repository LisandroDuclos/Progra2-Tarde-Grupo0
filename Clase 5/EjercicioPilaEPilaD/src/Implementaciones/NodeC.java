package Implementaciones;

public class NodeC {

    private int data;
    private NodeC next;

    public NodeC(int data, NodeC next) {
        this.data = data;
        this.next = next;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public NodeC getNext() {
        return next;
    }

    public void setNext(NodeC next) {
        this.next = next;
    }
}