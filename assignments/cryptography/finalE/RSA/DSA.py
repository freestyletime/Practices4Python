from libnum import *

# # # 签名 # # # 
# INPUT: Domain parameters (p,q,g); signer's private key a; message-to-be-signed, M; 
# a secure hash function Hash() with output of length |q|.

# Domain parameters (p=283,q=47,g=60)
p=283
q=47
g=60
a=24 # private key
# Message M with message digest h=Hash(M)
h = 41
# Choose a random k in the range [1,q−1].
k=15
# computes  X=g^k mod p
#           r=X mod q
X = pow(g, k, p)
r = X % q
# r≠0 so continue
# s = k^(−1) (h+ar) mod q
# computes k^(−1) mod q
s = ((h + a*r) * invmod(k, q)) % q
# s≠0 so continue
# Signature (r,s)
print("(r,s)",r,s)

# # # 验证 # # #
# Domain parameters (p=283,q=47,g=60)
p=283
q=47
g=60
A=pow(g,a,p) # public key
# Message M with message digest h=Hash(M)
h = 41
#  Signature (r,s)=(19,30).
r = 19
s = 30

# verifies that 0<r=19<47 and 0<s=30<47 ⇒ OK, so continue
# computes  w = s^(−1) mod q
w = invmod(s, q)
# computes u1 = hw mod q, u2 = rw mod q
u1 = h * w % q
u2 = r * w % q
#  computes X= g^u1 A^u2 mod p, v = X mod q
X = ((g ** u1) * (A ** u2)) % p
v = X % q
# checks that v=19=r, so he accepts the signature.
print("(v, r)", v, r)

# # practice # #
q=13
p=53 
g=16 
h=5
k=2
r=5
s=10

x=3
y = pow(g,x,p)

w = invmod(s, q)
a = h * w % q
b = r * w % q
X = ((g ** a) * (y ** b)) % p
v = X % q
print("(v, r)", v, r)
