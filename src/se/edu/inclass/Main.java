package se.edu.inclass;

import se.edu.inclass.data.DataManager;
import se.edu.inclass.task.Deadline;
import se.edu.inclass.task.Task;
import se.edu.inclass.task.TaskNameComparator;

import java.util.ArrayList;

import static java.util.stream.Collectors.toList;

public class Main {

    private TaskNameComparator taskNameComparator;

    public static void main(String[] args) {
        System.out.println("Welcome to Task (stream) manager\n");
        DataManager dm = new DataManager("./data/data.txt");
        ArrayList<Task> tasksData = dm.loadData();

        //printData(tasksData);
        System.out.println();
        System.out.println("Printing deadlines before sorting");
        printDeadlines(tasksData);
        System.out.println();
//        System.out.println("Printing deadlines");
//        printDeadlines(tasksData);
//
//        System.out.println("Total number of deadlines: " + countDeadlines(tasksData));
//        printData(tasksData);
//        printDataUsingStream(tasksData);
        printDeadlinesUsingStream(tasksData);
        System.out.println("Total number of deadlines counted using stream: " + countDeadlinesUsingStream(tasksData));


        System.out.println("Printing deadlines after sorting");
        printDeadlinesUsingStream(tasksData);

        ArrayList<Task> filteredList = filterTaskListUsingStreams(tasksData, "11");
        System.out.println("\n Filtered list of tasks:");
        printData(filteredList);
    }

    private static int countDeadlines(ArrayList<Task> tasksData) {
        int count = 0;
        for (Task t : tasksData) {
            if (t instanceof Deadline) {
                count++;
            }
        }
        return count;
    }

    private static int countDeadlinesUsingStream(ArrayList<Task> tasks) {
        int count = (int) tasks.stream()
                .filter(t -> t instanceof Deadline) // filter takes a predicate
                .count();
        return count;
    }

    public static void printData(ArrayList<Task> tasksData) {
        System.out.println("Printing data using iterator");
        for (Task t : tasksData) {
            System.out.println(t);
        }
    }

    public static void printDataUsingStream(ArrayList<Task> tasks) {
        System.out.println("Printing data using streams");
        tasks.stream() // convert to stream
                .forEach(System.out::println);
    }

    public static void printDeadlines(ArrayList<Task> tasksData) {
        System.out.println("Printing deadline using iterator");
        for (Task t : tasksData) {
            if (t instanceof Deadline) {
                System.out.println(t);
            }
        }
    }

    public static void printDeadlinesUsingStream(ArrayList<Task> tasks) {
        System.out.println("Printing deadline using stream");
        tasks.stream()
                .filter(t -> t instanceof Deadline)
                .sorted((a, b) -> a.getDescription().compareToIgnoreCase(b.getDescription()))
                .forEach(System.out::println);

    }

    public static ArrayList<Task> filterTaskListUsingStreams(ArrayList<Task> tasks, String filterString) {
        ArrayList<Task> filteredList = (ArrayList<Task>) tasks.stream()
                .filter(t -> t.getDescription().contains(filterString))
                .collect(toList());


        return filteredList;

    }
}
