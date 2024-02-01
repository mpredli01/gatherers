/*
 *
 */
package org.redlich.gatherers;

import java.util.ArrayList;
import java.util.List;
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
        final long FIXED_SIZE = 3;
        final int GROUPING = 2;

        displayTitle("[APP] Welcome to the Stream Gatherers Demo Application");
        System.out.println("\n");

        System.out.println("[APP] Finding the first two groups of three *without* a Stream Gatherer:");
        var result1 = findGroupsOfThree(FIXED_SIZE, GROUPING);
        System.out.println("[APP] " + result1 + "\n");

        System.out.println("[APP] Finding the first two groups of three *with* a Stream Gatherer:");
        var result2 = findGroupsOfThreeWithGatherer(FIXED_SIZE, GROUPING);
        System.out.println("[APP] " + result2 + "\n");
        }

    /*
     *
     */
    public static ArrayList<ArrayList<Integer>> findGroupsOfThree(long fixed_size, int grouping) {
        return Stream.iterate(0, i -> i + 1)
                .limit(fixed_size * grouping)
                .collect(Collector.of(
                        () -> new ArrayList<ArrayList<Integer>>(),
                        (groups, element) -> {
                            if(groups.isEmpty() || groups.getLast().size() == fixed_size) {
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
        }

    /*
     *
     */
    public static List<List<Integer>> findGroupsOfThreeWithGatherer(long fixed_size, int grouping) {
        return Stream.iterate(0, i -> i + 1)
                .gather(Gatherers.windowFixed((int)fixed_size))
                .limit(grouping)
                .collect(Collectors.toList());
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
