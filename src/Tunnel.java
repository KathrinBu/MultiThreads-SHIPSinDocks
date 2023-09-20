import java.util.ArrayList;
import java.util.List;

public class Tunnel {
    private List<Ship> shipList;
    private static final int maxShipsInTunel = 5;
    private static final int minShipsInTunel = 0;
    private int shipsCounter = 0;

    public Tunnel(){
        shipList=new ArrayList<>();
    }

    public synchronized boolean add(Ship ship) throws InterruptedException {
        if (shipsCounter<maxShipsInTunel){
            notifyAll();
            shipList.add(ship);
            System.out.println("ship arrived in the tunnel "+shipList.size()+ " it's type - " +
                    ship.getType() + " it's size - " +ship.getSize() +"\n" + Thread.currentThread().getName());
            shipsCounter++;
        } else {
            System.out.println(shipList.size() + " There is no place for a ship in the tunnel: " +
                    Thread.currentThread().getName());
            wait();
            return false;
        }
        return true;
    }

    public synchronized Ship get(Type type) throws InterruptedException {
    if (shipsCounter>minShipsInTunel){
        notifyAll();
        for (Ship s: shipList) {
            if (s.getType()== type){
                shipsCounter--;
                System.out.println(shipList.size()+"- this ship is taken from the tunnel to: " +
                                                   Thread.currentThread().getName());
                shipList.remove(s);
                return s;
            }
        }
    }
        System.out.println("There are no ships in the tunnel");
        wait();
        return null;
    }
}
