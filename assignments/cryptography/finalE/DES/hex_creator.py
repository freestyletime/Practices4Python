# hex code cretor
start_code = "049104CD5701"

keys = []

def implement(text, result):
    for n in range(16):
        for m in range(16):
            for q in range(16):
                for o in range(16):
                    s = text + hex(n)[-1:].upper() + hex(m)[-1:].upper() + hex(q)[-1:].upper() + hex(o)[-1:].upper()
                    result.append(s)

implement(start_code, keys)
# save the keys
with open('./assignments/cryptography/keys.ser','w') as file:
    for key in keys:
        file.write(key + '\n');
    file.close();
