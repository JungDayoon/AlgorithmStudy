quad_tree =[]

def zip_quad(start_x, start_y, width):
    new_list = []

    for i in range(start_x,start_x+width):
        for j in range(start_y, start_y+width):
            if quad_tree[j][i] not in new_list:
                new_list.append(quad_tree[j][i])
    if(len(new_list)==1):
        return str(new_list[0])
    half_width = int(width/2)
    return "("+zip_quad(start_x, start_y, half_width)+zip_quad(start_x+half_width, start_y, half_width)+zip_quad(start_x, start_y+half_width, half_width)+zip_quad(start_x+half_width, start_y+half_width, half_width)+")"

a = int(input())

for i in range(0, a):
    new_quad_tree = list(map(int,input()))
    quad_tree.append(new_quad_tree)
#print(quad_tree)

print(zip_quad(0,0,a))
    