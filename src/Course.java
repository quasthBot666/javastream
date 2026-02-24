public class Course {
    private String name;
    private String instructor;
    private int credits;

    public Course(String name, String instructor, int credits) {
        this.name = name;
        this.instructor = instructor;
        this.credits = credits;
    }

    public String getName() {
        return name;
    }

    public String getInstructor() {
        return instructor;
    }

    public int getCredits() {
        return credits;
    }

    public String toString() {
        return "Course(name='" + name + "', instructor='" + instructor + "', credits=" + credits + ")";
    }
}

class AssignmentTask {
    private String title;
    private Course course;
    private int estimatedHours;
    private int daysUntilDue;
    private boolean completed;

    public AssignmentTask(String title, Course course, int estimatedHours, int daysUntilDue) {
        this.title = title;
        this.course = course;
        this.estimatedHours = estimatedHours;
        this.daysUntilDue = daysUntilDue;
        this.completed = false;
    }

    public String getTitle() {
        return title;
    }

    public Course getCourse() {
        return course;
    }

    public int getEstimatedHours() {
        return estimatedHours;
    }

    public int getDaysUntilDue() {
        return daysUntilDue;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void markCompleted() {
        this.completed = true;
    }

    public boolean isUrgent() {
        return daysUntilDue <= 2 && !completed;
    }

    public String toString() {
        return "AssignmentTask(title='" + title + "', course='" + course.getName() +
                "', estHours=" + estimatedHours + ", dueIn=" + daysUntilDue +
                ", completed=" + completed + ")";
    }
}

class StudySession {
    private Course course;
    private int minutes;

    public StudySession(Course course, int minutes) {
        this.course = course;
        this.minutes = minutes;
    }

    public Course getCourse() {
        return course;
    }

    public int getMinutes() {
        return minutes;
    }

    public double hours() {
        return minutes / 60.0;
    }

    public String toString() {
        return "StudySession(course='" + course.getName() + "', minutes=" + minutes + ")";
    }
}

class CampusLifeApp {
    public static void main(String[] args) {
        Course OOP = new Course("OOP", "Mr. OOP", 6);
        Course discreteMath = new Course("Discrete Math", "Ms. BallerinoCappuccino", 3);
        Course English = new Course("English", "Mr. Skibidi", 3);
        Course[] courses = {OOP, discreteMath, English};

        AssignmentTask[] assignments = {
                new AssignmentTask("Lab 2", OOP, 3, 1),
                new AssignmentTask("Final Project", OOP, 10, 5),
                new AssignmentTask("Homework 3", discreteMath, 4, 0),
                new AssignmentTask("Proof Assignment", discreteMath, 5, 2),
                new AssignmentTask("HW 1", English, 6, 3),
                new AssignmentTask("Group project", English, 2, 1)
        };

        StudySession[] sessions = {
                new StudySession(OOP, 90),
                new StudySession(OOP, 120),
                new StudySession(discreteMath, 60),
                new StudySession(discreteMath, 75),
                new StudySession(English, 45)
        };

        System.out.println("===========================================");
        System.out.println("Продолжительность сессий");
        for (StudySession s : sessions) {
            System.out.println(s.getCourse().getName() + ": " + s.getMinutes() + " минут (" + s.hours() + " часов)");
        }

        System.out.println("\n===========================================");
        System.out.println("Все задания (Срочные отмечены *)");
        for (AssignmentTask a : assignments) {
            System.out.print(a.getTitle() + " (" + a.getCourse().getName() + ")");
            if (a.isUrgent()) {
                System.out.print(" ← * срочно! (осталось " + a.getDaysUntilDue() + " дн.)");
            }
            System.out.println();
        }

        System.out.println("\n===========================================");
        System.out.println("Количество часов для невыполненных заданий");
        int totalHoursRemaining = 0;
        for (AssignmentTask a : assignments) {
            if (!a.isCompleted()) {
                totalHoursRemaining += a.getEstimatedHours();
            }
        }
        System.out.println("Всего осталось часов: " + totalHoursRemaining);

        System.out.println("\n===========================================");
        System.out.println("Общее время изучения для каждого курса");
        for (Course c : courses) {
            double totalHours = 0;
            for (StudySession s : sessions) {
                if (s.getCourse() == c) {
                    totalHours += s.hours();
                }
            }
            System.out.println(c.getName() + ": " + totalHours + " часов");
        }

        System.out.println("\n===========================================");
        System.out.println("Отмечаем выполненные задания");
        AssignmentTask toComplete = assignments[2];
        System.out.println("До отметки: " + toComplete.getTitle() + " (" + toComplete.getEstimatedHours() + " часов)");
        toComplete.markCompleted();
        System.out.println("После отметки: задание выполнено");

        System.out.println("\n===========================================");
        System.out.println("Пересчет оставшихся часов");
        int newTotalHoursRemaining = 0;
        for (AssignmentTask a : assignments) {
            if (!a.isCompleted()) {
                newTotalHoursRemaining += a.getEstimatedHours();
            }
        }
        System.out.println("Было часов: " + totalHoursRemaining);
        System.out.println("Стало часов: " + newTotalHoursRemaining);
        System.out.println("Сэкономлено: " + (totalHoursRemaining - newTotalHoursRemaining) + " часов");

        System.out.println("\n===========================================");
        System.out.println("ИТОГОВАЯ ИНФОРМАЦИЯ");
        System.out.println("Всего курсов: " + courses.length);
        System.out.println("Всего заданий: " + assignments.length);
        System.out.println("Выполненных заданий: " + countCompleted(assignments));
        System.out.println("Срочных заданий осталось: " + countUrgent(assignments));
        System.out.println("Всего учебных сессий: " + sessions.length);
    }

    private static int countCompleted(AssignmentTask[] assignments) {
        int count = 0;
        for (AssignmentTask a : assignments) {
            if (a.isCompleted()) count++;
        }
        return count;
    }

    private static int countUrgent(AssignmentTask[] assignments) {
        int count = 0;
        for (AssignmentTask a : assignments) {
            if (a.isUrgent()) count++;
        }
        return count;
    }
}