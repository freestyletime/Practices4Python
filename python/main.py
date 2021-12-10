
#from string import capwords as to_jaden_case
def to_jaden_case(string):
    words = string.split()
    result = ""
    for word in words:
        result = result + word.capitalize() + " "

    return result.rstrip()


def descending_order(num):
    result = []
    for n in str(num):
        result.append(n)
    result.sort(reverse=True)
    return int("".join(x for x in result))


def unique_in_order(iterable):
    pre = ""
    result = []
    for x in iterable:
        if x!= pre:
            result.append(x)
            pre = x       
    return result


def dig_pow(n, p):
    nums = []
    for x in str(n):
        nums.append(pow(int(x), p))
        p += 1
    result = sum(nums)
    return -1 if result % n else int(result / n)

def nb_year(p0, percent, aug, p, years = 0):
    if p0 < p:
        return nb_year(p0 + int(p0 * percent / 100) + aug, percent, aug, p, years + 1)
    return years


#simpler function
def validBraces(s):
  while '{}' in s or '()' in s or '[]' in s:
      s=s.replace('{}','')
      s=s.replace('[]','')
      s=s.replace('()','')
  return s==''

# def validBraces(s):
#     pairs = ['{}', '()', '[]']
#     for pair in pairs:
#         for pair in s:
#             s = s.replace(pair, "")
#     return s == ""

# traditional function
# def validBraces(data):
#     collect = {'(' : ')', '[' : ']', '{' : '}'}
#     stack = []
#     for s in data:
#         if s in collect:
#             stack.append(s)
#         else:
#             if len(stack) == 0 and collect[stack.pop()]!= s:
#                 return False
#     return len(stack) == 0


def alphabet_position(text):
    result = []
    for s in text.lower():
        if s.isalpha():
            r = ord(s) - 96
            result.append(str(r))
    return " ".join(result)


def alphabet_position2(text):
    return " ".join(str(ord(s) - 96) for s in text.lower() if s.isalpha())


def domain_name(url):
    if url.find("//") != -1:
         url = url.split("//")[1]
    for s in url.split("."):
        if s != "www":
            return s


def domain_name2(url):
    return url.split("//")[-1].split("www.")[-1].split(".")[0]
    

import re
def solution(text):
    result = []
    for s in re.split('(..)', text):
        if s.isalpha():
            if len(s) == 1:
                result.append(s + "_")
            else:
                result.append(s)
    return result


def solution2(s):
    return re.findall(".{2}", s + "_")

def cakes(recipe, available):
    num = 0
    for k, v in recipe.items():
        if k in available:
            tmp = available[k] // v
            if num == 0 or tmp < num:
                num = tmp
        else:
            return num
    return num

