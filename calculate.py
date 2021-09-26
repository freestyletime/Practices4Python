def zero(s=None):
    return s(0) if s else 0
    
def one(s=None):
    return s(1) if s else 1
        
def two(s=None):
    return s(2) if s else 2

def three(s=None):
    return s(3) if s else 3

def four(s=None):
    return s(4) if s else 4

def five(s=None):
    return s(5) if s else 5

def six(s=None):
    return s(6) if s else 6

def seven(s=None):
    return s(7) if s else 7

def eight(s=None):
    return s(8) if s else 8

def nine(s=None):
    return s(9) if s else 9

def plus(s):
    def _plus(n):
        return n + s
    return _plus

def minus(s):
    def _minus(n):
        return n - s
    return _minus

def times(s):
    def _times(n):
        return n * s
    return _times

def divided_by(s):
    def _divided_by(n):
        return n // s
    return _divided_by


print(seven(times(five())))
print(four(plus(nine())))
print(eight(minus(three())))
print(six(divided_by(two())))