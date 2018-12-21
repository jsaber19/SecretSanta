import java.awt.*;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static java.awt.Desktop.getDesktop;

public class SantaList implements Iterable<Object>{
    private ListNode head, tail;
    public int size;

    public SantaList(ArrayList<ListNode> santas){
        size = santas.size();
        if (santas.size()<1){
            throw new NoSuchElementException();
        }
        if (santas.size() == 1){
            head = santas.remove(0);
        }
        if (santas.size() == 2){
            head = santas.remove(0);
            tail = santas.remove(0);
            head.setNext(tail);
            tail.setPrevious(head);
        }
        if (santas.size() > 2){
            head = santas.remove(0);
            tail = santas.remove(santas.size()-1);
            tail.setPrevious(head);

            while(!santas.isEmpty()){
                ListNode temp = santas.remove(0);
                temp.setPrevious(tail.getPrevious());
                tail.getPrevious().setNext(temp);
                temp.setNext(tail);
                tail.setPrevious(temp);
            }
        }
    }

    public void generateSantas(){
        shuffle();

        String SantasAssignments = "";


        for (ListNode node = head; node!= null; node = node.getNext()){
            if (node.getNext() == null){
                SantasAssignments += node.getValue() + " is giving to " + head.getValue() + ".\n";
            }
            else {
                SantasAssignments += node.getValue() + " is giving to " + node.getNext().getValue() + ".\n";
            }
        }

        try
        {
            PrintWriter writer = new PrintWriter("santa-assignments.txt", "UTF-8");
            writer.println(SantasAssignments);
            writer.close();


        } catch (IOException ex){

        }
    }

    public void shuffle(){
        ArrayList temp = new ArrayList(size);
        for (ListNode node = head; node != null; node = node.getNext()){
            temp.add(node);
        }


        if (size > 2) {
            ListNode newHead = null;
            ListNode newTail = null;

            newHead = (ListNode)temp.remove((int)(Math.random()*temp.size()));
            newTail = (ListNode)temp.remove((int)(Math.random()*temp.size()));
            newHead.setNext(newTail);
            newTail.setPrevious(newHead);

            while(!temp.isEmpty()){
                ListNode curr = (ListNode)temp.remove((int)(Math.random()*temp.size()));
                curr.setPrevious(newTail.getPrevious());
                newTail.getPrevious().setNext(curr);
                curr.setNext(newTail);
                newTail.setPrevious(curr);
            }

            head = newHead;
            tail = newTail;
            tail.setNext(null);
        }

        else if (size == 1){
            head = (ListNode)temp.remove(0);
            tail = head;
        }
        else if (size == 2){
            head = (ListNode)temp.remove(1);
            tail = (ListNode)temp.remove(0);
            head.setNext(tail);
            tail.setPrevious(head);
            tail.setNext(null);
        }

    }

    // what austin showed us in class and https://stackoverflow.com/questions/4737841/urlencoder-not-able-to-translate-space-character
    public void sendEmails() throws IOException, URISyntaxException {
        Desktop desktop = getDesktop();

        for (ListNode node = head; node != null; node = node.getNext()){
            if (node.getNext() == null){
                String to = URLEncoder.encode(node.getEmail(), "UTF-8");
                String subject = URLEncoder.encode("Your Secret Santa Assignment", "UTF-8").replace("+", "%20");
                String body = URLEncoder.encode("Please send a gift to " + head.getValue() + ".", "UTF-8").replace("+", "%20");

                String uriString = String.format("mailto:%s?subject=%s&body=%s", to, subject, body);
                desktop.mail(new URI(uriString));
            }
            else {
                String to = URLEncoder.encode(node.getEmail(), "UTF-8");
                String subject = URLEncoder.encode("Your Secret Santa Assignment", "UTF-8").replace("+", "%20");
                String body = URLEncoder.encode("Please send a gift to " + node.getNext().getValue() + ".", "UTF-8").replace("+", "%20");

                String uriString = String.format("mailto:%s?subject=%s&body=%s", to, subject, body);
                desktop.mail(new URI(uriString));
            }

        }
    }

    public Iterator<Object> iterator() {
        return new DLLIterator(head);
    }

    private class DLLIterator implements Iterator<Object>{
        private ListNode nextNode;

        public DLLIterator(ListNode head){
            nextNode = head;
        }

        public boolean hasNext(){
            return nextNode != null;
        }

        public Object next(){
            if(nextNode == null){
                throw new NoSuchElementException();
            }
            Object value = nextNode.getValue();
            nextNode = nextNode.getNext();
            return value;
        }
    }

    public void setHead(ListNode head){
        this.head = head;
    }

    public void setTail(ListNode tail){
        this.tail = tail;
    }
}
