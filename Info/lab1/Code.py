n = int(input())

s = ''
while abs(n) > 0:
    r = n % (-10)
    m = n // (-10)

    if r < 0:
        r += 10
        m += 1

    s += str(r)
    n = m

print(s[::-1])