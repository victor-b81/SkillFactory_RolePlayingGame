import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Trade extends Thread{
    private final Characters hero;

    // Метод торговли
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
                                "У торговца есть:\n" +
                                "зелье лечения, по цене " + pricePotionHealing + " золота\n" +
                                "-------------------------\n" +
                                "у вас " + hero.getGold() + " золота, и " + hero.getHealingPotion() + " зелья лечения \n" +
                                "-------------------------\n" +
                                "Купить зелье(yes) или Вернуться в город(no)?\n" +
                                "-------------------------\n" +
                                "Ваш ход -> ");
            try {
                String cmd = cmdReader.readLine();
                if (cmd.equals("yes")) {
                    if (hero.getGold() - pricePotionHealing >= 0) {
                        hero.setHealingPotion(hero.getHealingPotion() + 1);
                        hero.setGold(hero.getGold() - pricePotionHealing);
                        System.out.println("-------------------------\n" +
                                           "Вы преобрели зелье лечения");
                    }else {
                        System.out.println(">>>> Недостаточно золота <<<<");
                    }
                }else if (cmd.equals("no")) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
