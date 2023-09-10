package de.groygroy.linuxmagazin.java21;

import java.util.Date;
import java.util.FormatProcessor;
import java.util.Locale;

import static java.util.FormatProcessor.FMT;
import static java.lang.StringTemplate.STR;


public class Templates {

    public static void main(String ... args) {
        // Code bis Java 15 -- 21
        var name = "Erika Mustermann";
        var tag = new java.util.Date();

        var grussFmt = "Hallo %s";
        System.out.println( grussFmt.formatted( name));
        // Code mit Java 21
        System.out.println( STR."Hallo \{name}");

        // Code bis Java 15 -- 21
        var jsonFmt = "{\n  \"name\" : \"%s\",\n  \"tag\" : \"%tA %te. %tB\"\n}";
        System.out.println(jsonFmt.formatted( name, tag,tag, tag));

        // Code mit Java 21 und FMT
        var fp = FormatProcessor.create(Locale.GERMAN);
        System.out.println( fp."""
                    {
                      "name": "\{name}",
                      "tag" : "%tA\{tag} %te\{tag} %tB\{tag}"                      
                    }
                    """);

            }
}
