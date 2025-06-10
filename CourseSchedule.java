import java.util.*;

/**
 * Leetcode 207. Course Schedule
 * Link: https://leetcode.com/problems/course-schedule/description/
 */
public class CourseSchedule {
    /**
     * Graph solution using topological sorting where we look for courses which doesnt have
     * any pending prerequisites at the moment and mark them taken and along the way reduce
     * other courses pending prerequisites which are unblocked by this taken course. Repeat until
     * all courses are taken or there isnt a possible scenario for finishing all courses
     *
     * TC: O(V + E) SC: O(V + E) V -> vertices in graph E -> edges in graph
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] dependencies = new int[numCourses]; //O(V) space
        Map<Integer, List<Integer>> outToInMap = new HashMap<>(); // O(V + E) space total V keys and total E in values

        for (int[] pair : prerequisites) { // O(E) time
            int in = pair[0]; //incoming edge - dependent
            int out = pair[1]; //outgoing edge - independent

            if (!outToInMap.containsKey(out)) {
                outToInMap.put(out, new ArrayList<>());
            }
            outToInMap.get(out).add(in);
            dependencies[in]++;
        }

        Queue<Integer> courseTakingQueue = new LinkedList<>(); // O(V) space

        for (int i = 0; i < dependencies.length; i++) { // O(V) time
            if (dependencies[i] == 0) {
                courseTakingQueue.add(i);
            }
        }

        int totalTaken = 0;
        while (!courseTakingQueue.isEmpty()) { // maximum V iterations Time including inner operations O(V + E)
            int taken = courseTakingQueue.poll();
            totalTaken++;

            if (totalTaken == numCourses) {
                return true;
            }

            List<Integer> unblockedByTaken = outToInMap.get(taken);
            if (unblockedByTaken != null) {
                for (int unblocked : unblockedByTaken) { // each iteration process a few Edges, all iterations will process E edges in total
                    dependencies[unblocked]--;

                    if (dependencies[unblocked] == 0) {
                        courseTakingQueue.add(unblocked);
                    }
                }
            }
        }
        return false;
    }
}
