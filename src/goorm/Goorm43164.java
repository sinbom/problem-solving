package goorm;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Goorm43164 {

    public static class Person implements Comparable<Person> {

        private final int eat;

        private final int wait;

        public Person(int eat, int wait) {
            this.eat = eat;
            this.wait = wait;
        }

        @Override
        public int compareTo(Person o) {
            return Integer.compare(o.eat, this.eat);
        }
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int n = Integer.parseInt(reader.readLine());
            Person[] persons = new Person[n];
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            StringTokenizer stringTokenizer2 = new StringTokenizer(reader.readLine());

            for (int i = 0; i < n; i++) {
                persons[i] = new Person(
                        Integer.parseInt(stringTokenizer.nextToken()),
                        Integer.parseInt(stringTokenizer2.nextToken())
                );
            }
            Arrays.sort(persons);

            int answer = persons[0].wait + persons[0].eat;
            int totalWaitTime = persons[0].wait;

            for (int i = 1; i < persons.length; i++) {
                int totalTime = totalWaitTime + persons[i].wait + persons[i].eat;
                answer = Math.max(answer, totalTime);
                totalWaitTime += persons[i].wait;
            }

            writer.write(answer + "");
        }
    }

}
