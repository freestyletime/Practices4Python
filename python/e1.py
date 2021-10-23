 # Chris Chen 
 # christianchen@gmail.com 
 # Created on Sun Oct 17 2021
 # ^V^ Enjoy every day.

# For information on knight moves, see https://en.wikipedia.org/wiki/Knight_%28chess%29
# For information on algebraic notation, see https://en.wikipedia.org/wiki/Algebraic_notation_%28chess%29
def knight(p1, p2):
    direction = ((1, 2), (1, -2), (-1, 2), (-1, -2), (2, 1), (2, -1), (-2, 1), (-2, -1))
    boarder = [1,2,3,4,5,6,7,8]
    
    def caculation(x, y, p):
        for m in direction:
            tx = x + m[0]
            ty = y + m[1]
            if tx in boarder and ty in boarder and [tx, ty] not in p:
                p.append([tx, ty])

    sx, sy = ord(p1[0]) - 96, int(p1[1])
    ex, ey = ord(p2[0]) - 96, int(p2[1])
    # step / possibilities
    step, p = 0, [[sx, sy]]

    # caculation
    if (ex - sx) == 0 and (ey - sy) == 0: return step
    while True:
        step += 1
        s = len(p)
        while s > 0:
            s -= 1
            f = p.pop(0)
            caculation(f[0], f[1], p)
        if [ex, ey] in p: return step


def gcd(a,b):
        while a!=0:
            a,b = b%a,a
        return b

def findModReverse(a,m): #这个扩展欧几里得算法求模逆

        if gcd(a,m)!=1:
            return None
        u1,u2,u3 = 1,0,a
        v1,v2,v3 = 0,1,m
        while v3!=0:
            q = u3//v3
            v1,v2,v3,u1,u2,u3 = (u1-q*v1),(u2-q*v2),(u3-q*v3),v1,v2,v3
        return u1%m

##################################################################

print(knight('a1', 'c1'))
print(knight('a1', 'f4'))
print(knight('a1', 'h8'))

print(f'你所求的b值(模逆)为：{findModReverse(379, 676)}')
##################################################################