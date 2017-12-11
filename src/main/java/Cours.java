/**
 * Entity representing a cours
 *
 * @author Edward Ransome
 * @author Michaël Spierer
 */

public class Cours {
    private int id;
    private String titre;
    private int credit;

    public Cours() {
    }

    public String toString(){
        return "Cours : "+titre+" , crédit : "+credit;
    }

    public Cours(String titre, int credit) {
        this.titre = titre;
        this.credit = credit;
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public int getCredit() {
        return credit;
    }

    private void setCredit(int credit) {
        this.credit = credit;
    }

    public String getTitre() {
        return titre;
    }

    private void setTitre(String titre) {
        this.titre = titre;
    }
}