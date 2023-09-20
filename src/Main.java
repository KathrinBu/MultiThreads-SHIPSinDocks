import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
Есть транспортные корабли, которые подплывают к туннелю и далее плывут к причалам для погрузки разного рода товара.
Они проходят через узкий туннель где одновременно могут находиться только 5 кораблей. Под словом “подплывают к туннели” имеется ввиду то, что корабли должны откуда-то появляться. Их может быть ограниченное количество, то есть 10 или 100, а может быть бесконечное множество. Слово “Подплывают” назовем генератором кораблей.
Вид кораблей и их вместительность могут быть разными в зависимости от типа товаров, которые нужно загрузить на корабль. Есть 3 Типа кораблей (Вода, Еда и Одежда) и три вида вместительности 5, 10, 15 шт. товаров. 3 типа кораблей * 3 вида вместительности = 9 разных видов кораблей.
Далее есть 3 вида причалов для погрузки кораблей — Еда, Вода и Одежда. Каждый причал берет или подзывает к себе необходимый ему корабль и начинает его загружать. За одну секунду причал загружает на корабль 1 ед. товара. То есть если у корабля вместительность 5 шт., то причал загрузит его за 5 секунд своей работы.
 */

public class Main {
    public static void main(String[] args) {
        Tunnel tunnel=new Tunnel();
        ShipGenerator shipGenerator=new ShipGenerator(tunnel,6);
        Dock dock1=new Dock(tunnel,Type.FOOD);
        Dock dock2=new Dock(tunnel, Type.CLOTHE);
        Dock dock3=new Dock(tunnel, Type.WATER);

        ExecutorService executorService= Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());//берем кол-во нитей равное кол-ву ядер процессора
        executorService.execute(shipGenerator);
        executorService.execute(dock1);
        executorService.execute(dock2);
        executorService.execute(dock3);

        executorService.shutdown();

    }
}

