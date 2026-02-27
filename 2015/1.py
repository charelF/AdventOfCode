lines = open("inputs/1").read()

floor = 0
for i, p in enumerate(lines):
    if floor == -1:
        print(f"part 2: {i}")
    if p == '(':
        floor += 1
    elif p == ")":
        floor -= 1
print(f"part 1: {floor}")

