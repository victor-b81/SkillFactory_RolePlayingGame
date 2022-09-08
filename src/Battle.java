import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@FunctionalInterface
interface doFight {
    String fight() throws InterruptedException, IOException;
}

@FunctionalInterface
interface doHit {
    boolean hit(Characters attackingChar, Characters attackedChar);
}

public class Battle extends Thread{
    Characters hero;
    Characters monster;

    public Battle(Characters hero, Characters monster) {
        this.hero = hero;
        this.monster = monster;
    }
    //Выполняем одимн удар, и проверяем удил или нет
    private final doHit dHit = (Characters attackingChar, Characters attackedChar) -> {
        int attack = attackingChar.attack();
        if (attack == 0) {
            System.out.println(attackingChar.getName() + " промахнулся!");
            return false;
        }
        System.out.println(attackingChar.getName() + " нанес повреждения: " + attack);
        return attackedChar.gotDamage(attack).equals("dead");
    };


    // Метод боя!
    doFight dFight = () -> {
        String whoMove = "Hero";
        String whoWin = null;
        boolean endFight = false;
        BufferedReader cmdReader;
        cmdReader = new BufferedReader(new InputStreamReader(System.in));
        while (!endFight){
            if (whoMove.equals("Hero")){
                whoMove = "Monster";
                System.out.print("-------------------------\n" +
                        "У " + hero.getName() +
                        ", здоровья: " + hero.getHealth() +
                        ", зелья лечения: " + hero.getHealingPotion() +
                        ", золота: " + hero.getGold() + "\n" +
                        "У " + monster.getName() + ", здоровья: " + monster.getHealth() + "\n" +
                        "--- 1. Атаковать / 2. Использовать зелье (+30 к здоровью) / 3. Уйти в город\n" +
                        "-------------------------\n" +
                        "Ваш ход -> ");
                        String cmd = cmdReader.readLine();
                        System.out.println("-------------------------");
                        switch (cmd){
                            case "1" -> endFight = dHit.hit(hero, monster);
                            case "2" -> {
                                hero.healing(30);
                                whoMove = "Hero";
                            }
                            case "3" -> {
                                return "Уходим в город";
                            }
                        }
                   if (endFight) {
                       whoWin = hero.getName();
                       hero.setHealth(100);
                       hero.setGold(monster.getGold());
                       hero.upLevel();
                   }
            }else {
                endFight = dHit.hit(monster, hero);
                    if (endFight) whoWin = monster.getName();
                    whoMove = "Hero";
            }
            Thread.sleep(1000);
        }
        return whoWin + " победил!";
    };

    @Override
    public void run() {
        try {
            System.out.println("--------------------------");
            System.out.println(dFight.fight());
        } catch (InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