def cakes2(recipe, available):
    return min(available.get(k, 0)// v for k, v in recipe.items())


def count_bits(n):
    return bin(n).count("1")

def same_structure_as(original,other):
    if type(original) == type(other) and len(original) == len(other):
        for i, v in enumerate(original):
            if type([]) == type(v) and type([]) == type(other[i]):
                return same_structure_as(v, other[i])
            elif type([]) == type(v) or type([]) == type(other[i]):
                return False
        return True
    else:
        return False

def same_structure_as2(original, other):
    if type(original) == list == type(other):
        return len(original) == len(other) and all(map(same_structure_as, original, other))
    else:
        return type(original) != list != type(other)

def format_duration(seconds):
    result = ""
    y = 0
    d = 0
    h = 0
    m = 0
    s = 0

    m, s = divmod(seconds, 60)
    if m > 59:
        h, m = divmod(m, 60)
        if h > 23:
            d, h = divmod(h, 24)
            if d > 364:
                y, d = divmod(d, 365)

    if y > 0:
        result += "%d years" % y if y > 1 else "%d year" % y
    
    if d > 0:
        if len(result) > 0:
            result += ", %d days" % d if d > 1 else ", %d day" % d
        else:
            result += "%d days" % d if d > 1 else "%d day" % d
    
    if h > 0:
        if len(result) > 0:
            result += ", %d hours" % h if h > 1 else ", %d hour" % h
        else:
            result += "%d hours" % h if h > 1 else "%d hour" % h

    if m > 0:
        if len(result) > 0:
            result += ", %d minutes" % m if m > 1 else ", %d minute" % m
        else:
            result += "%d minutes" % m if m > 1 else "%d minute" % m

    if s > 0:
        if len(result) > 0:
            result += ", %d seconds" % s if s > 1 else ", %d second" % s
        else:
            result += "%d seconds" % s if s > 1 else "%d second" % s

    if result.count(",") != 0:
        n = result.rsplit(',', 1)
        n.insert(1, ' and')
        return "".join(n)
    return result

import math
def isPP(n):
    x = math.sqrt(n)
    if x % 1 == 0:
        return [int(x), 2]
    else:
        o = math.ceil(x) + 1
        if o < 3: return None
        for i in range(2, o):
            for t in range(3, o):
                b = math.pow(i, t)
                if b == n:
                    return [i, t]
                elif b > n:
                    break
        else:
            return None

def palindromes(text):
    if text == None or len(text) == 0:
        return False
    if len(text) == 2:
        return True if text[0] == text[1] else False
    
    max = len(text) // 2
    return True if text[:max] == text[max + 1:][::-1] else False

def twoSum(nums, target):
    length = len(nums)
    max = length * (length - 1) // 2
    x = 0
    y = 1
    for i in range(0, max):
        if nums[x] + nums[y] == target:
            return [x, y]
        else:
            if y < length - 1:
                y += 1
            else:
                x += 1
                y = x + 1

    return [-1, -1]


def filterX(string, markers):
    result = ""
    for line in string.split("\n"):
        for marker in markers:
            if marker in line:
                line = line.split(marker)[0].strip()
        result += line + "\n"
    return result[:-1]


def snail(snail_map):
    result = []
    l = len(snail_map)
    if l == 0 or len(snail_map[0]) == 0: return result
    # total step length
    s = 2*l - 1
    # every step length
    rules = [l]
    # direction 0->right/1->down/2->left/3->up
    d = 0 # init state 0
    while l > 1:
        l -= 1
        rules.append(l)
        rules.append(l)
    
    # x and y are current indexs for arrys
    x = 0
    y = -1

    for i in range(s):
        for i in range(rules[i]):
            if d % 4 == 0:
                y += 1
                result.append(snail_map[x][y])
            elif d % 4 == 1:
                x += 1
                result.append(snail_map[x][y])
            elif d % 4 == 2:
                y -= 1
                result.append(snail_map[x][y])
            elif d % 4 == 3:
                x -= 1
                result.append(snail_map[x][y])
        d += 1

    return result

def snail1(array):
    a = []
    while array:
        a.extend(list(array.pop(0)))
        array = zip(*array)
        array = list(array)
        array.reverse()
    return a

def solution(number):
    def solution2(x,y):
        result = 0
        init = x
        while x < y:
            result += x
            x += init
        return result
    return solution2(3, number) + solution2(5, number) - solution2(15, number) 

def valid_solution(board):
    #  column to row
    def r2c(matrix):
        matrix = zip(*matrix)
        return list(matrix)
   
    # get sub-grid
    # matrix - 9*9 | n - number(1-9)
    def m3x3(matrix, n):
        r, x , y = [], 0, 6 if n%3 == 0 else 3*(n%3 - 1)
        
        if n < 4: x = 0
        elif n < 7: x = 3
        else: x = 6

        for i1, row in enumerate(matrix):
            if i1 >= x and i1 < x + 3:
                r.extend(row[y:y+3])
            elif i1 == x + 3:
                break
        return r
    
    def check_list(row):
        if len(row) == 9:
            if sum(row) == 45:
                for e in row:
                    if e < 1 or e > 9: return False
                    if row.count(e) != 1: return False
                return True
        return False

    # check rows
    def check(matrix):
        for row in matrix:
            if not check_list(row): return False
        return True;

    # check sub-grid
    def check_3x3(matrix):
        for i in range(1, 10):
            if not check_list(m3x3(matrix, i)): return False
        return True
    
    return check_3x3(board) and check(board) and check(r2c(board))

if __name__ == '__main__':
    print(to_jaden_case("Hello world, my mother is beautiful woman absolutly!"))
    print(descending_order(4928471))
    print(unique_in_order(["A","A","B","B","C","c","a","a","A","A","B","B"]))
    print(dig_pow(115,1))
    print(nb_year(1500000, 0.25, 1000, 2000000))
    print(validBraces("((((()))))"))
    print(ord('z'))
    print(alphabet_position("The sunset sets at twelve o' clock."))
    print(alphabet_position2("The sunset sets at twelve o' clock."))
    print(domain_name("www.xakep.ru"))
    print(domain_name2("https://youtube.com"))
    print(solution2('abcdef'))

    recipe = {"flour": 500, "sugar": 200, "eggs": 1}
    available = {"flour": 1200, "sugar": 1200, "eggs": 5, "milk": 200}
    print(cakes2(recipe, available))
    print(count_bits(7))
    print(same_structure_as([1,'[',']'],['[',']',1]))
    print(same_structure_as2([1,'[',']'],['[',']',1]))
    print(format_duration(3662))
    print(isPP(8))

    print(palindromes("aounbbbnuoa"))
    print(palindromes("dad"))
    print(palindromes("Dad"))

    print(twoSum([2, 7, 11, 15], 9))
    print(twoSum([2, 7, 11, 15], 17))
    print(twoSum([2, 7, 11, 15], 22))
    print(twoSum([2, 7, 11, 15], 26))
    print(twoSum([2, 7, 11, 15], 100))

    print(filterX("apples, pears # and bananas\ngrapes\nbananas !apples", ["#", "!"]))
    print(filterX("a #b\nc\nd $e f g", ["#", "$"]))


    array = [[1,2,3],
             [4,5,6],
             [7,8,9]]
    expected = [1,2,3,6,9,8,7,4,5]
    print(snail(array))
    array1 = [[1,2,3,15],
              [8,9,4,11],
              [7,6,5,24],
              [7,6,5,10]]
    print(snail1(array1))

    print(solution(10))
    print(solution(20))
    print(solution(200))

    print(valid_solution([[5, 3, 4, 6, 7, 8, 9, 1, 2], 
                          [6, 7, 2, 1, 9, 5, 3, 4, 8],
                          [1, 9, 8, 3, 4, 2, 5, 6, 7],
                          [8, 5, 9, 7, 6, 1, 4, 2, 3],
                          [4, 2, 6, 8, 5, 3, 7, 9, 1],
                          [7, 1, 3, 9, 2, 4, 8, 5, 6],
                          [9, 6, 1, 5, 3, 7, 2, 8, 4],
                          [2, 8, 7, 4, 1, 9, 6, 3, 5],
                          [3, 4, 5, 2, 8, 6, 1, 7, 9]]));

    print(valid_solution([[5, 3, 4, 6, 7, 8, 9, 1, 2], 
                          [6, 7, 2, 1, 9, 0, 3, 4, 9],
                          [1, 0, 0, 3, 4, 2, 5, 6, 0],
                          [8, 5, 9, 7, 6, 1, 0, 2, 0],
                          [4, 2, 6, 8, 5, 3, 7, 9, 1],
                          [7, 1, 3, 9, 2, 4, 8, 5, 6],
                          [9, 0, 1, 5, 3, 7, 2, 1, 4],
                          [2, 8, 7, 4, 1, 9, 6, 3, 5],
                          [3, 0, 0, 4, 8, 1, 1, 7, 9]]))
