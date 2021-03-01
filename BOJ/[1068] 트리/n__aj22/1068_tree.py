tree_dic = {}
root =-1
def delete(node):
    queue = []
    queue.append(node)
    while(queue):
        now_node = queue.pop(0)
        for i in tree_dic.keys():
            if(now_node in tree_dic[i]):
                tree_dic[i].remove(now_node)
        if(now_node in tree_dic.keys()):
            append_node = tree_dic[now_node]
            for n in append_node:
                queue.append(n)
            del tree_dic[now_node]

def init():
    for i in range(N):
        tree_dic[i] = []
    return
if __name__ == "__main__":
    N = int(input())
    tree = list(map(int, input().split()))
    delete_node = int(input())
    init()
    for i in range(N):
        if(tree[i]==-1):
            root = i
        else:
            tree_dic[tree[i]].append(i)
    delete(delete_node)
    if(delete_node == root):
        print(0)
    else:
        delete(delete_node)
        count = 0
        for i in tree_dic.keys():
            if(tree_dic[i] == []):
                count+=1
        print(count)