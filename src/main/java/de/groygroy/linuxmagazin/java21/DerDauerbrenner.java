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


import jdk.incubator.vector.*;

import java.util.Random;

import static java.lang.StringTemplate.STR;

/**
 * Beispiel für JEP 448: Vector API.
 * Das basiert auf dem Code von
 * <a href="https://stackoverflow.com/questions/68061254/why-is-the-java-vector-api-so-slow-compared-to-scalar">https://stackoverflow.com/questions/68061254/why-is-the-java-vector-api-so-slow-compared-to-scalar</a>
 */
public class DerDauerbrenner {

    static final VectorSpecies<Float> SPECIES = FloatVector.SPECIES_PREFERRED;

    public static void main(String ... args) {


        doBytes();
        doInt();
        doLong();
        doFloat();
        doDouble();



    }



    static void doBytes() {
        System.out.println("Byte");

        var spc = ByteVector.SPECIES_PREFERRED;
        System.out.println(spc.toString());

        Random rand = new Random();
        int size = 1000000;
        var a = new byte[size];
        var b = new byte[size];
        for (int ix = 0; ix < size; ix++) {
            byte by = (byte) (rand.nextInt());
            a[ix] = by;
            by = (byte) (rand.nextInt());
            b[ix] = by;
        }

        var start = System.nanoTime();
        var nonresult = new byte[a.length];
        for (int ix = 0; ix < a.length; ix++) {
            nonresult[ix] = (byte) (a[ix] * b[ix]) ;
        }
        var stop = System.nanoTime();
        System.out.println("    Scalar:    " + (stop-start) + " [ns]");

        var vecresult = new byte[a.length];
        start = System.nanoTime();
        int ix = 0;
        for (; ix < spc.loopBound(a.length); ix += spc.length()) {
            var va = ByteVector.fromArray(spc, a, ix);
            var vb = ByteVector.fromArray(spc, b, ix);
            var Result = va.mul(vb);
            Result.intoArray(vecresult, ix);
        }
        for (int ix2 = ix; ix2 < a.length; ix2++)
            vecresult[ix2] = (byte) (a[ix2] * b[ix2]);
        stop = System.nanoTime();

        System.out.println("    Vectorized: " + (stop-start) + " [ns]");
    }


    static void doInt() {

        System.out.println("Int ");
        var spc = IntVector.SPECIES_PREFERRED;
        System.out.println(spc.toString());
        Random rand = new Random();
        int size = 1000000;
        var a = new int[size];
        var b = new int[size];
        for (int ix = 0; ix < size; ix++) {
            int by = rand.nextInt();
            a[ix] = by;
            by = rand.nextInt();
            b[ix] = by;
        }

        var start = System.nanoTime();
        var nonresult = new int[a.length];
        for (int ix = 0; ix < a.length; ix++) {
            nonresult[ix] = a[ix] * b[ix];
        }
        var stop = System.nanoTime();
        System.out.println("    Scalar:     " + (stop-start) + " [ns]");

        var vecresult = new int[a.length];
        start = System.nanoTime();
        int ix = 0;
        for (; ix < spc.loopBound(a.length); ix += spc.length()) {
            var va = IntVector.fromArray(spc, a, ix);
            var vb = IntVector.fromArray(spc, b, ix);
            var Result = va.mul(vb);
            Result.intoArray(vecresult, ix);
        }
        for (int ix2 = ix; ix2 < a.length; ix2++)
            vecresult[ix2] = a[ix2] * b[ix2];
        stop = System.nanoTime();
        System.out.println("    Vectorized: " + (stop-start) + " [ns]");

    }

    static void doLong() {

        System.out.println("Long");

        var spc = LongVector.SPECIES_PREFERRED;
        System.out.println(spc.toString());
        Random rand = new Random();
        int size = 1000000;
        var a = new long[size];
        var b = new long[size];
        for (int ix = 0; ix < size; ix++) {
            long by = rand.nextLong();
            a[ix] = by;
            by = rand.nextLong();
            b[ix] = by;
        }

        var start = System.nanoTime();
        var nonresult = new long[a.length];
        for (int ix = 0; ix < a.length; ix++) {
            nonresult[ix] = a[ix] * b[ix];
        }
        var  stop = System.nanoTime();
        System.out.println("    Scalar:     " + (stop-start) + " [ns]");

        var vecresult = new long[a.length];
        start = System.nanoTime();
        int ix = 0;
        for (; ix < spc.loopBound(a.length); ix += spc.length()) {
            var va = LongVector.fromArray(spc, a, ix);
            var vb = LongVector.fromArray(spc, b, ix);
            var Result = va.mul(vb);
            Result.intoArray(vecresult, ix);
        }
        for (int ix2 = ix; ix2 < a.length; ix2++)
            vecresult[ix2] = a[ix2] * b[ix2];
        stop = System.nanoTime();
        System.out.println("    Vectorized: " + (stop-start) + " [ns]");


    }

    static void doFloat() {

        System.out.println("Float");

        var spc = FloatVector.SPECIES_PREFERRED;
        System.out.println(spc.toString());
        Random rand = new Random();
        int size = 1000000;
        var a = new float[size];
        var b = new float[size];
        for (int ix = 0; ix < size; ix++) {
            float by = rand.nextFloat();
            a[ix] = by;
            by = rand.nextFloat();
            b[ix] = by;
        }

        var  start = System.nanoTime();
        var nonresult = new float[a.length];
        for (int ix = 0; ix < a.length; ix++) {
            nonresult[ix] = a[ix] * b[ix];
        }
        var stop = System.nanoTime();
        System.out.println("    Scalar:     " + (stop-start) + " [ns]");

        var vecresult = new float[a.length];
        start = System.nanoTime();
        int ix = 0;
        for (; ix < spc.loopBound(a.length); ix += spc.length()) {
            var va = FloatVector.fromArray(spc, a, ix);
            var vb = FloatVector.fromArray(spc, b, ix);
            var Result = va.mul(vb);
            Result.intoArray(vecresult, ix);
        }
        for (int ix2 = ix; ix2 < a.length; ix2++)
            vecresult[ix2] = a[ix2] * b[ix2];
        stop = System.nanoTime();
        System.out.println("    Vectorized: " + (stop-start) + " [ns]");

    }

    static void doDouble() {

        System.out.println("Double");

        var spc = DoubleVector.SPECIES_PREFERRED;
        System.out.println(spc.toString());
        Random rand = new Random();
        int size = 1000000;
        var a = new double[size];
        var b = new double[size];
        for (int ix = 0; ix < size; ix++) {
            double by = rand.nextDouble();
            a[ix] = by;
            by = rand.nextDouble();
            b[ix] = by;
        }

        var start = System.nanoTime();
        var nonresult = new double[a.length];
        for (int ix = 0; ix < a.length; ix++) {
            nonresult[ix] = a[ix] * b[ix];
        }
        var stop = System.nanoTime();
        System.out.println("    Scalar:     " + (stop-start) + " [ns]");

        var vecresult = new double[a.length];
        start = System.nanoTime();
        int ix = 0;
        for (; ix < spc.loopBound(a.length); ix += spc.length()) {
            var va = DoubleVector.fromArray(spc, a, ix);
            var vb = DoubleVector.fromArray(spc, b, ix);
            var Result = va.mul(vb);
            Result.intoArray(vecresult, ix);
        }
        for (int ix2 = ix; ix2 < a.length; ix2++)
            vecresult[ix2] = a[ix2] * b[ix2];
        stop = System.nanoTime();
        System.out.println("    Vectorized: " + (stop-start) + " [ns]");
    }


}
