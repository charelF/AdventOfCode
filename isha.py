if __name__ == '__main__':
    lines = []
    with open('day06t.txt') as file:
        # numbers = [list(line.strip()) for line in file.readlines()]
        lines = file.read().splitlines()

    # print(numbers)
    nums = [line.split() for line in lines[:-1]]
    operations = lines[-1].split()

    # print(nums)
    # print(operations)
    answers = []
    for c in range(len(nums[0])):
        numbers = [int(row[c]) for row in nums]
        op = operations[c]

        res = numbers[0]
        for n in numbers[1:]:
            res = res * n if op =='*' else res+n

        answers.append(res)

    total = sum(answers)

    # print(total)


    # part 2 

    with open('day06.txt') as file:
        numbers = [line.split("\n")[0] for line in file.readlines()]

    # print(numbers)

    operations = []
    temp = []
    nums = []

    for r1, r2, r3, r4, r5 in zip(numbers[0], numbers[1], numbers[2], numbers[3], numbers[4]):
        if r1 == r2 == r3 == r4 == r5 == ' ':
            nums.append(temp)
            temp = []
            continue 

        if r5 == '*' or r5 == '+':
            operations.append(r5)
            temp.append(r1+r2+r3+r4)
        else:
            temp.append(r1+r2+r3+r4)
    
    nums.append(temp)

    print(nums)
    print(operations)

    total = 0
    for n, op in zip(nums, operations):
        values = [int(val.strip()) for val in n]

        print(values)

        if op == '+':
            total += sum(values)

        elif op == '*':
            product = 1
            for v in values:
                product *= v
            total += product

    print(total)

            






