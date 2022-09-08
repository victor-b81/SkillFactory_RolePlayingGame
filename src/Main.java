import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader cmdReader = new BufferedReader(new InputStreamReader(System.in));
    private static Characters hero = null;
    static String cmd = "";

    //Метод темный лес
    private static void blackForest() throws IOException {
        Battle battle = new Battle(hero, rndMonster());
        battle.start();
        try {
            battle.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if (hero.getHealth()!=0) {
            do {
                System.out.print("""
                        --------------------------
                        1. Продолжить воевать в лесу / 2. Вернуться в город.
                        --------------------------
                        Ваш ход ->\s""");
                cmd = cmdReader.readLine();
                if (cmd.equals("1")) {
                    blackForest();
                } else if (!cmd.equals("2")) {
                    System.out.print("Сделайте выбор 1 или 2\nВаш ход -> ");
                }
            } while (!cmd.equals("2") || hero.getHealth() == 0);
        }
    }

    //Метод создаем случайного монстра
    private static Characters rndMonster(){
        int rnd = (int) (Math.random() * 10);
        if (rnd % 2 == 0) return new Skelet("Скелет",100,20,20,100,10);
        else return new Goblin("Гоблин",100,10,10,100,20);
    }

    //Метод торговец
    private static void store() {
        Trade trade = new Trade(hero);
        trade.start();
        try {
            trade.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws IOException {
        System.out.print("Введите имя персонажа: ");
        String nameHero = cmdReader.readLine();

        hero = new Hero(nameHero, 100, 20, 20, 0, 0, 1);

        System.out.printf("%s, приветствую в городе!%n", nameHero);
        do {
            if (hero.getHealth() != 0){
                System.out.print("""
                    --------------------------
                    --------------------------
                    Выберете, что будете делать
                    1. Идем к торговцу
                    2. Идем в тёмный лес
                    3. Выходим из игры
                    --------------------------
                    """);
                System.out.print("У героя сейчас " + hero.getBrunt() + " силы, " +
                                    hero.getSkill() + " ловкости, " + hero.getExperience() + " опыта, " +
                                    hero.getLvl() + " lvl, " + hero.getGold() + " золота, " + hero.getHealingPotion() +
                                    " зелья лечения.\n" +
                                    "--------------------------\n" +
                                    "Ваш ход -> ");
                cmd = cmdReader.readLine();

                if (cmd.equals("1")) {
                    store();
                } else if (cmd.equals("2")) {
                    blackForest();
                } else if (!cmd.equals("3")) {
                    System.out.println("Сделайте выбор 1, 2 или 3");
                }
            } else {
                System.out.println(hero.getName() + " погиб!\nИгра окончена.");
                cmd = "3";
            }
        } while (!cmd.equals("3"));
    }
}