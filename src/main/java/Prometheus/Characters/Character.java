package Prometheus.Characters;

public class Character {

    //generic character sheet shared amongst Player, NPCs, and Enemies

    //parameters
    private String name;
    private String ancestry;
    private int age;
//    private String alignment;
    private int[] stats = new int[6];
        //[0]=Strength
        //[1]=Dexterity
        //[2]=Constitution
        //[3]=Intelligence
        //[4]=Wisdom
        //[5]=Charisma

    //derived parameters
        //maxHp
        //AC, touch, flatfooted
        //saves
        //skills

    //Getters & Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAncestry() {
        return ancestry;
    }

    public void setAncestry(String ancestry) {
        this.ancestry = ancestry;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

//    public String getAlignment() {
//        return alignment;
//    }
//
//    public void setAlignment(String alignment) {
//        this.alignment = alignment;
//    }

    public int[] getStats() {
        return stats;
    }

    public void setStats(int[] stats) {
        this.stats = stats;
    }

    //constructor
    public Character(String name, String ancestry, int age, int[] stats){
     this.name=name;
     this.ancestry=ancestry;
     this.age=age;
     this.stats=stats;
    }

    //methods

}
