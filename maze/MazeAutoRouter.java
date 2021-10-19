package maze;

import java.util.LinkedList;

import com.anish.calabashbros.Calabash;
import com.anish.calabashbros.Floor;
import com.anish.calabashbros.World;

public class MazeAutoRouter {
    private int[][] maze;
    private int[][] visited;
    private Calabash bro;
    private World world;
    private LinkedList<Trip> plans;
    private LinkedList<Trip> temp;
    private boolean findSolution = false;

    public MazeAutoRouter(int[][] maze,Calabash bro,World world){
        this.maze = maze;
        this.bro = bro;
        this.world = world;
        visited = new int[maze.length][maze[0].length];
        plans = new LinkedList<>();
        temp = new LinkedList<>();
    }

    public void startAutoDrive(){
        generateDfsPlan();
    }

    private void generateDfsPlan(){
        findSolution = false;
        dfs(bro.getX(),bro.getY());
    }

    @SuppressWarnings("unchecked")
    private void dfs(int curX,int curY){
        if(curX == World.WIDTH-1 && curY == World.HEIGHT - 1){
            if(!findSolution){
                plans = (LinkedList<Trip>) temp.clone();
                findSolution = true;
            }
            return;
        }
        int[] dx = {1,-1,0,0};
        int[] dy = {0,0,1,-1};
        for(int i = 0;i < 4; i ++){
            int x = curX + dx[i],y = curY + dy[i];
            if(x >= 0 && y >= 0 && x < World.WIDTH && y < World.HEIGHT && maze[x][y] == 1 && visited[x][y] == 0){
                Trip trip = new Trip(new Node(curX,curY), new Node(x,y));
                temp.add(trip);
                visited[x][y] = 1;
                dfs(x,y);
                visited[x][y] = 0;
                temp.remove(trip);
            }
        }
    }
    //return true if reach exit
    public boolean execute(){
        Trip curTrip = plans.poll();
        Node dest = curTrip.getDest();
        Node src = curTrip.getSrc();
        bro.moveTo(dest.x, dest.y);
        world.put(new Floor(world), src.x, src.y);
        if(dest.x == World.WIDTH-1 && dest.y == World.HEIGHT - 1)return true;
        else return false;
    }

}
