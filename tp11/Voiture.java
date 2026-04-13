import java.io.Serializable;

public class Voiture implements Serializable
{
  String mat ;
  int carburant ;
  public Voiture (String m, int c)
  {
    mat=m ;
    carburant = c ;
  }
}
