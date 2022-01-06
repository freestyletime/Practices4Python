from libnum import *

# 以下为rsa加密，signature需要调换公钥与私钥的位置

p = 137
q = 131
n = p * q
e = 3 # public key (n , e)
d = 11787 #private key (n, d)
m = 513

# # #  加密 # # # 

# # # directly # # #
# encrypt dir
c = pow(m, e, n)
print("encrypt", c)
# decrypt
m = pow(c, d, n)
print("decrypt", m)


# # # undirectly # # #
# dP = e-1 mod (p-1) = d mod (p-1) = 11787 mod 136 = 91
# dQ = e-1 mod (q-1) = d mod (q-1) = 11787 mod 130 = 87
dP = d % (p - 1)
dQ = d %(q - 1)
# qInv = q-1 mod p = 131-1 mod 137 = 114
# m1 = c^dP mod p = 836391 mod 137 = 102
# m2 = c^dQ mod q = 836387 mod 131 = 120
qInv = invmod(q, p)
m1 = pow(c, dP, p)
m2 = pow(c, dQ, q)
# h = qInv.(m1 - m2) mod p = 114.(102-120+137) mod 137 = 3
h = qInv * (m1 - m2) % p
m = m2 + h * q
print(m)

print("# # # # #")
# # # 签名 # # # 
# # # directly # # #
c = pow(m, d, n)
print("encrypt", c)
# # # 验证 # # #
m = pow(c, e, n)
print("decrypt", m)

# # # undirectly # # #
# dP = e-1 mod (p-1) = d mod (p-1) = 11787 mod 136 = 91
# dQ = e-1 mod (q-1) = d mod (q-1) = 11787 mod 130 = 87
sP = d % (p - 1)
sQ = d % (q - 1)
# qInv = q-1 mod p = 131-1 mod 137 = 114
# m1 = m^dP mod p = 836391 mod 137 = 102
# m2 = m^dQ mod q = 836387 mod 131 = 120

qInv = invmod(q, p)
m1 = pow(m, sP, p)
m2 = pow(m, sQ, q)
# h = qInv.(m1 - m2) mod p = 114.(102-120+137) mod 137 = 3
h = qInv * (m1 - m2) % p
c = m2 + h * q
print(c)


p = 10007
q = 37
n = p * q
m = 11
sP = 8102
sQ = 27
d = 0 # private key

T = invmod(q, p)
U = T * (sP - sQ)  % p
c = sQ + U * q

# get private key
for x in range(100000):
    if c == pow(m, x, n):
        d = x
        break;
print("private key",d)
print("encrypt", c)
# # # 验证 # # #
e = 265097
n = 370259
m = pow(c, e, n)
print("decrypt", m)