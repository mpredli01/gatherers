/*
 *
 */
package org.redlich.gatherers;

import java.util.ArrayList;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Gatherers;
import java.util.stream.Stream;

/*
 *
 */
public class FixedWindow {
    /*
     *
     */
    public static void main(String[] args) {
        final int MAX = 10;
        final int FACTOR = 2;

        displayTitle("[APP] Welcome to the Stream Gatherers Demo Application");

        var result = Stream.iterate(0, i -> i + 1)
                .limit(MAX)
                .collect(Collector.of(
                        () -> new ArrayList<ArrayList<Integer>>(),
                        (groups, element) -> {
                            if(groups.isEmpty() || groups.getLast().size() == FACTOR) {
                                var current = new ArrayList<Integer>();
                                current.add(element);
                                groups.addLast(current);
                                }
                            else {
                                groups.getLast().add(element);
                                }
                            },
                        (left, right) -> {
                            throw new UnsupportedOperationException("Cannot be parallelized");
                            }
                        ));
        System.out.println("[APP] " + result);

        var result2 = Stream.iterate(0, i -> i + 1)
                .gather(Gatherers.windowFixed(FACTOR))
                .limit(MAX)
                .collect(Collectors.toList());
        System.out.println("[APP] " + result2);
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
