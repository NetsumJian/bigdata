package queue;

import java.util.PriorityQueue;
import java.util.concurrent.*;

public class BlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        // arrayQueue();
        // linkedQueue();
        BlockingQueue<Student> students = new PriorityBlockingQueue<Student>(10);
        students.put(new Student("lili",20,70));
        students.put(new Student("didi",12,30));
        students.put(new Student("gege",43,56));
        students.put(new Student("meme",32,82));

        // System.out.println(students);
        for (Student s :
                students) {
            System.out.println(s);
        }
    }

    private static void linkedQueue() throws InterruptedException {
        BlockingQueue<String> queue = new LinkedBlockingQueue<>();
        // String s = queue.remove();
        String s = queue.poll();
        queue.take();
        System.out.println(s);
    }

    private static void arrayQueue() throws InterruptedException {
        BlockingQueue<String> queue = new ArrayBlockingQueue<>(5);
        queue.add("a");
        queue.add("a");
        queue.add("a");
        queue.add("a");
        queue.add("a");

//        queue.add("b");
//        queue.offer("c");
//        queue.put("d");
        queue.offer("e",5, TimeUnit.SECONDS);

        System.out.println(queue);
    }
}

class Student implements Comparable<Student>{
    String name;
    int age;
    int score;

    public Student(String name, int age, int score) {
        this.name = name;
        this.age = age;
        this.score = score;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", score=" + score +
                '}';
    }

    @Override
    public int compareTo(Student o) {
        return o.age - this.age;
    }
}