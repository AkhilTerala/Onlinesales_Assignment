Task-1 Solution:

package Assignment;
import java.util.*;

public class EventSimulation {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of outcomes: ");
        int numOutcomes = scanner.nextInt();
        scanner.nextLine();

        List<Map<String, Integer>> eventProbabilities = new ArrayList<>();

        for (int i = 0; i < numOutcomes; i++) {
            System.out.print("Enter the outcome (integer): ");
            String outcome = scanner.next();
            System.out.print("Enter the probability (integer): ");
            int probability = scanner.nextInt();
            scanner.nextLine();

            Map<String, Integer> outcomeProbability = new HashMap<>();
            outcomeProbability.put(outcome, probability);

            eventProbabilities.add(outcomeProbability);        }

        System.out.print("Enter the number of occurrences: ");
        int numberOfOccurrences = scanner.nextInt();
        scanner.close();

        simulateEvent(eventProbabilities, numberOfOccurrences);
    }

    public static void simulateEvent(List<Map<String, Integer>> eventProbabilities, int numberOfOccurrences) {
        Random random = new Random();
        int[] outcomeCounts = new int[eventProbabilities.size()];
        int totalProbability = eventProbabilities.stream()
                .mapToInt(outcome -> outcome.values().iterator().next())
                .sum();

        for (int i = 0; i < numberOfOccurrences; i++) {
            int randomValue = random.nextInt(totalProbability) + 1;
            int cumulativeProbability = 0;

            for (int j = 0; j < eventProbabilities.size(); j++) {
                int probability = eventProbabilities.get(j).values().iterator().next();
                cumulativeProbability += probability;
                System.out.println("rv "+randomValue);
                System.out.println("Cp "+cumulativeProbability);

                if (randomValue <= cumulativeProbability) {
                    String outcome = eventProbabilities.get(j).keySet().iterator().next();
                    outcomeCounts[j]++;
                    break;
                }
            }
        }

        for (int i = 0; i < outcomeCounts.length; i++) {
            String outcome = eventProbabilities.get(i).keySet().iterator().next();
            int count = outcomeCounts[i];
            System.out.println("Outcome " + outcome + " appeared has " + count + " times.");
        }
    }

}

