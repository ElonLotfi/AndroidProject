package donnees;


public enum  TypeArms {
    Pierre("Pierre"),
    Feuille("Feuille"),
    Ciseaux("Ciseaux"),
    UNDEFINED("undefined");

    private String name = "";

    TypeArms(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }


}
