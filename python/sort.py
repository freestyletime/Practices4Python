# Calculate an algorithm's time complexity:
# Using master theorem:
# T(N) = a*T(N/b) + O(N^d)
# Detail reference: https://zh.wikipedia.org/wiki/%E4%B8%BB%E5%AE%9A%E7%90%86

import math

# variables value switch
def swap(a, b):
    a = a ^ b
    b = a ^ b
    a = a ^ b
    return a, b


def fastSort(list):
    for i in range(0, len(list) - 1):
        for j in range(i + 1, len(list)):
            if list[j] < list[i]:
                list[i], list[j] = swap(list[i], list[j])
    return list


def bubbleSort(list):
    for i in range(len(list), 1, -1):
        for j in range(1, i):
            if list[j - 1] > list[j]:
                list[j - 1], list[j] = swap(list[j - 1], list[j])
    return list


def insertSort(list):
    for i in range(1, len(list)):
        for j in range(i, 0, -1):
            if list[j] < list[j-1]:
                list[j], list[j-1] = swap(list[j], list[j-1])
            else:
                break
    return list


def mergeSort(list):
    if(len(list) < 2):
        return list
    middle = math.floor(len(list) / 2)
    left, right = list[0:middle], list[middle:]
    return merge(mergeSort(left), mergeSort(right))

def merge(left, right):
    result = []
    while left and right:
        if left[0] < right[0]:
            result.append(left.pop(0))
        else:
            result.append(right.pop(0))
    
    while left:
        result.append(left.pop(0))
    while right:
        result.append(right.pop(0))
    return result

def shellSort(arr):
    gap = 1
    while(gap < len(arr)/3):
        gap = gap*3+1
    while gap > 0:
        for i in range(gap, len(arr)):
            temp = arr[i]
            j = i - gap
            while j >= 0 and arr[j] > temp:
                arr[j + gap] = arr[j]
                j -= gap
            arr[j + gap] = temp
        gap = math.floor(gap/3)
    return arr

print(shellSort([9,5,3,3,6,1,7,22,53,4,7,9,8,2]))