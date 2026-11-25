/*
 * 1487. Making File Names Unique  (Medium)   [OA question, also known as "Device Name System"]
 * https://leetcode.com/problems/making-file-names-unique/
 *
 * Pattern         : Hashing
 * Trigger -> Trick: "make names unique with the smallest (k)" -> HashMap<name, nextK>.
 *                   If taken, loop k++ while name(k) is taken; then store BOTH
 *                   name -> k + 1 (where to start next time) and newName -> 1
 *                   (the new name itself is now taken).
 *
 * Tricky case: ["kaido", "kaido(1)", "kaido", "kaido(1)"]
 *           -> ["kaido", "kaido(1)", "kaido(2)", "kaido(1)(1)"]
 *
 * Brute force : Set of used names, try (1), (2), ... from 1 every time
 *               -> O(n^2) on ["a", "a", "a", ...]  (TLE)
 * Optimised   : remember the next k per base name    -> Time ~O(n * L) amortised, Space O(n * L)
 *               (L = name length, for building/hashing the strings)
 *
 * Brute force (TLE, shown for reasoning):
 *   Set<String> used = new HashSet<>();
 *   for (int i = 0; i < names.length; i++) {
 *       String name = names[i];
 *       if (used.contains(name)) {
 *           int k = 1;
 *           while (used.contains(name + "(" + k + ")")) k++;
 *           name = name + "(" + k + ")";
 *       }
 *       used.add(name);
 *       res[i] = name;
 *   }
 */
import java.util.*;

class MakingFileNamesUnique {

    static class Solution {
        public String[] getFolderNames(String[] names) {
            Map<String, Integer> container = new HashMap<>();
            String[] res = new String[names.length];
            for (int i = 0; i < names.length; i++) {
                String str = names[i];
                if (!container.containsKey(str)) {
                    res[i] = str;
                    container.put(str, 1);
                } else {
                    int k = container.get(str);
                    String newName = str + "(" + k + ")";
                    while (container.containsKey(newName)) {
                        k++;
                        newName = str + "(" + k + ")";
                    }
                    res[i] = newName;
                    container.put(str, k + 1);
                    container.put(newName, 1);
                }
            }
            return res;
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(Arrays.toString(s.getFolderNames(new String[]{"pes", "fifa", "gta", "pes(2019)"})));
        // [pes, fifa, gta, pes(2019)]
        System.out.println(Arrays.toString(s.getFolderNames(new String[]{"gta", "gta(1)", "gta", "avalon"})));
        // [gta, gta(1), gta(2), avalon]
        System.out.println(Arrays.toString(s.getFolderNames(new String[]{"onepiece", "onepiece(1)", "onepiece(2)", "onepiece(3)", "onepiece"})));
        // [onepiece, onepiece(1), onepiece(2), onepiece(3), onepiece(4)]
        System.out.println(Arrays.toString(s.getFolderNames(new String[]{"kaido", "kaido(1)", "kaido", "kaido(1)"})));
        // [kaido, kaido(1), kaido(2), kaido(1)(1)]
    }
}
