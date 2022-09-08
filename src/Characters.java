public abstract class Characters implements shouldAttack {

    // name - ���; health - �������� (������� �����); gold - ������; skill - ��������; experience - ����; brunt - ����
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

    //����� ����� �����
    public int attack(){
        if (skill * 3 >= getRnd()) {                   //����������� ����������� �� �����
            amountHit++;                               //������� ���������� ���� (���� �������� ������ ��, �� ����� ����� ������� ����������� ����)
            if (getRnd() + skill >= 60) {              //������� ������������ �����
                amountHit = 0;
                return 2 * brunt;
            }
            return (brunt);
        } return 0;
    }

    //��������� ������ �����
    public void upLevel(){
        experience++;
        if (experience%10 == 1){
            System.out.println(">>>>>> ������� ������� <<<<<<");
            lvl++;
            skill++;
            brunt+=2;
        }
    }

    //�������� ������� �����������
    public String gotDamage(int damage){
        if (health - damage<=0) {
            health=0;
            return "dead";                      //�������� �����
        }else {
            health -= damage;
        }
        return "damage";
    }


    // ��������� ��������� ������ �� 0 �� 100
    private int getRnd(){
        return ((int) (Math.random() * 100));
    }

    public int getHealth() {
        return health;
    }


    //�������� �������
    public void healing (int healing){
        if (getHealingPotion()>0 && getHealth()+healing<=100){
            setHealingPotion(getHealingPotion()-1);
            setHealth(getHealth()+healing);
            System.out.println("�� ������������ " + healing + " ��������. ������ � ��� " + getHealth() + " ��������.");
        } else if (getHealingPotion()>0 && getHealth()+healing>100){
            setHealingPotion(getHealingPotion()-1);
            setHealth(100);
            System.out.println("�� ������������ ��� ��������, ������ � ��� " + getHealth() + " ��������.");
        } else {
            System.out.println("� ����� ��� ����� �������");
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
