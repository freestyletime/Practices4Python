
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