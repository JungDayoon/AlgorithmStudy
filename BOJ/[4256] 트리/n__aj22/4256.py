preorder = []
inorder = []

def find_postorder(pre_s, pre_e, in_s, in_e):
    if(pre_s>pre_e):
        return
    root = preorder[pre_s]

    left_length = 0
    for i in range(in_s, in_e):
        if(inorder[i] == root):
            left_length = i-in_s
            break
    find_postorder(pre_s+1, pre_s+left_length, in_s, in_s+left_length-1)
    find_postorder(pre_s+left_length+1, pre_e, in_s+left_length+1, in_e)

    print(root, end=" ")
    return
        
if __name__ == "__main__":
    T = int(input())
    for t in range(T):
        n = int(input())
        preorder = list(map(int, input().split()))
        inorder = list(map(int, input().split()))
        find_postorder(0, n-1, 0, n-1)
        print()
