/*
 *
 */
package org.redlich.gatherers;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Gatherers;
import java.util.stream.Stream;

/*
 *
 */
record TempReading(Instant obtainedAt, int kelvins) {
    TempReading(String time, int kelvins) {
        this(Instant.parse(time), kelvins);
        }

    /*
     *
     */
    public static void main(String[] args) {
        displayTitle("[APP] Welcome to the Stream Gatherers Demo Application");

        Stream<TempReading> readings = loadRecentTempReadings();
        System.out.println("[APP] Most recent temperature readings:");
        readings.forEach(reading -> System.out.println("[APP] * " + reading.kelvins() + "ºK read on " + reading.obtainedAt()));

        var result1 = findSuspiciousTempReading(TempReading.loadRecentTempReadings());
        System.out.println(result1);

        var result2 = findSuspiciousTempReadingWithGatherer(TempReading.loadRecentTempReadings());
        System.out.println(result2);
        }

    /*
     *
     */
    public static Stream<TempReading> loadRecentTempReadings() {
        return Stream.of(
                new TempReading("2023-09-21T10:15:30.00Z", 310),
                new TempReading("2023-09-21T10:15:31.00Z", 312),
                new TempReading("2023-09-21T10:15:32.00Z", 350),
                new TempReading("2023-09-21T10:15:33.00Z", 310)
                );
        }

    /*
     *
     */
    public static boolean isSuspiciousTempReading(TempReading previous, TempReading next) {
        return next.obtainedAt().isBefore(previous.obtainedAt().plusSeconds(5))
                && (next.kelvins() > previous.kelvins() + 30
                || next.kelvins() < previous.kelvins() - 30);
        }

    /*
     *
     */
    public static List<List<TempReading>> findSuspiciousTempReading(Stream<TempReading> source) {
        var suspicious = new ArrayList<List<TempReading>>();
        TempReading previous = null;
        boolean hasPrevious = false;
        for(TempReading next : source.toList()) {
            if(!hasPrevious) {
                hasPrevious = true;
                previous = next;
                }
            else {
                if (isSuspiciousTempReading(previous, next))
                    suspicious.add(List.of(previous, next));
                previous = next;
                }
            }
        return suspicious;
        }

    /*
     *
     */
    public static List<List<TempReading>> findSuspiciousTempReadingWithGatherer(Stream<TempReading> source) {
        return source.gather(Gatherers.windowSliding(2))
                .filter(window -> (window.size() == 2
                        && isSuspiciousTempReading(window.get(0),
                        window.get(1))))
                .toList();
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
    