package de.groygroy.linuxmagazin.java21;


import java.util.Date;

/**
 * Beispiel für JEP 440 : Patterns
 */
public class Patterns {

    public static void main(String ... args) {

        var e1 = new Eintrag(10, "Martin Thaler");
        var e2 = new Eintrag(10, "Jonathan Trotz");
        Object o = new Seite( new Date(), e1, e2);


        if ( o instanceof Seite(Date d,  Eintrag a, Eintrag b)) {
            System.out.printf("Seite vom %tD: %s, %s %n",d, a.schüler, b.schüler);
        } else  if (o instanceof Eintrag(int k, String s)) {
            System.out.printf("Eintrag für %s aus Klasse %d %n",s,k);
        } else if (o instanceof String s) {
            System.out.printf("String %s %n", s);
        } else {
            System.out.printf("Anderes %s %n", o);
        }

        switch( o) {
            case Seite(var d, var a, var b)
                when 10== a.klasse -> {
                    System.out.printf("Untersekunda %s",a.schüler);
            }
            case Seite(var d, var a, var b) -> {
                System.out.printf("Klasse %d %s",a.klasse, a.schüler);
            }
            default -> {
                System.out.printf("Anderes %s %n", o);
            }
        }

    }
    public static record Eintrag(int klasse, String schüler) {}
    public static record Seite(Date tag, Eintrag e1, Eintrag e2) {};


}
