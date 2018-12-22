public class ListNode {
    // fields for next and previous nodes and name and email
    private Object value;
    private ListNode previous;
    private ListNode next;
    private String email;

    // constructors
    public ListNode(Object v, String e){
        value = v;
        email = e;
        previous = null;
        next = null;
    }

    public ListNode(Object v, ListNode pr, ListNode nx, String e){
        value = v;
        previous = pr;
        next = nx;
        email = e;
    }

    // getters
    public Object getValue() {
        return value;
    }

    public ListNode getPrevious() {
        return previous;
    }

    public ListNode getNext() {
        return next;
    }

    public String getEmail() {
        return email;
    }

    // setters
    public void setValue(Object value) {
        this.value = value;
    }

    public void setPrevious(ListNode previous) {
        this.previous = previous;
    }

    public void setNext(ListNode next) {
        this.next = next;
    }

    public void setEmail(String e) {
        email = e;
    }

}
