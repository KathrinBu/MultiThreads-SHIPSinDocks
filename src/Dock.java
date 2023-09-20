public class Dock implements Runnable{
    private Tunnel tunnel;
    private Type shipType;

    public Dock(Tunnel tunnel, Type shipType) {
        this.tunnel = tunnel;
        this.shipType = shipType;
    }

    @Override
    public void run() {
        while (true){
            try {
            Thread.currentThread().setName("on Load type - "+ shipType);
                Thread.sleep(500);
                Ship ship=tunnel.get(shipType);
                if (ship !=null)
                    while (ship.countCheck()){
                        Thread.sleep(100);
                        ship.add(1);
                        System.out.println(ship.getCount()+" the ship is loading..."+Thread.currentThread().getName());
                    }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
