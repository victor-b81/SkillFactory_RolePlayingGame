import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader cmdReader = new BufferedReader(new InputStreamReader(System.in));
    private static Characters hero = null;
    static String cmd = "";

    //����� ������ ���
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
                        1. ���������� ������� � ���� / 2. ��������� � �����.
                        --------------------------
                        ��� ��� ->\s""");
                cmd = cmdReader.readLine();
                if (cmd.equals("1")) {
                    blackForest();
                } else if (!cmd.equals("2")) {
                    System.out.print("�������� ����� 1 ��� 2\n��� ��� -> ");
                }
            } while (!cmd.equals("2") || hero.getHealth() == 0);
        }
    }

    //����� ������� ���������� �������
    private static Characters rndMonster(){
        int rnd = (int) (Math.random() * 10);
        if (rnd % 2 == 0) return new Skelet("������",100,20,20,100,10);
        else return new Goblin("������",100,10,10,100,20);
    }

    //����� ��������
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
        System.out.print("������� ��� ���������: ");
        String nameHero = cmdReader.readLine();

        hero = new Hero(nameHero, 100, 20, 20, 0, 0, 1);

        System.out.printf("%s, ����������� � ������!%n", nameHero);
        do {
            if (hero.getHealth() != 0){
                System.out.print("""
                    --------------------------
                    --------------------------
                    ��������, ��� ������ ������
                    1. ���� � ��������
                    2. ���� � ����� ���
                    3. ������� �� ����
                    --------------------------
                    """);
                System.out.print("� ����� ������ " + hero.getBrunt() + " ����, " +
                                    hero.getSkill() + " ��������, " + hero.getExperience() + " �����, " +
                                    hero.getLvl() + " lvl, " + hero.getGold() + " ������, " + hero.getHealingPotion() +
                                    " ����� �������.\n" +
                                    "--------------------------\n" +
                                    "��� ��� -> ");
                cmd = cmdReader.readLine();

                if (cmd.equals("1")) {
                    store();
                } else if (cmd.equals("2")) {
                    blackForest();
                } else if (!cmd.equals("3")) {
                    System.out.println("�������� ����� 1, 2 ��� 3");
                }
            } else {
                System.out.println(hero.getName() + " �����!\n���� ��������.");
                cmd = "3";
            }
        } while (!cmd.equals("3"));
    }
}