/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package task2;

/**
 *
 * @author lenovo
 */
import java.lang.*;

class Main {

    // M is number of applicants
    // and N is number of hospitals
    static final int M = 6;
    static final int N = 6;
    static int matchR[] = new int[N];

    // A DFS based recursive function that
    // returns true if a matching for
    // vertex u is possible
    boolean bpm(boolean bpGraph[][], int u,
            boolean seen[], int matchR[]) {
        // Try every hospital one by one
        for (int v = 0; v < N; v++) {
            // If applicant u is interested
            // in hospital v and v is not visited
            if (bpGraph[u][v] && !seen[v]) {

                // Mark v as visited
                seen[v] = true;

                // If hospital 'v' is not assigned to
                // an applicant OR previously
                // assigned applicant for hospital v (which
                // is matchR[v]) has an alternate hospital available.
                // Since v is marked as visited in the
                // above line, matchR[v] in the following
                // recursive call will not get hospital 'v' again
                if (matchR[v] < 0 || bpm(bpGraph, matchR[v],
                        seen, matchR)) {
                    matchR[v] = u;
                    return true;
                }
            }
        }
        return false;
    }

    // Returns maximum number
    // of matching from M to N
    int maxBPM(boolean bpGraph[][]) {
        // An array to keep track of the
        // applicants assigned to hospitals.
        // The value of matchR[i] is the
        // applicant number assigned to hospital i,
        // the value -1 indicates nobody is assigned.
        // int matchR[] = new int[N];

        // Initially all hospitals are available
        for (int i = 0; i < N; ++i) {
            matchR[i] = -1;
        }

        // Count of hospitals assigned to applicants
        int result = 0;
        for (int u = 0; u < M; u++) {
            // Mark all hospitals as not seen
            // for next applicant.
            boolean seen[] = new boolean[N];
            for (int i = 0; i < N; ++i) {
                seen[i] = false;
            }

            // Find if the applicant 'u' can get a hospital
            if (bpm(bpGraph, u, seen, matchR)) {
                result++;
            }
        }
        return result;
    }

    // Driver Code
    public static void main(String[] args) throws java.lang.Exception {
        // Let us create a bpGraph shown
        // in the above example
        boolean bpGraph[][] = new boolean[][]{
            {true, true, false,
                false, false, false},
            {false, false, false,
                false, false, true},
            {true, false, false,
                true, false, false},
            {false, false, true,
                false, false, false},
            {false, false, false,
                true, true, false},
            {false, false, false,
                false, false, true}};
        Main m = new Main();
        int ans = m.maxBPM(bpGraph);
        for (int i = 0; i < N; i++) {
            if (matchR[i] > -1) {
                System.out.println( "Hospital no."+ (i + 1) +" has patient no." + (matchR[i] + 1));
            } else {
                System.out.println((i + 1) + "Hospital no."+ (i + 1) +" has no patient");
            }
        }
        System.out.println("Maximum number of applicants that can get a position in hospital are " + ans);

    }
}
