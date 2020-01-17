with open("ship", "r") as f:
    content = f.read()
    content = content.split(",")
    scaleFactor = 3
    x = "int[] xPoints = {"
    y = "int[] yPoints = {"
    i = 0
    minX = 100000000
    minY = 100000000
    maxX = 0
    maxY = 0

    for c in content:
        if i % 2 == 0:
            if int(c) // scaleFactor > maxX:
                maxX = int(c) // scaleFactor
            if int(c) // scaleFactor < minX:
                minX = int(c) // scaleFactor
        else:
            if int(c) // scaleFactor > maxY:
                maxY = int(c) // scaleFactor
            if int(c) // scaleFactor < minY:
                minY = int(c) // scaleFactor
        i += 1

    midX = (minX + maxX) // 2
    midY = (minY + maxY) // 2

    for c in content:
        if i % 2 == 0:
            x += str(int(c) // scaleFactor - midX)
            x += ", "
        else:
            y += str(int(c) // scaleFactor - midY)
            y += ", "
        i += 1
    
    x = x[0:-2] + "};"
    y = y[0:-2] + "};"
    print(x)
    print(y)
    print("x: ", minX, maxX)
    print("y: ", minY, maxY)
