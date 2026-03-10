import java.util.*;
import java.util.stream.*;

public class ClassWork {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 8740844, 9, 666, 1234567, 9, 9, 312, 777);
        List<String> words = Arrays.asList("bayball", "skibidi", "a is just a", "yaustaldelatmaynanimaciu", "my gpa 2.6");
        List<Student> students = Arrays.asList(
                new Student("Bayball", "A1", 3.9),
                new Student("Daniyar(balbes)", "B1", 0.1),
                new Student("Azamat", "B1", 4.0),
                new Student("Vlad", "A1", 2.8),
                new Student("Roman", "A2", 3.3),
                new Student("Gerob", "B2", 3.7)
        );
        List<Integer> evenNumbers =
                numbers.stream()
                        .filter(n -> n % 2 == 0)
                        .collect(Collectors.toList());
        System.out.println("1. Even numbers: " + evenNumbers);
        List<String> upperCaseWords =
                words.stream()
                        .map(String::toUpperCase)
                        .collect(Collectors.toList());
        System.out.println("2. Uppercase words: " + upperCaseWords);
        long count =
                words.stream()
                        .filter(w -> w.startsWith("a"))
                        .count();
        System.out.println("3. Words starting with 'a': " + count);
        List<Integer> sortedDesc =
                numbers.stream()
                        .sorted(Comparator.reverseOrder())
                        .collect(Collectors.toList());
        System.out.println("4. Sorted descending: " + sortedDesc);
        int max =
                numbers.stream()
                        .max(Integer::compare)
                        .get();
        int min =
                numbers.stream()
                        .min(Integer::compare)
                        .get();
        System.out.println("5. Max: " + max);
        System.out.println("   Min: " + min);
        List<Integer> noDuplicates =
                numbers.stream()
                        .distinct()
                        .collect(Collectors.toList());
        System.out.println("6. Without duplicates: " + noDuplicates);
        String joined =
                words.stream()
                        .collect(Collectors.joining(", "));
        System.out.println("7. Joined string: " + joined);
        Map<String, List<Student>> groupedStudents =
                students.stream()
                        .collect(Collectors.groupingBy(Student::getGroup));
        System.out.println("8. Grouped students: " + groupedStudents);
        double averageGpa =
                students.stream()
                        .mapToDouble(Student::getGpa)
                        .average()
                        .orElse(0);
        System.out.println("9. Average GPA: " + averageGpa);
        List<Student> topStudents =
                students.stream()
                        .filter(s -> s.getGpa() > 3.5)
                        .limit(3)
                        .collect(Collectors.toList());
        System.out.println("10. First 3 students GPA>3.5: " + topStudents);
        long countHighGpa =
                students.stream()
                        .filter(s -> s.getGpa() > 3.5)
                        .count();
        System.out.println("11. Students with GPA>3.5: " + countHighGpa);
    }
}
class Student {
    private String name;
    private String group;
    private double gpa;
    public Student(String name, String group, double gpa) {
        this.name = name;
        this.group = group;
        this.gpa = gpa;
    }
    public String getName() {
        return name;
    }
    public String getGroup() {
        return group;
    }
    public double getGpa() {
        return gpa;
    }
    public String toString() {
        return name + "(group=" + group + ", gpa=" + gpa + ")";
    }
}