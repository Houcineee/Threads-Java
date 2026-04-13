import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;

public class Personne implements Serializable {
  private static final long serialVersionUID = 1L;

  private final String nom;
  private final String prenom;
  private final LocalDate dateNaissance;

  public Personne(String nom, String prenom, LocalDate dateNaissance) {
    this.nom = nom;
    this.prenom = prenom;
    this.dateNaissance = dateNaissance;
  }

  public String getNom() {
    return nom;
  }

  public String getPrenom() {
    return prenom;
  }

  public LocalDate getDateNaissance() {
    return dateNaissance;
  }

  public int calculAge() {
    return Period.between(dateNaissance, LocalDate.now()).getYears();
  }

  @Override
  public String toString() {
    return "Personne{"
        + "nom='" + nom + '\''
        + ", prenom='" + prenom + '\''
        + ", dateNaissance=" + dateNaissance
        + '}';
  }
}
