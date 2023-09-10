/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.groygroy.linuxmagazin.java21;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.UUID;

public class Collections {


    public static void main(String ... args) {
        // Listen Beispiel
        // Code bis Java 21
        var liste = new ArrayList<String>();
        liste.add("A");
        liste.add("B");
        liste.add(0, "C");
        System.out.println("#1 : " + liste + ", ende " + liste.get(liste.size()-1));
        liste.remove(0);
        liste.remove( liste.size()-1);

        // Code mit Java 21 + JEP 431
        liste.addLast("D");
        liste.addFirst("E");

        System.out.println("#2 : " + liste + ", ende " + liste.getLast());
        liste.removeFirst();
        liste.removeLast();


        // Set Beispiel
        var linkedMap = new LinkedHashMap<String, UUID>();
        linkedMap.put("B", UUID.randomUUID());
        linkedMap.putFirst("A", UUID.randomUUID());
        linkedMap.putLast("C", UUID.randomUUID());

        System.out.println("map " + linkedMap.keySet());

    }
}
