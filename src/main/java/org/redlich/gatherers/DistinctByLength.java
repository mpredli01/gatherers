/*
 *
 */
package org.redlich.gatherers;

import java.util.stream.Stream;

/*
 *
 */
public record DistinctByLength(String str) {

    /*
     *
     */
    public static void main(String[] args) {
        displayTitle("[APP] Welcome to the Stream Gatherers Demo Application");

        var result = Stream.of("Jakarta", "", "EE", "will", "be", "released", "", "soon")
                .map(DistinctByLength::new)
                .distinct()
                .map(DistinctByLength::str)
                .toList();
        System.out.println("[APP] " + result);
        }

    /*
     *
     */
    @Override
    public boolean equals(Object obj) {
        return obj instanceof DistinctByLength(String other)
                && str.length() == other.length();
        }

    /*
     *
     */
    @Override
    public int hashCode() {
        return str == null ? 0 : Integer.hashCode(str.length());
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
