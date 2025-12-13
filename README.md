# Advent of Code

## Favourites

### 2025-D2-P2 golfed

```kotlin
private fun p2v2(input: String) = input
    .split(',')
    .map { range ->
        range.split('-').map { it.toLong() }.let { (start, end) -> (start..end).asSequence() }
    }
    .flatMap { seq ->
        seq.filter { element ->
            element.toString().let { code ->
                (1..code.length / 2).any { groupLength ->
                    (code.chunked(groupLength).toSet().size == 1)
                }
            }
        }
    }
    .distinct()
    .sum()
    .also { println("part 2: $it") }
```

### 2025-D11 matrixed

```python
import toposort
import numpy as np

d = {"out": set()}
for line in open("11").read().split("\n"):
    a, b = line.split(": ")
    c = b.split(" ")
    d[a] = set(c)

# need to toposort because adjacency matrix needs to be upper triangular, and it only is if our indices are topologically sorted
indices = toposort.toposort_flatten(d, sort=True)
you = indices.index("you")
out = indices.index("out")
svr = indices.index("svr")
dac = indices.index("dac")
fft = indices.index("fft")
n = len(indices)

# build A (adjacency matrix)
A =  np.zeros(dtype=np.int64, shape=(n,n))
for (k,vs) in d.items():
    i = indices.index(k)
    for v in vs:
        j = indices.index(v)
        A[i,j] = 1

I = np.identity(n)

T = np.linalg.inv(I - A).round().astype(int)  # T = (I - A)^-1 

print("part 1: ",T[you, out])
print("part 2: ", 
      (T[svr, fft] * T[fft, dac] * T[dac, out]) + # visiting fft before dac
      (T[svr, dac] * T[dac, fft] * T[fft, out]) # visiting dac before ftt
)
```