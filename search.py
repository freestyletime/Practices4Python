# Calculate an algorithm's time complexity:
# Using master theorem:
# T(N) = a*T(N/b) + O(N^d)
# Detail reference: https://zh.wikipedia.org/wiki/%E4%B8%BB%E5%AE%9A%E7%90%86


def getMax(data): 
    return binarySearch(data, 0, len(data) - 1);


def binarySearch(data, l, r):
    if l == r:
        return data[l]
    mid = l + ((r-l) >> 1)
    leftMax = binarySearch(data, l, mid)
    rightMax = binarySearch(data, mid + 1, r)
    return  leftMax if leftMax > rightMax else rightMax


print(getMax([5,9,1,4,6,3,10]))