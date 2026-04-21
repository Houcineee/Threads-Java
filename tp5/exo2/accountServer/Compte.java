package accountServer;

public class Compte {
  private Double solde;
  private String name;

  public Compte(String name, Double solde) {
    this.name = name;
    this.solde = solde;
  }

  public String getName() {
    return name;
  }

  public Double getSolde() {
    return solde;
  }

  public void setSolde(Double solde) {
    this.solde = solde;
  }

}
