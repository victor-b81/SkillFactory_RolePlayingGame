public abstract class Characters implements shouldAttack {

    // name - ИМЯ; health - ЗДОРОВЬЕ (ЕДИНИЦЫ ЖИЗНИ); gold - ЗОЛОТО; skill - ЛОВКОСТЬ; experience - ОПЫТ; brunt - СИЛА
    private final String name;
    private int health, gold, skill, experience, brunt, amountHit=0, lvl=0, healingPotion;

    public Characters(String name, int health, int brunt, int skill, int experience, int gold, int healingPotion) {
        this.healingPotion = healingPotion;
        this.name = name;
        this.health = health;
        this.gold = gold;
        this.skill = skill;
        this.experience = experience;
        this.brunt = brunt;
    }

    public Characters(String name, int health, int brunt, int skill, int experience, int gold) {
        this.name = name;
        this.health = health;
        this.gold = gold;
        this.skill = skill;
        this.experience = experience;
        this.brunt = brunt;
    }

    //метод атаки героя
    public int attack(){
        if (skill * 3 >= getRnd()) {                   //Определение промахнулся ли герой
            amountHit++;                               //Счетчик количества атак (если значение больше рёх, то герой может сделать критический удар)
            if (getRnd() + skill >= 60) {              //Условие критического удара
                amountHit = 0;
                return 2 * brunt;
            }
            return (brunt);
        } return 0;
    }

    //Повышение уровня героя
    public void upLevel(){
        experience++;
        if (experience%10 == 1){
            System.out.println(">>>>>> Уровень повышен <<<<<<");
            lvl++;
            skill++;
            brunt+=2;
        }
    }

    //Порсонаж получил повреждения
    public String gotDamage(int damage){
        if (health - damage<=0) {
            health=0;
            return "dead";                      //персонаж погиб
        }else {
            health -= damage;
        }
        return "damage";
    }


    // Генератор случайных чистел от 0 до 100
    private int getRnd(){
        return ((int) (Math.random() * 100));
    }

    public int getHealth() {
        return health;
    }


    //персонаж лечится
    public void healing (int healing){
        if (getHealingPotion()>0 && getHealth()+healing<=100){
            setHealingPotion(getHealingPotion()-1);
            setHealth(getHealth()+healing);
            System.out.println("Вы восстановили " + healing + " здоровья. Теперь у вас " + getHealth() + " здоровья.");
        } else if (getHealingPotion()>0 && getHealth()+healing>100){
            setHealingPotion(getHealingPotion()-1);
            setHealth(100);
            System.out.println("Вы восстановили все здоровье, теперь у вас " + getHealth() + " здоровья.");
        } else {
            System.out.println("У героя нет зелья лечения");
        }
    }

    public String getName() {
        return name;
    }

    public int getGold() {
        return gold;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getHealingPotion() {
        return healingPotion;
    }

    public void setHealingPotion(int healingPotion) {
        this.healingPotion = healingPotion;
    }

    public int getBrunt() {
        return brunt;
    }

    public int getSkill() {
        return skill;
    }

    public int getExperience() {
        return experience;
    }

    public int getLvl() {
        return lvl;
    }
}
