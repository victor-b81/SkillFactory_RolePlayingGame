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
    //��������� ����� ����, � ��������� ���� ��� ���
    private final doHit dHit = (Characters attackingChar, Characters attackedChar) -> {
        int attack = attackingChar.attack();
        if (attack == 0) {
            System.out.println(attackingChar.getName() + " �����������!");
            return false;
        }
        System.out.println(attackingChar.getName() + " ����� �����������: " + attack);
        return attackedChar.gotDamage(attack).equals("dead");
    };


    // ����� ���!
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
                        "� " + hero.getName() +
                        ", ��������: " + hero.getHealth() +
                        ", ����� �������: " + hero.getHealingPotion() +
                        ", ������: " + hero.getGold() + "\n" +
                        "� " + monster.getName() + ", ��������: " + monster.getHealth() + "\n" +
                        "--- 1. ��������� / 2. ������������ ����� (+30 � ��������) / 3. ���� � �����\n" +
                        "-------------------------\n" +
                        "��� ��� -> ");
                        String cmd = cmdReader.readLine();
                        System.out.println("-------------------------");
                        switch (cmd){
                            case "1" -> endFight = dHit.hit(hero, monster);
                            case "2" -> {
                                hero.healing(30);
                                whoMove = "Hero";
                            }
                            case "3" -> {
                                return "������ � �����";
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
        return whoWin + " �������!";
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
