// TASK_1
import java.util.*;

class Task implements Comparable<Task> 
{
    String id;
    String description;
    int priority;

    public Task(String id, String description, int priority) 
    {
        this.id = id;
        this.description = description;
        this.priority = priority;
    }

    @Override
    public int compareTo(Task other) 
    {
        return Integer.compare(other.priority, this.priority); 
    }

    @Override
    public String toString() 
    {
        return "Task ID: " + id + ", Description: " + description + ", Priority: " + priority;
    }
}

class TaskManager 
{
    private PriorityQueue<Task> taskQueue;
    private Map<String, Task> taskMap; 

    public TaskManager() 
    {
        taskQueue = new PriorityQueue<>();
        taskMap = new HashMap<>();
    }

    public void addTask(String id, String description, int priority) 
    {
        if (taskMap.containsKey(id)) 
        {
            System.out.println("Task ID already exists!");
            return;
        }
        Task task = new Task(id, description, priority);
        taskQueue.add(task);
        taskMap.put(id, task);
    }

    public void removeTask(String id) 
    {
        if (!taskMap.containsKey(id)) 
        {
            System.out.println("Task ID not found!");
            return;
        }
        Task taskToRemove = taskMap.get(id);
        taskQueue.remove(taskToRemove);
        taskMap.remove(id);
        System.out.println("Task removed successfully.");
    }

    public Task getHighestPriorityTask() 
    {
        return taskQueue.peek();
    }

    public void printTasks() 
    {
        if (taskQueue.isEmpty()) 
        {
            System.out.println("No tasks available.");
            return;
        }
        System.out.println("Tasks in the queue:");
        for (Task task : taskQueue) {
            System.out.println(task);
        }
    }
}

public class Task_1 
{
    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);
        TaskManager taskManager = new TaskManager();

        while (true) 
        {
            System.out.println("\nTask Manager Menu:");
            System.out.println("1. Add Task");
            System.out.println("2. Remove Task");
            System.out.println("3. Get Highest Priority Task");
            System.out.println("4. Show All Tasks");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1: // Add Task
                    System.out.print("Enter Task ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Enter Task Description: ");
                    String description = scanner.nextLine();
                    System.out.print("Enter Task Priority: ");
                    int priority = scanner.nextInt();
                    taskManager.addTask(id, description, priority);
                    break;

                case 2: // Remove Task
                    System.out.print("Enter Task ID to remove: ");
                    String removeId = scanner.nextLine();
                    taskManager.removeTask(removeId);
                    break;

                case 3: // Get Highest Priority Task
                    Task highestTask = taskManager.getHighestPriorityTask();
                    if (highestTask != null) {
                        System.out.println("Highest Priority Task: " + highestTask);
                    } else {
                        System.out.println("No tasks available.");
                    }
                    break;

                case 4: // Show All Tasks
                    taskManager.printTasks();
                    break;

                case 5: // Exit
                    System.out.println("Exiting Task Manager. Goodbye!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice! Please enter a valid option.");
            }
        }
    }
}
