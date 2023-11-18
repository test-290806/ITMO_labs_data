from task0 import JSON2XML0
from task1 import JSON2XML1
from task2 import JSON2XML2
from task3 import JSON2XML3
from time import time

def getTime(inputFile, outputFile, f):
    startTime = time()
    for i in range(100):
        f(inputFile, outputFile)
    endTime = time()
    return endTime - startTime

def main():
    funcs = [JSON2XML0, JSON2XML1, JSON2XML2, JSON2XML3]
    for i in range(len(funcs)):
        fileName = f'day{i}.xml'
        print(f'Время стократного выполнения для task{i}: {round(getTime("day.json", fileName, funcs[i]), 9)} сек')

if __name__ == '__main__':
    main()