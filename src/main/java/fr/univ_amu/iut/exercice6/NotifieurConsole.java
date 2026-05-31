package fr.univ_amu.iut.exercice6;

/** Implémentation qui écrit les notifications sur la sortie standard. */
public class NotifieurConsole implements Notifieur {

  @Override
  public void notifier(String message) {
    System.out.println("[NOTIF] " + message);
  }
}
