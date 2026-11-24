# dsa-java

My LeetCode solutions in **Java**, grouped by **pattern**. I use this repo as a revision notebook.

Each file has:
- the problem link and difficulty
- the pattern, plus a one-line **trigger → trick** (what in the question points to the pattern, and the key idea)
- time and space complexity
- a brute-force version and an optimised version where both exist (`Solution` is the one you can paste into LeetCode)
- a small `main` with the sample tests

## Progress

| # | Problem | Pattern | Difficulty | Solution | Date |
|---|---------|---------|------------|----------|------|
| 1 | [Two Sum](https://leetcode.com/problems/two-sum/) | Hashing | Easy | [0001_TwoSum.java](01-hashing/0001_TwoSum.java) | 2026-11-19 |
| 49 | [Group Anagrams](https://leetcode.com/problems/group-anagrams/) | Hashing | Medium | [0049_GroupAnagrams.java](01-hashing/0049_GroupAnagrams.java) | 2026-11-19 |
| 128 | [Longest Consecutive Sequence](https://leetcode.com/problems/longest-consecutive-sequence/) | Hashing | Medium | [0128_LongestConsecutiveSequence.java](01-hashing/0128_LongestConsecutiveSequence.java) | 2026-11-19 |
| 238 | [Product of Array Except Self](https://leetcode.com/problems/product-of-array-except-self/) | Prefix Sum | Medium | [0238_ProductOfArrayExceptSelf.java](02-prefix-sum/0238_ProductOfArrayExceptSelf.java) | 2026-11-20 |
| 347 | [Top K Frequent Elements](https://leetcode.com/problems/top-k-frequent-elements/) | Heap | Medium | [0347_TopKFrequentElements.java](03-heap/0347_TopKFrequentElements.java) | 2026-11-19 |
| 443 | [String Compression](https://leetcode.com/problems/string-compression/) | Two Pointers | Medium | [0443_StringCompression.java](04-two-pointers/0443_StringCompression.java) | 2026-11-24 |
| 560 | [Subarray Sum Equals K](https://leetcode.com/problems/subarray-sum-equals-k/) | Prefix Sum | Medium | [0560_SubarraySumEqualsK.java](02-prefix-sum/0560_SubarraySumEqualsK.java) | 2026-11-22 |
| 724 | [Find Pivot Index](https://leetcode.com/problems/find-pivot-index/) | Prefix Sum | Easy | [0724_FindPivotIndex.java](02-prefix-sum/0724_FindPivotIndex.java) | 2026-11-19 |

**Total: 8** (Easy 2 · Medium 6 · Hard 0)

## Patterns learned

| Folder | Pattern | Trigger → trick |
|--------|---------|-----------------|
| [01-hashing](01-hashing) | Hashing | "Have I seen X before?" / grouping / O(1) lookup → HashMap or HashSet. Pair sum → look for the complement; anagrams → sorted word as key; consecutive run → start only where `x-1` is missing. |
| [02-prefix-sum](02-prefix-sum) | Prefix Sum | "Sum of a left part vs right part / a range" → keep a running sum; `right = total - left - nums[i]`. "Everything except me" → prefix and suffix running products (start at 1, use the running value before including the current element, no division). "Count subarrays with sum = k" (negatives allowed) → prefix sum + HashMap of prefix counts seeded `{0: 1}`, look up `sum - k` before storing `sum` (Two Sum on prefix sums). |
| [03-heap](03-heap) | Heap | "Top / smallest / most frequent k" → min-heap of size k (pop when size > k), or bucket sort for O(n). |
| [04-two-pointers](04-two-pointers) | Two Pointers | "Modify / compress in place, O(1) space" → read pointer walks each consecutive group, write pointer `w` writes the result behind it (safe because the output never grows past the input). |

## Run it

Needs JDK 11 or newer. Run any single file directly:

```bash
java 01-hashing/0001_TwoSum.java
```

Compile everything at once (bash):

```bash
javac -d out $(find . -name "*.java")
```
