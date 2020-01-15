with open("dog", "r") as f:
    content = f.read()
    content = content.split(",")
    x = "int[] dogX = {"
    y = "int[] dogY = {"
    i = 0
    for c in content:
        if i % 2 == 0:
            x += str(int(c) // 4 + 700)
            x += ", "
        else:
            y += str(int(c) // 4 + 500)
            y += ", "
        i += 1
    
    x = x[0:-2] + "};"
    y = y[0:-2] + "};"
    print(x)
    print(y)
