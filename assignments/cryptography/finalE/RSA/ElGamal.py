from libnum import *


def gcd(a,b):
    if(b==0):
        return a
    else:
        return gcd(b,a%b)

# # # #  加密 # # # 

# # encryption
# # Domain parameters (p,q,g)
# p=283
# q=47
# g=60
# b=7 # private key
# B=pow(g,b,p) # public key
# # encoded message, such that 0<m<p−1.
# m=101
# # 1. Alice chooses a random k in the range [2,q−2]
# k=36
# # 2. Alice computes  c1=g^k mod p
# c1 = pow(g, k, p)
# # 3. Alice computes  c2=m(B^k)mod p
# c2 = (m * (B**k)) % p
# # Alice sends ciphertext  (c1,c2)
# print("(c1, c2)", c1, c2)

# # decryption
# # Domain parameters (p,q,g)
# # Bob's private key;
# p=283
# q=47
# g=60
# b=7 # private key
# # ciphertext (c1,c2)=(78,218).
# c1 = 78
# c2 = 218
# # Bob computes m=c1^(p−b−1) c2 mod p
# m = ((c1 ** (p-b-1)) * c2) % p
# print("m",m)


# # # # 签名 # # # 

# # public key (p,g,y)
# p=71
# g=7
# x=16 # private key
# h=15 # plain txt
# # y = g ^ x mod p
# y = pow(g, x, p) # public key
# # gcd(k, p) = 1
# k = 31
# # a = g ^ k mod p
# a = pow(g, k, p)
# # b = (h - xa) (k^(-1)) mod (p - 1)
# b = ((h - x * a) * invmod(k, p -1)) % (p - 1)
# print("(a, b)", a, b)


# # # # 验证 # # #

# # g^h mod p = y^a a^b mod p
# v1 = pow(g, h, p)
# v2 = ((y ** a) * (a **b)) % p
# print("(v1, v2)", v1, v2)


p = 1108763003735791619954956188174516481632915747603958198829971
g = 1003881344097987265669023808507
y = 691211563106287456956354712023253451930598224329972661947170

message_1 = 421686623346586045529058689921305287957984053787253675952665
Signature_1_a = 735644768715909718055934343063024653554989850751893195334523
Signature_1_b = 350783756442592559333703783406939589064258481023889291509618

message_2 = 556148106667725277658269384346017345759447276081963923247023
Signature_2_a = 700049814131283104218397415101154280285755123299867242111073
Signature_2_b = 275605393876829529388164177284851167406737581770793647688221

for i in range(p):
    if(Signature_1_a == pow(g, i, p)):
        k = i
        print("(k)", k)
        print("gcd k", gcd(k, p-1))
        break

t = invmod(k, p -1)
r = t * Signature_2_b

# (m1 − k*s1)r^−1 mod (p−1)


 


# Signature_1_b = ((message_1 - x * Signature_1_a) * invmod(k, p -1)) % (p - 1)
# Signature_2_b = ((message_2 - x * Signature_2_a) * invmod(k, p -1)) % (p - 1)

# for i in range(p):
#      if(Signature_1_b == ((message_1 - i * Signature_1_a) * k1) % (p - 1)):
#         x = i
#         print("(x)", x)

# for i in range(p):
#     if(Signature_2_a == pow(g, i, p)):
#         k = i
#         print("(k)", k)
#         break

# for i in range(p):
#      if(Signature_2_b == ((message_2 - i * Signature_2_a) * k1) % (p - 1)):
#         x = i
#         print("(x)", x)

# for i in range(i, p):
#     n = pow(g, i, p)
#     print(i)
#     if(y == n):
#         x = i
#         print("(x)", x)
#         break