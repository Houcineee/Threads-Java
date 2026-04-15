public class Operations implements Runnable {
  private Compte c ; 
  public Operations(Compte c ) {
    this.c = c ; 
  }
    

  @Override
  public void run(){
    try {
      c.debiter(100.0) ; 
      c.crediter(100.0) ; 
      c.lireSolde() ; 
      
    } catch (Exception e) {
      System.out.println("Something wrong happened");

    }

  }

}
