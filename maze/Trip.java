package maze;

public class Trip {
    private  Node src;
    private  Node dest;
    public Trip(Node src,Node dest){
        this.src = src;
        this.dest = dest;
    }

    public Node getSrc(){return src;}
    public Node getDest(){return dest;}

}
