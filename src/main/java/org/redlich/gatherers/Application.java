/*
 *
 */
package org.redlich.gatherers;

import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Application {

    /*
     *
     */
    public static void main(String[] args) {
        displayTitle("[APP] Welcome to the Stream Gatherers Demo Application");

        long numberOfWords = getNumberOfNonZeroWords();
        System.out.println("[APP] There are " + numberOfWords + " non-zero words in the phrase");
        }

    /*
     *
     */
    public static long getNumberOfNonZeroWords() {
        return Stream.of("Jakarta", "", "EE", "will", "be", "released", "", "soon")
                .filter(Predicate.not(String::isEmpty))
                .collect(Collectors.counting());
        }

    /*
     *
     */
    public static void displayTitle(String title) {
        int length = title.length();
        System.out.print("[APP] ");
        for(int i = 0; i < length; ++i) {
            System.out.print("-");
            }
        System.out.println();
        System.out.println(title);
        System.out.print("[APP] ");
        for(int i = 0; i < length; ++i) {
            System.out.print("-");
            }
        System.out.println();
        }
    }
    