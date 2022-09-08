import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Trade extends Thread{
    private final Characters hero;

    // ����� ��������
    public Trade(Characters hero) {
        this.hero = hero;
    }

    @Override
    public void run(){
        int pricePotionHealing = 2;
        BufferedReader cmdReader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.print( "-------------------------\n" +
                                "-------------------------\n" +
                                "� �������� ����:\n" +
                                "����� �������, �� ���� " + pricePotionHealing + " ������\n" +
                                "-------------------------\n" +
                                "� ��� " + hero.getGold() + " ������, � " + hero.getHealingPotion() + " ����� ������� \n" +
                                "-------------------------\n" +
                                "������ �����(yes) ��� ��������� � �����(no)?\n" +
                                "-------------------------\n" +
                                "��� ��� -> ");
            try {
                String cmd = cmdReader.readLine();
                if (cmd.equals("yes")) {
                    if (hero.getGold() - pricePotionHealing >= 0) {
                        hero.setHealingPotion(hero.getHealingPotion() + 1);
                        hero.setGold(hero.getGold() - pricePotionHealing);
                        System.out.println("-------------------------\n" +
                                           "�� ��������� ����� �������");
                    }else {
                        System.out.println(">>>> ������������ ������ <<<<");
                    }
                }else if (cmd.equals("no")) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
